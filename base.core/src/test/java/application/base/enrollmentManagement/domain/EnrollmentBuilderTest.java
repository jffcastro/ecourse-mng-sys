package application.base.enrollmentManagement.domain;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.enrollmentManagement.EnrollmentBuilder;
import application.base.domain.enrollmentManagement.EnrollmentsStatus;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.StudentBuilder;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class EnrollmentBuilderTest {

    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    final SystemUser student1 = userBuilder
            .withUsername("Student")
            .withEmail("Student@email.com")
            .withPassword("Password1")
            .withName("StudentFstName", "StudentLstName")
            .withRoles(BaseRoles.STUDENT)
            .build();
    Student student = new StudentBuilder()
            .withSystemUser(student1)
            .withMecanographicNumber("1212121")
            .build();

    Course course = new CourseBuilder()
            .withCode("JAVA-1")
            .withName(new CourseName("course name"))
            .withDescription("course description")
            .hasMinStudents(10)
            .hasMaxStudents(20)
            .build();

    private void openCourseEnrollments(Course course){
        course.openCourse(Date.from(Instant.now().plus(Duration.ofDays(90))));
        course.openEnrollments(Date.from(Instant.now().plus(Duration.ofDays(30))));
    }

    @Test
    public void ensureCanCreateEnrollmentWithEnrollmentStatusStudentCourseGradeExamEnrollment() {
        openCourseEnrollments(course);
        Enrollment actual = new EnrollmentBuilder()
                .withEnrollmentStatus(EnrollmentsStatus.ACCEPTED)
                .ofStudent(student)
                .inCourse(course)
                .build();

        Enrollment expected = new Enrollment(EnrollmentsStatus.ACCEPTED, student, course);

        Assertions.assertTrue(expected.sameAs(actual));
    }

    @Test
    public void ensureCannotBuildWithNullEnrollStatus() {
        openCourseEnrollments(course);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Enrollment(null, student, course);
        });
    }

    @Test
    public void ensureCannotBuildWithNullStudent() {
        openCourseEnrollments(course);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Enrollment(EnrollmentsStatus.ACCEPTED, null, course);
        });
    }

    @Test
    public void ensureCannotBuildWithNullCourse() {
        openCourseEnrollments(course);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Enrollment(EnrollmentsStatus.ACCEPTED, student, null);
        });
    }

}