package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardStatus;
import application.base.repositories.sharedBoardManagement.SharedBoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaSharedBoardRepository extends JpaAutoTxRepository<SharedBoard, Long, Long> implements SharedBoardRepository {

    public JpaSharedBoardRepository(final TransactionalContext autoTx) {
        super(autoTx, "sharedBoardId");
    }

    public JpaSharedBoardRepository(final String persistenceUnitName){
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "sharedBoardId");
    }

    public Iterable<SharedBoard> findAll(){
        final TypedQuery<SharedBoard> query = entityManager().createQuery(
                "SELECT sharedBoard FROM SharedBoard sharedBoard",
                SharedBoard.class);

        return query.getResultList();
    }

    public Iterable<SharedBoard> findSharedBoardOfUser(SystemUser user){
        final TypedQuery<SharedBoard> query = entityManager().createQuery(
                "SELECT sharedBoard FROM SharedBoard sharedBoard WHERE sharedBoard.owner =: user AND sharedBoard.status =: status",
                SharedBoard.class);
        query.setParameter("user", user);
        query.setParameter("status", SharedBoardStatus.OPEN);

        return query.getResultList();
    }

}
