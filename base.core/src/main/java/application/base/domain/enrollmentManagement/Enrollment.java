package application.base.domain.enrollmentManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseStatus;
import application.base.usermanagement.domain.Student;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Enrollment")
public class Enrollment implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long enrollmentID;

    @Enumerated(EnumType.STRING)
    private EnrollmentsStatus enrollmentsStatus;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "enrollment")
    private List<ExamEnrollment> examEnrollment;

    protected Enrollment() {
    }

    public Enrollment(final EnrollmentsStatus enrollmentsStatus, final Student student, final Course course) {
        Preconditions.noneNull(enrollmentsStatus, student, course);

        if (course.courseStatus().equals(CourseStatus.ENROLL)) {
            this.enrollmentsStatus = enrollmentsStatus;
            this.student = student;
            this.course = course;
        }
    }

    public EnrollmentsStatus enrollmentsStatus() {
        return enrollmentsStatus;
    }

    public Student student() {
        return student;
    }

    public Course course() {
        return course;
    }

    public void acceptEnrollment() {
        this.enrollmentsStatus = EnrollmentsStatus.ACCEPTED;
    }

    public void rejectEnrollment() {
        this.enrollmentsStatus = EnrollmentsStatus.NOT_ACCEPTED;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Enrollment that = (Enrollment) other;
        return enrollmentsStatus.equals(that.enrollmentsStatus) &&
                student.sameAs(that.student) &&
                course.sameAs(that.course);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual((DomainEntity<?>) this, o);
    }

    @Override
    public Long identity() {
        return enrollmentID;
    }

    @Override
    public String toString() {
        return "Enrollment" +
                " of student: " + student.mecanographicNumber() +
                " in course: " + course.courseCode();
    }
}