package application.base.application.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.PostItHistory;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.sharedBoardManagement.PostItHistoryRepository;
import application.base.repositories.sharedBoardManagement.SharedBoardInvitationRepository;
import application.base.repositories.sharedBoardManagement.SharedBoardRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;

public class ViewBoardHistoryOfUpdatesController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private SharedBoardRepository sharedBoardRepository = PersistenceContext.repositories().sharedBoardRepository();

    private SharedBoardInvitationRepository sharedBoardInvitationRepository = PersistenceContext.repositories().sharedBoardInvitationRepository();

    private PostItHistoryRepository postItHistoryRepository = PersistenceContext.repositories().postItHistoryRepository();

    /**
     * This method accesses the SharedBoardInvitation repository and returns the shared boards
     * in which the user logged in the system has any type of permission.
     *
     * @return list of shared boards available for user
     */
    public Iterable<SharedBoard> findSharedBoardsAvailableForUser() {
        SystemUser user = authorizationService.session().get().authenticatedUser();
        List<SharedBoard> userSharedBoards = (List<SharedBoard>) sharedBoardRepository.findSharedBoardOfUser(user);
        List<SharedBoard> sharedBoardsAvailableForUser = (List<SharedBoard>) sharedBoardInvitationRepository.findSharedBoardsAvailableForUser(user);
        for (SharedBoard sb : userSharedBoards) {
            sharedBoardsAvailableForUser.add(sb);
        }
        return sharedBoardsAvailableForUser;
    }

    /**
     * This method accesses the SharedBoardUpdate repository and returns the list of updates related
     * to the shared board.
     *
     * @param sharedBoard - shared board to find updates of
     * @return list of updates of shared board
     */
    public Iterable<PostItHistory> findHistoryOfUpdatesOfSharedBoard(SharedBoard sharedBoard) {
        return postItHistoryRepository.findUpdatesOfSharedBoard(sharedBoard);
    }
}
