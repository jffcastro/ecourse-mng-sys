package application.base.domain.examManagement.valueObjects.header;

import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import eapli.framework.validations.Preconditions;

public class HeaderBuilder {

    private Exam exam;
    private String description;
    private FeedbackType feedbackType;
    private GradeType gradeType;

    public HeaderBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public HeaderBuilder withFeedbackType(FeedbackType feedbackType){
        this.feedbackType = feedbackType;
        return this;
    }

    public HeaderBuilder withGradeType(GradeType gradeType){
        this.gradeType = gradeType;
        return this;
    }

    public Header build() {
        Preconditions.noneNull(description, feedbackType, gradeType);
        return new Header(description, feedbackType, gradeType);
    }
}
