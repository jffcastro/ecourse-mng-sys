package application.base.application.classManagement;

import application.base.domain.classManagement.CourseClass;
import application.base.domain.classManagement.CourseClassBuilder;
import application.base.domain.courseManagement.Course;
import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.classManagement.CourseClassRepository;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import application.base.usermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * The type Schedule class controller.
 */
public class ScheduleClassController {

    private CheckIfUserIsFreeService checkIfUserIsFreeService = new CheckIfUserIsFreeService();
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();
    private CourseClassRepository courseClassRepository = PersistenceContext.repositories().courseClassRepository();
    private EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();

    private MeetingRepository meetingRepository = PersistenceContext.repositories().meetingRepository();

    private MeetingInvitationRepository meetingInvitationRepository = PersistenceContext.repositories().meetingInvitationRepository();

    /**
     * List courses iterable.
     *
     * @return the iterable
     */
    public Iterable<Course> listCourses() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return courseRepository.findCourseWithEnrollStatus();
    }

    /**
     * List students by course list.
     *
     * @param course the course
     * @return the list
     */
    public List<Student> listStudentsByCourse(Course course) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return enrollmentRepository.findUsersOfCourse(course);
    }


    private int getDurationInMinutes(LocalTime startTime, LocalTime endTime) {
        return (int) startTime.until(endTime, java.time.temporal.ChronoUnit.MINUTES);
    }


    /**
     * Gets recurring dates.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param weekday   the weekday
     * @return the recurring dates
     */
    public List<Date> getRecurringDates(Date startDate, Date endDate, DayOfWeek weekday) {
        List<Date> recurringDates = new ArrayList<>();

        // Convert startDate and endDate to LocalDate objects
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Iterate through the dates between startDate and endDate
        LocalDate currentDate = startLocalDate;
        while (!currentDate.isAfter(endLocalDate)) {
            // Check if the current date is the desired weekday
            if (currentDate.getDayOfWeek() == weekday) {
                recurringDates.add(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            currentDate = currentDate.plusDays(1); // Move to the next day
        }

        return recurringDates;
    }


    /**
     * Get teacher teacher.
     *
     * @return the teacher
     */
    public void getScheduleOfTeacher() {
        SystemUser user = authorizationService.session().get().authenticatedUser();
        Teacher teacher = teacherRepository.findBySystemUser(user).orElse(null);

        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        List<CourseClass> teacherClasses = (List<CourseClass>) courseClassRepository.findClassesOfTeacher(teacher);
        List<Meeting> teacherMeetings = (List<Meeting>) meetingRepository.findMeetingOfUser(user);
        List<MeetingInvitation> teacherMeetingInvitations = (List<MeetingInvitation>) meetingInvitationRepository.findMeetingInvitationOfUserWithStatusAccepted(user);

        StringBuilder scheduleBuilder = new StringBuilder();
        appendScheduleEntries(scheduleBuilder, teacherClasses);
        appendScheduleEntries(scheduleBuilder, teacherMeetings);
        appendScheduleEntries(scheduleBuilder, teacherMeetingInvitations);

        String schedule = scheduleBuilder.toString();
        System.out.printf("%nTeacher: %s%n", teacher.identity());
        System.out.println("Teacher Schedule:");
        System.out.println(schedule);
    }

    private void appendScheduleEntries(StringBuilder scheduleBuilder, List<?> entries) {
        for (Object entry : entries) {
            String scheduleEntry = formatScheduleEntry(entry);
            scheduleBuilder.append(scheduleEntry).append(System.lineSeparator());
        }
    }

    private String formatScheduleEntry(Object entry) {
        if (entry instanceof CourseClass) {
            CourseClass courseClass = (CourseClass) entry;
            return String.format("%s | %s - %s | %s | %s", courseClass.getDateOfCourseClass(), courseClass.getStartTime(),
                    courseClass.getEndTime(), courseClass.getWeekDay(), courseClass.classTitle());
        } else if (entry instanceof Meeting) {
            Meeting meeting = (Meeting) entry;
            return String.format("%s | %s - %s | %s", meeting.getDate(), meeting.getStartTime(),
                    meeting.getEndTime(), meeting.organizer());
        } else if (entry instanceof MeetingInvitation) {
            MeetingInvitation meetingInvitation = (MeetingInvitation) entry;
            return String.format("%s | %s | %s | %s", meetingInvitation.identity(),
                    meetingInvitation.meetingInvitationStatus(), meetingInvitation.meeting().getDate(),meetingInvitation.meeting().organizer());
        }

        return "";
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
     * Create course class.
     *
     * @param teacher           the teacher
     * @param title             the title
     * @param dateOfCourseClass the date of course class
     * @param weekDay           the week day
     * @param startTime         the start time
     * @param endTime           the end time
     * @param course            the course
     * @param students          the students
     */
    public void createCourseClass(Teacher teacher, String title, Date dateOfCourseClass, String weekDay, LocalTime startTime, LocalTime endTime, Course course, List<Student> students) {

        final CourseClass newCourseClass = new CourseClassBuilder().withTeacher(teacher).withClassTitle(title).withDateOfCourseClass(dateOfCourseClass).withCourse(course).withStudents(students).
                withWeekDays(weekDay).withStartTime(startTime).withEndTime(endTime).build();

        courseClassRepository.save(newCourseClass);
    }

    /**
     * Is title valid boolean.
     *
     * @param title the title
     * @return the boolean
     */
    public boolean isTitleValid(String title) {
        int minLength = 3;
        int maxLength = 30;
        int length = title.length();

        return length >= minLength && length <= maxLength;
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
     * Check and get start date date.
     *
     * @param startDate the start date
     * @return the date
     */
    public Date checkAndGetStartDate(String startDate) {
        LocalDate currentDate = LocalDate.now();
        Date parsedStartDate = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parsedStartDate = format.parse(startDate);
            String formattedStartDate = format.format(parsedStartDate);
            if (!formattedStartDate.equals(startDate)) {
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
     * Check and get end date date.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the date
     */
    public Date checkAndGetEndDate(String startDate, String endDate) {
        Date parsedStartDate = null;
        Date parsedEndDate = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parsedStartDate = format.parse(startDate);
            parsedEndDate = format.parse(endDate);
            String formattedStartDate = format.format(parsedStartDate);
            String formattedEndDate = format.format(parsedEndDate);

            if (!formattedStartDate.equals(startDate) || !formattedEndDate.equals(endDate)) {
                // Invalid format, startDate or endDate does not match the expected format "yyyy-MM-dd"
                return null; // Or throw an exception if desired
            }
        } catch (ParseException e) {
            // Invalid format, unable to parse startDate or endDate
            return null; // Or throw an exception if desired
        }

        LocalDate localStartDate = parsedStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = parsedEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long daysDifference = ChronoUnit.DAYS.between(localStartDate, localEndDate);
        long monthsDifference = ChronoUnit.MONTHS.between(localStartDate, localEndDate);

        if (localEndDate.isAfter(localStartDate) && daysDifference >= 14 && monthsDifference <= 6) {
            return parsedEndDate;
        } else {
            return null; // Or throw an exception if desired
        }
    }

    /**
     * Gets teacher.
     *
     * @return the teacher
     */
    public Teacher getTeacher() {
        SystemUser teacher;
        if (authorizationService.session().isPresent()) {
            teacher = this.authorizationService.session().get().authenticatedUser();
            return teacherRepository.findBySystemUser(teacher).get();
        }
        return null;
    }
}
