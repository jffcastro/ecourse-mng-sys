package application.base.sharedBoardManagement.domain;

import application.base.domain.sharedBoardManagement.*;
import application.base.domain.sharedBoardManagement.builders.ColumnBuilder;
import application.base.domain.sharedBoardManagement.builders.RowBuilder;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CellBuilderTest {
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


    @Test
    public void ensureCanBuildWithRowAndColumn(){
        Cell expected = cellBuilder
                .inRow(row)
                .inColumn(column)
                .build();

        Cell actual = new Cell(row, column);

        Assertions.assertTrue(expected.sameAs(actual));

    }

    @Test
    public void testFailedCauseRowIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    cellBuilder
                            .inRow(null)
                            .inColumn(column)
                            .build();
                }
        );
    }

    @Test
    public void testFailedCauseColumnIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    cellBuilder
                            .inRow(row)
                            .inColumn(null)
                            .build();
                }
        );
    }
}
