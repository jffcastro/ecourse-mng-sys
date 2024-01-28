package application.base.enrollmentManagement.domain;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.enrollmentManagement.*;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.ExamBuilder;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.StudentBuilder;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamEnrollmentBuilderTest {

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

    final Course course = new CourseBuilder()
            .withCode("JAVA-1")
            .withName(new CourseName("EAPLI"))
            .withDescription("Java Programming")
            .hasMinStudents(10)
            .hasMaxStudents(20)
            .build();

    private Exam exam = new ExamBuilder()
            .withTitle("title")
            .ofCourse(course)
            .withDescription("description")
            .withOpenDate(stringToDate("30/10/2024"))
            .withCloseDate(stringToDate("01/11/2024"))
            .withLanguage(new Language("language"))
            .withHeader(new Header("header", FeedbackType.AFTER_CLOSING, GradeType.AFTER_CLOSING))
            .build();

    private Enrollment enrollment;


    private void openCourse() {
        course.openCourse(stringToDate("30/09/2024"));
        course.openEnrollments(stringToDate("30/08/2024"));
        enrollment = new EnrollmentBuilder()
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


    /**
     * To test the exam enrollment, the exam ID must not be null.
     * As we are not saving the object on the db, we change the ID to test the classe.
     */
    private void createExamId(){
        this.exam.changeExamId(123L);
    }

    @Test
    public void testFailedBecauseExamIdIsNull() {
        openCourse();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    new ExamEnrollmentBuilder()
                            .ofExam(null)
                            .ofEnrollment(enrollment)
                            .withExamEnrollmentStatus(ExamEnrollmentStatus.ENROLLED)
                            .build();
                }
        );
    }

    @Test
    public void testFailedBecauseEnrollmentIsNull() {
        openCourse();
        createExamId();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    new ExamEnrollmentBuilder()
                            .ofExam(exam.examID())
                            .ofEnrollment(null)
                            .withExamEnrollmentStatus(ExamEnrollmentStatus.ENROLLED)
                            .build();
                }
        );
    }

    @Test
    public void testFailedBecauseStatusIsNull() {
        openCourse();
        createExamId();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    new ExamEnrollmentBuilder()
                            .ofExam(exam.examID())
                            .ofEnrollment(enrollment)
                            .withExamEnrollmentStatus(null)
                            .build();
                }
        );
    }

}
