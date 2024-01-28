package application.base.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.Section;
import application.base.domain.examManagement.valueObjects.ExamStatus;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import org.junit.Test;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamTest {

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
    public void testFailedBecauseCourseIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(null, title, description, stringToDate("30/10/2023"),
                            stringToDate("30/12/2023"), language, header);
                }
        );
    }

    @Test
    public void testFailedBecauseTitleIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(course, null, description, stringToDate("30/10/2023"),
                            stringToDate("30/12/2023"), language, header);
                }
        );
    }

    @Test
    public void testFailedBecauseDescriptionIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(course, title, null, stringToDate("30/10/2023"),
                            stringToDate("30/12/2023"), language, header);
                }
        );
    }

    @Test
    public void testFailedBecauseOpenDateIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(course, title, description, null,
                            stringToDate("30/12/2023"), language, header);
                }
        );
    }

    @Test
    public void testFailedBecauseCloseDateIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(course, title, description, stringToDate("30/10/2023"),
                            null, language, header);
                }
        );
    }

    @Test
    public void testFailedBecauseLanguageIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(course, title, description, stringToDate("30/10/2023"),
                            stringToDate("30/12/2023"), null, header);
                }
        );
    }

    @Test
    public void testFailedBecauseHeaderIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(course, title, description, stringToDate("30/10/2023"),
                            stringToDate("30/12/2023"), language, null);
                }
        );
    }

    @Test
    public void testFailedBecauseCloseDateIsBeforeOpenDate() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(course, title, description, stringToDate("30/10/2024"),
                            stringToDate("30/12/2023"), language, header);
                }
        );
    }

    @Test
    public void testFailedBecauseOpenDateIsBeforeOpenDate() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Exam(course, title, description, stringToDate("30/01/2022"),
                            stringToDate("30/12/2023"), language, header);
                }
        );
    }

    @Test
    public void examID() {
        Long expcted = 123L;
        exam.changeExamId(expcted);

        Assert.assertEquals(expcted, exam.examID());
    }

    @Test
    public void course() {
        Assert.assertEquals(course, exam.course());
    }

    @Test
    public void title() {
        Assert.assertEquals(title, exam.title());
    }

    @Test
    public void description() {
        Assert.assertEquals(description, exam.description());
    }

    @Test
    public void examStatus() {
        Assert.assertEquals(ExamStatus.CREATED, exam.examStatus());
    }

    @Test
    public void language() {
        Assert.assertEquals(language, exam.language());
    }

    @Test
    public void header() {
        Assert.assertEquals(header, exam.header());
    }

    @Test
    public void sections() {
        List<Section> expcted = new ArrayList<>();
        List<Section> actual = exam.sections();

        Assert.assertEquals(expcted, actual);
    }


    @Test
    public void changeSections() {
        Section section = new Section(exam, "title");
        List<Section> expcted = new ArrayList<>();
        expcted.add(section);
        exam.changeSections(expcted);
        List<Section> actual = exam.sections();

        Assert.assertEquals(expcted, actual);
    }

    @Test
    public void testSameAsForTheSameObject() {
        Assert.assertTrue(exam.sameAs(exam));
    }

    @Test
    public void testSameAsForIdenticalObject() {
        Exam exam = new Exam(course, title, description, stringToDate("30/10/2023"),
                stringToDate("30/12/2023"), language, header);
        Exam exam1 = new Exam(course, title, description, stringToDate("30/10/2023"),
                stringToDate("30/12/2023"), language, header);

        Assert.assertTrue(exam.sameAs(exam1));
    }

    @Test
    public void testFailsBecauseEntitiesAreDifferent() {
        Assert.assertFalse(exam.sameAs("other"));
    }


    @Test
    public void identity() {
        Long expcted = 123L;
        exam.changeExamId(expcted);

        Assert.assertEquals(expcted, exam.identity());
    }

    @Test
    public void testToString() {
        String expected = "Exam" +
                " with title: " + title +
                ", with description: " + description +
                ", with status: " + ExamStatus.CREATED +
                ", with open date:" + stringToDate("30/10/2023") +
                " and close date:" + stringToDate("30/12/2023");
        String actual = exam.toString();

        Assert.assertEquals(expected, actual);
    }

}