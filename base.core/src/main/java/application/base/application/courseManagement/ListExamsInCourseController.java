package application.base.application.courseManagement;


import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.examManagement.ExamRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListExamsInCourseController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

   private ExamRepository examRepository = PersistenceContext.repositories().examRepository();

   private ListAvailableCoursesController listAvailableCoursesController = new ListAvailableCoursesController();

    public Iterable<Course> listCoursesAvailableForTeacher(){
        return listAvailableCoursesController.listCoursesAvailableForTeacher();
    }

    public Iterable<Exam> listAllExamsOfCourse(Course course){
        return examRepository.findExamsOfCourse(course);

    }


}