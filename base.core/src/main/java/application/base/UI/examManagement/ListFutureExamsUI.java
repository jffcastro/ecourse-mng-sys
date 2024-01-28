package application.base.UI.examManagement;

import application.base.application.examManagement.ListFutureExamsController;
import application.base.domain.examManagement.Exam;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;
import java.util.Optional;


public class ListFutureExamsUI extends AbstractUI {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private ListFutureExamsController controller = new ListFutureExamsController();

    protected boolean doShow() {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)) {
            List<Exam> studentFutureExams = controller.findListStudentFutureExams();
            if(!studentFutureExams.isEmpty()) {
                for (Exam exam : studentFutureExams) {
                    System.out.printf("Exam id %s --> %s, %s\n",exam.identity(), exam.title(), exam.description());
                }
            } else {
                System.out.println("There are no future exams");
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "List Student Future Exams";
    }
    }

