package application.base.domain.enrollmentManagement;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Grade implements ValueObject, Comparable<Grade>, Serializable {

    private static final long serialVersionUID = 1L;

    private Float grade;

    public Grade (final Float grade){
        if(grade < 0){
            throw new IllegalArgumentException("The grade must be greater than or equal to zero!");
        }
        else if(grade > 20){
            throw new IllegalArgumentException("The maximum grade is 20!");
        }
        this.grade = grade;
    }

    protected Grade(){}

    @Override
    public String toString() {
        return String.valueOf(this.grade);
    }

    @Override
    public int compareTo(final Grade arg0) {
        return grade.compareTo(arg0.grade);
    }

}