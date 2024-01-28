package application.base.domain.sharedBoardManagement.builders;

import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardInvitation;
import application.base.domain.sharedBoardManagement.UserPermission;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

public class SharedBoardInvitationBuilder implements DomainFactory<SharedBoardInvitation> {

    private SharedBoardInvitation theSharedBoardInvitation;
    private SharedBoard sharedBoard;

    private UserPermission userPermission;

    private SystemUser userInvite;

    public SharedBoardInvitationBuilder withSharedBoard(final SharedBoard sharedBoard) {
        this.sharedBoard = sharedBoard;
        return this;
    }

    public SharedBoardInvitationBuilder withUserPermission(final UserPermission userPermission) {
        this.userPermission = userPermission;
        return this;
    }

    public SharedBoardInvitationBuilder withUser(final SystemUser userInvite){
        this.userInvite = userInvite;
        return this;
    }
    @Override
    public SharedBoardInvitation build() {
        Preconditions.noneNull(sharedBoard, userPermission, userInvite);

        theSharedBoardInvitation = new SharedBoardInvitation(
                userPermission,
                sharedBoard,
                userInvite
        );
        return theSharedBoardInvitation;
    }
}
