package application.base.persistence.impl.inmemory;

import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.PostIt;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.repositories.sharedBoardManagement.PostItRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryPostItRepository extends InMemoryDomainRepository<PostIt, Long>
        implements PostItRepository {
    @Override
    public Iterable<Cell> findCellsWithPostItFromSharedBoard(SharedBoard sharedBoard) {
        return null;
    }

    @Override
    public Iterable<PostIt> findPostItsFromSharedBoard(SharedBoard sharedBoard, SystemUser user) {
        return null;
    }
}
