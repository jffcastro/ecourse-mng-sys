package application.base.UI.examManagement;

import application.base.application.examManagement.CreateUpdateAutoExamController;
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

public class CreateUpdateAutoExamUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUpdateAutoExamUI.class);

    private CreateUpdateAutoExamController controller = new CreateUpdateAutoExamController();

    @Override
    protected boolean doShow() {
        System.out.println("1. Create Auto Exam");
        System.out.println("2. Check Auto Exam");
        System.out.println("0. Exit");

        final int option = Console.readInteger("Please choose an option");

        try {
            if (option == 1) {
                Exam exam = createAutoExam();
                if (exam!=null){
                    getExamInfo(exam);
                } else {
                    System.out.println("No exam created.");
                }
            } else if (option == 2) {
                Exam exam = controller.checkExam(Console.readLine("Insert exam id:"));
                getExamInfo(exam);
            } else {
                System.out.println("No valid option selected.");
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

    public Exam createAutoExam() throws IOException {
        Course course = chooseCourse();
        if (course != null) {
            String path = Console.readLine("Insert file path:");
            File file = controller.importFile(path);

            if (file != null) {
                Exam exam = controller.createAutoExam(course, file);
                if (exam != null) {
                    System.out.printf("Auto Exam created: %s\n", exam);
                    return exam;
                } else {
                    System.out.println("Exam not created. Quotation is not 20.");
                }
            } else {
                System.out.println("File not valid. Try again.");
            }
        } else {
            System.out.println("No courses available for you to create exams for.");
        }
        return null;
    }


    /**
     * Method used to show the list of courses available for the teacher to create an exam for.
     *
     * @return course selected
     */
    private Course chooseCourse() {
        Iterable<Course> coursesList = controller.findCoursesAvailableForStudent();
        if (coursesList.iterator().hasNext()) {
            final SelectWidget<Course> selector = new SelectWidget<>("Select a course to crete an exam for:", coursesList);
            selector.show();
            return selector.selectedElement();
        } else {
            System.out.println("No courses available to create exams.");
        }
        return null;
    }

    private void getExamInfo(Exam exam){
        for (int i = 0; i < exam.sections().size(); i++) {
            for (int j = 0; j < exam.sections().get(i).questions().size(); j++) {
                System.out.printf("\nSection %d, Type :%s Question %d: %s with quotation =%s\n", i + 1, exam.sections().get(i).questions().get(j).questionType(),j + 1 ,exam.sections().get(i).questions().get(j).question(), exam.sections().get(i).questions().get(j).quotation());
            }
        }
    }



    /**
     * Method used to show the list of exams available for teacher and select one exam to update.
     *
     * @return exam selected
     */

    @Override
    public String headline() {
        return "Create/Update Auto Exam";
    }
}
