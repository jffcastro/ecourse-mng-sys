package application.base.UI.examManagement;

import application.base.application.examManagement.CreateUpdateExamController;
import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class CreateUpdateExamUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUpdateExamUI.class);

    private CreateUpdateExamController controller = new CreateUpdateExamController();

    @Override
    public String headline() {
        return "Create/Update Exam";
    }

    @Override
    protected boolean doShow() {
        System.out.println("1. Create Exam");
        System.out.println("2. Update Exam");
        System.out.println("0. Exit");

        final int option = Console.readOption(1, 2, 0);

        try {
            switch (option) {
                case 1:
                    createExam();
                    break;
                case 2:
                    updateExam();
                    break;
                default:
                    System.out.println("No valid option selected.");
                    break;
            }
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void createExam() throws IOException {
        Course course = chooseCourse();
        if (course != null) {
            String path = Console.readLine("Insert file path:");
            File file = controller.importFile(path);

            if (file != null) {
                Exam exam = controller.createExam(course, file);
                if (exam != null) {
                    System.out.printf("Exam created: %s\n", exam);
                } else {
                    System.out.println("Exam not valid. Try again.");
                }
            } else {
                System.out.println("File not valid. Try again.");
            }
        } else {
            System.out.println("No courses available for you to create exams for.");
        }
    }

    private void updateExam() throws IOException {
        Exam exam = chooseExam();

        if (exam != null) {
            String path = Console.readLine("Insert file path:");
            File file = controller.importFile(path);

            if (file != null) {
                Exam newExam = controller.updateExam(exam, file);
                if (exam != null) {
                    System.out.printf("Updated exam: %s\n", newExam);
                } else {
                    System.out.println("Exam not valid. Try again.");
                }
            } else {
                System.out.println("File not valid. Try again.");
            }
        } else {
            System.out.println("No exams available for you to update.");
        }
    }

    /**
     * Method used to show the list of courses available for the teacher to create an exam for.
     *
     * @return course selected
     */
    private Course chooseCourse() {
        Iterable<Course> coursesList = controller.findCoursesAvailableForTeacher();
        if (coursesList.iterator().hasNext()) {
            final SelectWidget<Course> selector = new SelectWidget<>("Select a course to crete an exam for:", coursesList);
            selector.show();
            return selector.selectedElement();
        } else {
            System.out.println("No courses available to create exams.");
        }
        return null;
    }

    /**
     * Method used to show the list of exams available for teacher and select one exam to update.
     *
     * @return exam selected
     */
    private Exam chooseExam() {
        Iterable<Exam> examsList = controller.findExamsAvailableForTeacher();
        if (examsList.iterator().hasNext()) {
            final SelectWidget<Exam> selector = new SelectWidget<>("Select an exam to update:", examsList);
            selector.show();
            return selector.selectedElement();
        } else {
            System.out.println("No courses available to create exams.");
        }
        return null;
    }

}
