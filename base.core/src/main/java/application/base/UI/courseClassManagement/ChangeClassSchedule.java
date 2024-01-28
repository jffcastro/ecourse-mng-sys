package application.base.UI.courseClassManagement;

import application.base.application.classManagement.ChangeClassScheduleController;
import application.base.domain.classManagement.CourseClass;
import application.base.domain.courseManagement.Course;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The type Change class schedule.
 */
public class ChangeClassSchedule extends AbstractUI {

    /**
     * The Scanner.
     */

    private static final Logger LOGGER = LogManager.getLogger(ChangeClassSchedule.class);
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final ChangeClassScheduleController theController = new ChangeClassScheduleController();

    @Override
    protected boolean doShow() {
        try {

            Scanner scanner = new Scanner(System.in);

            LocalTime startTime = null;
            LocalTime endTime = null;

            if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)) {

                List<SystemUser> systemUserList = new ArrayList<>();

                System.out.println("Lets change the schedule of a class!");
                System.out.println("\nWe're going to need some information to find the class you want to change.");

                String title = Console.readLine("\nType the class title you want to change:");

                System.out.println("Type the date of the class you want to change: FORMAT: YYYY-MM-DD");
                String dateString = scanner.nextLine();

                Date date = theController.checkDate(dateString);

                Iterable<CourseClass> courseClass = theController.findClassOfTitleAndDate(title, date);

                if (!courseClass.iterator().hasNext()) {
                    System.out.println("\nCLASS NOT FOUND! Please try again.");
                    return false;
                } else {
                    System.out.println("\nCLASS FOUND!");
                    CourseClass theClass = courseClass.iterator().next();

                    List<Student> students = theClass.getStudents();

                    if (students.isEmpty()){
                        System.out.println("\nCLASS WAS FOUND BUT HAS NO STUDENTS ASSIGNED! Please try again.");
                        return false;
                    }

                    System.out.println("The class you want to change is: " + theClass.classTitle() + " with" +
                            theClass.getStudents().size() + " students and the teacher is " +
                            theClass.getTeacher().acronym() + ".");

                    Teacher teacher = theClass.getTeacher();

                    if (teacher == null) {
                        System.out.println("\nCLASS WAS FOUND BUT HAS NO TEACHER ASSIGNED! Please try again.");
                        return false;
                    }

                    while (true) {
                        dateString = Console.readLine("\nType the new date (FORMAT: YYYY-MM-DD), it must be after the current date:");
                        if (theController.checkAndGetDate(dateString) != null) {
                            break;
                        } else {
                            System.out.println("Invalid start date! Start date must be after the current date.");
                        }
                    }

                    while (true) {
                        startTime = readTime("\nType the start time (FORMAT: HH:mm): ");
                        endTime = readTime("\nType the end time (FORMAT: HH:mm), it must be after the start time and at max 8 hours after the start time:");
                        if (theController.isTimeRangeValid(startTime, endTime)) {
                            break;
                        } else {
                            System.out.println("Invalid time range! End time must be after the start time and the time range must be at max 8 hours, try again.");
                        }
                    }

                    Date newDate = theController.checkAndGetDate(dateString);
                    String weekDay = theController.getWeekdayFromDate(newDate);

                    systemUserList.add(teacher.user());
                    for (Student student : students) {
                        systemUserList.add(student.user());
                    }

                    if (theController.checkCourseClass(systemUserList, title, newDate, startTime, endTime)) {
                        theController.changeSchedule(theClass, newDate, weekDay, startTime, endTime);
                        System.out.println("\nCHANGED CLASS from " + date.toString() + " to " + newDate.toString() + " at " + startTime + " to " + endTime + " with success!");
                    }else {
                        System.out.println("\nCLASS NOT CHANGED!");
                        System.out.println("There is already a class scheduled for this teacher at the date , or none of the students are free at this time.");
                    }
                }
            } else {
                System.out.println("You are not authorized to perform this operation.");
                return false;
            }

        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        }

        return false;
    }


    @Override
    public String headline() {
        return "Change Class Schedule";
    }

    private LocalTime readTime(String prompt) {
        while (true) {
            String timeString = Console.readLine(prompt);
            try {
                return LocalTime.parse(timeString);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please try again.");
            }
        }
    }

}
