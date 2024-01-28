package application.base.sharedBoardManagement.domain;

import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SharedBoardBuilderTest {

    final SystemUserBuilder userBuilder1 = UserBuilderHelper.builder();
    final SystemUser owner = userBuilder1
            .withUsername("owner")
            .withPassword("Password1")
            .withName("ownerFstName", "ownerlSTname")
            .withEmail("owner@gmail.com")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final String title = "title";

    final Integer maxNumberOfRows = 12;

    final Integer maxNumberOfColumns = 8;
    final SharedBoardBuilder sharedBoardBuilder = new SharedBoardBuilder();

    @Test
    public void ensureCanBuildSharedBoardWithTitleOwnerNrOfRowsAndColumnsInvitationAndUpdateList() {
        SharedBoardBuilder builder = new SharedBoardBuilder();
        SharedBoard actual = builder
                .withTitle(title)
                .ownedBy(owner)
                .hasMaxNumberOfRows(12)
                .hasMaxNumberOfColumns(8)
                .withInvitationList(null)
                .build();

        SharedBoard expected = new SharedBoard(title, owner, maxNumberOfRows, maxNumberOfColumns, null);

        Assertions.assertTrue(expected.sameAs(actual));
    }

    @Test
    public void ensureCannotBuildWithNullTitle() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sharedBoardBuilder.withTitle(null).ownedBy(owner). hasMaxNumberOfRows(maxNumberOfRows).hasMaxNumberOfColumns(maxNumberOfColumns)
                    .withInvitationList(null).build();
        });
    }

    @Test
    public void ensureCannotBuildWithNullOwner() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sharedBoardBuilder.withTitle(title).ownedBy(null).hasMaxNumberOfRows(maxNumberOfRows).hasMaxNumberOfColumns(maxNumberOfColumns)
                    .withInvitationList(null).build();
        });
    }

    @Test
    public void ensureCannotBuildWithNullNumberOfRows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sharedBoardBuilder.withTitle(title).ownedBy(owner).hasMaxNumberOfRows(null).hasMaxNumberOfColumns(maxNumberOfColumns)
                    .withInvitationList(null).build();
        });
    }

    @Test
    public void ensureCannotBuildWithNullNumberOfColumns() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sharedBoardBuilder.withTitle(title).ownedBy(owner).hasMaxNumberOfRows(maxNumberOfRows).hasMaxNumberOfColumns(null)
                    .withInvitationList(null).build();
        });
    }
}