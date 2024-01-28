package application.base.courseManagement.domain;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.courseManagement.CourseStatus;
import application.base.usermanagement.domain.*;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseTest {

    private static final Logger LOGGER = LogManager.getLogger(CourseTest.class);

    final String courseCode = "TEST-1";
    final CourseName courseName = new CourseName("Test Course");
    final String description = "This is a open enrollments in a course test ";
    final Integer minStudents = 15;
    final Integer maxStudents = 25;

    //Course
    CourseBuilder builder = new CourseBuilder();
    Course course = builder
            .withCode(courseCode)
            .withName(courseName)
            .withDescription(description)
            .hasMinStudents(minStudents)
            .hasMaxStudents(maxStudents)
            .build();

    Course course1 = builder
            .withCode(courseCode)
            .withName(courseName)
            .withDescription(description)
            .hasMinStudents(minStudents)
            .hasMaxStudents(maxStudents)
            .build();

    //Teacher
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    final SystemUser teacherBuilder = userBuilder
            .withUsername("Teacher")
            .withEmail("Teacher@email.com")
            .withPassword("Password1")
            .withName("TeacherFstName", "TeacherLstName")
            .withRoles(BaseRoles.TEACHER)
            .build();
    Teacher teacher = new TeacherBuilder()
            .withSystemUser(teacherBuilder)
            .withAcronym("tcr")
            .build();

    //Date
    private Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }





    @org.junit.Test
    public void canOpenCourseWithCloseStatusAndValidDate() {
        /* When creating a course, the status is 'CLOSE' */
        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);
        Date closeDate = stringToDate("30/12/2024");
        course.openCourse(closeDate);

        CourseStatus expected = CourseStatus.OPEN;
        CourseStatus actual = course.courseStatus();

        Assertions.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void cannotOpenCourseWithInvalidDate() {
        /* When creating a course, the status is 'CLOSE' */
        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);
        Date closeDate = stringToDate("01/01/2020");
        course.openCourse(closeDate);

        CourseStatus expected = CourseStatus.OPEN;
        CourseStatus actual = course.courseStatus();

        Assertions.assertNotEquals(expected, actual);
    }

    @org.junit.Test
    public void cannotOpenCourseWithOpenStatus() {
        /* In this case, the course will be open first with a date, and then
        it will be tried to open it again with a new date, which won't change. */
        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);
        Date closeDate = stringToDate("30/12/2024");
        course.openCourse(closeDate);

        Date newCloseDate = stringToDate("01/01/2025");
        course.openCourse(newCloseDate);

        Date expected = newCloseDate;
        Date actual = course.closeCourseDate();

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void testCourseStatusWhenCloseCourse() {
        Date endDateCourse = stringToDate("22/02/2024");

        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);

        course.openCourse(endDateCourse);
        course.closeCourse();

        CourseStatus expected = CourseStatus.CLOSE;
        CourseStatus actual = course.courseStatus();

        Assertions.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void shouldNotOpenEnrollmentsForCourseWithNotOpenStatus() {
        Date endDateEnroll = stringToDate("22/02/2024");

        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);

        course.openEnrollments(endDateEnroll);
        CourseStatus expected = CourseStatus.ENROLL;
        CourseStatus actual = course.courseStatus();

        Assertions.assertNotEquals(expected, actual);
    }

    @org.junit.Test
    public void shouldNotCloseEnrollmentsForCourseWithNotENROLLStatus() {
        Date endDateCourse = stringToDate("22/02/2024");


        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);

        course.openCourse(endDateCourse);
        course.closeEnrollments();
        CourseStatus expected = CourseStatus.IN_PROGRESS;
        CourseStatus actual = course.courseStatus();

        Assertions.assertNotEquals(expected, actual);
    }

    @org.junit.Test
    public void testCourseStatusWhenOpenEnrollments() {
        Date endDateCourse = stringToDate("22/02/2024");
        Date endDateEnrollments = stringToDate("22/09/2023");

        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);

        course.openCourse(endDateCourse);
        course.openEnrollments(endDateEnrollments);
        CourseStatus expected = CourseStatus.ENROLL;
        CourseStatus actual = course.courseStatus();

        Assertions.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void testCourseStatusWhenCloseEnrollments() {
        Date endDateCourse = stringToDate("22/02/2024");
        Date endDateEnrollments = stringToDate("22/09/2023");

        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);

        course.openCourse(endDateCourse);
        course.openEnrollments(endDateEnrollments);
        course.closeEnrollments();
        CourseStatus expected = CourseStatus.IN_PROGRESS;
        CourseStatus actual = course.courseStatus();

        Assertions.assertEquals(expected, actual);
    }


    @org.junit.Test
    public void testCourseStatusWhenCreated() {
        Course course = new Course(courseCode, courseName, description, minStudents, maxStudents);

        CourseStatus expected = CourseStatus.CLOSE;
        CourseStatus actual = course.courseStatus();

        Assertions.assertEquals(expected, actual);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCourseCodeMustNotHaveLengthBiggerThan10() {
        System.out.println("Course code must have less than ten characters!!");
        new Course("course code big", new CourseName("Course name"), "new course description", 10, 20);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureNameCodeMustNotHaveLengthLessThan3() {
        System.out.println("Course name must have more than three characters!!");
        new Course("JAVA-1", new CourseName(""), "new course description", 10, 20);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCourseNameMustNotHaveLengthBiggerThan10() {
        System.out.println("Course name must have less than thirty characters!!");
        new Course("JAVA-1", new CourseName("This Course Name is really big, we have to put another"), "new course description", 10, 20);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureNameDescriptionMustNotHaveLengthLessThan10() {
        System.out.println("Course description must have more than ten characters!!");
        new Course("JAVA-1", new CourseName("Course Name"), "descr", 10, 20);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCourseDescriptionMustNotHaveLengthBiggerThan150() {
        System.out.println("Course description must have less than one hundred and fifty characters!!");
        new Course("JAVA-1", new CourseName("Course Name"), "This specific course has a really long long description so the test will be a sucess! But, in reality we can't have a course description like this, it's not allow to have something like this :(", 10, 20);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureMinStudentsMustNotBeNegative() {
        System.out.println("Min Student can't be negative");
        new Course("JAVA-1", new CourseName("Course Name"), "new course description", -1, 10);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureMaxStudentsMustNotBeNegative() {
        System.out.println("Min Student can't be negative");
        new Course("JAVA-1", new CourseName("Course Name"), "new course description", 10, -1);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureMaxStudentsIsMoreThanMinStudents() {
        System.out.println("Max students must be more then min students");
        new Course("JAVA-1", new CourseName("Course Name"), "new course description", 10, 9);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testFailedCauseMinStudentsIs0() {
        System.out.println("Min students can´t be zero");
        new Course("JAVA-1", new CourseName("Course Name"), "new course description", 0, 9);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testFailedCauseMaxStudentsIs0() {
        System.out.println("Min students can´t be zero");
        new Course("JAVA-1", new CourseName("Course Name"), "new course description", 10, 0);
    }

    @Test
    public void testSameAs() {
        Assertions.assertTrue(course.sameAs(course1));
        Assertions.assertTrue(course.sameAs(course));
        Assertions.assertFalse(course.sameAs("other"));
    }

    @Test
    public void testToString() {
        CourseStatus courseStatus = CourseStatus.CLOSE;
        String expected = "Course: " + courseCode +
                " - " + courseName +
                "(" + description + ")" +
                ",\n with minimum " + minStudents + " students" +
                " and maximum " + maxStudents + " students" +
                ",\n with status: " + courseStatus;
        Assertions.assertEquals(course.toString(), expected);

        String expected1 = expected;

        Assertions.assertEquals(course1.toString(), expected1);
    }
}
