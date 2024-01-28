package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.sharedBoardManagement.SharedBoardColumn;
import application.base.repositories.sharedBoardManagement.ColumnRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaColumnRepository extends JpaAutoTxRepository<SharedBoardColumn, Long, Long> implements ColumnRepository {

    public JpaColumnRepository(final TransactionalContext autoTx) {
        super(autoTx, "columnID");
    }

    public JpaColumnRepository(final String persistenceUnitName){
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "columnID");
    }
}
