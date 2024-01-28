package application.base.persistence.impl.inmemory;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.Student;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;

public class InMemoryEnrollmentRepository extends InMemoryDomainRepository<Enrollment, Long> implements EnrollmentRepository {

    @Override
    public List<Student> findUsersOfCourse(Course course) {
        return null;
    }

    @Override
    public Iterable<Course> findCoursesStudentIsEnroll(Student student) {
        return null;
    }

    @Override
    public Enrollment findStudentEnrollmentInCourse(Student student, Course course) {
        return null;
    }

    @Override
    public Iterable<Enrollment> findEnrollmentsRequests() {
        return null;
    }

    @Override
    public Iterable<Enrollment> findEnrollmentsOfCourse(Course course) {
        return null;
    }
}
