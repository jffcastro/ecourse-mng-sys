package application.base.domain.meetingManagement;

public enum MeetingInvitationStatus {
    /**
     * Indicates that the meeting is pending
     */
    PENDING,

    /**
     * Indicates that the meeting is accepted
     */
    ACCEPTED,

    /**
     * IIndicates that the meeting is declined
     */
    DECLINED,

    /**
     * Indicates that the meeting is canceled
     */
    CANCELED;
}
