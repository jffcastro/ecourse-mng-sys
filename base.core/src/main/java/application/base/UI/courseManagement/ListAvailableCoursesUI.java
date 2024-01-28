package application.base.UI.courseManagement;

import application.base.application.courseManagement.ListAvailableCoursesController;
import application.base.domain.courseManagement.Course;
import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListAvailableCoursesUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(ListAvailableCoursesUI.class);

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private ListAvailableCoursesController theController = new ListAvailableCoursesController();

    @Override
    protected boolean doShow() {
        if(authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER)) {
            Iterable<Course> coursesList = theController.listCoursesAvailableForManager();
            if(coursesList.iterator().hasNext()) {
                for (Course course : coursesList) {
                    System.out.printf("%s - %s\n", course.courseCode(), course.courseName());
                }
            } else {
                System.out.println("There are no available courses for you!");
            }
        }
        else if(authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)) {
            Iterable<Course> coursesList = theController.listCoursesAvailableForTeacher();
            if(coursesList.iterator().hasNext()) {
                for (Course course : coursesList) {
                    System.out.printf("%s - %s\n", course.courseCode(), course.courseName());
                }
            } else {
                System.out.println("There are no available courses for you!");
            }
        }
        else if(authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)){
            Iterable<Course> enrolledList = theController.listCoursesWhereStudentIsEnroll();
            Iterable<Course> canEnrollList = theController.listCoursesWhereStudentCanEnroll(enrolledList);

            System.out.println("Courses where you are already enrolled:");
            if(enrolledList.iterator().hasNext()) {
                for (Course courseHeIsEnroll : enrolledList) {
                    System.out.printf("%s - %s\n", courseHeIsEnroll.courseCode(), courseHeIsEnroll.courseName());
                }
            } else {
                System.out.println("There are no available courses for you!");
            }

            System.out.println("");
            System.out.println("Courses where you can enroll:");
            if (canEnrollList.iterator().hasNext()){
                for (Course courseHeCanEnroll : canEnrollList){
                        System.out.printf("%s - %s\n",courseHeCanEnroll.courseCode(), courseHeCanEnroll.courseName());
                    }
            } else {
                    System.out.println("There are no available courses for you!");
                }
            }
        return false;
    }

    @Override
    public String headline() {
        return "List courses";
    }
}
