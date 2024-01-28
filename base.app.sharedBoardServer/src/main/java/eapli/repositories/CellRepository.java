package eapli.repositories;

import application.base.Application;
import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.Row;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardColumn;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class CellRepository extends JpaAutoTxRepository<Cell, Long, Long> {

    public CellRepository(final TransactionalContext autoTx) {
        super(autoTx, "cellId");
    }

    public CellRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "cellId");
    }

    public Iterable<Cell> findAllFreeCellsFromSharedBoard(SharedBoard sharedBoard) {
        final TypedQuery<Cell> query = entityManager().createQuery(
                "SELECT cell FROM Cell cell" +
                        " WHERE cell.row.sharedBoard =: sharedBoard " +
                        " AND cell.sharedBoardColumn.sharedBoard =: sharedBoard" +
                        " AND cell.isFree =: cellState",
                Cell.class);
        query.setParameter("sharedBoard", sharedBoard);
        query.setParameter("cellState", true);

        return query.getResultList();
    }

    public Cell findCell(String rowTitle, String columnTitle){
        final TypedQuery<Cell> query = entityManager().createQuery(
                "SELECT cell FROM Cell cell WHERE cell.row.title =: rowTitle AND cell.sharedBoardColumn.title =: columnTitle", Cell.class
        );
        query.setParameter("rowTitle", rowTitle);
        query.setParameter("columnTitle", columnTitle);
        return query.getSingleResult();
    }
}
