package application.base.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.Section;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionType;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SectionTest {

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

    final Section section = new Section(exam, description);

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
    public void testFailedBecauseExamIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Section(null, description);
                }
        );
    }

    @Test
    public void testFailedBecauseDescriptionIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Section(exam, null);
                }
        );
    }

    @Test
    public void exam(){
        Assert.assertEquals(exam, section.exam());
    }

    @Test
    public void description(){
        Assert.assertEquals(description, section.description());
    }

    @Test
    public void questions(){
        List<Question> expected = new ArrayList<>();
        Assert.assertEquals(expected, section.questions());
    }

    @Test
    public void changeQuestions(){
        Question question = new Question(course, QuestionType.MULTIPLE_CHOICE,"question", "correct answer", 5);
        List<Question> expected = new ArrayList<>();
        expected.add(question);
        section.changeQuestions(expected);
        Assert.assertEquals(expected, section.questions());
    }

    @Test
    public void testSameAsForTheSameObject() {
        Assert.assertTrue(section.sameAs(section));
    }

    @Test
    public void testSameAsForIdenticalObject() {
        Section section = new Section(exam, description);
        Section section1 = new Section(exam, description);

        Assert.assertTrue(section.sameAs(section1));
    }

    @Test
    public void testFailsBecauseEntitiesAreDifferent() {
        Assert.assertFalse(section.sameAs("other"));
    }


    @Test
    public void identity() {
        Assert.assertEquals(null, section.identity());
    }
}