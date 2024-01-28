package application.base.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.ExamBuilder;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamBuilderTest {

    final ExamBuilder examBuilder = new ExamBuilder();

    final Course course = new CourseBuilder()
            .withCode("JAVA-1")
            .withName(new CourseName("course name"))
            .withDescription("course description")
            .hasMinStudents(10)
            .hasMaxStudents(20)
            .build();

    final String title = "title";

    final String description = "description";

    final Language language = new Language("teste");

    final Header header = new Header("teste", FeedbackType.NONE, GradeType.NONE);

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
    public void ensureExamWasSuccessfullyCreated(){
        Exam actual = examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                .withOpenDate(stringToDate("30/10/2023")).withCloseDate(stringToDate("30/12/2023"))
                .withLanguage(language).withHeader(header).build();
        Exam expected = new Exam(course, title, description, stringToDate("30/10/2023"),
                stringToDate("30/12/2023"), language, header);

        Assertions.assertTrue(expected.sameAs(actual));
    }

    @Test
    public void testFailedBecauseCourseIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(null).withTitle(title).withDescription(description)
                            .withOpenDate(stringToDate("30/10/2023")).withCloseDate(stringToDate("30/12/2023"))
                            .withLanguage(language).withHeader(header).build();
                }
        );
    }

    @Test
    public void testFailedBecauseTitleIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(null).withDescription(description)
                            .withOpenDate(stringToDate("30/10/2023")).withCloseDate(stringToDate("30/12/2023"))
                            .withLanguage(language).withHeader(header).build();
                }
        );
    }

    @Test
    public void testFailedBecauseDescriptionIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(null)
                            .withOpenDate(stringToDate("30/10/2023")).withCloseDate(stringToDate("30/12/2023"))
                            .withLanguage(language).withHeader(header).build();
                }
        );
    }

    @Test
    public void testFailedBecauseOpenDateIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                            .withOpenDate(null).withCloseDate(stringToDate("30/12/2023"))
                            .withLanguage(language).withHeader(header).build();
                }
        );
    }

    @Test
    public void testFailedBecauseCloseDateIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                            .withOpenDate(stringToDate("30/10/2023")).withCloseDate(null)
                            .withLanguage(language).withHeader(header).build();
                }
        );
    }

    @Test
    public void testFailedBecauseLanguageIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                            .withOpenDate(stringToDate("30/10/2023")).withCloseDate(stringToDate("30/12/2023"))
                            .withLanguage(null).withHeader(header).build();
                }
        );
    }

    @Test
    public void testFailedBecauseHeaderIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                            .withOpenDate(stringToDate("30/10/2023")).withCloseDate(stringToDate("30/12/2023"))
                            .withLanguage(language).withHeader(null).build();
                }
        );
    }

    @Test
    public void testFailedBecauseCloseDateIsBeforeOpenDate() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                            .withOpenDate(stringToDate("30/01/2023")).withCloseDate(stringToDate("30/12/2023"))
                            .withLanguage(language).withHeader(header).build();
                }
        );
    }

    @Test
    public void testFailedBecauseOpenDateIsBeforeOpenDate() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                            .withOpenDate(stringToDate("30/10/2024")).withCloseDate(stringToDate("30/12/2023"))
                            .withLanguage(language).withHeader(header).build();
                }
        );
    }

    @Test
    public void ensureAutoWasSuccessfullyCreated(){
        Exam actual = examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                .withLanguage(language).withHeader(header).buildAuto();
        Exam expected = new Exam(course, title, description, language, header);

        Assertions.assertTrue(expected.sameAs(actual));
    }


    @Test
    public void testFailedBecauseCourseIsNullAuto() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(null).withTitle(title).withDescription(description)
                            .withLanguage(language).withHeader(header).buildAuto();
                }
        );
    }

    @Test
    public void testFailedBecauseTitleIsNullAuto() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(null).withDescription(description)
                            .withLanguage(language).withHeader(header).buildAuto();
                }
        );
    }

    @Test
    public void testFailedBecauseDescriptionIsNullAuto() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(null)
                            .withLanguage(language).withHeader(header).buildAuto();
                }
        );
    }

    @Test
    public void testFailedBecauseLanguageIsNullAuto() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                            .withLanguage(null).withHeader(header).buildAuto();
                }
        );
    }

    @Test
    public void testFailedBecauseHeaderIsNullAuto() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    examBuilder.ofCourse(course).withTitle(title).withDescription(description)
                            .withLanguage(language).withHeader(null).buildAuto();
                }
        );
    }


}