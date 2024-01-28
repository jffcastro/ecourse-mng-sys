package application.base.domain.sharedBoardManagement;

import application.base.domain.courseManagement.Course;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class SharedBoardInvitation implements AggregateRoot<Long> {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long inviteeId;

    @ManyToOne
    private SharedBoard sharedBoard;

    @OneToOne
    private SystemUser userInvite;

    @Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    private UserPermission userPermission;

    protected SharedBoardInvitation () {}


    public SharedBoardInvitation(final UserPermission userPermission, final SharedBoard sharedBoard, final SystemUser userInvite) {
        Preconditions.noneNull(userPermission, sharedBoard, userInvite);
        this.userPermission = userPermission;
        this.sharedBoard = sharedBoard;
        this.userInvite = userInvite;
    }

    public SharedBoard sharedBoard() {
        return sharedBoard;
    }

    public SystemUser userInvite() {return userInvite;}

    public UserPermission userPermission() {
        return userPermission;
    }

    @Override
    public String toString() {
        return "Shared Board Invitation:" +
                " User " + userInvite.identity() +
                " was invite to the shared board: " + sharedBoard +
                " with " + userPermission + " permission!";
    }

    @Override
    public boolean sameAs(final Object o) {
        if (!(o instanceof SharedBoardInvitation)) {
            return false;
        }

        final SharedBoardInvitation that = (SharedBoardInvitation) o;
        if (this == that) {
            return true;
        }

        return sharedBoard.equals(that.sharedBoard) && userInvite.equals(that.userInvite)
                && userPermission.equals(that.userPermission);
    }

    @Override
    public Long identity() {
        return null;
    }
}
