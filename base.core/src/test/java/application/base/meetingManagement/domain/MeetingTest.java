package application.base.meetingManagement.domain;

import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingBuilder;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.domain.meetingManagement.MeetingStatus;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingTest {
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();

    final SystemUser organizer = userBuilder
            .withUsername("Organizer")
            .withEmail("Organizer@email.com")
            .withPassword("Password1")
            .withName("OrganizertFstName", "OrganizertLstName")
            .withRoles(BaseRoles.STUDENT)
            .build();

    LocalDate today = LocalDate.now();
    LocalDate dateIn3Day = today.plusDays(3);
    Date date = Date.from(dateIn3Day.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));

    Date dateNow = Date.from(today.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));

    LocalTime now = LocalTime.now();
    LocalTime after = now.plusHours(1);

    LocalTime TenHours = LocalTime.of(10,0);
    LocalTime ElevenHours = LocalTime.of(11,0);
    Meeting meeting = new Meeting("description",date,now,after,organizer);

    @org.junit.jupiter.api.Test
    void getDescription() {Assert.assertEquals("description",meeting.description());
    }

    @org.junit.jupiter.api.Test
    void getDate() {
        Assert.assertEquals(date,meeting.getDate());
    }

    @org.junit.jupiter.api.Test
    void getStartTime() {
        Assert.assertEquals(now,meeting.getStartTime());
    }

    @org.junit.jupiter.api.Test
    void getEndTime() {
        Assert.assertEquals(after,meeting.getEndTime());
    }

    @org.junit.jupiter.api.Test
    void getOrganizer() {
        Assert.assertEquals(organizer,meeting.organizer());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Meeting meeting2 = new Meeting("description",date,now,after,organizer);
        Assert.assertEquals(meeting,meeting2);
    }

    @org.junit.jupiter.api.Test
    void ensureMeetingIsNotEqualToNull() {
        Assert.assertNotEquals(meeting,null);
    }

    @org.junit.jupiter.api.Test
    void ensureCantCreateMeetingWithNullDescription() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Meeting(null,date,now,after,organizer));
    }

    @org.junit.jupiter.api.Test
    void ensureCantCreateMeetingWithNullDate() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Meeting("description",null,now,after,organizer));
    }

    @org.junit.jupiter.api.Test
    void ensureCantCreateMeetingWithNullStartTime() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Meeting("description",date,null,after,organizer));
    }

    @org.junit.jupiter.api.Test
    void ensureCantCreateMeetingWithNullEndTime() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Meeting("description",date,now,null,organizer));
    }

    @org.junit.jupiter.api.Test
    void ensureCantCreateMeetingWithNullOrganizer() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Meeting("description",date,now,after,null));
    }

    @org.junit.jupiter.api.Test
    void ensureCantCreateMeetingWithStartTimeAfterEndTime() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Meeting("description",date,after,now,organizer));
    }

    @org.junit.jupiter.api.Test
    void ensureCantCreateMeetingWithDateBeforeToday() {
        LocalDate yesterday = today.minusDays(1);
        Date date = Date.from(yesterday.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Meeting("description",date,now,after,organizer));
    }

    @org.junit.jupiter.api.Test
    void ensureCantCreateMeetingWithDateEqualsToday() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Meeting("description",dateNow,now,after,organizer));
    }



    Long id = null;
    String description = "description";
    MeetingBuilder builder = new MeetingBuilder();

    Meeting meeting1 = builder
            .withOrganizer(organizer)
            .withDescription(description)
            .withDate(date)
            .withStartTime(now)
            .withEndTime(after)
            .build();


    Meeting meeting2 = builder
            .withOrganizer(organizer)
            .withDescription(description)
            .withDate(date)
            .withStartTime(now)
            .withEndTime(after)
            .build();




    @org.junit.jupiter.api.Test
    public void testSameAs() {
        List<MeetingInvitation> nullList = new ArrayList<>();
        meeting1.setMeetingInvitation(nullList);
        meeting2.setMeetingInvitation(nullList);
        Assert.assertTrue(meeting1.sameAs(meeting2));
        Assert.assertTrue(meeting1.sameAs(meeting1));
        Assert.assertFalse(meeting1.sameAs("other"));
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        String expected = "Meeting: " + id +
                " - " + description;
        Assert.assertEquals(meeting1.toString(), expected);

        String expected1 = "Meeting: " + id +
                " - " + description;
        Assert.assertEquals(meeting2.toString(), expected1);
    }

    @org.junit.jupiter.api.Test
    void ensureCanCancelMeetingWithStatusCreated() {
        meeting1.cancelMeeting();
        Assert.assertEquals(MeetingStatus.CANCELED, meeting1.meetingStatus());
    }

    @org.junit.jupiter.api.Test
    void ensureCantCancelMeetingWithStatusCanceled() {
        meeting1.cancelMeeting();
        // Verifica se o status da reunião permanece o mesmo (não é alterado)
        Assert.assertNotEquals(MeetingStatus.CANCELED, meeting.meetingStatus());
    }


}