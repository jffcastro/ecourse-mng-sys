package application.base.repositories.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardInvitation;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.TypedQuery;

public interface SharedBoardInvitationRepository extends DomainRepository<Long, SharedBoardInvitation> {

    /**
     * This method returns the shared board invitation of the user in the shared board
     * It returns a unique value
     * @param sharedBoard
     * @param user
     */
    SharedBoardInvitation findSharedBoardInvitationOfUser(SharedBoard sharedBoard, SystemUser user);


    /**
     * This method returns all of tha shared board invitations of a specific shared board
     * @param sharedBoard
     */
    Iterable<SharedBoardInvitation> findSharedBoardInvitationsOfSharedBoard(SharedBoard sharedBoard);

    Iterable<SharedBoard> findSharedBoardsAvailableForUser(SystemUser user);
}
