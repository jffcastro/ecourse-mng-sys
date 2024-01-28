package application.base.domain.meetingManagement;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

/**
 * The type Meeting invitation.
 */
@Entity
public class MeetingInvitation implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long inviteeID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeetingInvitationStatus meetingInvitationStatus;

    @ManyToOne
    private Meeting meeting;

    @OneToOne
    private SystemUser invitee;

    /**
     * Instantiates a new Meeting invitation.
     */
    protected MeetingInvitation(){}

    /**
     * Instantiates a new Meeting invitation.
     *
     * @param meeting                 the meeting
     * @param invitee                 the invitee
     */
    public MeetingInvitation(final Meeting meeting, final SystemUser invitee) {
        this.meetingInvitationStatus = MeetingInvitationStatus.PENDING;
        this.meeting = meeting;
        this.invitee = invitee;
    }


    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual((DomainEntity<?>) this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode((DomainEntity<?>) this);
    }

    /**
     * Meeting invitation status meeting invitation status.
     *
     * @return the meeting invitation status
     */
    public MeetingInvitationStatus meetingInvitationStatus() {
        return meetingInvitationStatus;
    }

    /**
     * Meeting meeting.
     *
     * @return the meeting
     */
    public Meeting meeting() {
        return meeting;
    }

    public SystemUser invitee() {return  invitee;}


    /**
     * Accept meeting meeting invitation status.
     *
     * @return the meeting invitation status
     */
    public MeetingInvitationStatus acceptMeeting() {
        if (meetingInvitationStatus.equals(MeetingInvitationStatus.PENDING)) {
            this.meetingInvitationStatus = MeetingInvitationStatus.ACCEPTED;
        } else if (meetingInvitationStatus.equals(MeetingInvitationStatus.DECLINED)) {
            this.meetingInvitationStatus = MeetingInvitationStatus.ACCEPTED;
        } else if (meetingInvitationStatus.equals(MeetingInvitationStatus.ACCEPTED)) {
            System.out.println("Invitation for this meeting is already accepted.");
        } else {
            System.out.println("Unable to accept invitation for this meeting.");
        }
        return meetingInvitationStatus;
    }

    /**
     * Decline meeting meeting invitation status.
     *
     * @return the meeting invitation status
     */
    public MeetingInvitationStatus declineMeeting() {
        if (meetingInvitationStatus.equals(MeetingInvitationStatus.PENDING)) {
            this.meetingInvitationStatus = MeetingInvitationStatus.DECLINED;
        } else if (meetingInvitationStatus.equals(MeetingInvitationStatus.ACCEPTED)) {
            this.meetingInvitationStatus = MeetingInvitationStatus.DECLINED;
        } else if (meetingInvitationStatus.equals(MeetingInvitationStatus.DECLINED)) {
            System.out.println("Invitation for this meeting is already declined.");
        } else {
            System.out.println("Unable to decline invitation for this meeting.");
        }
        return meetingInvitationStatus;
    }

    public Long identity() {
        return inviteeID;
    }

    public boolean sameAs(final Object o) {
        if (!(o instanceof MeetingInvitation)) {
            return false;
        }

        final MeetingInvitation that = (MeetingInvitation) o;
        if (this == that) {
            return true;
        }

        return identity()==that.identity()
                && meetingInvitationStatus.equals(that.meetingInvitationStatus)
                && meeting.equals(that.meeting);
    }

    /**
     * Sets meeting.
     *
     * @param meeting the meeting
     */
    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    /**
     * Sets meeting invitation status.
     *
     * @param meetingInvitationStatus the meeting invitation status
     */
    public void setMeetingInvitationStatus(MeetingInvitationStatus meetingInvitationStatus) {
        this.meetingInvitationStatus = meetingInvitationStatus;
    }

    /**
     * Changes meeting invitation status to 'CANCELED'.
     *
     * @param meetingInvitation
     */
    public void changeMeetingInvitationStatusToCanceled(MeetingInvitation meetingInvitation){
        if(meetingInvitation.meetingInvitationStatus == MeetingInvitationStatus.ACCEPTED || meetingInvitation.meetingInvitationStatus == MeetingInvitationStatus.PENDING){
            this.meetingInvitationStatus = MeetingInvitationStatus.CANCELED;
        }
    }

    @Override
    public String toString() {
        return "Meeting - " + meeting;
    }
}
