package application.base.UI.meetingManagement;

import application.base.application.meetingManagement.UserMeetingInvitationController;
import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.domain.meetingManagement.MeetingInvitationStatus;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class UserMeetingInvitationUI extends AbstractUI {

    private UserMeetingInvitationController theController = new UserMeetingInvitationController();

    private static final Logger LOGGER = LogManager.getLogger(UserMeetingInvitationUI.class);
    @Override
    protected boolean doShow() {

        List<MeetingInvitation> meetingInvitationList = null;
        meetingInvitationList = theController.getMeetingInvitationList();

        if (!meetingInvitationList.isEmpty()) {
            System.out.println("You have the following meeting invitations:");
            for (int i = 0; i < meetingInvitationList.size(); i++) {
                MeetingInvitation meetingInvitation = meetingInvitationList.get(i);
                Meeting meeting = meetingInvitation.meeting();

                System.out.println("Invitation ID: " + meetingInvitation.identity());
                System.out.println("Meeting: " + meeting.identity() + " - " + meeting.description());
                System.out.println("Organizer: " + meeting.organizer().username());
                System.out.println("Start Date: " + meeting.getDate());
                System.out.println("Start Time: " + meeting.getStartTime());
                System.out.println("End Time: " + meeting.getEndTime());
                System.out.println("Status: " + meetingInvitation.meetingInvitationStatus());
                System.out.println("--------------------------------------------------");
            }

            // Prompt for invitation selection
            System.out.print("Enter the Invitation ID to accept or decline: ");
            Scanner scanner = new Scanner(System.in);
            String invitationId = scanner.nextLine();

            MeetingInvitation selectedInvitation = null;
            for (MeetingInvitation invitation : meetingInvitationList) {
                if (invitation.identity().toString().equals(invitationId)) {
                    selectedInvitation = invitation;
                    break;
                }
            }

            if (selectedInvitation != null) {
                // Prompt for status update
                System.out.println("Select an option:");
                System.out.println("1. Accept");
                System.out.println("2. Decline");
                System.out.print("Enter the option number: ");
                String optionInput = scanner.nextLine();

                MeetingInvitationStatus status = MeetingInvitationStatus.PENDING;

                if (optionInput.equals("1")) {
                    status = MeetingInvitationStatus.ACCEPTED;
                } else if (optionInput.equals("2")) {
                    status = MeetingInvitationStatus.DECLINED;
                } else {
                    System.out.println("Invalid option.");
                    return false;
                }

                if (status == MeetingInvitationStatus.ACCEPTED) {
                    // Check if user is free
                    if (theController.checkMeeting(theController.getUser(), selectedInvitation.meeting().getDate(), selectedInvitation.meeting().getStartTime(), selectedInvitation.meeting().getEndTime())) {
                        selectedInvitation.setMeetingInvitationStatus(status);
                        theController.saveMeetingInvitation(selectedInvitation);
                        System.out.println("Invitation status updated successfully to " + status + ".");
                    } else {
                        System.out.println("You are not free at the selected time.");
                    }
                } else {
                    selectedInvitation.setMeetingInvitationStatus(status);
                    theController.saveMeetingInvitation(selectedInvitation);
                    System.out.println("Invitation status updated successfully to " + status + ".");
                }


            } else {
                System.out.println("Invalid invitation ID.");
            }


        } else {
            System.out.println("You have no meeting invitations.");
            return false;
        }

        return true;
    }


    @Override
    public String headline() {
        return "User Meeting Invitation";
    }


}
