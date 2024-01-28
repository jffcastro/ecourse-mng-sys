package application.base.domain.meetingManagement;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.validations.Preconditions;
import org.springframework.security.core.userdetails.User;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * The type Meeting builder.
 */
public class MeetingBuilder implements DomainFactory<Meeting> {

    private String description;

    private List<MeetingInvitation> meetingInvitation;

    private SystemUser organizer;

    private Date date;

    private LocalTime startTime;

    private LocalTime endTime;

    /**
     * With description meeting builder.
     *
     * @param description the description
     * @return the meeting builder
     */
    public MeetingBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * With meeting invitation meeting builder.
     *
     * @param meetingInvitation the meeting invitation
     * @return the meeting builder
     */

    /*
    public MeetingBuilder withMeetingInvitation(List<MeetingInvitation> meetingInvitation) {
        this.meetingInvitation = meetingInvitation;
        return this;
    }*/

    /**
     * With organizer meeting builder.
     *
     * @param organizer the organizer
     * @return the meeting builder
     */
    public MeetingBuilder withOrganizer(SystemUser organizer) {
        this.organizer = organizer;
        return this;
    }

    /**
     * With date meeting builder.
     *
     * @param date the date
     * @return the meeting builder
     */
    public MeetingBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    /**
     * With start time meeting builder.
     *
     * @param startTime the start time
     * @return the meeting builder
     */
    public MeetingBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * With end time meeting builder.
     *
     * @param endTime the end time
     * @return the meeting builder
     */
    public MeetingBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }


    @Override
    public Meeting build() {
        Preconditions.nonNull(description, "Description must not be null");
        Preconditions.nonNull(organizer, "Organizer must not be null");
        Preconditions.nonNull(date, "Date must not be null");
        Preconditions.nonNull(startTime, "Start time must not be null");
        Preconditions.nonNull(endTime, "End time must not be null");

        return new Meeting(description, date,startTime,endTime, organizer);
    }
}
