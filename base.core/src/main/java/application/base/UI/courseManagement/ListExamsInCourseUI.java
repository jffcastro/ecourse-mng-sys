package application.base.UI.courseManagement;
import application.base.application.courseManagement.ListExamsInCourseController;
import application.base.application.courseManagement.SetTeachersCourseController;
import application.base.domain.examManagement.Exam;
import application.base.usermanagement.domain.BaseRoles;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import application.base.domain.courseManagement.Course;
import org.apache.logging.log4j.LogManager;

import java.util.List;

public class ListExamsInCourseUI extends AbstractUI {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private ListExamsInCourseController controller = new ListExamsInCourseController();

    @Override
    protected boolean doShow() {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)) {
            Iterable<Course> teacherCoursesList = controller.listCoursesAvailableForTeacher();
            if (teacherCoursesList.iterator().hasNext()) {
                Course course = chooseCourse("Select course", teacherCoursesList);
                if (course != null) {
                    Iterable<Exam> teacherCourseExamsList = controller.listAllExamsOfCourse(course);
                    if (teacherCourseExamsList.iterator().hasNext()) {
                        for (Exam exam : teacherCourseExamsList) {
                            System.out.printf("Exam id %s --> %s, %s\n", exam.identity(), exam.title(), exam.description());
                        }
                    } else {
                        System.out.println("List of exams is empty");
                    }
                    System.out.println("Operation success!");
                } else{
                    return false;
                }
            } else {
                System.out.println("List of courses empty");
            }
        }
        return false;
    }
    @Override
    public String headline() {
        return "List exams in a course of a teacher";
    }

    private Course chooseCourse(String message, Iterable<Course> courseList){
        final SelectWidget<Course> selector = new SelectWidget<>(message, courseList);
        selector.show();
        return selector.selectedElement();
    }


}
