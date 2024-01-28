package application.base.UI.questionManagement;

import application.base.UI.examManagement.CreateUpdateExamUI;
import application.base.application.questionManagement.CreateUpdateQuestionController;
import application.base.domain.courseManagement.Course;
import application.base.domain.questionManagement.Question;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class CreateUpdateQuestionsUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUpdateExamUI.class);

    private CreateUpdateQuestionController controller = new CreateUpdateQuestionController();

    @Override
    public String headline() {
        return "Create/Update Question";
    }

    @Override
    protected boolean doShow() {
        System.out.println("1. Create Question");
        System.out.println("2. Update Question");
        System.out.println("0. Exit");

        final int option = Console.readOption(1, 2, 0);

        try {
            switch (option) {
                case 1:
                    createQuestion();
                    break;
                case 2:
                    updateQuestion();
                    break;
                default:
                    System.out.println("No valid option selected.");
                    break;
            }
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void createQuestion() throws IOException {
        Course course = chooseCourse();
        if(course != null) {
            String path = Console.readLine("Insert file path:");
            File file = controller.importFile(path);

            if (file != null) {
                Question question = controller.createQuestion(file, course);
                if (question != null) {
                    System.out.printf("Question created!! \n%s", question);
                } else {
                    System.out.println("Question not valid. Try again.");
                }
            } else {
                System.out.println("File not valid. Try again.");
            }
        }
    }

    private void updateQuestion() throws IOException {
        Question question = chooseQuestion();
        Course course = chooseCourse();
        if(question != null) {
            String path = Console.readLine("Insert file path:");
            File file = controller.importFile(path);

            if (file != null) {
                Question newQuestion = controller.updateQuestion(question, file, course);
                if (question != null) {
                    System.out.printf("The question was updated!! \n%s", newQuestion);
                } else {
                    System.out.println("Question not valid. Try again.");
                }
            } else {
                System.out.println("File not valid. Try again.");
            }
        }
    }

    /**
     * Method used to show the list of courses available for the teacher to create a question for.
     *
     * @return course selected
     */
    private Course chooseCourse() {
        Iterable<Course> coursesList = controller.findCoursesAvailableForTeacher();
        if (coursesList.iterator().hasNext()) {
            final SelectWidget<Course> selector = new SelectWidget<>("Select a course to crete a question for:", coursesList);
            selector.show();
            return selector.selectedElement();
        } else {
            System.out.println("You have no courses available!");
        }
        return null;
    }

    /**
     * Method used to show the list of all existing questions
     *
     * @return question selected
     */
    private Question chooseQuestion() {
        Iterable<Question> questionList = controller.findAllQuestions();
        if (questionList.iterator().hasNext()) {
            final SelectWidget<Question> selector = new SelectWidget<>("Select a question to update:", questionList);
            selector.show();
            return selector.selectedElement();
        } else {
            System.out.println("No questions available!");
        }
        return null;
    }
}
