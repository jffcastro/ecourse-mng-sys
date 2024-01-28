package application.base.sharedBoardManagement.domain;

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

public class RowBuilderTest {

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

    @Test
    public void ensureCanBuildWithRowNumberSharedBoardAndTitle(){
        Row expected = rowBuilder
                .withRowNumber(rowNumber)
                .ofSharedBoard(sharedBoard)
                .withTitle(title)
                .build();

        Row actual = new Row(rowNumber, sharedBoard, title);

        Assertions.assertTrue(expected.sameAs(actual));

    }

    @Test
    public void testFailedCauseRowNumberIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    rowBuilder
                            .withRowNumber(null)
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
                    rowBuilder
                            .withRowNumber(rowNumber)
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
                    rowBuilder
                            .withRowNumber(rowNumber)
                            .ofSharedBoard(sharedBoard)
                            .withTitle(null)
                            .build();
                }
        );
    }
}
