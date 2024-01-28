package application.base.domain.questionManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Section;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
@Table(name = "Question")
public class Question implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long questionID;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Lob
    @ManyToOne
    private Course course;

    @Column(nullable = false)
    private QuestionType questionType;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String correctAnswer;

    @Column(nullable = false)
    private Integer quotation;

    protected Question(){
    }

    public Question(Course course, QuestionType questionType, String question, String correctAnswer, Integer quotation) {
        this.course = course;
        this.questionType = questionType;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.quotation = quotation;
    }

    public Question(Section section, Course course, QuestionType questionType, String question, String correctAnswer, Integer quotation) {
        this.section = section;
        this.course = course;
        this.questionType = questionType;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.quotation = quotation;
    }

    public Section section(){
        return section;
    }

    public String question() {
        return question;
    }

    public String answer() {
        return correctAnswer;
    }

    public QuestionType questionType() {
        return questionType;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public Long identity() {
        return questionID;
    }

    public Integer quotation(){
        return quotation;
    }


    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Question otherQuestion = (Question) other;

        return course.equals(course)
                && question.equals(otherQuestion.question)
                && correctAnswer.equals(otherQuestion.correctAnswer);
    }

    @Override
    public String toString() {

        if (questionType.equals(QuestionType.MATCHING)) {
            String[] questionItSelf = question.split(";");
            String questionText = questionItSelf[0];

            String[] leftList = questionItSelf[1].split(",");
            String[] rightList = questionItSelf[2].split(",");

            StringBuilder retToString = new StringBuilder("Matching Question: " + questionText + '\n');
            for (int i = 0; i < leftList.length; i++) {
                retToString.append(String.format("%-10s - %-10s%n", leftList[i], rightList[i]));
            }

            return retToString.toString();
        }

        if (questionType.equals(QuestionType.MISSING_WORD)) {
            String[] questionItSelf = question.split(";");
            String questionText = questionItSelf[0];

            String[] options = questionItSelf[1].split(",");
            String retToString = "Missing Word Question: " + questionText + '\n';
            for (int i = 0; i < options.length; i++) {
                retToString += i +1 + ". " + options[i] + '\n';
            }
            return retToString;
        }

        if (questionType.equals(QuestionType.MULTIPLE_CHOICE)) {
            String[] questionItSelf = question.split(";");
            String questionText = questionItSelf[0];

            String[] options = questionItSelf[1].split(",");
            String retToString = "Multiple Choice Question: " + questionText + '\n';
            for (int i = 0; i < options.length; i++) {
                retToString += i + 1 + ". " + options[i] + '\n';
            }

            return retToString;
        }

        if(questionType.equals(QuestionType.SHORT)){
            String[] questionItSelf = question.split(";");
            String questionText = questionItSelf[0];
            return "Short Question: " +  questionText + '\n';
        }

        if(questionType.equals(QuestionType.NUMERIC)){
            String[] questionItSelf = question.split(";");
            String questionText = questionItSelf[0];
            return "Numeric Question: " +  questionText + '\n';
        }

        if(questionType.equals(QuestionType.TRUE_OR_FALSE)){
            String[] questionItSelf = question.split(";");
            String questionText = questionItSelf[0];
            return "True/False Question: " +  questionText + '\n';
        }

        return "";
    }

}
