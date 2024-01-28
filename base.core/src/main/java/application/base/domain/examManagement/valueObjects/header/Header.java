package application.base.domain.examManagement.valueObjects.header;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

public class Header implements ValueObject{

    private String headerDescription;

    @Enumerated(EnumType.STRING)
    private FeedbackType feedbackType;

    @Enumerated(EnumType.STRING)
    private GradeType gradeType;

    protected Header(){
    }

    public Header( final String headerDescription, final FeedbackType feedbackType, GradeType gradeType){
        Preconditions.nonNull(headerDescription);

        this.headerDescription = headerDescription;
        this.feedbackType = feedbackType;
        this.gradeType = gradeType;
    }

    public String headerDescription() {
        return headerDescription;
    }

    public FeedbackType feedbackType() {
        return feedbackType;
    }

    public GradeType gradeType() {
        return gradeType;
    }

    @Override
    public String toString() {
        return headerDescription + '\n' +
               " with feedack type defined as " + feedbackType + '\n' +
               " and grade type as " + gradeType + '\n';
    }

}
