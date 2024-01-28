package application.base.application.meetingManagement;

import application.base.application.classManagement.CheckIfUserIsFreeService;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class UserMeetingInvitationController {

    private CheckIfUserIsFreeService checkIfUserIsFreeService  = new CheckIfUserIsFreeService();;


    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private MeetingInvitationRepository meetingInvitationRepository = PersistenceContext.repositories().meetingInvitationRepository();

    public List<MeetingInvitation>getMeetingInvitationList() {

        return (List<MeetingInvitation>) meetingInvitationRepository.findMeetingInvitationOfUserWithStatusPending(getUser());
    }

    public SystemUser getUser() {
        SystemUser systemUser;
        if (authorizationService.session().isPresent()) {
            systemUser = this.authorizationService.session().get().authenticatedUser();
            return systemUser;
        }
        return null;
    }

    public void saveMeetingInvitation(MeetingInvitation meetingInvitation) {
        meetingInvitationRepository.save(meetingInvitation);
    }

    public boolean checkMeeting(SystemUser user, Date dateOfMeeting, LocalTime startTime, LocalTime endTime) {

        System.out.println("\nValidating Meeting/Invitation...");

        if (checkIfUserIsFreeService.checkIfUserIsNotFree(user, dateOfMeeting, startTime, endTime)) {
            return false;
        } else {
            return true;
        }

    }



}
