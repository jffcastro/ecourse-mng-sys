package application.base.repositories.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.PostIt;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public interface PostItRepository extends DomainRepository<Long, PostIt>{

    Iterable<Cell> findCellsWithPostItFromSharedBoard(SharedBoard sharedBoard);

    Iterable<PostIt> findPostItsFromSharedBoard(SharedBoard sharedBoard, SystemUser user);
}
