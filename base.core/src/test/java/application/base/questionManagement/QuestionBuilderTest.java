package application.base.questionManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.Section;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionBuilder;
import application.base.domain.questionManagement.QuestionType;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuestionBuilderTest {

    final QuestionBuilder questionBuilder = new QuestionBuilder();
    final CourseBuilder courseBuilder = new CourseBuilder();
    Integer quotation = 8;

    Course course = courseBuilder
            .withName("Course")
            .withCode("DESC")
            .withDescription("description")
            .hasMinStudents(10)
            .hasMaxStudents(20)
            .build();

    final Exam exam = new Exam(course, "title", "description",
            stringToDate("30/10/2023"), stringToDate("30/12/2023"),
            new Language("language"), new Header("header", FeedbackType.NONE, GradeType.NONE));

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
    public void ensureQuestionWasSuccessfullyCreated(){
        Question actual = questionBuilder.ofCourse(course)
                .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                .withQuestion("question")
                .withCorrectAnswer("correct")
                .withQuotation(quotation)
                .build();
        Question expected = new Question(course, QuestionType.MULTIPLE_CHOICE, "question", "correct", 8);

        Assertions.assertTrue(expected.sameAs(actual));
    }

    @Test
    public void testFailedBecauseCourseIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(null)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion("question")
                            .withCorrectAnswer("correct")
                            .withQuotation(quotation)
                            .build();
                }
        );
    }

    @Test
    public void testFailedBecauseQuestionTypeIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(course)
                            .ofQuestionType(null)
                            .withQuestion("question")
                            .withCorrectAnswer("correct")
                            .withQuotation(quotation)
                            .build();
                }
        );
    }
    @Test
    public void testFailedBecauseQuestionIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(course)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion(null)
                            .withCorrectAnswer("correct")
                            .withQuotation(quotation)
                            .build();
                }
        );
    }
    @Test
    public void testFailedBecauseCorrectAnswerIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(course)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion("question")
                            .withCorrectAnswer(null)
                            .withQuotation(quotation)
                            .build();
                }
        );
    }
    @Test
    public void testFailedBecauseQuotationIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(course)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion("question")
                            .withCorrectAnswer("correct")
                            .withQuotation(null)
                            .build();
                }
        );
    }

    @Test
    public void testFailedBecauseSectionIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder
                            .ofCourse(course)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion("question")
                            .withCorrectAnswer("correct")
                            .withQuotation(null)
                            .ofSection(new Section(exam, "description"))
                            .build();
                }
        );
    }
}
