package application.base.persistence.impl.inmemory;

import application.base.domain.courseManagement.Course;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCourseRepository extends InMemoryDomainRepository<Course, Long>
        implements CourseRepository {
    @Override
    public Iterable<Course> findCourseWithOpenStatus() {
        return null;
    }

    @Override
    public Iterable<Course> findCourseWithEnrollStatus() {
        return null;
    }

    @Override
    public Iterable<Course> findCoursesAvailableToOpen() {
        return null;
    }

    @Override
    public Iterable<Course> findCoursesAvailableToClose() {
        return null;
    }

    @Override
    public Iterable<Course> findCourseListForStudentWhereHeCanEnroll(Student student) {
        return null;
    }

    @Override
    public Iterable<Course> findCourseListForStudent(SystemUser student) {
        return null;
    }

    @Override
    public Course findByCode(String courseName) {
        return null;
    }

}
