package application.base.repositories.meetingManagement;

import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Date;

public interface MeetingInvitationRepository extends DomainRepository<Long, MeetingInvitation> {

    Iterable<MeetingInvitation> findAll();

    MeetingInvitation findMeetingInvitationByMeetingAndUser(SystemUser user, Meeting meeting);

    Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusAccepted(SystemUser user);

    Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusAcceptedOnDate(SystemUser user, Date date);

    Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusPending(SystemUser user);

    /**
     * The method below will return all the meeting invites whose meeting corresponds to the one chosen by the user and
     * will only show the participants who have already responded to the invitation, that is, who have accepted or rejected it.
     * @param meeting
     */
    Iterable<MeetingInvitation> findByIdAndWithAcceptedAndRejectedStatus(Meeting meeting);

    /**
     * The method below will return all the meeting invites (with status ACCEPTED or PENDING) whose meeting corresponds to the one chosen by the user
     * @param meeting
     */
    Iterable<MeetingInvitation> findMeetingsInvitationOfMeeting(Meeting meeting);


}
