package application.base.enrollmentManagement.domain;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.enrollmentManagement.EnrollmentBuilder;
import application.base.domain.enrollmentManagement.EnrollmentsStatus;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.StudentBuilder;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnrollmentTest {

    final SystemUserBuilder userBuilder1 = UserBuilderHelper.builder();
    final SystemUser student1 = userBuilder1
            .withUsername("student")
            .withPassword("Password1")
            .withName("studentFstName", "studentlSTname")
            .withEmail("student@gmail.com")
            .withRoles(BaseRoles.STUDENT)
            .build();
    final Student student = new StudentBuilder()
            .withSystemUser(student1)
            .withMecanographicNumber("1212121")
            .build();

    Course course = new CourseBuilder()
            .withCode("JAVA-1")
            .withName(new CourseName("EAPLI"))
            .withDescription("Java Programming")
            .hasMinStudents(10)
            .hasMaxStudents(20)
            .build();

    EnrollmentBuilder enrollmentBuilder = new EnrollmentBuilder();
    Enrollment enrollment;

    Enrollment enrollment1;

    private void openCourse(){
        course.openCourse(stringToDate("30/09/2024"));
        course.openEnrollments(stringToDate("30/08/2024"));
        enrollment = enrollmentBuilder
                .inCourse(course)
                .ofStudent(student)
                .withEnrollmentStatus(EnrollmentsStatus.ACCEPTED)
                .build();
        enrollment1 = enrollmentBuilder
                .inCourse(course)
                .ofStudent(student)
                .withEnrollmentStatus(EnrollmentsStatus.ACCEPTED)
                .build();
    }

    private Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }


    @Test
    public void testFailedCauseEnrollmentStatusIsNull(){
        openCourse();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new Enrollment(null, student, course);}
        );
    }

    @Test
    public void testFailedCauseStudentIsNull(){
        openCourse();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new Enrollment(EnrollmentsStatus.UNDER_APPRECIATION, null, course);}
        );
    }

    @Test
    public void testFailedCauseCourseIsNull(){
        openCourse();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {new Enrollment(EnrollmentsStatus.ACCEPTED, student, null);}
        );
    }

    @Test
    public void enrollmentsStatus() {
        openCourse();
        Assert.assertEquals(EnrollmentsStatus.ACCEPTED, enrollment.enrollmentsStatus());
    }

    @Test
    public void student() {
        openCourse();
        Assert.assertEquals(student, enrollment.student());
    }

    @Test
    public void course() {
        openCourse();
        Assert.assertEquals(course, enrollment.course());
    }

    @Test
    public void testEnrollmentStatusWhenAcceptEnrollment() {
        openCourse();
        Enrollment enrollment = new Enrollment(EnrollmentsStatus.UNDER_APPRECIATION, student, course);

        enrollment.acceptEnrollment();
        EnrollmentsStatus expected = EnrollmentsStatus.ACCEPTED;
        EnrollmentsStatus actual = enrollment.enrollmentsStatus();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEnrollmentStatusWhenRejectEnrollment() {
        openCourse();
        Enrollment enrollment = new Enrollment(EnrollmentsStatus.UNDER_APPRECIATION, student, course);

        enrollment.rejectEnrollment();
        EnrollmentsStatus expected = EnrollmentsStatus.NOT_ACCEPTED;
        EnrollmentsStatus actual = enrollment.enrollmentsStatus();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSameAs() {
        openCourse();
        Assertions.assertTrue(enrollment.sameAs(enrollment));
        Assertions.assertTrue(enrollment.sameAs(enrollment1));
        Assertions.assertFalse(this.enrollment.sameAs("other"));
    }

    @Test
    public void testToString() {
        openCourse();
        String expected = "Enrollment" +
                " of student: " + student.mecanographicNumber() +
                " in course: " + course.courseCode();
        Assertions.assertEquals(enrollment.toString(), expected);

    }

}
