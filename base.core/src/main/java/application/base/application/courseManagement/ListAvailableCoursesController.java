package application.base.application.courseManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.teachingManagement.Teaching;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.repositories.teachingManagment.TeachingRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import application.base.usermanagement.repositories.StudentRepository;
import application.base.usermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListAvailableCoursesController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();

    private StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();

    private TeachingRepository teachingRepository = PersistenceContext.repositories().teachingRepository();

    private EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();

    @Transactional
    public Iterable<Course> listCoursesAvailableForManager() {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER)) {
            return courseRepository.findAll();
        }
        return null;
    }

    public Iterable<Course> listCoursesAvailableForTeacher() {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)) {
            if (authorizationService.session().isPresent()) {
                Teacher teacher = teacherRepository.findBySystemUser(authorizationService.session().get().authenticatedUser()).get();
                return teachingRepository.findCoursesWhereTeacherTeaches(teacher);
            }
        }
        return null;
    }

    public Iterable<Course> listCoursesAvailableForStudent() {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)) {
            if (authorizationService.session().isPresent()) {
                Student student = studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser());
                return enrollmentRepository.findCoursesStudentIsEnroll(student);
            }
        }
        return null;
    }

    public Iterable<Course> listCoursesWhereStudentIsEnroll() {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)) {
            if (authorizationService.session().isPresent()) {
                Student student = studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser());
                return enrollmentRepository.findCoursesStudentIsEnroll(student);
            }
        }
        return null;
    }

    public Iterable<Course> listCoursesWhereStudentCanEnroll(Iterable<Course> coursesWhereStudentIsEnroll) {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)) {
            if (authorizationService.session().isPresent()) {
                Student student = studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser());
                Iterable<Course> coursesInEnrollStatus = courseRepository.findCourseListForStudentWhereHeCanEnroll(student);
                return removeCoursesWhereStudentIsAlreadyEnrolled((List<Course>) coursesWhereStudentIsEnroll, (List<Course>) coursesInEnrollStatus);
            }
        }
        return null;
    }

    private Iterable<Course> removeCoursesWhereStudentIsAlreadyEnrolled(List<Course> coursesWhereStudentIsEnroll, List<Course> coursesEnrollStatus) {
        List<Course> coursesToEnroll = new ArrayList<>(coursesEnrollStatus);

        for (Course enrolledCourse : coursesWhereStudentIsEnroll) {
            coursesToEnroll.removeIf(course -> course.sameAs(enrolledCourse));
        }

        return coursesToEnroll;
    }
}
