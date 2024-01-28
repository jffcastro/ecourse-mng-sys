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

import java.util.ArrayList;
import java.util.List;

public class CellTest {

    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    final SystemUser owner = userBuilder
            .withUsername("owner")
            .withPassword("Password1")
            .withName("ownerFstName", "ownerlSTname")
            .withEmail("owner@gmail.com")
            .withRoles(BaseRoles.STUDENT)
            .build();
    SharedBoardBuilder builder = new SharedBoardBuilder();
    SharedBoard sharedBoard = builder
            .withTitle("title")
            .ownedBy(owner)
            .hasMaxNumberOfRows(8)
            .hasMaxNumberOfColumns(8)
            .withInvitationList(null)
            .build();

    RowBuilder rowBuilder = new RowBuilder();
    Integer rowNumber = 1;
    String title = "Title";
    Row row = rowBuilder
            .withRowNumber(rowNumber)
            .ofSharedBoard(sharedBoard)
            .withTitle(title)
            .build();

    ColumnBuilder columnBuilder = new ColumnBuilder();
    Integer columnNumber = 1;

    SharedBoardColumn column = columnBuilder
            .withColumnNumber(columnNumber)
            .ofSharedBoard(sharedBoard)
            .withTitle(title)
            .build();

    CellBuilder cellBuilder = new CellBuilder();

    Cell cell = cellBuilder
            .inRow(row)
            .inColumn(column)
            .build();

    Cell cell1 = cellBuilder
            .inRow(row)
            .inColumn(column)
            .build();

    @Test
    public void ensureItMustHaveRow(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new Cell(null, column);}
        );
    }

    @Test
    public void ensureItMustHaveColumn(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new Cell(row, null);}
        );
    }

    @Test
    public void ensureIsFreeWhenCreated(){
        Assert.assertEquals(cell.isFree(), true);
    }

    @Test
    public void testSameAs() {
        Assertions.assertTrue(cell.sameAs(cell1));
        Assertions.assertTrue(cell.sameAs(cell));
        Assertions.assertFalse(cell.sameAs("other"));
    }
    @Test
    public void testToString() {
        String expected = row.title() +
                "," + column.title() + "=>" + cell.identity();
        Assertions.assertEquals(cell.toString(), expected);

    }
}
