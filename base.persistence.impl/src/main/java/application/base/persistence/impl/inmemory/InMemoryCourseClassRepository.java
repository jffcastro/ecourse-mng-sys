package application.base.persistence.impl.inmemory;

import application.base.domain.classManagement.CourseClass;
import application.base.repositories.classManagement.CourseClassRepository;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Date;

public class InMemoryCourseClassRepository extends InMemoryDomainRepository<CourseClass, Long> implements CourseClassRepository {

    @Override
    public Iterable<CourseClass> findAllCourseClasses() {
        return null;
    }

    @Override
    public CourseClass findCourseClassByTitle(String className) {
        return null;
    }

    @Override
    public Iterable<CourseClass> findClassesOfStudent(Student student) {
        return null;
    }

    @Override
    public Iterable<CourseClass> findClassesOfStudentOnDate(Student student, Date date) {
        return null;
    }

    @Override
    public Iterable<CourseClass> findClassesOfTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public Iterable<CourseClass> findClassesOfTeacherOnDate(Teacher teacher, Date date) {
        return null;
    }

    @Override
    public Iterable<CourseClass> findClassOfTitleAndDate(String title, Date date) {
        return null;
    }

}
