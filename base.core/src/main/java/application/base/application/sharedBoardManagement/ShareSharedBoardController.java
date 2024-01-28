package application.base.application.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.*;
import application.base.domain.sharedBoardManagement.builders.SharedBoardInvitationBuilder;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.sharedBoardManagement.SharedBoardInvitationRepository;
import application.base.repositories.sharedBoardManagement.SharedBoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class ShareSharedBoardController {

    private SharedBoardRepository sharedBoardRepository = PersistenceContext.repositories().sharedBoardRepository();

    private SharedBoardInvitationRepository sharedBoardInvitationRepository = PersistenceContext.repositories().sharedBoardInvitationRepository();

    private UserRepository userRepository = PersistenceContext.repositories().userRepository();


    @Transactional
    public Iterable<SharedBoard> listMySharedBoards(SystemUser user){
        return sharedBoardRepository.findSharedBoardOfUser(user);
    }

    public List<SystemUser> listSharedBoardInvitationsOfSharedBoard(SharedBoard sharedBoard){
        Iterable<SharedBoardInvitation> list = sharedBoardInvitationRepository.findSharedBoardInvitationsOfSharedBoard(sharedBoard);
        SystemUser user;
        List<SystemUser> usersAlreadyInvite = new ArrayList<>();
        for(SharedBoardInvitation sharedBoardInvitation: list) {
            user = sharedBoardInvitation.userInvite();
            usersAlreadyInvite.add(user);
        }
        return usersAlreadyInvite;
    }
    public Iterable<SystemUser> listAvailableUsers(Iterable<SystemUser> usersAlreadyInvite) {
        Iterable<SystemUser> allUsers = userRepository.findAll();
        return removeSystemUsersAlreadyInvited((List<SystemUser>) usersAlreadyInvite, (List<SystemUser>) allUsers);
    }

    @Transactional
    public void createSharedBoardInvitation(SharedBoard sharedBoard, UserPermission userPermission, SystemUser user){
        final SharedBoardInvitation sharedBoardInvitation = new SharedBoardInvitationBuilder().withSharedBoard(sharedBoard)
                .withUserPermission(userPermission)
                .withUser(user)
                .build();

        sharedBoardInvitationRepository.save(sharedBoardInvitation);

//        Thread shareSharedBoardThread = new Thread(new ShareSharedBoardThread(sharedBoard, user, userPermission));
//        shareSharedBoardThread.start();
    }

    @Transactional
    public SharedBoardInvitation findSharedBoardInvitation(SharedBoard sharedBoard, SystemUser user){
        return sharedBoardInvitationRepository.findSharedBoardInvitationOfUser(sharedBoard, user);
    }

    private Iterable<SystemUser> removeSystemUsersAlreadyInvited(List<SystemUser> usersAlreadyInvite,List<SystemUser> allUsers){
        List<SystemUser> usersAvailable = new ArrayList<>(allUsers);

        for (SystemUser users : usersAlreadyInvite) {
            usersAvailable.removeIf(systemUser -> systemUser.sameAs(users));
        }

        return usersAvailable;
    }
}
