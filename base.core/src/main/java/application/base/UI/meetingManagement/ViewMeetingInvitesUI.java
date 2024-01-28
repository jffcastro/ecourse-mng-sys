package application.base.UI.meetingManagement;

import application.base.application.meetingManagement.ViewMeetingInvitesController;
import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewMeetingInvitesUI extends AbstractUI {
    private static final Logger LOGGER = LogManager.getLogger(ViewMeetingInvitesUI.class);

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private ViewMeetingInvitesController theController = new ViewMeetingInvitesController();

    @Override
    protected boolean doShow() {
        System.out.println("You want to see the participants of your meeting or meeting you were invite?");
        System.out.println("1. My meetings");
        System.out.println("2. Meetings I was invite");
        System.out.println("0. Exit");

        final int option = Console.readOption(1, 2, 0);

        try {
            switch (option) {
                case 1:
                    System.out.println("Your meetings: \n");
                    Iterable<Meeting> meetingList = theController.listMeetingOfUser(authorizationService.session().get().authenticatedUser());
                    if (!meetingList.iterator().hasNext()) {
                        System.out.println("There are no available meetings for you!");
                    } else {
                        Meeting meeting = chooseMeeting("Select a meeting to see the participants: ", meetingList);

                        Iterable<MeetingInvitation> invitationList = theController.listMeetingInvitation(meeting);
                        if (!invitationList.iterator().hasNext()) {
                            System.out.println("This meeting has no participants!");
                        } else {
                            System.out.println("\nParticipants:");
                            for (MeetingInvitation invitation : invitationList) {
                                System.out.printf("User: %s"  + ", Status: %s\n", invitation.invitee().username(), invitation.meetingInvitationStatus());
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Meeting you were invite: \n");
                    Iterable<MeetingInvitation> meetingInvitationList = theController.listMeetingWhereUserIs(authorizationService.session().get().authenticatedUser());
                    if (!meetingInvitationList.iterator().hasNext()) {
                        System.out.println("There are no available meetings for you!");
                    } else {
                        MeetingInvitation meetingInvitation = chooseMeetingInvitation("Select a meeting to see the participants: ", meetingInvitationList);

                        Iterable<MeetingInvitation> invitationList = theController.listMeetingInvitation(meetingInvitation.meeting());

                        System.out.println("\nParticipants:\n");
                        System.out.printf("Owner: %s\n", meetingInvitation.meeting().organizer().username());
                        for (MeetingInvitation invitation : invitationList) {
                            System.out.printf("User: %s"  + ", Status: %s\n", invitation.invitee().username(), invitation.meetingInvitationStatus());
                        }
                    }
                    break;
                default:
                    System.out.println("No valid option selected.");
                    break;
            }
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }
        return true;
    }

    /**
     * Method used to show meeting list and select one meeting.
     *
     * @param message - message to be printed to the user, asking to select a meeting
     * @param meetingList - meeting list to choose from
     * @return meeting selected
     */
    private Meeting chooseMeeting(String message, Iterable<Meeting> meetingList) {
        final SelectWidget<Meeting> selector = new SelectWidget<>(message, meetingList);
        selector.show();
        return selector.selectedElement();
    }

    /**
     * Method used to show meeting (invitation) list and select one meeting associated with one meeting invitation.
     *
     * @param message - message to be printed to the user, asking to select a meeting
     * @param meetingInvitationList - meeting list to choose from
     * @return meeting selected
     */
    private MeetingInvitation chooseMeetingInvitation(String message, Iterable<MeetingInvitation> meetingInvitationList) {
        final SelectWidget<MeetingInvitation> selector = new SelectWidget<>(message, meetingInvitationList);
        selector.show();
        return selector.selectedElement();
    }

    @Override
    public String headline() {
        return "View Meeting Participants";
    }
}
