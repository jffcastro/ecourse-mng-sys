package application.base.persistence.impl.inmemory;

import application.base.domain.courseManagement.Course;
import application.base.domain.teachingManagement.Teaching;
import application.base.repositories.teachingManagment.TeachingRepository;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryTeachingRepository extends InMemoryDomainRepository<Teaching, Long> implements TeachingRepository {
    @Override
    public Iterable<Teaching> findAllTeachings() {
        return null;
    }

    @Override
    public Iterable<Course> findCoursesWhereTeacherTeaches(Teacher teacher) {
        return null;
    }

    @Override
    public Iterable<Teacher> findTeachersOfCourse(Course course) {
        return null;
    }

    @Override
    public Teacher findTeacherInChargeOfCourse(Course course) {
        return null;
    }
}
