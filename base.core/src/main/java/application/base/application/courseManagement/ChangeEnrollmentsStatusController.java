package application.base.application.courseManagement;

import application.base.domain.courseManagement.Course;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.springframework.transaction.annotation.Transactional;
import application.base.repositories.courseManagement.CourseRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ChangeEnrollmentsStatusController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    /**
     * This method returns the list of courses with Open Status.
     * Enrollments are available to be open if, and only if, the course is Open, otherwise it's not possible.
     *
     * @return list of courses with Open status
     */
    @Transactional
    public Iterable<Course> listOpenCourses(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        return courseRepository.findCourseWithOpenStatus();
    }

    /**
     * This method validates the course close date.
     * The date is valid if it is later than system date.
     *
     * @param endDateEnroll - course close date inserted by the user
     * @return date validation
     */
    public boolean validateDate(Course course, Date endDateEnroll){
        if(endDateEnroll.after(course.closeCourseDate()) || endDateEnroll.before(Date.from(Instant.now()))){
            return false;
        }
        return true;
    }


    /**
     * This method opens enrollments in a course.
     * It consists of changing the course status do 'Enroll' and the close date to
     * the date passed as parameter, and saves the course in the repository
     * @param course
     * @param endDateEnroll
     */
    @Transactional
    public void openEnrollments(Course course, Date endDateEnroll){

        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        course.openEnrollments(endDateEnroll);
        courseRepository.save(course);
    }


    /**
     * This method returns the list of courses with Enroll Status.
     * Enrollments are available to be closed if, and only if, the course has an enroll status, otherwise it's not possible.
     *
     * @return list of courses with Enroll status
     */

    public Iterable<Course> listEnrollCourses(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        return courseRepository.findCourseWithEnrollStatus();
    }

    /**
     * This method closes enrollments in a course.
     * It consists of changing the course status do 'IN_PROGRESS' and the close date to
     * the system date, and saves the course in the repository
     * @param course
     */
    @Transactional
    public void closeEnrollments(Course course){

        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        course.closeEnrollments();

        courseRepository.save(course);
    }

}
