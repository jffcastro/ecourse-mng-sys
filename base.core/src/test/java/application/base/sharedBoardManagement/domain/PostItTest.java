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

public class PostItTest {

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

    Integer rowNumber1 = 4;
    String title1 = "Chili";

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

    PostIt postIt1 = builder
            .withContent(content)
            .withOwner(owner)
            .withCell(cell)
            .build();

   @Test
    public void ensureItMustHaveContent(){
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {new PostIt(null, owner, cell);}
        );
    }

    @Test
    public void ensureItchangesCell() {
        postIt.changeCell(cell1);
        Assert.assertEquals(cell1, postIt.cell());
    }

    @Test
    public void ensureItNotchangesCell() {
        postIt.changeCell(cell);
        Assert.assertEquals(cell,postIt.cell());
    }

    String content1 = "dois";

    @Test
    public void ensureItchangesContent() {
        postIt.changeContent(content1);
        Assert.assertNotEquals(content,content1);
    }

    @Test
    public void ensureItNotchangesContent() {
        postIt.changeContent(content);
        Assert.assertEquals(content,content);
    }

    @Test
    public void testSameAs() {
        Assert.assertTrue(postIt.sameAs(postIt1));
        Assert.assertTrue(postIt.sameAs(postIt));
        Assert.assertFalse(postIt.sameAs("other"));
    }
    @Test
    public void testToString() {
        String expected = "PostIt: " + content + '-' + owner.username() +
                "-" +  cell;
        Assert.assertEquals(postIt.toString(), expected);

    }

}
