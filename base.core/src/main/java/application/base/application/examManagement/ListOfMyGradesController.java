package application.base.application.examManagement;

import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.repositories.sharedBoardManagement.SharedBoardRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

public class ListOfMyGradesController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private ExamEnrollmentRepository examEnrollment = PersistenceContext.repositories().examEnrollmentRepository();

    public Iterable<ExamEnrollment> listMyGrades(Student student){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        return examEnrollment.findExamGradesOfStudent(student);
    }
}
