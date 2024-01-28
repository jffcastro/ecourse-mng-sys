package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.courseManagement.CourseStatus;
import application.base.domain.examManagement.Exam;
import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.domain.meetingManagement.MeetingInvitationStatus;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.Optional;

/**
 * The type Jpa meeting invitation repository.
 */
public class JpaMeetingInvitationRepository extends JpaAutoTxRepository<MeetingInvitation, Long, Long> implements MeetingInvitationRepository {

    /**
     * Instantiates a new Jpa meeting invitation repository.
     *
     * @param autoTx the auto tx
     */
    public JpaMeetingInvitationRepository(final TransactionalContext autoTx) {
        super(autoTx, "meetingInvitationID");
    }

    /**
     * Instantiates a new Jpa meeting repository.
     *
     * @param name the name
     */
    public JpaMeetingInvitationRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "meetingInvitationID");
    }

    @Override
    public Iterable<MeetingInvitation> findAll() {
        final TypedQuery<MeetingInvitation> query = entityManager().createQuery(
                "SELECT meetingInvitation FROM MeetingInvitation meetingInvitation",
                MeetingInvitation.class);

        return query.getResultList();
    }

    @Override
    public MeetingInvitation findMeetingInvitationByMeetingAndUser(SystemUser user, Meeting meeting) {
        final TypedQuery<MeetingInvitation> query = entityManager().createQuery(
                "SELECT meetingInvitation FROM MeetingInvitation meetingInvitation WHERE meetingInvitation.meeting = :meeting AND meetingInvitation.invitee = :user",
                MeetingInvitation.class);

        query.setParameter("meeting", meeting);
        query.setParameter("user", user);

        return query.getSingleResult();
    }

    @Override
    public Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusAccepted(SystemUser user) {
        final TypedQuery<MeetingInvitation> query = entityManager().createQuery(
                "SELECT meetingInvitation FROM MeetingInvitation meetingInvitation WHERE meetingInvitation.invitee = :user AND meetingInvitation.meetingInvitationStatus = 'ACCEPTED'",
                MeetingInvitation.class);

        query.setParameter("user", user);

        return query.getResultList();
    }

    @Override
    public Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusAcceptedOnDate(SystemUser user, Date date) {
        final TypedQuery<MeetingInvitation> query = entityManager().createQuery(
                "SELECT meetingInvitation FROM MeetingInvitation meetingInvitation WHERE meetingInvitation.invitee = :user AND meetingInvitation.meetingInvitationStatus = 'ACCEPTED' AND meetingInvitation.meeting.date = :date",
                MeetingInvitation.class);

        query.setParameter("user", user);
        query.setParameter("date", date);

        return query.getResultList();
    }

    @Override
    public Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusPending(SystemUser user) {
        final TypedQuery<MeetingInvitation> query = entityManager().createQuery(
                "SELECT meetingInvitation FROM MeetingInvitation meetingInvitation WHERE meetingInvitation.invitee = :user AND meetingInvitation.meetingInvitationStatus = 'PENDING'",
                MeetingInvitation.class);

        query.setParameter("user", user);

        return query.getResultList();
    }

    @Override
    public Iterable<MeetingInvitation> findByIdAndWithAcceptedAndRejectedStatus(Meeting meeting) {
        TypedQuery<MeetingInvitation> meetingInvitation = entityManager().createQuery(
                "SELECT meetingInvitation FROM MeetingInvitation meetingInvitation WHERE meetingInvitation.meeting = :meeting" +
                        " AND (meetingInvitation.meetingInvitationStatus = :statusA OR meetingInvitation.meetingInvitationStatus = :statusB)",
                MeetingInvitation.class);
        meetingInvitation.setParameter("meeting", meeting);
        meetingInvitation.setParameter("statusA", MeetingInvitationStatus.ACCEPTED);
        meetingInvitation.setParameter("statusB", MeetingInvitationStatus.DECLINED);
        return meetingInvitation.getResultList();
    }

    @Override
    public Iterable<MeetingInvitation> findMeetingsInvitationOfMeeting(Meeting meeting) {
         final TypedQuery<MeetingInvitation> query = entityManager().createQuery(
                "SELECT meetingInvitation FROM MeetingInvitation meetingInvitation WHERE meetingInvitation.meeting = :meeting AND (meetingInvitation.meetingInvitationStatus = :statusA OR meetingInvitation.meetingInvitationStatus = :statusP )",
                MeetingInvitation.class);

        query.setParameter("meeting", meeting);
        query.setParameter("statusA", MeetingInvitationStatus.ACCEPTED);
        query.setParameter("statusP", MeetingInvitationStatus.PENDING);
        return query.getResultList();
    }

}
