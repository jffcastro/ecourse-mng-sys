package application.base.infrastructure.bootstrapers.demo;

import application.base.application.classManagement.ScheduleClassController;
import application.base.application.courseManagement.CreateCourseController;
import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.classManagement.CourseClassRepository;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.repositories.TeacherRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * The type Course class bootstrapper.
 */
public class CourseClassBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

    private static final CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();
    private static final EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();
    private static final ScheduleClassController scheduleClassController = new ScheduleClassController();

    private static final TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();

    @Override
    public boolean execute() {

        try {
            scheduleClassController.createCourseClass(teacherRepository.findAll().iterator().next(), "EAPLI-C1", convertStringToDate("2023-12-19"), "Monday",
                    convertStringToLocalTime("10:00","HH:mm"), convertStringToLocalTime("12:00","HH:mm"),
                    getCourseByCode("MATCP"),getStudetsOfCourse(getCourseByCode("MATCP")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Convert string to date date.
     *
     * @param dateString the date string
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }

    /**
     * Gets course by code.
     *
     * @param code the code
     * @return the course by code
     */
    public static Course getCourseByCode(String code) {
        return courseRepository.findByCode(code);
    }

    /**
     * Convert string to local time local time.
     *
     * @param timeString the time string
     * @param format     the format
     * @return the local time
     */
    public static LocalTime convertStringToLocalTime(String timeString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalTime.parse(timeString, formatter);
    }

    /**
     * Gets studets of course.
     *
     * @param course the course
     * @return the studets of course
     */
    public static List<Student> getStudetsOfCourse(Course course) {
        return enrollmentRepository.findUsersOfCourse(course);
    }
}
