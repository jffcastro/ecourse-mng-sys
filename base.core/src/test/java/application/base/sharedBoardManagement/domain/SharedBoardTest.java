package application.base.sharedBoardManagement.domain;

import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.domain.sharedBoardManagement.SharedBoardStatus;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SharedBoardTest {
    private static final Logger LOGGER = LogManager
            .getLogger(SharedBoardTest.class);

    //Owner
    final SystemUserBuilder userBuilder1 = UserBuilderHelper.builder();
    final SystemUser owner = userBuilder1
            .withUsername("owner")
            .withPassword("Password1")
            .withName("ownerFstName", "ownerlSTname")
            .withEmail("owner@gmail.com")
            .withRoles(BaseRoles.STUDENT)
            .build();


    //Shared Board
    private final String title = "shared board title";
    private final Integer maxNumberOfRows = 12;
    private final Integer maxNumberOfColumns = 8;

    SharedBoardBuilder builder = new SharedBoardBuilder();
    SharedBoard sharedBoard = builder
            .withTitle(title)
            .ownedBy(owner)
            .hasMaxNumberOfRows(maxNumberOfRows)
            .hasMaxNumberOfColumns(maxNumberOfColumns)
            .withInvitationList(null)
            .build();

    SharedBoard sharedBoard1 = builder
            .withTitle(title)
            .ownedBy(owner)
            .hasMaxNumberOfRows(maxNumberOfRows)
            .hasMaxNumberOfColumns(maxNumberOfColumns)
            .withInvitationList(null)
            .build();

    @Test
    public void testFailedCauseMaxNumberOfRowsAndColumnsAre0(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new SharedBoard("Shared Board Title Test", null, 0, 0, null);}
        );
    }

    @Test
    public void testFailedCauseMaxNumberOfRowsIsBiggerThan20(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new SharedBoard("Shared Board Title Test", null, 21, 5, null);}
        );
    }


    @Test
    public void testFailedCauseMaxNumberOfColumnsIsBiggerThan10(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new SharedBoard("Shared Board Title Test", null, 5, 11, null);}
        );
    }

    @Test
    public void ensureMustHaveTitle() {
        assertThrows(IllegalArgumentException.class, () -> new SharedBoard(null, owner, 9,9, null));
    }

    @Test
    public void ensureMustHaveMaxNumberOfRows() {
        assertThrows(IllegalArgumentException.class, () -> new SharedBoard("title", owner, null,9,  null));
    }

    @Test
    public void ensureMustHaveMaxNumberOfColumns() {
        assertThrows(IllegalArgumentException.class, () -> new SharedBoard("title", owner, 9,null, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNumberOfRowsMustNotBeNegative() {
        System.out.println("Max Number Of Rows must not be negative");
        new SharedBoard("title", owner, -1, 2,  null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNumberOfColumnsMustNotBeNegative() {
        System.out.println("Max Number Of Columns must not be negative");
        new SharedBoard("title", owner, 2, -1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSharedBoardTitleMustNotHaveLengthLessThan3() {
        System.out.println("Title must have more than three characters!!");
        new SharedBoard("", owner, 2, 2,  null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSharedBoardTitleMustNotHaveLengthBiggerThan50() {
        System.out.println("Title must have less than fifty characters!!");
        new SharedBoard("This title is really big, it cant have more than 50 characters so I will have to put another title to work", owner, 2, 2,  null);
    }


    @Test
    public void testSameAs() {
        Assertions.assertTrue(sharedBoard.sameAs(sharedBoard1));
        Assertions.assertTrue(sharedBoard.sameAs(sharedBoard));
        Assertions.assertFalse(sharedBoard.sameAs("other"));
    }

//    @Test
//    public void testToString() {
//        SharedBoardStatus status = SharedBoardStatus.OPEN;
//        String expected = "Shared Board: \"" + title + "\" owned by " + owner.name() + " (" + owner.username() + ")"
//                + " with " + maxNumberOfRows + " rows and " + maxNumberOfColumns + " columns, is " + status;
//        Assertions.assertEquals(sharedBoard.toString(), expected);
//
//        String expected1 = "Shared Board: \"" + title + "\" owned by " + owner.name() + " (" + owner.username() + ")"
//                + " with " + maxNumberOfRows + " rows and " + maxNumberOfColumns + " columns, is " + status;
//        Assertions.assertEquals(sharedBoard1.toString(), expected1);
//    }
}
