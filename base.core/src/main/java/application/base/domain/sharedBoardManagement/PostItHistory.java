package application.base.domain.sharedBoardManagement;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostItHistory implements AggregateRoot<Long> {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long postItHistoryID;

    @ManyToOne
    private PostIt postIt;

    private Date timeStamp;

    private String oldDescription;

    @ManyToOne
    private Cell oldCell;

    private String newDescription;

    @ManyToOne
    private Cell newCell;

    public PostIt getPostIt() {
        return postIt;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getOldDescription() {
        return oldDescription;
    }

    public Cell getOldCell() {
        return oldCell;
    }

    protected PostItHistory() {
    }

    public PostItHistory(final PostIt postIt, final Date timeStamp, final Cell oldCell, final String oldDescription, final Cell newCell, final String newDescription) {
        this.postIt = postIt;
        this.timeStamp = timeStamp;
        this.oldCell = oldCell;
        this.oldDescription = oldDescription;
        this.newCell = newCell;
        this.newDescription = newDescription;
    }

    public PostIt postIt() {
        return postIt;
    }

    public Date timeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        if (oldDescription != newDescription) {

            return "PostItHistory -" +
                    " old content: " + oldDescription +
                    " new content: " + newDescription +
                    " at: " + timeStamp;
        }

        if (oldCell != newCell) {
            return "PostItHistory -" +
                    " oldCell: " + oldCell +
                    " newCell: " + newCell +
                    " at: " + timeStamp;
        }
        return null;
    }

        @Override
        public boolean sameAs (Object other){
            if (!(other instanceof PostItHistory)) {
                return false;
            }
            final PostItHistory that = (PostItHistory) other;
            if (this == that) {
                return true;
            }

            return timeStamp == that.timeStamp
                    && postItHistoryID == that.postItHistoryID;
        }

        @Override
        public Long identity () {
            return postItHistoryID;
        }
    }
