package application.base.domain.examManagement;

import application.base.domain.questionManagement.Question;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Section")
public class Section implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long sectionID;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private String description;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Question> questions;

    protected Section(){
    }

    public Section(Exam exam, String description) {
        Preconditions.noneNull(exam, description);
        this.exam = exam;
        this.description = description;
        this.questions = new ArrayList<>();
    }

    public Exam exam() {
        return exam;
    }

    public String description() {
        return description;
    }

    public List<Question> questions() {
        return questions;
    }

    public void changeQuestions(List<Question> questions){
        this.questions = questions;
    }

    @Override
    public boolean sameAs(Object o) {
        if (!(o instanceof Section)) {
            return false;
        }

        final Section that = (Section) o;
        if (this == that) {
            return true;
        }

        return exam.equals(that.exam)
                && description.equals(that.description);
    }

    @Override
    public Long identity() {
        return this.sectionID;
    }

}