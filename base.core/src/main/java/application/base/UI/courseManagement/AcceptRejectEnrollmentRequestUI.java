package application.base.UI.courseManagement;

import application.base.UI.clientuser.AcceptRefuseSignupRequestUI;
import application.base.application.courseManagement.AcceptRejectEnrollmentRequestController;

import application.base.domain.enrollmentManagement.Enrollment;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AcceptRejectEnrollmentRequestUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptRefuseSignupRequestUI.class);

    private AcceptRejectEnrollmentRequestController controller = new AcceptRejectEnrollmentRequestController();

    @Override
    public String headline() {
        return "Accept/Reject Enrollment Request";
    }

    @Override
    protected boolean doShow() {
        Iterable<Enrollment> enrollmentRequests = controller.findEnrollmentRequests();
        if(!enrollmentRequests.iterator().hasNext()){
            System.out.println("There are no enrollment requests!");
        }
        else {
            Enrollment enrollment = chooseEnrollment("Choose an enrollment:", enrollmentRequests);

            System.out.println("1. Accept Enrollment");
            System.out.println("2. Reject Enrollment");
            System.out.println("0. Exit");

            final int option = Console.readOption(1, 2, 0);

            try {
                switch (option) {
                    case 1:
                        controller.acceptEnrollment(enrollment);
                        System.out.println("Operation successful. Enrollment was accepted.");
                        break;
                    case 2:
                        controller.rejectEnrollment(enrollment);
                        System.out.println("Operation successful. Enrollment was rejected.");
                        break;
                    default:
                        System.out.println("No valid option selected.");
                        break;
                }
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
            }
        }
        return false;
    }

    /**
     * Method used to show enrollments list and select one enrollment.
     *
     * @param message - message to be printed to the user, asking to select a course
     * @param enrollmentsList - enrollments list to choose from
     * @return course selected
     */
    private Enrollment chooseEnrollment(String message, Iterable<Enrollment> enrollmentsList){
        final SelectWidget<Enrollment> selector = new SelectWidget<>(message, enrollmentsList);
        selector.show();
        return selector.selectedElement();
    }
}
