package application.base.persistence.impl.jpa;


import application.base.Application;
import application.base.domain.classManagement.CourseClass;
import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.domain.meetingManagement.MeetingStatus;
import application.base.repositories.examManagement.ExamRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;

/**
 * The type Jpa meeting repository.
 */
public class JpaMeetingRepository extends JpaAutoTxRepository<Meeting, Long, Long> implements MeetingRepository {

    /**
     * Instantiates a new Jpa meeting repository.
     *
     * @param autoTx the auto tx
     */
    public JpaMeetingRepository(final TransactionalContext autoTx) {
        super(autoTx, "meetingID");
    }

    /**
     * Instantiates a new Jpa meeting repository.
     *
     * @param name the name
     */
    public JpaMeetingRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "meetingID");
    }

    @Override
    public Iterable<Meeting> findAll() {
        final TypedQuery<Meeting> query = entityManager().createQuery(
                "SELECT meeting FROM Meeting meeting",
                Meeting.class);

        return query.getResultList();
    }

    @Override
    public Meeting findMeetingByDateAndOrganizer(SystemUser organizer, Date date, String description) {
        final TypedQuery<Meeting> query = entityManager().createQuery(
                "SELECT meeting FROM Meeting meeting WHERE meeting.organizer = :organizer AND meeting.date = :date AND meeting.description = :description",
                Meeting.class);

        query.setParameter("organizer", organizer);
        query.setParameter("date", date);
        query.setParameter("description", description);

        return query.getSingleResult();
    }

    @Override
    public Iterable<Meeting> findMeetingOfUser(SystemUser user) {
        final TypedQuery<Meeting> query = entityManager().createQuery(
                "SELECT meeting FROM Meeting meeting WHERE meeting.organizer = :user",
                Meeting.class);

        query.setParameter("user", user);

        return query.getResultList();
    }

    @Override
    public Iterable<Meeting> findMeetingsNotCanceledOfUser(SystemUser user) {
        final TypedQuery<Meeting> query = entityManager().createQuery(
                "SELECT meeting FROM Meeting meeting WHERE meeting.organizer = :user AND meeting.meetingStatus != :meetingStatus",
                Meeting.class);

        query.setParameter("user", user);
        query.setParameter("meetingStatus", MeetingStatus.CANCELED);

        return query.getResultList();
    }

    @Override
    public Iterable<Meeting> findMeetingOfUserOnDate(SystemUser user, Date date) {
        final TypedQuery<Meeting> query = entityManager().createQuery(
                "SELECT meeting FROM Meeting meeting WHERE meeting.organizer = :user AND meeting.date = :date",
                Meeting.class);

        query.setParameter("user", user);
        query.setParameter("date", date);

        return query.getResultList();
    }

    @Override
    public Iterable<Meeting> findMeetingsAvailableToCancel(SystemUser user) {
        final TypedQuery<Meeting> query = entityManager().createQuery(
                "SELECT meeting FROM Meeting meeting" +
                        " WHERE meeting.organizer = :user" +
                        " AND meetingStatus = :meetingStatus" +
                        " AND (meeting.date > :currentDate" +
                        " OR (meeting.date = :currentDate AND meeting.startTime > :currentTime))",
                Meeting.class);
        query.setParameter("user", user);
        query.setParameter("meetingStatus", MeetingStatus.CREATED);
        query.setParameter("currentDate", Date.from(Instant.now()));
        query.setParameter("currentTime", LocalTime.now());
        return query.getResultList();
    }


}
