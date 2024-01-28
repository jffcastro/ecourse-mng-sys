package application.base.UI.courseManagement;

import application.base.application.courseManagement.CreateCourseController;
import application.base.domain.courseManagement.CourseName;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateCourseUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(CreateCourseUI.class);

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();;

    private CreateCourseController theController = new CreateCourseController();

    @Override
    protected boolean doShow() {
        String courseCode = null;
        boolean courseCodeValid = false;
        while (!courseCodeValid) {
            courseCode = Console.readLine("Course Code: ");
            courseCodeValid = theController.validateCourseCode(courseCode);
            if (!courseCodeValid) {
                System.out.println("Course code must have more than 3 characters and less than 10!");
            }
        }

        CourseName courseName = null;
        boolean courseNameValid = false;
        while (!courseNameValid) {
            courseName = CourseName.valueOf(Console.readLine("Course Name: "));
            courseNameValid = theController.validateCourseName(CourseName.valueOf(String.valueOf(courseName)));
            if (!courseNameValid) {
                System.out.println("Course name must have more than 3 characters and less than 30!");
            }
        }

        String description = null;
        boolean descriptionValid = false;
        while (!descriptionValid) {
            description = Console.readLine("Course Description: ");
            descriptionValid = theController.validateCourseDescription(description);
            if (!descriptionValid) {
                System.out.println("Course description must have more than 10 characters and less than 150!");
            }
        }

        Integer minStudents = null;
        boolean minStudentsValid = false;
        while (!minStudentsValid) {
            minStudents = Console.readInteger("Minimum students: ");
            minStudentsValid = theController.validateMinStudents(minStudents);
            if (!minStudentsValid) {
                System.out.println("You have to have a positive number of minimum students!");
            }
        }

        Integer maxStudents = null;
        boolean maxStudentsValid = false;
        while (!maxStudentsValid) {
            maxStudents = Console.readInteger("Maximum Students: ");
            maxStudentsValid = theController.validateMaxStudents(minStudents, maxStudents);

            if (!maxStudentsValid) {
                System.out.println("You have to have a positive number of maximum students and it can be less than the minimum students!");
            }
        }

        try {
            theController.createCourse(courseCode, courseName, description, minStudents, maxStudents);
            System.out.println("Course created!!");
        } catch (final IntegrityViolationException e) {
            System.out.println("That course already exists!");
        } catch (final ConcurrencyException e) {
            LOGGER.error("Error performing the operation", e);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Create a course";
    }
}