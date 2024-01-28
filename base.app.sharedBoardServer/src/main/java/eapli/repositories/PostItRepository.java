package eapli.repositories;

import application.base.Application;
import application.base.domain.sharedBoardManagement.*;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class PostItRepository extends JpaAutoTxRepository<PostIt, Long, Long> {

    public PostItRepository(final TransactionalContext autoTx) {
        super(autoTx, "postItId");
    }

    public PostItRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "postItId");
    }

    public Iterable<PostIt> findPostItOfUser(String username) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT postIt FROM PostIt postIt" +
                        " WHERE postIt.owner.username =: username ",
                PostIt.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    public PostIt findPostItById(Long id) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT postIt FROM PostIt postIt" +
                        " WHERE postIt.postItID =: id ",
                PostIt.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public PostIt findPostItFromUserAndContentAndCell(SystemUser user, String content, String row, String sharedBoardColumn) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT postIt FROM PostIt postIt" +
                        " WHERE postIt.owner =: user " +
                        " AND postIt.content =: content " +
                        " AND postIt.cell.row.title =: row " +
                        " AND postIt.cell.sharedBoardColumn.title =: sharedBoardColumn ",
                PostIt.class);
        query.setParameter("user", user);
        query.setParameter("content", content);
        query.setParameter("row", row);
        query.setParameter("sharedBoardColumn", sharedBoardColumn);
        if(query.getResultList().size() == 0)
            return null;
        return query.getSingleResult();
    }

    public Iterable<PostIt> findPostItsFromSharedBoard(SharedBoard sharedBoard, SystemUser user) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT postIt FROM PostIt postIt" +
                        " WHERE postIt.cell.sharedBoardColumn.sharedBoard =: sharedBoard " +
                        " AND postIt.cell.row.sharedBoard =: sharedBoard "+
                        "AND postIt.owner =:user",
                PostIt.class);
        query.setParameter("sharedBoard", sharedBoard);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public PostIt findPostIt(Cell cell){
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT postIt FROM PostIt postIt" +
                        " WHERE postIt.cell =: cell ",
                PostIt.class);
        query.setParameter("cell", cell);
        if(query.getResultList().size() == 0)
            return null;
        return query.getSingleResult();
    }

}
