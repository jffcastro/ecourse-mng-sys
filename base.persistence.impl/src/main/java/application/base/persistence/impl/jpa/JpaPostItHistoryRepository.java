package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.sharedBoardManagement.PostItHistory;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.repositories.sharedBoardManagement.PostItHistoryRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;


public class JpaPostItHistoryRepository extends JpaAutoTxRepository<PostItHistory, Long, Long> implements PostItHistoryRepository {

    /**
     * Instantiates a new Jpa meeting repository.
     *
     * @param autoTx the auto tx
     */
    public JpaPostItHistoryRepository(final TransactionalContext autoTx) {
        super(autoTx, "postItHistoryID");
    }

    /**
     * Instantiates a new Jpa meeting repository.
     *
     * @param name the name
     */
    public JpaPostItHistoryRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "postItHistoryID");
    }

    @Override
    public Iterable<PostItHistory> findUpdatesOfSharedBoard(SharedBoard sharedBoard) {
        final TypedQuery<PostItHistory> query = entityManager().createQuery(
                "SELECT postItHistory FROM PostItHistory postItHistory" +
                        " WHERE postItHistory.postIt.cell.row.sharedBoard =: sharedBoard" +
                        " AND postItHistory.postIt.cell.sharedBoardColumn.sharedBoard =: sharedBoard",
                PostItHistory.class);

        query.setParameter("sharedBoard", sharedBoard);
        return query.getResultList();
    }

}