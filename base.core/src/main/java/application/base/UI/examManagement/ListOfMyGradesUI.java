package application.base.UI.examManagement;

import application.base.application.examManagement.ListOfMyGradesController;
import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

public class ListOfMyGradesUI extends AbstractUI {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();
    private ListOfMyGradesController theController = new ListOfMyGradesController();

    @Override
    protected boolean doShow() {
        Iterable<ExamEnrollment> listGrades = theController.listMyGrades(studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser()));
        if(!listGrades.iterator().hasNext()){
            System.out.println("You don´t have any grades available!");
        }
        else{
            for(ExamEnrollment examEnrollment : listGrades){
                System.out.printf("Course> %s: Exam %d - Grade: %s\n", examEnrollment.enrollment().course().courseCode(), examEnrollment.examID(), examEnrollment.examGrade());
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "View the list of my grades";
    }
}
