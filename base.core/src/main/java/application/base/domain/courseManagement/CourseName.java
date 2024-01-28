package application.base.domain.courseManagement;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CourseName implements ValueObject, Comparable<CourseName>, Serializable {

    private static final long serialVersionUID = 1L;

    private String courseName;

    public CourseName(final String courseName) {
        if (courseName == null || courseName.length() < 3 || courseName.length() > 30){
            throw new IllegalArgumentException("Course code must be between 3 and 10");
        }
        this.courseName = courseName;
    }

    protected CourseName(){}

    public static CourseName valueOf(final String courseName) {
        return new CourseName(courseName);
    }

    public int length() {
        return courseName.length();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseName)) {
            return false;
        }

        final CourseName that = (CourseName) o;
        return this.courseName.equals(that.courseName);
    }

    @Override
    public int hashCode() {
        return this.courseName.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(this.courseName);
    }

    @Override
    public int compareTo(final CourseName arg0) {
        return courseName.compareTo(arg0.courseName);
    }
}
