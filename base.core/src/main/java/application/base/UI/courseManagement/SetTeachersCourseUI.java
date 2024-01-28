package application.base.UI.courseManagement;

import application.base.application.courseManagement.SetTeachersCourseController;
import application.base.domain.courseManagement.Course;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class SetTeachersCourseUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(ListAvailableCoursesUI.class);

    private SetTeachersCourseController theController = new SetTeachersCourseController();

    @Override
    protected boolean doShow() {
        Iterable<Course> coursesList = theController.findCourseWithOpenStatus();
        if (coursesList.iterator().hasNext()) {
            Course course = chooseCourse("Select course", coursesList);

            if (course != null) {
                List<Teacher> teacherList = theController.findTeachersAvailableForCourse(course);

                if (!teacherList.iterator().hasNext()) {
                    System.out.println("There are no teachers available!");
                } else {
                    if (!theController.courseHasTeacherInCharge(course)) {
                        Teacher teacherInCharge = chooseTeacher("Choose teacher in charge:", teacherList);
                        if (teacherInCharge != null) {
                            teacherList.remove(teacherInCharge);
                            theController.saveTeacherInCharge(teacherInCharge, course);
                            System.out.println("Teacher in charge was set successful.");
                        }
                    } else {
                        System.out.println("Course already has teacher in charge;");
                    }

                    boolean b = true;
                    while (!teacherList.isEmpty() && b) {
                        System.out.println("Select an option:");
                        System.out.println("1. Select a Teacher");
                        System.out.println("0. Close the loop");

                        int option = Console.readOption(1, 1, 0);

                        switch (option) {
                            case 1:
                                Teacher teacher = chooseTeacher("Select teacher", teacherList);
                                teacherList.remove(teacher);
                                String msg = theController.saveTeacher(teacher, course);
                                System.out.println(msg);
                                break;
                            case 0:
                                b = false;
                                break;
                            default:
                                System.out.println("Invalid option");
                        }
                    }
                }
                System.out.println("Set operation was successful");
            } else {
                return false;
            }
        } else {
            System.out.println("There are no courses to list!");
        }
        return false;
    }


    @Override
    public String headline() {
        return "Set teacher of a course";
    }

    private Course chooseCourse(String message, Iterable<Course> courseList) {
        final SelectWidget<Course> selector = new SelectWidget<>(message, courseList);
        selector.show();
        return selector.selectedElement();
    }

    private Teacher chooseTeacher(String message, Iterable<Teacher> teacherList) {
        final SelectWidget<Teacher> selector = new SelectWidget<>(message, teacherList);
        selector.show();
        return selector.selectedElement();
    }
}
