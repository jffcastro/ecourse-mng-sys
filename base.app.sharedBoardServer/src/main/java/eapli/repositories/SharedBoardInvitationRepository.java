package eapli.repositories;

import application.base.Application;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardInvitation;
import application.base.domain.sharedBoardManagement.SharedBoardStatus;
import application.base.domain.sharedBoardManagement.UserPermission;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class SharedBoardInvitationRepository extends JpaAutoTxRepository<SharedBoardInvitation, Long, Long> {

    public SharedBoardInvitationRepository(final TransactionalContext autoTx) {
        super(autoTx, "sharedBoardInvitationId");
    }

    public SharedBoardInvitationRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "sharedBoardInvitationId");
    }

    public Iterable<SharedBoard> findSharedBoardsAvailableForUser(SystemUser user) {
        final TypedQuery<SharedBoard> query = entityManager().createQuery(
                "SELECT sharedBoardInvitation.sharedBoard FROM SharedBoardInvitation sharedBoardInvitation " +
                        "WHERE sharedBoardInvitation.userInvite =: user " +
                        "AND sharedBoardInvitation.sharedBoard.status =: status " +
                        "AND sharedBoardInvitation.userPermission =: status2",
                SharedBoard.class);
        query.setParameter("user", user);
        query.setParameter("status", SharedBoardStatus.OPEN);
        query.setParameter("status2", UserPermission.WRITE);
        return query.getResultList();
    }

    public Iterable<SharedBoard> findSharedBoardsUserWasInvite(SystemUser user) {
        final TypedQuery<SharedBoard> query = entityManager().createQuery(
                "SELECT sharedBoardInvitation.sharedBoard FROM SharedBoardInvitation sharedBoardInvitation " +
                        "WHERE sharedBoardInvitation.userInvite =: user " +
                        "AND sharedBoardInvitation.sharedBoard.status =: status ",
                SharedBoard.class);
        query.setParameter("user", user);
        query.setParameter("status", SharedBoardStatus.OPEN);
        return query.getResultList();
    }

    public Iterable<SharedBoardInvitation> findSharedBoardInvitationsOfSharedBoard(SharedBoard theSharedBoard){
        final TypedQuery<SharedBoardInvitation> query = entityManager().createQuery(
                "SELECT sharedBoardInvitation FROM SharedBoardInvitation sharedBoardInvitation " +
                        "WHERE sharedBoardInvitation.sharedBoard =: theSharedBoard"
                , SharedBoardInvitation.class);
        query.setParameter("theSharedBoard", theSharedBoard);

        return query.getResultList();
    }
}
