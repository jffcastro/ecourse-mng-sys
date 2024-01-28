package application.base.domain.sharedBoardManagement;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

import javax.persistence.ManyToOne;
import java.util.Date;

public class PostItHistoryBuilder implements DomainFactory<PostItHistory> {

    private PostItHistory thePostItHistory;
    @ManyToOne
    private PostIt postIt;

    private Date timeStamp;

    private String oldDescription;

    @ManyToOne
    private Cell oldCell;

    private String newDescription;

    @ManyToOne
    private Cell newCell;


    public PostItHistoryBuilder withPostIt(final PostIt postIt) {
        this.postIt = postIt;
        return this;
    }

    public PostItHistoryBuilder withTimeStamp(final Date timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public PostItHistoryBuilder withOldContent(final String oldDescription){
        this.oldDescription = oldDescription;
        return this;
    }

    public PostItHistoryBuilder withNewContent(final String newDescription){
        this.newDescription = newDescription;
        return this;
    }

    public PostItHistoryBuilder withNewCell(final Cell newCell){
        this.newCell = newCell;
        return this;
    }

    public PostItHistoryBuilder withOldCell(final Cell oldCell){
        this.oldCell = oldCell;
        return this;
    }

    @Override
    public PostItHistory build() {
        Preconditions.noneNull(postIt, timeStamp, oldCell, oldDescription, newCell, newDescription);

        thePostItHistory = new PostItHistory(postIt, timeStamp, oldCell, oldDescription, newCell, newDescription);

        return thePostItHistory;
    }
}
