package application.base.application.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.*;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.sharedBoardManagement.CellRepository;
import application.base.repositories.sharedBoardManagement.PostItRepository;
import application.base.repositories.sharedBoardManagement.SharedBoardInvitationRepository;
import application.base.repositories.sharedBoardManagement.SharedBoardRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class AddPostItController {
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private SharedBoardInvitationRepository sharedBoardInvitationRepository = PersistenceContext.repositories().sharedBoardInvitationRepository();

    private UserRepository userRepository = PersistenceContext.repositories().userRepository();

    private PostItRepository postItRepository = PersistenceContext.repositories().postItRepository();

    private CellRepository cellRepository = PersistenceContext.repositories().cellRepository();

    private SharedBoardRepository sharedBoardRepository = PersistenceContext.repositories().sharedBoardRepository();


    public Iterable<SharedBoard> findSharedBoard(SystemUser user){
        List<SharedBoard> userSharedBoards = (List<SharedBoard>) sharedBoardRepository.findSharedBoardOfUser(user);
        List<SharedBoard> sharedBoardsAvailableForUser = (List<SharedBoard>) sharedBoardInvitationRepository.findSharedBoardsAvailableForUser(user);
        for (SharedBoard sb : userSharedBoards) {
            sharedBoardsAvailableForUser.add(sb);
        }
        return sharedBoardsAvailableForUser;
    }

    public Iterable<Cell> findFreeCells(SharedBoard sharedBoard){
        List<Cell> allCells = (List<Cell>) cellRepository.findAllCellsFromSharedBoard(sharedBoard);
        List<Cell> cellsWithPostIts = (List<Cell>) postItRepository.findCellsWithPostItFromSharedBoard(sharedBoard);

        for (Cell c: cellsWithPostIts) {
            allCells.remove(c);
        }

        return allCells;
    }

    public PostIt createPostIt(String content, SystemUser user, Cell cell) {
        PostIt postIt;
        postIt = new PostItBuilder().withContent(content).withOwner(user).withCell(cell).build();
        postItRepository.save(postIt);
        return postIt;
    }

    /**
     * This method validates the content of the post it entered by the user who is adding it.
     * This must be at least 3 characters long and can only be a maximum of 50 characters long.
     * @param content
     * @return content validation
     */
    public boolean validateContent(String content){
        if(content.length() <= 1 || content.length() > 50){
            return false;
        }
        return true;
    }
}
