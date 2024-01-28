package application.base.UI.meetingManagement;

import application.base.application.meetingManagement.CancelMeetingController;
import application.base.domain.meetingManagement.Meeting;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CancelMeetingUI extends AbstractUI {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    CancelMeetingController theController = new CancelMeetingController();

    @Override
    protected boolean doShow() {
        Iterable<Meeting> listMeetings = theController.findMeetingsAvailableToCancel(authorizationService.session().get().authenticatedUser());
        if (!listMeetings.iterator().hasNext()) {
            System.out.println("There are no available meetings for you!");
        } else {
            Meeting meeting = chooseMeeting("Select a meeting to cancel: ", listMeetings);
            if(meeting != null) {
                theController.cancelMeeting(meeting);
                theController.cancelMeetingInvitations(meeting);
                System.out.println("Meeting canceled with success!");
            }
        }
        return false;
    }

    private Meeting chooseMeeting(String message, Iterable<Meeting> meetingList) {
        final SelectWidget<Meeting> selector = new SelectWidget<>(message, meetingList);
        selector.show();
        return selector.selectedElement();
    }

    @Override
    public String headline() {
        return "Cancel Meeting";
    }
}

