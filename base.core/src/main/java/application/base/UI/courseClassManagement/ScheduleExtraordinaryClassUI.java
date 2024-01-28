package application.base.UI.courseClassManagement;

import application.base.application.classManagement.ScheduleExtraordinaryClassController;
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
import eapli.framework.presentation.console.SelectWidget;
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
 * The type Schedule extraordinary class ui.
 */
public class ScheduleExtraordinaryClassUI extends AbstractUI {

    /**
     * The Scanner.
     */


    /**
     * The Extraordinary students.
     */

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final ScheduleExtraordinaryClassController theController = new ScheduleExtraordinaryClassController();

    private static final Logger LOGGER = LogManager.getLogger(ScheduleExtraordinaryClassUI.class);

    @Override
    protected boolean doShow() {
        try {

            Scanner scanner = new Scanner(System.in);

            List<SystemUser> systemUserList = new ArrayList<>();
            List<Student> extraordinaryStudents = new ArrayList<>();

            Course theCourse = null;
            String title = null;
            String dateString = null;
            LocalTime startTime = null;
            LocalTime endTime = null;


            if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)) {
                System.out.println("Lets schedule a new extraordinary class!");

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
                    System.out.println("There are no courses available. Please try again.");
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

                while (true) {
                    dateString = Console.readLine("\nType the date (FORMAT: YYYY-MM-DD), it must be after the current date:");
                    if (theController.checkAndGetDate(dateString) != null) {
                        break;
                    } else {
                        System.out.println("Invalid date! Date must be after the current date.");
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

                Date date = theController.checkAndGetDate(dateString);
                String weekDay = theController.getWeekdayFromDate(date);

                boolean keepAddingStudents = true;

                while (keepAddingStudents) {
                    System.out.println("Select an option:");
                    System.out.println("1. Add a student");
                    System.out.println("0. Close the loop");

                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (option) {
                        case 1:
                            Student theStudent = chooseStudent(studentList);
                            if (theStudent != null) {
                                extraordinaryStudents.add(theStudent);
                                studentList.remove(theStudent); // Remove the selected student from the original list
                            }
                            break;
                        case 0:
                            keepAddingStudents = false;
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                }

                if (extraordinaryStudents.isEmpty()) {
                    System.out.println("No added students found. Please try again.");
                    return false;
                }

                systemUserList.add(teacher.user());
                for (Student student : extraordinaryStudents) {
                    systemUserList.add(student.user());
                }

                if (theController.checkCourseClass(systemUserList, title, date, startTime,endTime)) {
                    theController.createCourseClass(teacher, title, date, weekDay, startTime, endTime, theCourse, extraordinaryStudents);
                    System.out.println("\nCREATED EXTRAORDINARY CLASS at date: " + date.toString() + " with the following students:");
                    for (Student student : extraordinaryStudents) {
                        System.out.println(student.user().identity());
                    }
                } else {
                    System.out.println("\nIGNORED EXTRAORDINARY CLASS at date: " + date.toString());
                    System.out.println("There is already a class or meeting scheduled for this teacher at the date , or none of the students are free at this time.");
                }
            } else {
                System.out.println("You are not authorized to perform this action");
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
        final SelectWidget<Course> selector = new SelectWidget<>("Select a course to link to the course class:", courseList);
        selector.show();
        return selector.selectedElement();
    }

    private Student chooseStudent(List<Student> studentList) {
        List<Student> availableStudents = new ArrayList<>(studentList);

        while (!availableStudents.isEmpty()) {
            System.out.println("Select a student to add to the class:");
            for (int i = 0; i < availableStudents.size(); i++) {
                Student student = availableStudents.get(i);
                System.out.printf("%d. Student ID: %s, Name: %s, Email: %s, Role: %s\n",
                        (i + 1), student.identity(), student.user().name(), student.user().email(), student.user().roleTypes());
            }
            int option = Console.readOption(1, availableStudents.size() + 1, 0); // Allow cancel option
            if (option == 0) {
                return null;
            } else if (option <= availableStudents.size()) {
                return availableStudents.get(option - 1);
            }
        }
        return null;
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

    @Override
    public String headline() {
        return "Schedule a new extraordinary class";
    }

}
