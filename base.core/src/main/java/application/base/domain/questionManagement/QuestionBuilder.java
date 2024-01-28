package application.base.domain.questionManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Section;
import eapli.framework.validations.Preconditions;

public class QuestionBuilder {

    private Section section;
    private Course course;
    private QuestionType questionType;
    private String question;
    private String correctAnswer;
    private Integer quotation;

    public QuestionBuilder ofSection(Section section){
        this.section = section;
        return this;
    }

    public QuestionBuilder ofCourse(Course course) {
        this.course = course;
        return this;
    }

    public QuestionBuilder ofQuestionType(QuestionType questionType) {
        this.questionType = questionType;
        return this;
    }

    public QuestionBuilder withQuestion(String question) {
        this.question = question;
        return this;
    }

    public QuestionBuilder withCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public QuestionBuilder withQuotation(Integer quotation) {
        this.quotation = quotation;
        return this;
    }

    public Question build() {
        Preconditions.noneNull(course, questionType, question, correctAnswer, quotation);
        return new Question(section, course, questionType, question, correctAnswer, quotation);
    }
}
