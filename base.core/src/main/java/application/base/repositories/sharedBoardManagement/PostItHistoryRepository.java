package application.base.repositories.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.PostItHistory;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.domain.repositories.DomainRepository;

public interface PostItHistoryRepository extends DomainRepository<Long, PostItHistory> {

    /**
     * This method is used in US 3009 and return the list of updates on post it's
     * of a given shared board.
     *
     * @param sharedBoard - shared board to find updates of
     * @return list of updates on post it's of the given shared board
     */
    Iterable<PostItHistory> findUpdatesOfSharedBoard(SharedBoard sharedBoard);
}
