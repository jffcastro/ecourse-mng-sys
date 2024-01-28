package application.base.domain.sharedBoardManagement;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

public class PostItBuilder implements DomainFactory<PostIt> {
    private PostIt thePostIt;

    private String content;

    private SystemUser owner;

    private Cell cell;

    public PostItBuilder withContent(final String content) {
        this.content = content;
        return this;
    }

    public PostItBuilder withOwner(final SystemUser owner) {
        this.owner = owner;
        return this;
    }

    public PostItBuilder withCell(final Cell cell) {
        this.cell = cell;
        return this;
    }

    @Override
    public PostIt build() {
        Preconditions.noneNull(content, owner, cell);

        thePostIt = new PostIt(content, owner, cell);

        return thePostIt;
    }
}
