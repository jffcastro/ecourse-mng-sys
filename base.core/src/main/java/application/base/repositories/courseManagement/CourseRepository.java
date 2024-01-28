package application.base.repositories.courseManagement;

import application.base.domain.courseManagement.Course;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public interface CourseRepository extends DomainRepository<Long, Course> {

        /**
         * This method is used by US 1006 and returns the list of all the existing courses
         * It was considered that all the courses were available to a manager
         *
         * @return list all courses
         */
        Iterable<Course> findAll();

        /**
         * This method is used in US 1007 and returns a course with a given name (unique value).
         *
         * @return course with given name
         */
        Course findByCode(String courseCode);

        /**
         * This method is used by US 1003 and US 1005 and returns the list of courses with Open Status.
         * Enrollments are available to be open if, and only if, the course is Open, otherwise it's not possible.
         *
         * @return list of courses with Open status
         */
        Iterable<Course> findCourseWithOpenStatus();

        /**
         * This method is used by US 1003 and returns the list of courses with Enroll Status.
         * Enrollments are available to be close if, and only if, the course is with an Enroll status, otherwise it's not possible.
         *
         * @return list of courses with Enroll status
         */
        Iterable<Course> findCourseWithEnrollStatus();

        /**
         * This method is used by US 1004 and returns the list of courses avaliable to be open.
         * A course is available to be open if, and only if, it is close, otherwise it's not possible,
         * and if it has not be open yet (no course close date).
         *
         * @return list of courses available to open
         */
        Iterable<Course> findCoursesAvailableToOpen();

        /**
         * This method is used by US 1004 and returns the list of courses avaliable to be closed.
         * A course is available to be closed if in any state, execpt if already closed.
         *
         * @return list of courses available to closed
         */
        Iterable<Course> findCoursesAvailableToClose();

        /**
         * This method is used by US 1006 and returns the list of courses where the student can enroll.
         *
         * @return list of courses where the student can enroll
         */
        Iterable<Course> findCourseListForStudentWhereHeCanEnroll(Student student);

        Iterable<Course> findCourseListForStudent(SystemUser student);



}