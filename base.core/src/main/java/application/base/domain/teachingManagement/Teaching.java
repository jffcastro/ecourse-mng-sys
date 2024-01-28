package application.base.domain.teachingManagement;

import application.base.domain.courseManagement.Course;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import javax.persistence.*;

@Entity
@Table(name = "Teaching")
public class Teaching implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Course course;

    @Column(nullable = false)
    private boolean isPrimary;

    protected Teaching(){}

    public Teaching(final Teacher teacher, final Course course, final boolean isPrimary) {
        Preconditions.noneNull(teacher, course, isPrimary);

        this.teacher = teacher;
        this.course = course;
        this.isPrimary = isPrimary;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode((DomainEntity<?>) this);
    }

    public Teacher teacher() {
        return teacher;
    }

    public Course course() {
        return course;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public Long identity() {
        return id;
    }

    public boolean sameAs(final Object o) {
        if (!(o instanceof Teaching)) {
            return false;
        }

        final Teaching that = (Teaching) o;
        if (this == that) {
            return true;
        }

        return course.equals(that.course) && teacher.equals(that.teacher)
                && isPrimary==that.isPrimary;
    }

    @Override
    public String toString() {
        return "Teaching" +
                " of teacher: " + teacher.acronym() +
                " in course: " + course.courseCode();
    }
    
}
