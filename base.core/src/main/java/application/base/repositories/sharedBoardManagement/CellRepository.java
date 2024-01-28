package application.base.repositories.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.domain.repositories.DomainRepository;

public interface CellRepository extends DomainRepository<Long, Cell> {

    Iterable<Cell> findAllCellsFromSharedBoard(SharedBoard sharedBoard);
}
