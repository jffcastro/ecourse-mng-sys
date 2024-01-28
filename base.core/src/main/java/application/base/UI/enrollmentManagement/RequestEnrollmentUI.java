package application.base.UI.enrollmentManagement;

import application.base.application.courseManagement.ListAvailableCoursesController;
import application.base.application.enrollmentManagement.RequestEnrollmentController;
import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.EnrollmentsStatus;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class RequestEnrollmentUI extends AbstractUI {

    private RequestEnrollmentController theController = new RequestEnrollmentController();

    private ListAvailableCoursesController theController1 = new ListAvailableCoursesController();

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();


    @Override
    public String headline() {
        return "Request Enrollment";
    }

    @Override
    protected boolean doShow() {
        Iterable<Course> enrolledList = theController1.listCoursesWhereStudentIsEnroll();
        final Iterable<Course> enrollCoursesList = this.theController.listCoursesWhereStudentCanEnroll(enrolledList);

        if (!enrollCoursesList.iterator().hasNext())
            System.out.println("There are no courses available to request enrollments!");
        else {
            Course theCourse = chooseCourse("Select a course to request the enrollment:", enrollCoursesList);

            if (theCourse != null) {
                theController.createEnrollment(theCourse);
                System.out.println("Your request to enroll in this course was a success!!");
            }
        }
        return false;
    }

    /**
     * Method used to show course list and select one coruse.
     *
     * @param message - message to be printed to the user, asking to select a course
     * @param courseList - course list to choose from
     * @return course selected
     */
    private Course chooseCourse(String message, Iterable<Course> courseList) {
        final SelectWidget<Course> selector = new SelectWidget<>(message, courseList);
        selector.show();
        return selector.selectedElement();
    }
}
