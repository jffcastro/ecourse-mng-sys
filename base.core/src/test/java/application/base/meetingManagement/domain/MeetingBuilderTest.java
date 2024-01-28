package application.base.meetingManagement.domain;

import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingBuilder;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


public class MeetingBuilderTest {

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

    LocalTime now = LocalTime.now();
    LocalTime after = now.plusHours(1);

    LocalTime TenHours = LocalTime.of(10,0);
    LocalTime ElevenHours = LocalTime.of(11,0);


    @Test
    public void ensureMeetingBuilder() {
        MeetingBuilder meetingBuilder = new MeetingBuilder();
        meetingBuilder.withDescription("description");
        meetingBuilder.withOrganizer(organizer);
        meetingBuilder.withDate(date);
        meetingBuilder.withStartTime(now);
        meetingBuilder.withEndTime(after);
        Meeting meeting = meetingBuilder.build();
        Assert.assertEquals("description", meeting.description());
        Assert.assertEquals(date, meeting.getDate());
        Assert.assertEquals(now, meeting.getStartTime());
        Assert.assertEquals(after, meeting.getEndTime());
        Assert.assertEquals(organizer, meeting.organizer());
    }

    @Test
    public void ensureMeetingBuilderWithInvalidDescription() {
        MeetingBuilder meetingBuilder = new MeetingBuilder();
        meetingBuilder.withDescription(null);
        meetingBuilder.withOrganizer(organizer);
        meetingBuilder.withDate(date);
        meetingBuilder.withStartTime(now);
        meetingBuilder.withEndTime(after);
        Assert.assertThrows(IllegalArgumentException.class, () -> meetingBuilder.build());
    }

    @Test
    public void ensureMeetingBuilderWithInvalidOrganizer() {
        MeetingBuilder meetingBuilder = new MeetingBuilder();
        meetingBuilder.withDescription("description");
        meetingBuilder.withOrganizer(null);
        meetingBuilder.withDate(date);
        meetingBuilder.withStartTime(now);
        meetingBuilder.withEndTime(after);
        Assert.assertThrows(IllegalArgumentException.class, () -> meetingBuilder.build());
    }

    @Test
    public void ensureMeetingBuilderWithInvalidDate() {
        MeetingBuilder meetingBuilder = new MeetingBuilder();
        meetingBuilder.withDescription("description");
        meetingBuilder.withOrganizer(organizer);
        meetingBuilder.withDate(null);
        meetingBuilder.withStartTime(now);
        meetingBuilder.withEndTime(after);
        Assert.assertThrows(IllegalArgumentException.class, () -> meetingBuilder.build());
    }

    @Test
    public void ensureMeetingBuilderWithInvalidStartTime() {
        MeetingBuilder meetingBuilder = new MeetingBuilder();
        meetingBuilder.withDescription("description");
        meetingBuilder.withOrganizer(organizer);
        meetingBuilder.withDate(date);
        meetingBuilder.withStartTime(null);
        meetingBuilder.withEndTime(after);
        Assert.assertThrows(IllegalArgumentException.class, () -> meetingBuilder.build());
    }

    @Test
    public void ensureMeetingBuilderWithInvalidEndTime() {
        MeetingBuilder meetingBuilder = new MeetingBuilder();
        meetingBuilder.withDescription("description");
        meetingBuilder.withOrganizer(organizer);
        meetingBuilder.withDate(date);
        meetingBuilder.withStartTime(now);
        meetingBuilder.withEndTime(null);
        Assert.assertThrows(IllegalArgumentException.class, () -> meetingBuilder.build());
    }

    @Test
    public void ensureMeetingBuilderWithInvalidStartAndEndTime() {
        MeetingBuilder meetingBuilder = new MeetingBuilder();
        meetingBuilder.withDescription("description");
        meetingBuilder.withOrganizer(organizer);
        meetingBuilder.withDate(date);
        meetingBuilder.withStartTime(after);
        meetingBuilder.withEndTime(now);
        Assert.assertThrows(IllegalArgumentException.class, () -> meetingBuilder.build());
    }


}