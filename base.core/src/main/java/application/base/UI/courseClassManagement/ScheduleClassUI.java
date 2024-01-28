package application.base.UI.courseClassManagement;

import application.base.application.classManagement.ScheduleClassController;
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
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * The type Schedule class ui.
 */
public class ScheduleClassUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(ScheduleClassUI.class);

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final ScheduleClassController theController = new ScheduleClassController();


    @Override
    protected boolean doShow() {
        try {

            List<SystemUser> systemUserList = new ArrayList<>();

            Course theCourse = null;
            String title = null;
            String startDate = null;
            String endDate = null;
            LocalTime startTime = null;
            LocalTime endTime = null;

            if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)) {
                System.out.println("Lets schedule a new recurrent class!");

                Teacher teacher = theController.getTeacher();

                if (teacher == null) {
                    System.out.println("Login as a teacher to perform this action.");
                    return false;
                }

                System.out.println("The teacher of this class is going to be: " + teacher.acronym());


                Iterable<Course> coursesList = theController.listCourses();

                if (coursesList == null) {
                    System.out.println("There are no courses available. Please try again.");
                    return false;
                }

                theCourse = chooseCourse(coursesList);

                if (theCourse == null) {
                    System.out.println("Null Value received. Please try again.");
                    return false;
                }

                List<Student> studentList = theController.listStudentsByCourse(theCourse);

                if (studentList.isEmpty()) {
                    System.out.println("There are no students in this course. Please try again.");
                    return false;
                }

                while (true) {
                    title = Console.readLine("Type the class title, it must be between 3 and 30 characters. ");
                    if (theController.isTitleValid(title)) {
                        break;
                    } else {
                        System.out.println("Invalid title! Title must be between 3 and 30 characters.");
                    }
                }

                theController.getScheduleOfTeacher();

                String weekDay = getUserWeekdayInput();

                while (true) {
                    startDate = Console.readLine("\nType the start date (FORMAT: YYYY-MM-DD), it must be after the current date:");
                    if (theController.checkAndGetStartDate(startDate) != null) {
                        break;
                    } else {
                        System.out.println("Invalid start date! Start date must be after the current date.");
                    }
                }

                while (true) {
                    endDate = Console.readLine("\nType the end date (FORMAT: YYYY-MM-DD), it must be at least 14 days after the start date and at max 6 months after the start date:");
                    if (theController.checkAndGetEndDate(startDate, endDate)!= null) {
                        break;
                    } else {
                        System.out.println("Invalid end date! End date must be after the start date.");
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

                Date startDateCourseClass = theController.checkAndGetStartDate(startDate);
                Date endDateCourseClass = theController.checkAndGetEndDate(startDate, endDate);

                List<Date> recurringDates = theController.getRecurringDates(startDateCourseClass, endDateCourseClass, DayOfWeek.valueOf(weekDay));

                if (recurringDates.isEmpty()) {
                    System.out.println("There are no dates available for this time range. Please try again.");
                    return false;
                }

                systemUserList.add(teacher.user());
                for (Student student : studentList) {
                    systemUserList.add(student.user());
                }

                for (Date date : recurringDates) {
                    if(theController.checkCourseClass(systemUserList, title, date, startTime,endTime)){
                        theController.createCourseClass(teacher, title, date, weekDay, startTime, endTime, theCourse, studentList);
                        System.out.println("\nCREATED CLASS at date: " + date.toString());
                    } else {
                        System.out.println("\nIGNORED CLASS at date: " + date.toString());
                        System.out.println("There is already a class or meeting scheduled for this teacher at the date, or none of the students are free at this time.");
                    }
                }

            } else {
                System.out.println("You are not authorized to perform this action.");
                return false;
            }
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        }
        return true;
    }


    private Course chooseCourse(Iterable<Course> courseList) {
        final SelectWidget<Course> selector = new SelectWidget<>("Select a course to link to this class:", courseList);
        selector.show();
        return selector.selectedElement();
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

    /**
     * Gets user weekday input.
     *
     * @return the user weekday input
     */
    public static String getUserWeekdayInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect a weekday:");
        System.out.println("1. Monday");
        System.out.println("2. Tuesday");
        System.out.println("3. Wednesday");
        System.out.println("4. Thursday");
        System.out.println("5. Friday");
        System.out.println("6. Saturday");
        System.out.println("7. Sunday");
        System.out.print("Enter the number: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after reading the number

        String weekday;
        switch (choice) {
            case 1:
                weekday = "MONDAY";
                break;
            case 2:
                weekday = "TUESDAY";
                break;
            case 3:
                weekday = "WEDNESDAY";
                break;
            case 4:
                weekday = "THURSDAY";
                break;
            case 5:
                weekday = "FRIDAY";
                break;
            case 6:
                weekday = "SATURDAY";
                break;
            case 7:
                weekday = "SUNDAY";
                break;
            default:
                weekday = "Invalid weekday";
        }

        return weekday;
    }




    @Override
    public String headline() {
        return "Schedule a new recurrent class";
    }
}
