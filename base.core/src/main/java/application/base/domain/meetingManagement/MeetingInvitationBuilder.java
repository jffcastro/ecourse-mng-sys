package application.base.domain.meetingManagement;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

/**
 * The type Meeting invitation builder.
 */
public class MeetingInvitationBuilder implements DomainFactory<MeetingInvitation> {

    private Meeting meeting;
    private MeetingInvitationStatus meetingInvitationStatus;
    private SystemUser invitee;

    /**
     * With meeting meeting invitation builder.
     *
     * @param meeting the meeting
     * @return the meeting invitation builder
     */
    public MeetingInvitationBuilder withMeeting(Meeting meeting) {
        this.meeting = meeting;
        return this;
    }

    /**
     * With meeting invitation status meeting invitation builder.
     *
     * @param meetingInvitationStatus the meeting invitation status
     * @return the meeting invitation builder
     */
    public MeetingInvitationBuilder withMeetingInvitationStatus(MeetingInvitationStatus meetingInvitationStatus) {
        this.meetingInvitationStatus = meetingInvitationStatus;
        return this;
    }

    /**
     * With invitee meeting invitation builder.
     *
     * @param invitee the invitee
     * @return the meeting invitation builder
     */
    public MeetingInvitationBuilder withInvitee(SystemUser invitee) {
        this.invitee = invitee;
        return this;
    }

    @Override
    public MeetingInvitation build() {
        Preconditions.nonNull(meeting, "Meeting must not be null");
        Preconditions.nonNull(meetingInvitationStatus, "Meeting invitation status must not be null");
        Preconditions.nonNull(invitee, "Invitee must not be null");

        return new MeetingInvitation(meeting, invitee);
    }
}
