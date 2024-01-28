package application.base.domain.enrollmentManagement;

import application.base.domain.courseManagement.Course;
import application.base.usermanagement.domain.Student;
import eapli.framework.validations.Preconditions;

public class EnrollmentBuilder {

    private EnrollmentsStatus enrollmentsStatus;
    private Student student;
    private Course course;

    public EnrollmentBuilder withEnrollmentStatus(EnrollmentsStatus enrollmentsStatus) {
        this.enrollmentsStatus = enrollmentsStatus;
        return this;
    }

    public EnrollmentBuilder ofStudent(Student student) {
        this.student = student;
        return this;
    }

    public EnrollmentBuilder inCourse(Course course) {
        this.course = course;
        return this;
    }

    public Enrollment build() {
        Preconditions.noneNull(enrollmentsStatus, student, course);
        return new Enrollment(enrollmentsStatus, student, course);
    }
}

