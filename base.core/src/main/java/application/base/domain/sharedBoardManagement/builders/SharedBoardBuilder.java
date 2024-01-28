package application.base.domain.sharedBoardManagement.builders;

import application.base.Application;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardInvitation;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.List;

public class SharedBoardBuilder implements DomainFactory<SharedBoard> {

    /**
     * created a shared board
     */
    private SharedBoard theSharedBoard;

    private String title;

    private SystemUser owner;

    private Integer maxNumberOfRows;

    private Integer maxNumberOfColumns;

    private List<SharedBoardInvitation> sharedBoardInvitationList;


    public SharedBoardBuilder withTitle(final String title) {
        this.title = title;
        return this;
    }

    public SharedBoardBuilder ownedBy(SystemUser owner) {
        this.owner = owner;
        return this;
    }

    public SharedBoardBuilder hasMaxNumberOfRows(final Integer maxNumberOfRows) {
        this.maxNumberOfRows = maxNumberOfRows;
        return this;
    }

    public SharedBoardBuilder hasMaxNumberOfColumns(final Integer maxNumberOfColumns) {
        this.maxNumberOfColumns = maxNumberOfColumns;
        return this;
    }

    public SharedBoardBuilder withInvitationList(final List<SharedBoardInvitation> sharedBoardInvitationList) {
        this.sharedBoardInvitationList = null;
        return this;
    }

    @Override
    public SharedBoard build() {

        Preconditions.noneNull(title, maxNumberOfRows, maxNumberOfColumns);
        Preconditions.ensure(maxNumberOfRows <= Integer.parseInt(Application.settings().getProperty("sharedBoardMaxNumberOfRows"))
                && maxNumberOfColumns <= Integer.parseInt(Application.settings().getProperty("sharedBoardMaxNumberOfColumns")));

        theSharedBoard = new SharedBoard(title,
                owner,
                maxNumberOfRows,
                maxNumberOfColumns,
                sharedBoardInvitationList);

        return theSharedBoard;
    }

}