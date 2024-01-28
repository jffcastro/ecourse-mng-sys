package application.base.persistence.impl.inmemory;

import application.base.domain.sharedBoardManagement.SharedBoardColumn;
import application.base.repositories.sharedBoardManagement.ColumnRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryColumnRepository extends InMemoryDomainRepository<SharedBoardColumn, Long>
        implements ColumnRepository {
}
