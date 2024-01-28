package application.base.persistence.impl.inmemory;

import application.base.domain.sharedBoardManagement.Row;
import application.base.repositories.sharedBoardManagement.RowRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryRowRepository extends InMemoryDomainRepository<Row, Long>
        implements RowRepository {
}
