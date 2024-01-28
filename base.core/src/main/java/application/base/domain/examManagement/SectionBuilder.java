package application.base.domain.examManagement;

import eapli.framework.validations.Preconditions;

public class SectionBuilder {

    private Exam exam;
    private String description;

    public SectionBuilder() {
    }

    public SectionBuilder ofExam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public SectionBuilder withDescription(String description) {
        this.description = description;
        return this;
    }


    public Section build() {
        Preconditions.noneNull(exam, description);
        return new Section(exam, description);
    }

}
