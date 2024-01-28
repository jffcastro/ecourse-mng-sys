package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardInvitation;
import application.base.domain.sharedBoardManagement.SharedBoardStatus;
import application.base.domain.sharedBoardManagement.UserPermission;
import application.base.repositories.sharedBoardManagement.SharedBoardInvitationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaSharedBoardInvitationRepository extends JpaAutoTxRepository<SharedBoardInvitation, Long, Long>
        implements SharedBoardInvitationRepository {
    public JpaSharedBoardInvitationRepository(final TransactionalContext autoTx) {
        super(autoTx, "sharedBoardInvitationId");
    }

    public JpaSharedBoardInvitationRepository(final String persistenceUnitName){
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "sharedBoardInvitationId");
    }

    public SharedBoardInvitation findSharedBoardInvitationOfUser(SharedBoard theSharedBoard, SystemUser user){
        final TypedQuery<SharedBoardInvitation> query = entityManager().createQuery(
                "SELECT sharedBoardInvitation FROM SharedBoardInvitation sharedBoardInvitation " +
                        "WHERE sharedBoardInvitation.sharedBoard =: theSharedBoard AND sharedBoardInvitation.userInvite =: user"
                , SharedBoardInvitation.class);
        query.setParameter("theSharedBoard", theSharedBoard);
        query.setParameter("user", user);

        return query.getSingleResult();
    }

    public Iterable<SharedBoardInvitation> findSharedBoardInvitationsOfSharedBoard(SharedBoard theSharedBoard){
        final TypedQuery<SharedBoardInvitation> query = entityManager().createQuery(
                "SELECT sharedBoardInvitation FROM SharedBoardInvitation sharedBoardInvitation " +
                        "WHERE sharedBoardInvitation.sharedBoard =: theSharedBoard"
                , SharedBoardInvitation.class);
        query.setParameter("theSharedBoard", theSharedBoard);

        return query.getResultList();
    }

    @Override
    public Iterable<SharedBoard> findSharedBoardsAvailableForUser(SystemUser user) {
        final TypedQuery<SharedBoard> query = entityManager().createQuery(
                "SELECT sharedBoard FROM SharedBoardInvitation sharedBoardInvitation " +
                        "WHERE sharedBoardInvitation.userInvite =: user " +
                        "AND sharedBoardInvitation.sharedBoard.status =: status " +
                        "AND sharedBoardInvitation.userPermission =: status2",
                SharedBoard.class);
        query.setParameter("user", user);
        query.setParameter("status", SharedBoardStatus.OPEN);
        query.setParameter("status2", UserPermission.WRITE);
        return query.getResultList();
    }

}
