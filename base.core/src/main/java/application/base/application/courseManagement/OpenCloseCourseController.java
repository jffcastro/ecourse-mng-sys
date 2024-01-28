package application.base.application.courseManagement;

import application.base.domain.courseManagement.Course;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class OpenCloseCourseController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    /**
     * This method accesses the course repository and returns the list of courses avaliable to be open.
     * A course is available to be open if, and only if, it is close, otherwise it's not possible,
     * and if it has not be open yet (no course close date).
     *
     * @return iterable with the courses available to be opened
     */
    public Iterable<Course> findCoursesAvailableToOpen() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        return courseRepository.findCoursesAvailableToOpen();
    }

    /**
     * This method validates the course close date.
     * The date is valid if it is later than system date.
     *
     * @param closeDate - course close date inserted by the user
     * @return date validation
     */
    public boolean validateDate(Date closeDate){
        if (!closeDate.before(Date.from(Instant.now()))) {
            return true;
        }
        return false;
    }

    /**
     * This method opens a course, which consists of changing the course status to 'OPEN'
     * and the close date to the date passed as parameter, and saves the course in the repository.
     *
     * @param course - course to be opened
     * @param closeDate - course close date
     */
    public void openCourse(Course course, Date closeDate) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        course.openCourse(closeDate);
        saveCourse(course);
    }

    /**
     * This method accesses the course repository and returns the list of courses where the student can enroll.
     * A course is available to be closed if in any state, execpt if already closed.
     *
     * @return iterable with the courses available to be closed
     */
    public Iterable<Course> findCoursesAvailableToClose() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        return courseRepository.findCoursesAvailableToClose();
    }

    /**
     * This method closes a course, which consists of changing the course status to 'CLOSE'
     * and the close date to the sytem date, and saves the course in the repository.
     *
     * @param course - course to be closed
     */
    public void closeCourse(Course course) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        course.closeCourse();
        saveCourse(course);
    }

    /**
     * This method saves the course, with the new status, in the course repository.
     *
     * @param course - course to be saved
     */
    private void saveCourse(Course course) {
        courseRepository.save(course);
    }

}
