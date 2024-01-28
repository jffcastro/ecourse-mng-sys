package application.base.application.meetingManagement;

import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CancelMeetingController {
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private MeetingInvitationRepository meetingInvitationRepository = PersistenceContext.repositories().meetingInvitationRepository();
    private MeetingRepository meetingRepository = PersistenceContext.repositories().meetingRepository();

    public Iterable<Meeting> findMeetingsAvailableToCancel(SystemUser user){
        return meetingRepository.findMeetingsAvailableToCancel(user);
    }

    public void cancelMeeting(Meeting meeting){
        meeting.cancelMeeting();
        meetingRepository.save(meeting);
    }

    public void cancelMeetingInvitations(Meeting meeting) {
        Iterable<MeetingInvitation> listMeetingsInvitation = meetingInvitationRepository.findMeetingsInvitationOfMeeting(meeting);
        for (MeetingInvitation meetingInvitation: listMeetingsInvitation){
            meetingInvitation.changeMeetingInvitationStatusToCanceled(meetingInvitation);
            meetingInvitationRepository.save(meetingInvitation);
        }
    }
}
