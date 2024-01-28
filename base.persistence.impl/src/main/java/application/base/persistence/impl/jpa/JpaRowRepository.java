package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.sharedBoardManagement.Row;
import application.base.repositories.sharedBoardManagement.RowRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaRowRepository extends JpaAutoTxRepository<Row, Long, Long> implements RowRepository {

    public JpaRowRepository(final TransactionalContext autoTx) {
        super(autoTx, "sharedBoardInvitationId");
    }

    public JpaRowRepository(final String persistenceUnitName){
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "sharedBoardInvitationId");
    }
}
