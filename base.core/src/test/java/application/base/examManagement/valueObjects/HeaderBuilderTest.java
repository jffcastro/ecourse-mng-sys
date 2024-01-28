package application.base.examManagement.valueObjects;

import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import application.base.domain.examManagement.valueObjects.header.HeaderBuilder;
import org.junit.Assert;
import org.junit.Test;

public class HeaderBuilderTest {

    final String description = "description";
    final FeedbackType feedbackType = FeedbackType.NONE;
    final GradeType gradeType = GradeType.NONE;
    final Header header = new Header(description, feedbackType, gradeType);

    
    @Test
    public void testFailedBecauseDescriptionIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new HeaderBuilder()
                            .withDescription(null)
                            .withFeedbackType(feedbackType)
                            .withGradeType(gradeType)
                            .build();
                }
        );
    }

    @Test
    public void testFailedBecauseFeedbackTypeIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new HeaderBuilder()
                            .withDescription(description)
                            .withFeedbackType(null)
                            .withGradeType(gradeType)
                            .build();
                }
        );
    }

    @Test
    public void testFailedBecauseGradeTypeIsNull() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                {
                    new HeaderBuilder()
                            .withDescription(description)
                            .withFeedbackType(feedbackType)
                            .withGradeType(null)
                            .build();
                }
        );
    }

}
