
package application.base.application.meetingManagement;

import application.base.application.classManagement.CheckIfUserIsFreeService;
import application.base.domain.meetingManagement.*;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;

/**
 * The type Schedule meeting controller.
 */
public class ScheduleMeetingController {

    private MeetingRepository meetingRepository = PersistenceContext.repositories().meetingRepository();
    private MeetingInvitationRepository meetingInvitationRepository = PersistenceContext.repositories().meetingInvitationRepository();
    private TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();

    private UserRepository userRepository = PersistenceContext.repositories().userRepository();

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private CheckIfUserIsFreeService checkIfUserIsFreeService  = new CheckIfUserIsFreeService();;


    /**
     * Create meeting meeting.
     *
     * @param description the description
     * @param date        the date
     * @param startTime   the start time
     * @param endTime     the end time
     * @param organizer   the organizer
     * @throws ParseException the parse exception
     */
    public void createMeeting(String description, Date date, LocalTime startTime, LocalTime endTime, SystemUser organizer) throws ParseException {

        Meeting newMeeting = new MeetingBuilder().withOrganizer(organizer).withDescription(description)
                .withDate(date).withStartTime(startTime).withEndTime(endTime).build();

        meetingRepository.save(newMeeting);

    }

    /**
     * Check meeting boolean.
     *
     * @param user          the user
     * @param dateOfMeeting the date of meeting
     * @param startTime     the start time
     * @param endTime       the end time
     * @return the boolean
     */
    public boolean checkMeeting(SystemUser user, Date dateOfMeeting, LocalTime startTime, LocalTime endTime) {

        System.out.println("\nValidating Meeting/Invitation...");

        if (checkIfUserIsFreeService.checkIfUserIsNotFree(user, dateOfMeeting, startTime, endTime)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Is description valid boolean.
     *
     * @param description the description
     * @return the boolean
     */
    public boolean isDescriptionValid(String description) {
        int minLength = 10;
        int maxLength = 60;
        int length = description.length();

        return length >= minLength && length <= maxLength;
    }

    /**
     * Check and get date date.
     *
     * @param date the date
     * @return the date
     */
    public Date checkAndGetDate(String date) {
        LocalDate currentDate = LocalDate.now();
        Date parsedStartDate = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parsedStartDate = format.parse(date);
            String formattedStartDate = format.format(parsedStartDate);
            if (!formattedStartDate.equals(date)) {
                // Invalid format, startDate does not match the expected format "YYYY-MM-DD"
                return null; // Or throw an exception if desired
            }
        } catch (ParseException e) {
            // Invalid format, unable to parse startDate
            return null; // Or throw an exception if desired
        }

        if (parsedStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(currentDate)) {
            return parsedStartDate;
        } else {
            return null; // Or throw an exception if desired
        }
    }

    /**
     * Get meeting with organizer and date meeting.
     *
     * @param organizer   the organizer
     * @param date        the date
     * @param description the description
     * @return the meeting
     */
    public Meeting getMeetingWithOrganizerAndDate(SystemUser organizer, Date date, String description){
        return meetingRepository.findMeetingByDateAndOrganizer(organizer, date, description);
    }

    /**
     * Get meeting invitation with meeting and invitee meeting invitation.
     *
     * @param meeting the meeting
     * @param invitee the invitee
     * @return the meeting invitation
     */
    public MeetingInvitation getMeetingInvitationWithMeetingAndInvitee(Meeting meeting, SystemUser invitee){
        return meetingInvitationRepository.findMeetingInvitationByMeetingAndUser(invitee, meeting);
    }

    /**
     * Create meeting invitation meeting invitation.
     *
     * @param meeting                 the meeting
     * @param meetingInvitationStatus the meeting invitation status
     * @param user                    the user
     */
    @Transactional
    public void createMeetingInvitation(Meeting meeting, MeetingInvitationStatus meetingInvitationStatus, SystemUser user){

        final MeetingInvitation meetingInvitation = new MeetingInvitationBuilder().withMeeting(meeting)
                .withMeetingInvitationStatus(meetingInvitationStatus).withInvitee(user).build();

        meetingInvitationRepository.save(meetingInvitation);

    }

    /**
     * Convert string to date date.
     *
     * @param dateString the date string
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }

    /**
     * Is time range valid boolean.
     *
     * @param startTime the start time
     * @param endTime   the end time
     * @return the boolean
     */
    public boolean isTimeRangeValid(LocalTime startTime, LocalTime endTime) {
        return endTime.isAfter(startTime) && Duration.between(startTime, endTime).toHours() <= 4;
    }

    /**
     * Get users iterable.
     *
     * @return the iterable
     */
    public Iterable<SystemUser> getUsers(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.MANAGER,BaseRoles.STUDENT,BaseRoles.POWER_USER);
        return userRepository.findAll();
    }

    /**
     * Get user system user.
     *
     * @return the system user
     */
    public SystemUser getUser(){
        SystemUser systemUser;
        if (authorizationService.session().isPresent()) {
            systemUser = this.authorizationService.session().get().authenticatedUser();
            return systemUser;
        }
        return null;
    }

    /**
     * Change meeting invitations.
     *
     * @param meeting     the meeting
     * @param invitations the invitations
     */
    public void changeMeetingInvitations(Meeting meeting, List<MeetingInvitation> invitations){
        meeting.setMeetingInvitation(invitations);
        meetingRepository.save(meeting);
    }
}
