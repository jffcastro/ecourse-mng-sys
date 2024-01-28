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

import java.time.Instant;
import java.util.Date;

public class PostItHistoryTest {

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

    Integer rowNumber1 = 2;
    String title1 = "Titli";

    Row row1 = rowBuilder
            .withRowNumber(rowNumber1)
            .ofSharedBoard(sharedBoard)
            .withTitle(title1)
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

    Cell cell1 = cellBuilder
            .inRow(row1)
            .inColumn(column)
            .build();

    //POST-IT
    PostItBuilder builder = new PostItBuilder();

    String content = "um";
    PostIt postIt = builder
            .withContent(content)
            .withOwner(owner)
            .withCell(cell)
            .build();

    //POST-IT HISTORY

    PostItHistoryBuilder b = new PostItHistoryBuilder();
    Date timeStamp = Date.from(Instant.now());

    PostItHistory ph = b
            .withTimeStamp(timeStamp)
            .withPostIt(postIt)
            .withNewCell(cell)
            .withNewContent(content)
            .withOldCell(cell)
            .withOldContent(content)
            .build();

    PostItHistory ph1 = b
            .withTimeStamp(timeStamp)
            .withPostIt(postIt)
            .withNewCell(cell)
            .withNewContent(content)
            .withOldCell(cell)
            .withOldContent(content)
            .build();


    @Test
    public void testSameAs() {
        Assert.assertTrue(ph.sameAs(ph1));
        Assert.assertTrue(ph.sameAs(ph));
        Assert.assertFalse(ph.sameAs("other"));
    }
}
