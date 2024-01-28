package application.base.persistence.impl.inmemory;

import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.repositories.meetingManagement.MeetingRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Date;
import java.util.Optional;

/**
 * The type In memory meeting repository.
 */
public class InMemoryMeetingRepository extends InMemoryDomainRepository<Meeting, Long>
        implements MeetingRepository {


    @Override
    public Iterable<Meeting> findAll() {
        return null;
    }

    @Override
    public Meeting findMeetingByDateAndOrganizer(SystemUser organizer, Date date, String description) {
        return null;
    }

    @Override
    public Iterable<Meeting> findMeetingOfUser(SystemUser user) {
        return null;
    }

    @Override
    public Iterable<Meeting> findMeetingsNotCanceledOfUser(SystemUser user) {
        return null;
    }

    @Override
    public Iterable<Meeting> findMeetingOfUserOnDate(SystemUser user, Date date) {
        return null;
    }

    @Override
    public Iterable<Meeting> findMeetingsAvailableToCancel(SystemUser user) {return null;}


}
