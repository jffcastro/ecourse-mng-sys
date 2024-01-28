package application.base.UI.courseManagement;

import application.base.UI.clientuser.AcceptRefuseSignupRequestUI;
import application.base.application.courseManagement.OpenCloseCourseController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import application.base.domain.courseManagement.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class OpenCloseCourseUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptRefuseSignupRequestUI.class);

    private OpenCloseCourseController controller = new OpenCloseCourseController();

    @Override
    public String headline() {
        return "Open / Close Courses";
    }

    @Override
    protected boolean doShow() {
        System.out.println("1. Open Course");
        System.out.println("2. Close Course");
        System.out.println("0. Exit");

        final int option = Console.readOption(1, 2, 0);

        try {
            switch (option) {
                case 1:
                    openCourse();
                    break;
                case 2:
                    closeCourse();
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
        return true;
    }

    private void openCourse() {
        final Iterable<Course> openCoursesList = controller.findCoursesAvailableToOpen();

        if (!openCoursesList.iterator().hasNext())
            System.out.println("There are no courses available to be opened.!");
        else {
            Course course = chooseCourse("Select a course to open:", openCoursesList);

            if (course != null) {
                boolean validDate = false;
                while (!validDate) {
                    final Date closeDate = Console.readDate("Enter close date for course selected: dd/MM/yyyy", "dd/MM/yyyy");

                    validDate = controller.validateDate(closeDate);
                    if (!validDate) {
                        System.out.println("Close course date must be later than today.");
                    } else {
                        controller.openCourse(course, closeDate);
                        System.out.println("Course successfuly opened.");
                    }
                }
            }
        }
    }

    private void closeCourse() {
        final Iterable<Course> closedCoursesList = controller.findCoursesAvailableToClose();

        if (!closedCoursesList.iterator().hasNext())
            System.out.println("There are no courses available to be closed.!");
        else {
            Course theCourse = chooseCourse("Choose a course to close:", closedCoursesList);

            if (theCourse != null) {
                controller.closeCourse(theCourse);
                System.out.println("Course successfuly closed.");
            }
        }
    }

    /**
     * Method used to show course list and select one course.
     *
     * @param message - message to be printed to the user, asking to select a course
     * @param courseList - course list to choose from
     * @return course selected
     */
    private Course chooseCourse(String message, Iterable<Course> courseList) {
        final SelectWidget<Course> selector = new SelectWidget<>(message, courseList);
        selector.show();
        return selector.selectedElement();
    }

}