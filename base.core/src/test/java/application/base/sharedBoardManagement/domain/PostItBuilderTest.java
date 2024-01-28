package application.base.sharedBoardManagement.domain;

import application.base.domain.sharedBoardManagement.*;
import application.base.domain.sharedBoardManagement.builders.ColumnBuilder;
import application.base.domain.sharedBoardManagement.builders.RowBuilder;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PostItBuilderTest {
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    final SystemUser owner = userBuilder
            .withUsername("owner")
            .withPassword("Password1")
            .withName("ownerFstName", "ownerlSTname")
            .withEmail("owner@gmail.com")
            .withRoles(BaseRoles.STUDENT)
            .build();

    //SHARED BOARD
    SharedBoardBuilder sbBuilder = new SharedBoardBuilder();
    SharedBoard sharedBoard = sbBuilder
            .withTitle("title")
            .ownedBy(owner)
            .hasMaxNumberOfRows(8)
            .hasMaxNumberOfColumns(8)
            .withInvitationList(null)
            .build();

    //ROW
    Integer rowNumber = 1;
    String title = "Title";

    RowBuilder rowBuilder = new RowBuilder();
    Row row = rowBuilder
            .withRowNumber(rowNumber)
            .ofSharedBoard(sharedBoard)
            .withTitle(title)
            .build();


    //COLUMN

    ColumnBuilder columnBuilder = new ColumnBuilder();
    Integer columnNumber = 1;

    SharedBoardColumn column = columnBuilder
            .withColumnNumber(columnNumber)
            .ofSharedBoard(sharedBoard)
            .withTitle(title)
            .build();

    //CELL
    CellBuilder cellBuilder = new CellBuilder();

    Cell cell = cellBuilder
            .inRow(row)
            .inColumn(column)
            .build();


    //POST-IT
    PostItBuilder builder = new PostItBuilder();

    private String content = "um";

    @Test
    public void ensureCanBuild() {
        PostIt expected = builder
                .withContent(content)
                .withOwner(owner)
                .withCell(cell)
                .build();

        PostIt actual = new PostIt(content, owner, cell);

        Assert.assertTrue(expected.sameAs(actual));

    }

    @Test
    public void testFailedCauseContentIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    builder
                            .withContent(null)
                            .withOwner(owner)
                            .withCell(cell)
                            .build();
                }
        );
    }

    @Test
    public void testFailedCauseOwnerIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    builder
                            .withContent(content)
                            .withOwner(null)
                            .withCell(cell)
                            .build();
                }
        );
    }

    @Test
    public void testFailedCauseCellIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    builder
                            .withContent(content)
                            .withOwner(owner)
                            .withCell(null)
                            .build();
                }
        );
    }


}
