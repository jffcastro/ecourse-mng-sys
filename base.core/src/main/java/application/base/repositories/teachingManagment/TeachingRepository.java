package application.base.repositories.teachingManagment;

import application.base.domain.courseManagement.Course;
import application.base.domain.teachingManagement.Teaching;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.repositories.DomainRepository;

public interface TeachingRepository extends DomainRepository<Long, Teaching> {

    /**
     * This method returns the list of all the existing teachings
     *
     * @return list all teachings
     */
    Iterable<Teaching> findAllTeachings();

    /**
     * This method is used by US  1006 and returns the list of courses where the teacher teaches
     *
     * @return list courses where the teacher teaches
     */
    Iterable<Course> findCoursesWhereTeacherTeaches(Teacher teacher);

    /**
     * This method is used by US 1005 and returns the list of Teachers that teach in that course
     *
     * @return list teachers of course
     */
    Iterable<Teacher> findTeachersOfCourse(Course course);

    /**
     * This method is used by US  1005 and returns the teacher in charge of a course
     *
     * @return teacher in charge of course
     */
    Teacher findTeacherInChargeOfCourse(Course course);
}
