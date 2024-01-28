package application.base.meetingManagement.domain;

import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.domain.meetingManagement.MeetingInvitationBuilder;
import application.base.domain.meetingManagement.MeetingInvitationStatus;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * The type Meeting invitation builder test.
 */
class MeetingInvitationBuilderTest {

    /**
     * The User builder.
     */
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    /**
     * The Organizer.
     */
    final SystemUser organizer = userBuilder
            .withUsername("Organizer")
            .withEmail("Organizer@email.com")
            .withPassword("Password1")
            .withName("OrganizertFstName", "OrganizertLstName")
            .withRoles(BaseRoles.STUDENT)
            .build();

    /**
     * The User 1.
     */
    final SystemUser user1 = userBuilder
            .withUsername("UserMeet")
            .withEmail("UserMeet@email.com")
            .withPassword("Password1")
            .withName("UserMeettFstName", "UserMeettLstName")
            .withRoles(BaseRoles.STUDENT)
            .build();

    /**
     * The Today.
     */
    LocalDate today = LocalDate.now();
    /**
     * The Date in 3 day.
     */
    LocalDate dateIn3Day = today.plusDays(3);
    /**
     * The Date.
     */
    Date date = Date.from(dateIn3Day.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
    /**
     * The Now.
     */
    LocalTime now = LocalTime.now();
    /**
     * The After.
     */
    LocalTime after = now.plusHours(1);

    /**
     * The Meeting.
     */
    Meeting meeting = new Meeting("description",date,now,after,organizer);
    /**
     * The Meeting test.
     */
    Meeting meetingTest = new Meeting("test",date,now,after,organizer);

    /**
     * The Meeting invitation.
     */
    @Test
    void withMeeting() {
        MeetingInvitationBuilder builder = new MeetingInvitationBuilder();


        Assert.assertThrows(IllegalArgumentException.class, () -> {
            MeetingInvitation meetingInvitation = builder.withMeeting(meeting).build();
        });
    }

    /**
     * With meeting invitation status.
     */
    @Test
    void withMeetingInvitationStatus() {
        MeetingInvitationBuilder builder = new MeetingInvitationBuilder();
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            MeetingInvitation meetingInvitation = builder.withMeeting(meeting).withMeetingInvitationStatus(MeetingInvitationStatus.ACCEPTED).build();
        });
    }

    /**
     * With invitee.
     */
    @Test
    void withInvitee() {
        MeetingInvitationBuilder builder = new MeetingInvitationBuilder();
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            MeetingInvitation meetingInvitation = builder.withMeeting(meeting).withInvitee(user1).build();
        });
    }

    /**
     * Build.
     */
    @Test
    void build() {
        MeetingInvitationBuilder builder = new MeetingInvitationBuilder();
        MeetingInvitation meetingInvitation = builder.withMeeting(meeting).withInvitee(user1).withMeetingInvitationStatus(MeetingInvitationStatus.ACCEPTED).build();
        Assert.assertEquals(meetingInvitation.meeting(),meeting);
        Assert.assertEquals(meetingInvitation.invitee(),user1);
        Assert.assertEquals(meetingInvitation.meetingInvitationStatus(),MeetingInvitationStatus.PENDING);
    }
}