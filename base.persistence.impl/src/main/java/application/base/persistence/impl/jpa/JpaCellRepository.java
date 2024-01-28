package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.repositories.sharedBoardManagement.CellRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaCellRepository extends JpaAutoTxRepository<Cell, Long, Long> implements CellRepository {
    public JpaCellRepository(final TransactionalContext autoTx) {
        super(autoTx, "cellID");
    }
    public JpaCellRepository(final String persistenceUnitName){
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "cellID");
    }

    @Override
    public Iterable<Cell> findAllCellsFromSharedBoard(SharedBoard sharedBoard) {
        final TypedQuery<Cell> query = entityManager().createQuery(
                "SELECT cell FROM Cell cell" +
                        " WHERE cell.row.sharedBoard =: sharedBoard " +
                        " AND cell.sharedBoardColumn.sharedBoard =: sharedBoard",
                Cell.class);
        query.setParameter("sharedBoard", sharedBoard);
        return query.getResultList();
    }
}
