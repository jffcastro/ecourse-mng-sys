package application.base.repositories.classManagement;

import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.repositories.DomainRepository;
import application.base.domain.classManagement.CourseClass;

import java.util.Date;

/**
 * The interface Course class repository.
 */
public interface CourseClassRepository extends DomainRepository<Long, CourseClass> {

    /**
     * Find all course classes iterable.
     *
     * @return the iterable
     */
    Iterable<CourseClass> findAllCourseClasses();

    /**
     * Find course class by title course class.
     *
     * @param classTitle the class title
     * @return the course class
     */
    CourseClass findCourseClassByTitle(String classTitle);

    /**
     * Find classes of student iterable.
     *
     * @param student the student
     * @return the iterable
     */
    Iterable<CourseClass> findClassesOfStudent(Student student);

    Iterable<CourseClass> findClassesOfStudentOnDate(Student student, Date date);


    /**
     * Find classes of teacher iterable.
     *
     * @param teacher the teacher
     * @return the iterable
     */
    Iterable<CourseClass> findClassesOfTeacher(Teacher teacher);

    Iterable<CourseClass> findClassesOfTeacherOnDate(Teacher teacher, Date date);



    /**
     * Find class of title and date iterable.
     *
     * @param title the title
     * @param date  the date
     * @return the iterable
     */
    Iterable<CourseClass> findClassOfTitleAndDate(String title, Date date);


}
