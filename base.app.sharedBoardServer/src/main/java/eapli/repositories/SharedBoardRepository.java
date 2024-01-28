package eapli.repositories;

import application.base.Application;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardStatus;
import application.base.domain.sharedBoardManagement.UserPermission;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class SharedBoardRepository extends JpaAutoTxRepository<SharedBoard, Long, Long> {

    public SharedBoardRepository(final TransactionalContext autoTx) {
        super(autoTx, "sharedBoardId");
    }

    public SharedBoardRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),"sharedBoardId");
    }

    public Iterable<SharedBoard> findSharedBoardOfUser(SystemUser user){
        final TypedQuery<SharedBoard> query = entityManager().createQuery(
                "SELECT sharedBoard FROM SharedBoard sharedBoard " +
                        "WHERE sharedBoard.owner =: user AND sharedBoard.status =: status",
                SharedBoard.class);
        query.setParameter("user", user);
        query.setParameter("status", SharedBoardStatus.OPEN);

        return query.getResultList();
    }

    public SharedBoard findSharedBoard(String title) {
        final TypedQuery<SharedBoard> query = entityManager().createQuery(
                "SELECT sharedBoard FROM SharedBoard sharedBoard " +
                        "WHERE sharedBoard.title =: title ",
                SharedBoard.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }
}
