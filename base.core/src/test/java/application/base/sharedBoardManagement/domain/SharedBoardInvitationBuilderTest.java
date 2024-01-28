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

public class SharedBoardInvitationBuilderTest {
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

    SharedBoardBuilder builder = new SharedBoardBuilder();
    SharedBoard sharedBoard = builder
            .withTitle("shared board title")
            .ownedBy(owner)
            .hasMaxNumberOfRows(12)
            .hasMaxNumberOfColumns(8)
            .withInvitationList(null)
            .build();

    SharedBoardInvitationBuilder builderInvitation = new SharedBoardInvitationBuilder();

    @Test
    public void ensureCanBuildWithSharedBoardUserAndUserPermission(){
        SharedBoardInvitation expected = builderInvitation
                .withSharedBoard(sharedBoard)
                .withUser(invited)
                .withUserPermission(UserPermission.READ_ONLY)
                .build();

        SharedBoardInvitation actual = new SharedBoardInvitation(UserPermission.READ_ONLY, sharedBoard, invited);

        Assertions.assertTrue(expected.sameAs(actual));

    }
    @Test
    public void testFailedCauseSharedBoardIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    builderInvitation
                            .withSharedBoard(null)
                            .withUser(invited)
                            .withUserPermission(UserPermission.READ_ONLY)
                            .build();
                }
        );
    }

    @Test
    public void testFailedCauseUserIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    builderInvitation
                            .withSharedBoard(sharedBoard)
                            .withUser(null)
                            .withUserPermission(UserPermission.WRITE)
                            .build();
                }
        );
    }

    @Test
    public void testFailedCauseUserPermissionIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    builderInvitation
                            .withSharedBoard(sharedBoard)
                            .withUser(invited)
                            .withUserPermission(null)
                            .build();
                }
        );
    }
}
