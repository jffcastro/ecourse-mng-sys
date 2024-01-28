package application.base.sharedBoardManagement.domain;

import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.Row;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.builders.RowBuilder;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class RowTest {

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

    Row row1 = rowBuilder
            .withRowNumber(rowNumber)
            .ofSharedBoard(sharedBoard)
            .withTitle(title)
            .build();


    @Test
    public void ensureItMustHaveRowNumber(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new Row(null, sharedBoard, title);}
        );
    }

    @Test
    public void ensureItMustHaveSharedBoard(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new Row(rowNumber, null, title);}
        );
    }

    @Test
    public void ensureItMustHaveTitle(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new Row(rowNumber, sharedBoard, null);}
        );
    }

    @Test
    public void testSameAs() {
        Assertions.assertTrue(row.sameAs(row1));
        Assertions.assertTrue(row.sameAs(row));
        Assertions.assertFalse(row.sameAs("other"));
    }
    List<Cell> cellList = new ArrayList<>();
    @Test
    public void testToString() {
        String expected = "Row " + rowNumber + '\'' +
                " - " + title + '\'' +
                ", cellList=" + cellList;
        Assertions.assertEquals(row.toString(), expected);

    }
}
