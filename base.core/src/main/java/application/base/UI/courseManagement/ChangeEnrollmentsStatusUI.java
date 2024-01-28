package application.base.UI.courseManagement;

import application.base.application.courseManagement.ChangeEnrollmentsStatusController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import application.base.domain.courseManagement.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class ChangeEnrollmentsStatusUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(ChangeEnrollmentsStatusUI.class);

    private ChangeEnrollmentsStatusController theController = new ChangeEnrollmentsStatusController();

    @Override
    protected boolean doShow() {
        System.out.println("You want to Open or Close Enrollments?");
        System.out.println("1. Open Enrollments");
        System.out.println("2. Close Enrollments");
        System.out.println("0. Exit");

        final int option = Console.readOption(1, 2, 0);

        try {
            switch (option) {
                case 1:
                    openEnrollments();
                    break;
                case 2:
                    closeEnrollments();
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

    @Override
    public String headline() {
        return "Open / Close Enrollments in a Course";
    }

    private void openEnrollments(){
        final Iterable<Course> openCoursesList = this.theController.listOpenCourses();
        if(!openCoursesList.iterator().hasNext())
            System.out.println("There are no courses available to be open!");
        else {
            Course theCourse = chooseCourse("Select a course to open the enrollments:", openCoursesList);

            if (theCourse != null) {
                Date closeDate;
                boolean validDate = false;
                while (!validDate) {
                    closeDate = Console.readDate("Insert the enrollments closing date: dd/MM/yyyy", "dd/MM/yyyy");
                    validDate = theController.validateDate(theCourse, closeDate);
                    if (!validDate){
                        System.out.printf("The closing date for enrollments should be before %s and after today!" +
                                        "\nPlease try again.\n", theCourse.closeCourseDate());
                    } else {
                        theController.openEnrollments(theCourse, closeDate);
                        System.out.printf("Operation successful. Enrollments for course %s were opened.", theCourse.courseCode());
                    }
                }
            }
        }
    }

    private void closeEnrollments(){
        final Iterable<Course> enrollCoursesList = this.theController.listEnrollCourses();
        if(!enrollCoursesList.iterator().hasNext())
            System.out.println("There are no courses available to be closed!");

        else {
            Course theCourse = chooseCourse("Select a course to close the enrollments:", enrollCoursesList);

            if (theCourse != null) {
                try {
                    theController.closeEnrollments(theCourse);
                    System.out.println("The enrollments for this course were closed!!");
                } catch (final ConcurrencyException | IntegrityViolationException exMerge) {
                    System.out.println("The Course has been changed or deleted by other user. Please try again.");
                }
            }
        }
    }


    private Course chooseCourse(String message, Iterable<Course> courseList){
        final SelectWidget<Course> selector = new SelectWidget<>(message, courseList);
        selector.show();
        return selector.selectedElement();
    }
}
