package application.base.persistence.impl.inmemory;

import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.repositories.sharedBoardManagement.CellRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCellRepository extends InMemoryDomainRepository<Cell, Long>
        implements CellRepository {
    @Override
    public Iterable<Cell> findAllCellsFromSharedBoard(SharedBoard sharedBoard) {
        return null;
    }
}
