package eapli.repositories;

import application.base.Application;
import application.base.domain.sharedBoardManagement.PostIt;
import application.base.domain.sharedBoardManagement.PostItHistory;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class PostItHistoryRepository extends JpaAutoTxRepository<PostItHistory, Long, Long> {

    public PostItHistoryRepository(final TransactionalContext autoTx) {
        super(autoTx, "postItHistoryID");
    }

    public PostItHistoryRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "postItHistoryID");
    }

    public Iterable<PostItHistory> findUpdatesOfSharedBoard(SharedBoard sharedBoard) {
        final TypedQuery<PostItHistory> query = entityManager().createQuery(
                "SELECT postItHistory FROM PostItHistory postItHistory" +
                        " WHERE postItHistory.postIt.cell.row.sharedBoard =: sharedBoard" +
                        " AND postItHistory.postIt.cell.sharedBoardColumn.sharedBoard =: sharedBoard",
                PostItHistory.class);

        query.setParameter("sharedBoard", sharedBoard);
        return query.getResultList();
    }

    public PostItHistory findLastChangeOfPostIt(Long postItID) {
        final TypedQuery<PostItHistory> query = entityManager().createQuery(
                "SELECT postItHistory FROM PostItHistory postItHistory" +
                        " WHERE postItHistory.postIt.postItID =: postItID" +
                        " ORDER BY postItHistory.timeStamp DESC",
                PostItHistory.class);

        query.setParameter("postItID", postItID);
        query.setMaxResults(1);
        if(query.getResultList().size() == 0)
            return null;
        return query.getSingleResult();
    }

    public PostItHistory findPenultimateChangeInPostIt(Long postItID) {
        final TypedQuery<PostItHistory> query = entityManager().createQuery(
                "SELECT postItHistory FROM PostItHistory postItHistory" +
                        " WHERE postItHistory.postIt.postItID =: postItID" +
                        " ORDER BY postItHistory.timeStamp DESC",
                PostItHistory.class);

        query.setParameter("postItID", postItID);
        query.setMaxResults(2);
        return query.getResultList().get(1);
    }
}
