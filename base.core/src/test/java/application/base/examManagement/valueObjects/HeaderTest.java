package application.base.examManagement.valueObjects;

import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import org.junit.Assert;
import org.junit.Test;

public class HeaderTest {

    final String description = "description";
    final FeedbackType feedbackType = FeedbackType.NONE;
    final GradeType gradeType = GradeType.NONE;
    final Header header = new Header(description, feedbackType, gradeType);

    @Test
    public void testFailedBecauseDescriptionIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Header(null, feedbackType, gradeType);
                }
        );
    }

    @Test
    public void headerDescription(){
        Assert.assertEquals(description, header.headerDescription());
    }

    @Test
    public void feedbackType(){
        Assert.assertEquals(feedbackType, header.feedbackType());
    }

    @Test
    public void gradeType(){
        Assert.assertEquals(gradeType, header.gradeType());
    }

    @Test
    public void testToString(){
        String expected = description + '\n' +
                " with feedack type defined as " + feedbackType + '\n' +
                " and grade type as " + gradeType + '\n';
        String actual = header.toString();

        Assert.assertEquals(expected, actual);
    }
}