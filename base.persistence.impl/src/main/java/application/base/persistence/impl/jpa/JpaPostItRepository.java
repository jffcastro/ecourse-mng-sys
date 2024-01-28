package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.PostIt;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.repositories.sharedBoardManagement.PostItRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaPostItRepository extends JpaAutoTxRepository<PostIt, Long, Long> implements PostItRepository {

    public JpaPostItRepository(final TransactionalContext autoTx) {
        super(autoTx, "postItID");
    }

    public JpaPostItRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "postItID");
    }

    @Override
    public Iterable<Cell> findCellsWithPostItFromSharedBoard(SharedBoard sharedBoard) {
        final TypedQuery<Cell> query = entityManager().createQuery(
                "SELECT cell FROM PostIt postIt" +
                        " WHERE postIt.cell.sharedBoardColumn.sharedBoard =: sharedBoard " +
                        " AND postIt.cell.row.sharedBoard =: sharedBoard ",
                Cell.class);
        query.setParameter("sharedBoard", sharedBoard);
        return query.getResultList();
    }

    @Override
    public Iterable<PostIt> findPostItsFromSharedBoard(SharedBoard sharedBoard, SystemUser user) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT postIt FROM PostIt postIt" +
                        " WHERE postIt.cell.sharedBoardColumn.sharedBoard =: sharedBoard " +
                        " AND postIt.cell.row.sharedBoard =: sharedBoard "+
                        "AND postIt.owner =:user",
                PostIt.class);
        query.setParameter("sharedBoard", sharedBoard);
        query.setParameter("user", user);
        return query.getResultList();
    }



}
