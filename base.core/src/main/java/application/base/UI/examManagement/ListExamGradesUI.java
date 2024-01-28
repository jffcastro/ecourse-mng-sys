package application.base.UI.examManagement;

import application.base.application.examManagement.ListExamGradesController;
import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.ExamEnrollment;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;


public class ListExamGradesUI extends AbstractUI {

    private ListExamGradesController controller = new ListExamGradesController();

    @Override
    public String headline() {
        return "List Grades of Courses";
    }

    protected boolean doShow() {
        Iterable<Course> coursesAvailableList = controller.findCoursesAvailableForTeacher();

        if (coursesAvailableList.iterator().hasNext()) {
            System.out.println("1. View grades from a course");
            System.out.println("2. View grades from all courses available");
            System.out.println("0. Exit");

            final int option = Console.readOption(1, 2, 0);

            try {
                switch (option) {
                    case 1:
                        Course course = chooseExam(coursesAvailableList);
                        viewGradesFromCourse(course);
                        break;
                    case 2:
                        viewGradesFromAllCourses(coursesAvailableList);
                        break;
                    default:
                        System.out.println("No valid option selected.");
                        break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No exams available.");
        }
        return false;
    }

    /**
     * Method used to show the list of exams available for teacher and select one exam to update.
     *
     * @return exam selected
     */
    private Course chooseExam(Iterable<Course> coursesList) {
        final SelectWidget<Course> selector = new SelectWidget<>("Select an exam to view grades list for:", coursesList);
        selector.show();
        return selector.selectedElement();
    }

    private void viewGradesFromCourse(Course course) {
        Iterable<ExamEnrollment> examGrades = controller.findExamEnrollsOfCourse(course);
        if (examGrades.iterator().hasNext()) {
            System.out.println();
            System.out.println("Grades of course selected:");
            for (ExamEnrollment examEnrollment : examGrades) {
                System.out.printf("-> Exam: %s - Student: %s, Grade: %s\n",
                        examEnrollment.examID(),
                        examEnrollment.enrollment().student().mecanographicNumber(),
                        examEnrollment.examGrade());
            }
        } else {
            System.out.println("No grades for the exam selected.");
        }
    }

    private void viewGradesFromAllCourses(Iterable<Course> coursesAvailableList) {
        int gradesCount = 0;
        System.out.println();
        for (Course course : coursesAvailableList) {
            Iterable<ExamEnrollment> examGrades = controller.findExamEnrollsOfCourse(course);
            if (examGrades.iterator().hasNext()) {
                System.out.printf("Grades of Course %s:\n", course.courseCode());
                for (ExamEnrollment examEnrollment : examGrades) {
                    gradesCount++;
                    System.out.printf("-> Exam: %s - Student: %s, Grade: %s\n",
                            examEnrollment.examID(),
                            examEnrollment.enrollment().student().mecanographicNumber(),
                            examEnrollment.examGrade());
                }
            }
        }

        if (gradesCount == 0) {
            System.out.println("No grades available or any course.");
        }
    }

}