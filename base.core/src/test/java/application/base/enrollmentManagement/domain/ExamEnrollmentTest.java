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
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamEnrollmentTest {

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

    private ExamEnrollment examEnrollment;

    private void openCourseAndCreateEnrollment() {
        course.openCourse(stringToDate("30/09/2024"));
        course.openEnrollments(stringToDate("30/08/2024"));
        enrollment = new EnrollmentBuilder()
                .inCourse(course)
                .ofStudent(student)
                .withEnrollmentStatus(EnrollmentsStatus.ACCEPTED)
                .build();
        examEnrollment = new ExamEnrollment(123L, enrollment, ExamEnrollmentStatus.ENROLLED);
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
    private void createExamId() {
        this.exam.changeExamId(123L);
    }

    @Test
    public void testFailedBecauseExamIdIsNull() {
        openCourseAndCreateEnrollment();
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new ExamEnrollment(null, enrollment, ExamEnrollmentStatus.ENROLLED);
                }
        );
    }

    @Test
    public void testFailedBecauseEnrollmentIsNull() {
        openCourseAndCreateEnrollment();
        createExamId();
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new ExamEnrollment(exam.examID(), null, ExamEnrollmentStatus.ENROLLED);
                }
        );
    }

    @Test
    public void testFailedBecauseStatusIsNull() {
        openCourseAndCreateEnrollment();
        createExamId();
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new ExamEnrollment(exam.examID(), enrollment, null);
                }
        );
    }

    @Test
    public void examID() {
        openCourseAndCreateEnrollment();
        Long expected = 123L;
        Long actual = examEnrollment.examID();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void enrollment() {
        openCourseAndCreateEnrollment();
        Assert.assertEquals(enrollment, examEnrollment.enrollment());
    }

    @Test
    public void changeExamEnrollmentStatusToPresent() {
        openCourseAndCreateEnrollment();
        examEnrollment.changeExamEnrollmentStatusToPresent();
        Assert.assertEquals(ExamEnrollmentStatus.PRESENT, examEnrollment.examEnrollmentStatus());
    }

    @Test
    public void examEnrollmentStatus() {
        openCourseAndCreateEnrollment();
        Assert.assertEquals(ExamEnrollmentStatus.ENROLLED, examEnrollment.examEnrollmentStatus());
    }

    @Test
    public void examGrade() {
        openCourseAndCreateEnrollment();
        Grade expected = new Grade(10F);
        examEnrollment.changeExamGrade(expected);

        Assert.assertEquals(expected, examEnrollment.examGrade());
    }

    @Test
    public void testSameAsForTheSameObject() {
        openCourseAndCreateEnrollment();
        createExamId();
        ExamEnrollment examEnrollment = new ExamEnrollmentBuilder().ofExam(exam.examID())
                .ofEnrollment(enrollment)
                .withExamEnrollmentStatus(ExamEnrollmentStatus.ENROLLED)
                .build();

        Assert.assertTrue(examEnrollment.sameAs(examEnrollment));

    }

    @Test
    public void testSameAsForIdenticalObject() {
        openCourseAndCreateEnrollment();
        createExamId();
        ExamEnrollment examEnrollment = new ExamEnrollmentBuilder().ofExam(exam.examID())
                .ofEnrollment(enrollment)
                .withExamEnrollmentStatus(ExamEnrollmentStatus.ENROLLED)
                .build();

        ExamEnrollment examEnrollment1 = new ExamEnrollmentBuilder().ofExam(exam.examID())
                .ofEnrollment(enrollment)
                .withExamEnrollmentStatus(ExamEnrollmentStatus.ENROLLED)
                .build();

        Assert.assertTrue(examEnrollment.sameAs(examEnrollment1));

    }

    @Test
    public void testFailsBecauseEntitiesAreDifferent() {
        openCourseAndCreateEnrollment();
        createExamId();
        ExamEnrollment examEnrollment = new ExamEnrollmentBuilder().ofExam(exam.examID())
                .ofEnrollment(enrollment)
                .withExamEnrollmentStatus(ExamEnrollmentStatus.ENROLLED)
                .build();

        Assert.assertFalse(examEnrollment.sameAs("other"));
    }

}
