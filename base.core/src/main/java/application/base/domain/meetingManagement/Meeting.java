package application.base.domain.meetingManagement;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The type Meeting.
 */
@Entity
public class Meeting implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @OneToOne
    private SystemUser organizer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeetingStatus meetingStatus;

    @OneToMany
    private List<MeetingInvitation> meetingInvitation;

    /**
     * Instantiates a new Meeting.
     */
    protected Meeting(){}

    /**
     * Instantiates a new Meeting.
     *
     * @param description the description
     * @param date        the date
     * @param startTime   the start time
     * @param endTime     the end time
     * @param organizer   the organizer
     */
    public Meeting(final String description, final Date date, final LocalTime startTime,final LocalTime endTime, final SystemUser organizer) {
        Preconditions.noneNull(description, organizer,date,startTime,endTime);
        Preconditions.ensure(validateMeetingTime(startTime,endTime), "Meeting time is not valid");
        Preconditions.ensure(isDateAfterCurrentDate(date), "Meeting date is not valid");

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.organizer = organizer;
        this.meetingStatus = MeetingStatus.CREATED;
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
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Description string.
     *
     * @return the string
     */
    public String description() {
        return description;
    }

    /**
     * gets meeting status.
     *
     * @return MeetingStatus
     */
    public MeetingStatus meetingStatus() {return meetingStatus;}

    /**
     * Sets meeting invitation.
     *
     * @param meetingInvitation the meeting invitation
     */
    public void setMeetingInvitation(List<MeetingInvitation> meetingInvitation) {
        this.meetingInvitation = meetingInvitation;
    }

    /**
     * Time period date interval.
     *
     * @return the date interval
     */


    /**
     * Organizer system user.
     *
     * @return the system user
     */
    public SystemUser organizer() {
        return organizer;
    }

    /**
     * Meeting invitation list.
     *
     * @return the list
     */
    public List<MeetingInvitation> meetingInvitation() {
        return meetingInvitation;
    }

    /**
     * Cancels a meeting only if it has status CREATED
     */
    public void cancelMeeting() {
        if (meetingStatus.equals(MeetingStatus.CREATED)) {
            this.meetingStatus = MeetingStatus.CANCELED;
        }
    }
    public Long identity() {
        return id;
    }

    private boolean isDateAfterCurrentDate(Date date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate.isAfter(currentDate);
    }

    private boolean validateMeetingTime(LocalTime startTime, LocalTime endTime) {
        if (startTime == null || endTime == null) {
            return false;
        }

        if (startTime.isAfter(endTime)) {
            return false; // Start time should be before end time
        }

        // Add any additional validation rules specific to class time

        return true;
    }

    public boolean sameAs(final Object o) {
        if (!(o instanceof Meeting)) {
            return false;
        }

        final Meeting that = (Meeting) o;
        if (this == that) {
            return true;
        }

        return identity()==that.identity() && description.equals(that.description)
                && organizer.equals(that.organizer)
                && meetingInvitation.equals(that.meetingInvitation);
    }

    @Override
    public String toString() {
        return "Meeting: " + id +
                " - " + description;
    }
}
