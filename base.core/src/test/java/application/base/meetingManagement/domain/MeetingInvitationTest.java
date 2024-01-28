package application.base.meetingManagement.domain;

import application.base.domain.meetingManagement.*;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class MeetingInvitationTest {

    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();

    final MeetingBuilder meetingBuilder = new MeetingBuilder();

    final MeetingInvitationBuilder meetingInvitationBuilder = new MeetingInvitationBuilder();

    LocalTime now = LocalTime.now();
    LocalTime after = now.plusHours(1);
    LocalDate today = LocalDate.now();
    LocalDate dateIn3Day = today.plusDays(3);
    Date date = Date.from(dateIn3Day.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));

    final SystemUser organizer = userBuilder
            .withUsername("Organizer")
            .withEmail("Organizer@email.com")
            .withPassword("Password1")
            .withName("OrganizerFstName", "OrganizerLstName")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final SystemUser invited = userBuilder
            .withUsername("Invited")
            .withEmail("Invited@email.com")
            .withPassword("Password1")
            .withName("InvitedFstName", "InvitedLstName")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final Meeting meeting = meetingBuilder
            .withOrganizer(organizer)
            .withDescription("description")
            .withDate(date)
            .withStartTime(now)
            .withEndTime(after)
            .build();

    final Meeting meetingTest = meetingBuilder
            .withOrganizer(organizer)
            .withDescription("description")
            .withDate(date)
            .withStartTime(now)
            .withEndTime(after)
            .build();

    final MeetingInvitation meetingInvitation = meetingInvitationBuilder
            .withMeeting(meeting)
            .withInvitee(invited)
            .withMeetingInvitationStatus(MeetingInvitationStatus.PENDING)
            .build();
    final MeetingInvitation meetingInvitationExpected = meetingInvitationBuilder
            .withMeeting(meeting)
            .withInvitee(invited)
            .withMeetingInvitationStatus(MeetingInvitationStatus.PENDING)
            .build();

    /**
     * Test equals.
     */
    @org.junit.jupiter.api.Test
    void testEquals() {
        Assert.assertEquals(meetingInvitationExpected,meetingInvitation);
    }

    /**
     * Invitee id.
     */
    @org.junit.jupiter.api.Test
    void inviteeID() {
        Assert.assertEquals(invited.identity(),meetingInvitation.invitee().identity());
    }

    /**
     * Meeting invitation status.
     */
    @org.junit.jupiter.api.Test
    void meetingInvitationStatus() {
        Assert.assertEquals(MeetingInvitationStatus.PENDING,meetingInvitation.meetingInvitationStatus());
    }

    /**
     * Meeting.
     */
    @org.junit.jupiter.api.Test
    void meeting() {
        Assert.assertEquals(meeting,meetingInvitation.meeting());
    }

    /**
     * Invitee.
     */
    @org.junit.jupiter.api.Test
    void invitee() {
        Assert.assertEquals(invited,meetingInvitation.invitee());
    }

    /**
     * Accept meeting.
     */
    @org.junit.jupiter.api.Test
    void acceptMeeting() {
        Assert.assertEquals(MeetingInvitationStatus.ACCEPTED,meetingInvitation.acceptMeeting());
    }

    /**
     * Decline meeting.
     */
    @org.junit.jupiter.api.Test
    void declineMeeting() {
        Assert.assertEquals(MeetingInvitationStatus.DECLINED,meetingInvitation.declineMeeting());
    }

    /**
     * Identity.
     */
    @org.junit.jupiter.api.Test
    void identity() {
        Assert.assertEquals(meetingInvitation.identity(),meetingInvitation.identity());
    }


    @org.junit.jupiter.api.Test
    void sameAs() {
        Assert.assertEquals(true,meetingInvitation.sameAs(meetingInvitationExpected));
    }

    /**
     * Sets meeting.
     */
    @org.junit.jupiter.api.Test
    void setMeeting() {
        meetingInvitation.setMeeting(meetingTest);
        Assert.assertEquals(meetingTest,meetingInvitation.meeting());

    }

    /**
     * Sets meeting invitation status.
     */
    @org.junit.jupiter.api.Test
    void setMeetingInvitationStatus() {
        meetingInvitation.setMeetingInvitationStatus(MeetingInvitationStatus.ACCEPTED);
        Assert.assertEquals(MeetingInvitationStatus.ACCEPTED,meetingInvitation.meetingInvitationStatus());

    }



    @org.junit.jupiter.api.Test
    void ensureCanCancelMeetingInvitationWithStatusAccepted() {
        meetingInvitation.setMeetingInvitationStatus(MeetingInvitationStatus.ACCEPTED);
        meetingInvitation.changeMeetingInvitationStatusToCanceled(meetingInvitation);
        Assert.assertEquals(MeetingInvitationStatus.CANCELED, meetingInvitation.meetingInvitationStatus());
    }

    @org.junit.jupiter.api.Test
    void ensureCanCancelMeetingInvitationWithStatusPending() {
        meetingInvitation.changeMeetingInvitationStatusToCanceled(meetingInvitation);
        Assert.assertEquals(MeetingInvitationStatus.CANCELED, meetingInvitation.meetingInvitationStatus());
    }


    @org.junit.jupiter.api.Test
    void ensureCantCancelMeetingInvitationWithStatusCanceled() {
        meetingInvitation.setMeetingInvitationStatus(MeetingInvitationStatus.CANCELED);
        meetingInvitation.changeMeetingInvitationStatusToCanceled(meetingInvitation);
        // Verifica se o status do convite permanece o mesmo (não é alterado)
        Assert.assertEquals(MeetingInvitationStatus.CANCELED, meetingInvitation.meetingInvitationStatus());
    }

    @org.junit.jupiter.api.Test
    void ensureCantCancelMeetingInvitationWithStatusDeclined() {
        meetingInvitation.setMeetingInvitationStatus(MeetingInvitationStatus.DECLINED);
        meetingInvitation.changeMeetingInvitationStatusToCanceled(meetingInvitation);
        // Verifica se o status do convite permanece o mesmo (não é alterado)
        Assert.assertNotEquals(MeetingInvitationStatus.CANCELED, meetingInvitation.meetingInvitationStatus());
    }

    @Test
    public void testSameAs() {
        Assertions.assertTrue(meetingInvitation.sameAs(meetingInvitationExpected));
        Assertions.assertTrue(meetingInvitation.sameAs(meetingInvitation));
        Assertions.assertFalse(meetingInvitation.sameAs("other"));
    }

    @Test
    public void testToString() {
        String expected = "Meeting - " + meeting;
        Assert.assertEquals(meetingInvitation.toString(), expected);
    }

}
