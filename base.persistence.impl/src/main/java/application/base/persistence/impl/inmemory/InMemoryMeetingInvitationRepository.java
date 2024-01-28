package application.base.persistence.impl.inmemory;

import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Date;

public class InMemoryMeetingInvitationRepository extends InMemoryDomainRepository<MeetingInvitation, Long>
implements MeetingInvitationRepository {

    @Override
    public Iterable<MeetingInvitation> findAll(){
        return null;
    }

    @Override
    public MeetingInvitation findMeetingInvitationByMeetingAndUser(SystemUser user, Meeting meeting) {
        return null;
    }

    @Override
    public Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusAccepted(SystemUser user) {
        return null;
    }

    @Override
    public Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusAcceptedOnDate(SystemUser user, Date date) {
        return null;
    }

    @Override
    public Iterable<MeetingInvitation> findMeetingInvitationOfUserWithStatusPending(SystemUser user) {
        return null;
    }

    @Override
    public Iterable<MeetingInvitation> findByIdAndWithAcceptedAndRejectedStatus(Meeting inviteeID) {
        return null;
    }


    @Override
    public Iterable<MeetingInvitation> findMeetingsInvitationOfMeeting(Meeting meeting) {return null;}

}
