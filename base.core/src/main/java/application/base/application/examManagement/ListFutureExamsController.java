package application.base.application.examManagement;

import application.base.application.enrollmentManagement.BulkEnrollStudentsService;
import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.domain.examManagement.Exam;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Optional;

public class ListFutureExamsController {
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private ListFutureExamsService svc = new ListFutureExamsService();

    private StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();


    public List<Exam> findListStudentFutureExams(){
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)) {
            Student student = studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser());
            List<Exam> studentFutureExams = svc.findListStudentFutureExams(student);
            return studentFutureExams;
        }
        return null;
}
}
