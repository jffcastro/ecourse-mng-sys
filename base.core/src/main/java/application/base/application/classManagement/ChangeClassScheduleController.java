package application.base.application.classManagement;

import application.base.domain.classManagement.CourseClass;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.classManagement.CourseClassRepository;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Teacher;
import application.base.usermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;


/**
 * The type Change class schedule controller.
 */
public class ChangeClassScheduleController {

    private CheckIfUserIsFreeService checkIfUserIsFreeService = new CheckIfUserIsFreeService();
    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();
    private CourseClassRepository courseClassRepository = PersistenceContext.repositories().courseClassRepository();
    private EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    /**
     * Find class of title and date iterable.
     *
     * @param title the title
     * @param date  the date
     * @return the iterable
     */
    public Iterable<CourseClass> findClassOfTitleAndDate(String title, Date date) {
        return courseClassRepository.findClassOfTitleAndDate(title, date);
    }

    /**
     * Convert string to date date.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date checkDate(String date) {
        LocalDate currentDate = LocalDate.now();
        Date parsedStartDate = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parsedStartDate = format.parse(date);
            String formattedStartDate = format.format(parsedStartDate);
            if (!formattedStartDate.equals(date)) {
                // Invalid format, startDate does not match the expected format "YYYY-MM-DD"
                return null; // Or throw an exception if desired
            }
        } catch (ParseException e) {
            // Invalid format, unable to parse startDate
            return null; // Or throw an exception if desired
        }

        return parsedStartDate;

    }

    /**
     * Is time range valid boolean.
     *
     * @param startTime the start time
     * @param endTime   the end time
     * @return the boolean
     */
    public boolean isTimeRangeValid(LocalTime startTime, LocalTime endTime) {
        return endTime.isAfter(startTime) && Duration.between(startTime, endTime).toHours() <= 8;

    }

    /**
     * Check and get date date.
     *
     * @param date the date
     * @return the date
     */
    public Date checkAndGetDate(String date) {
        LocalDate currentDate = LocalDate.now();
        Date parsedStartDate = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parsedStartDate = format.parse(date);
            String formattedStartDate = format.format(parsedStartDate);
            if (!formattedStartDate.equals(date)) {
                // Invalid format, startDate does not match the expected format "YYYY-MM-DD"
                return null; // Or throw an exception if desired
            }
        } catch (ParseException e) {
            // Invalid format, unable to parse startDate
            return null; // Or throw an exception if desired
        }

        if (parsedStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(currentDate)) {
            return parsedStartDate;
        } else {
            return null; // Or throw an exception if desired
        }
    }

    /**
     * Get teacher teacher.
     *
     * @return the teacher
     */
    Teacher getTeacher() {
        SystemUser teacher;
        if (authorizationService.session().isPresent()) {
            teacher = this.authorizationService.session().get().authenticatedUser();
            return teacherRepository.findBySystemUser(teacher).get();
        }
        return null;
    }

    /**
     * Change schedule.
     *
     * @param courseClass  the course class
     * @param newDate      the new date
     * @param weekDay      the week day
     * @param newStartTime the new start time
     * @param newEndTime   the new end time
     */
    public void changeSchedule(CourseClass courseClass, Date newDate, String weekDay, LocalTime newStartTime, LocalTime newEndTime) {

        courseClass.setDateOfCourseClass(newDate);
        courseClass.setStartTime(newStartTime);
        courseClass.setEndTime(newEndTime);
        courseClass.setWeekDay(weekDay);

        courseClassRepository.save(courseClass);
    }

    /**
     * Check course class boolean.
     *
     * @param users             the users
     * @param title             the title
     * @param dateOfCourseClass the date of course class
     * @param startTime         the start time
     * @param endTime           the end time
     * @return the boolean
     */
    public boolean checkCourseClass(List<SystemUser> users, String title, Date dateOfCourseClass, LocalTime startTime, LocalTime endTime) {

        System.out.println("\nValidating Class");

        List<SystemUser> removedUsers = new ArrayList<>();

        int counter = 0;

        Iterator<SystemUser> iterator = users.iterator();
        while (iterator.hasNext()) {
            SystemUser user = iterator.next();

            if (user.hasAll(BaseRoles.TEACHER) && checkIfUserIsFreeService.checkIfUserIsNotFree(user, dateOfCourseClass, startTime, endTime)) {
                return false;
            } else if (user.hasAll(BaseRoles.STUDENT) && checkIfUserIsFreeService.checkIfUserIsNotFree(user, dateOfCourseClass, startTime, endTime)) {
                iterator.remove(); // Remove the user using the iterator
                removedUsers.add(user);
                counter++;
            }
        }

        if (users.size() == 1 && users.get(0).hasAll(BaseRoles.TEACHER)) {
            System.out.println("\nThere are no students available for the Class with title " + title + " on " + dateOfCourseClass + " at " + startTime + " - " + endTime + ".");
            return false;
        }

        if (counter > 0) {
            System.out.println("\nNumber of students removed from the Class with title " + title + " on " + dateOfCourseClass + " is " + counter + " students.");
            System.out.println("\nRemoved students:");
            for (SystemUser user : removedUsers) {

                System.out.printf("User identity: %s, User username: %s, User name: %s%n", user.identity(), user.username(), user.name());
            }
        }

        return true;
    }

    /**
     * Gets weekday from date.
     *
     * @param date the date
     * @return the weekday from date
     */
    public String getWeekdayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "SUNDAY";
            case Calendar.MONDAY:
                return "MONDAY";
            case Calendar.TUESDAY:
                return "TUESDAY";
            case Calendar.WEDNESDAY:
                return "WEDNESDAY";
            case Calendar.THURSDAY:
                return "THURSDAY";
            case Calendar.FRIDAY:
                return "FRIDAY";
            case Calendar.SATURDAY:
                return "SATURDAY";
            default:
                return "Invalid day";
        }
    }
}
