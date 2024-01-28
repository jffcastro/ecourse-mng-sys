package application.base.application.meetingManagement;

import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.transaction.annotation.Transactional;

public class ViewMeetingInvitesController {
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private MeetingRepository meetingRepository = PersistenceContext.repositories().meetingRepository();

    private MeetingInvitationRepository meetingInvitationRepository = PersistenceContext.repositories().meetingInvitationRepository();

    /**
     * This method returns the list of meetings that the user created.
     *
     * @param user the user who created the meeting
     * @return list of all meetings belonging to the user
     */
    @Transactional
    public Iterable<Meeting> listMeetingOfUser(SystemUser user) {
        return meetingRepository.findMeetingsNotCanceledOfUser(user);
    }


    /**
     * This method returns the list of meeting invitations with accept status of the user
     * @param user
     */
    @Transactional
    public Iterable<MeetingInvitation> listMeetingWhereUserIs(SystemUser user) {
        return meetingInvitationRepository.findMeetingInvitationOfUserWithStatusAccepted(user);
    }


    /**
     * This method returns the list of participants of a meeting that the user created.
     * Attendees whose invitation status is accepted or rejected will be shown.
     *
     * @param meeting the meeting that he wants to see the list
     * @return list of participants in the meeting and their status (accepted or declined)
     */
    @Transactional
    public Iterable<MeetingInvitation> listMeetingInvitation(Meeting meeting) {
        return meetingInvitationRepository.findByIdAndWithAcceptedAndRejectedStatus(meeting);
    }
}
