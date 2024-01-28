package application.base.sharedBoardManagement.domain;

import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardInvitation;
import application.base.domain.sharedBoardManagement.UserPermission;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.domain.sharedBoardManagement.builders.SharedBoardInvitationBuilder;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SharedBoardInvitationTest {

    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    final SystemUser owner = userBuilder
            .withUsername("owner")
            .withPassword("Password1")
            .withName("ownerFstName", "ownerlSTname")
            .withEmail("owner@gmail.com")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final SystemUser invited = userBuilder
            .withUsername("invited")
            .withPassword("Password1")
            .withName("invitedFstName", "invitedlSTname")
            .withEmail("invited@gmail.com")
            .withRoles(BaseRoles.TEACHER)
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

    SharedBoardInvitationBuilder builderInvitation = new SharedBoardInvitationBuilder();

    SharedBoardInvitation sharedBoardInvitation = builderInvitation
            .withSharedBoard(sharedBoard)
            .withUser(invited)
            .withUserPermission(UserPermission.READ_ONLY)
            .build();

    SharedBoardInvitation sharedBoardInvitation1 = builderInvitation
            .withSharedBoard(sharedBoard)
            .withUser(invited)
            .withUserPermission(UserPermission.READ_ONLY)
            .build();


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCantCreateSharedBoardInvitationWithNullPermission() {
        new SharedBoardInvitation(null,sharedBoard, invited);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCantCreateSharedBoardInvitationWithNullSharedBoard() {
        new SharedBoardInvitation(UserPermission.READ_ONLY,null, invited);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCantCreateSharedBoardInvitationWithNullUser() {
        new SharedBoardInvitation(UserPermission.READ_ONLY,sharedBoard, null);
    }

    @Test
    public void testSameAs() {
        Assertions.assertTrue(sharedBoardInvitation.sameAs(sharedBoardInvitation1));
        Assertions.assertTrue(sharedBoardInvitation.sameAs(sharedBoardInvitation));
        Assertions.assertFalse(sharedBoardInvitation.sameAs("other"));
    }

    @Test
    public void testToString() {
        UserPermission userPermission = UserPermission.READ_ONLY;
        String expected = "Shared Board Invitation:" +
                " User " + invited.identity() +
                " was invite to the shared board: " + sharedBoard +
                " with " + userPermission + " permission!";

        Assertions.assertEquals(sharedBoardInvitation.toString(), expected);
    }

}
