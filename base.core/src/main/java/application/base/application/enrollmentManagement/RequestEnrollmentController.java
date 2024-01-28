package application.base.application.enrollmentManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.enrollmentManagement.EnrollmentBuilder;
import application.base.domain.enrollmentManagement.EnrollmentsStatus;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestEnrollmentController {
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();

    private StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();

    /**
     * This method accesses the repository and returns the list of courses where student can enroll, which
     * is of courses where the status is 'ENROLL', excluding those where the student is already enrolled at.
     *
     * @param coursesWhereStudentIsEnroll - course where student is enroll. This list comes from another controller.
     * @return list of courses where student can enroll
     */
    public Iterable<Course> listCoursesWhereStudentCanEnroll(Iterable<Course> coursesWhereStudentIsEnroll) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        Student student = studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser());
        Iterable<Course> coursesWhereStudentCanEnroll = courseRepository.findCourseListForStudentWhereHeCanEnroll(student);
        return removeCoursesWhereStudentIsAlreadyEnrolled((List<Course>) coursesWhereStudentIsEnroll, (List<Course>) coursesWhereStudentCanEnroll);
    }

    /**
     * This method removes the courses where the student is already enrolled from
     * the list of courses where he can enroll.
     *
     * @param coursesWhereStudentIsEnroll  - list of courses where student is enrolled
     * @param coursesWhereStudentCanEnroll - list of courses where student can enroll
     * @return
     */
    private Iterable<Course> removeCoursesWhereStudentIsAlreadyEnrolled(List<Course> coursesWhereStudentIsEnroll, List<Course> coursesWhereStudentCanEnroll) {
        List<Course> coursesToEnroll = new ArrayList<>(coursesWhereStudentCanEnroll);

        for (Course enrolledCourse : coursesWhereStudentIsEnroll) {
            coursesToEnroll.removeIf(course -> course.sameAs(enrolledCourse));
        }

        return coursesToEnroll;
    }

    /**
     * This method creates an enrollment request and saves it in the repository.
     * Notes:
     * - When creating an enrollment request, the status is 'UNDER_APPRECIATION'
     * - The student is the studeng related to the user logged in the system
     *
     * @param course - course for the enrollment
     */
    public void createEnrollment(Course course) {
        Student student = studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser());

        final Enrollment newEnrollment = new EnrollmentBuilder()
                .withEnrollmentStatus(EnrollmentsStatus.UNDER_APPRECIATION)
                .ofStudent(student)
                .inCourse(course).build();

        enrollmentRepository.save(newEnrollment);
    }
}
