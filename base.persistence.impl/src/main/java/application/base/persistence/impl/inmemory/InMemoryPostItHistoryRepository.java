package application.base.persistence.impl.inmemory;

import application.base.domain.sharedBoardManagement.PostItHistory;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.repositories.sharedBoardManagement.PostItHistoryRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryPostItHistoryRepository extends InMemoryDomainRepository<PostItHistory, Long>
        implements PostItHistoryRepository {

    @Override
    public Iterable<PostItHistory> findUpdatesOfSharedBoard(SharedBoard sharedBoard) {
        return null;
    }
}
