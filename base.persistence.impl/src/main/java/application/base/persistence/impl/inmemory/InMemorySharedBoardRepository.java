package application.base.persistence.impl.inmemory;

import application.base.domain.courseManagement.Course;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.sharedBoardManagement.SharedBoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySharedBoardRepository extends InMemoryDomainRepository<SharedBoard, Long>
        implements SharedBoardRepository {
    @Override
    public boolean containsOfIdentity(Long id) {
        return super.containsOfIdentity(id);
    }

    @Override
    public Iterable<SharedBoard> findSharedBoardOfUser(SystemUser user) {
        return null;
    }
}
