package application.base.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.ExamBuilder;
import application.base.domain.examManagement.Section;
import application.base.domain.examManagement.SectionBuilder;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SectionBuilderTest {

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

    final Exam exam = new Exam(course, title, description, stringToDate("30/10/2023"),
            stringToDate("30/12/2023"), language, header);

    final SectionBuilder sectionBuilder = new SectionBuilder();

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
    public void ensureSectionWasSuccessfullyCreated(){
        Section expected = new Section(exam, description);
        Section actual = sectionBuilder.ofExam(exam).withDescription(description).build();

        Assertions.assertTrue(expected.sameAs(actual));
    }

    @Test
    public void testFailedBecauseExamIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    sectionBuilder.ofExam(null).withDescription(description).build();
                }
        );
    }

    @Test
    public void testFailedBecauseDescriptionIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    sectionBuilder.ofExam(exam).withDescription(null).build();
                }
        );
    }

}