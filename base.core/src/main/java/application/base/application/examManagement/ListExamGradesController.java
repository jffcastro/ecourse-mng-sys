package application.base.application.examManagement;

import application.base.application.courseManagement.ListAvailableCoursesController;
import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.repositories.examManagement.ExamRepository;
import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;


public class ListExamGradesController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private ListAvailableCoursesController listAvailableCoursesController = new ListAvailableCoursesController();

    private ExamEnrollmentRepository examEnrollmentRepository = PersistenceContext.repositories().examEnrollmentRepository();

    /**
     * This method find the exams available for the teacher
     * logged in the system.
     *
     * @return iterable of exams available for the teacher
     * logged in the system
     */
    public Iterable<Course> findCoursesAvailableForTeacher(){
        return listAvailableCoursesController.listCoursesAvailableForTeacher();
    }

    /**
     * This method finds the exam enrolls of a given course.
     *
     * @param course - course to find exam enrolls of
     * @return list of exam enrolls of a given course
     */
    public Iterable<ExamEnrollment> findExamEnrollsOfCourse(Course course) {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)) {
            return examEnrollmentRepository.findGradesOfCourse(course);
        }
        return null;
    }
}
