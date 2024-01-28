package application.base.sharedBoardManagement.domain;

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

public class ColumnBuilderTest {
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

    @Test
    public void ensureCanBuildWithRowNumberSharedBoardAndTitle(){
        SharedBoardColumn expected = columnBuilder
                .withColumnNumber(columnNumber)
                .ofSharedBoard(sharedBoard)
                .withTitle(title)
                .build();

        SharedBoardColumn actual = new SharedBoardColumn(columnNumber, sharedBoard, title);

        Assertions.assertTrue(expected.sameAs(actual));

    }

    @Test
    public void testFailedCauseRowNumberIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    columnBuilder
                            .withColumnNumber(null)
                            .ofSharedBoard(sharedBoard)
                            .withTitle(title)
                            .build();
                }
        );
    }

    @Test
    public void testFailedCauseSharedBoardIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    columnBuilder
                            .withColumnNumber(columnNumber)
                            .ofSharedBoard(null)
                            .withTitle(title)
                            .build();
                }
        );
    }

    @Test
    public void testFailedCauseTitleIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    columnBuilder
                            .withColumnNumber(columnNumber)
                            .ofSharedBoard(sharedBoard)
                            .withTitle(null)
                            .build();
                }
        );
    }
}
