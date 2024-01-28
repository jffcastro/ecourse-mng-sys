package application.base.repositories.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public interface SharedBoardRepository extends DomainRepository<Long, SharedBoard> {

    Iterable<SharedBoard> findAll();


    /**
     * This method returns a list with all the shared boards that the user creates
     * Only the ones with open status
     * @param user
     */
    Iterable<SharedBoard> findSharedBoardOfUser(SystemUser user);
}
