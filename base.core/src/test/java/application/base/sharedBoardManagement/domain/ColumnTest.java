package application.base.sharedBoardManagement.domain;

import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardColumn;
import application.base.domain.sharedBoardManagement.builders.ColumnBuilder;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ColumnTest {
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


    ColumnBuilder columnBuilder = new ColumnBuilder();
    Integer columnNumber = 1;
    String title = "Title";

    SharedBoardColumn column = columnBuilder
            .withColumnNumber(columnNumber)
            .ofSharedBoard(sharedBoard)
            .withTitle(title)
            .build();

    SharedBoardColumn column1 = columnBuilder
            .withColumnNumber(columnNumber)
            .ofSharedBoard(sharedBoard)
            .withTitle(title)
            .build();


    @Test
    public void ensureItMustHaveRowNumber(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new SharedBoardColumn(null, sharedBoard, title);}
        );
    }

    @Test
    public void ensureItMustHaveSharedBoard(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new SharedBoardColumn(columnNumber, null, title);}
        );
    }

    @Test
    public void ensureItMustHaveTitle(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new SharedBoardColumn(columnNumber, sharedBoard, null);}
        );
    }

    @Test
    public void testSameAs() {
        Assertions.assertTrue(column.sameAs(column1));
        Assertions.assertTrue(column.sameAs(column));
        Assertions.assertFalse(column.sameAs("other"));
    }
    List<Cell> cellList = new ArrayList<>();
    @Test
    public void testToString() {
        String expected = "Column{" +
                "title='" + title + '\'' +
                ", cellList=" + cellList +
                '}';
        Assertions.assertEquals(column.toString(), expected);

    }
}
