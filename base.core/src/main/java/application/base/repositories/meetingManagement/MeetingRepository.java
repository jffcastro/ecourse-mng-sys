package application.base.repositories.meetingManagement;

import application.base.domain.classManagement.CourseClass;
import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.usermanagement.domain.Student;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Date;

/**
 * The interface Meeting repository.
 */
public interface MeetingRepository extends DomainRepository<Long, Meeting> {

        Iterable<Meeting> findAll();

        Meeting findMeetingByDateAndOrganizer(SystemUser organizer, Date date, String description);

        /**
         * The method below will return all the meetings created by the user in question.
         * @param user
         */
        Iterable<Meeting> findMeetingOfUser(SystemUser user);

        Iterable<Meeting> findMeetingsNotCanceledOfUser(SystemUser user);

        Iterable<Meeting> findMeetingOfUserOnDate(SystemUser user, Date date);

        /**
         * The method bellow is used in US 4002 and return the list of meetings that are available to be canceled (meetings with status CREATED)
         * @param user
         * @return list of meetings available to cancel
         */
        Iterable<Meeting> findMeetingsAvailableToCancel(SystemUser user);


}
