package application.base.UI.examManagement;

import application.base.UI.clientuser.AcceptRefuseSignupRequestUI;
import application.base.application.examManagement.CommonTakeExamController;
import application.base.domain.enrollmentManagement.Grade;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.Section;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionType;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommonTakeExamUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptRefuseSignupRequestUI.class);

    private static CommonTakeExamController controller = new CommonTakeExamController();

    protected static Grade takeExam(Exam exam) {

        System.out.println("1. Take Manual Exam");
        System.out.println("2. Take Exam by File");
        System.out.println("0. Exit");

        final int option = Console.readOption(1, 2, 0);

        Grade grade = null;
        try {
            switch (option) {
                case 1:
                    grade = takeManualExam(exam);
                    break;
                case 2:
                    grade = takeExamByFile(exam);
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
        System.out.println("Exam finished.");
        return grade;
    }


    protected static Grade takeManualExam(Exam exam) {
        Float grade = 0F;
        List<Section> sectionList = exam.sections();

        for (int i = 0; i < sectionList.size(); i++) {
            Section section = sectionList.get(i);
            System.out.println();
            System.out.printf("Section %d - %s:%n", i + 1, section.description());

            List<Question> questionList = section.questions();
            for (int j = 0; j < questionList.size(); j++) {
                Question question = questionList.get(j);
                System.out.printf("Question %d - %s%n", j + 1, question);
                String studentAnswer = askForStudentAnswer(question);
                boolean correctStudentAnswer = controller.correctQuestion(question, studentAnswer);
                if (correctStudentAnswer) {
                    grade += question.quotation();
                }
            }
        }

        return new Grade(grade);
    }


    private static String askForStudentAnswer(Question question) {
        if (question.questionType().equals(QuestionType.MATCHING)) {
            return askForMatchingAnswer();
        } else if (question.questionType().equals(QuestionType.MISSING_WORD)) {
            return askfForMissingWordAnswer();
        } else if (question.questionType().equals(QuestionType.MULTIPLE_CHOICE)) {
            return askForMultipleChoiceAnswer();
        } else if (question.questionType().equals(QuestionType.SHORT)) {
            return askForShortAnswer();
        } else if (question.questionType().equals(QuestionType.NUMERIC)) {
            return askForNumericAnswer();
        } else if (question.questionType().equals(QuestionType.TRUE_OR_FALSE)) {
            return askForTrueOrFalseAnswer();
        }

        return "";
    }

    private static String askForMatchingAnswer() {
        return Console.readLine("Answer this question with the following structure: (example: " +
                "leftOption1->rightOption2, leftOption2->rightOption1)");
    }

    private static String askfForMissingWordAnswer() {
        return Console.readLine("Answer this question with the following structure: " +
                "option1, option2");
    }

    private static String askForMultipleChoiceAnswer() {
        return Console.readLine("Type the correct answer:");
    }

    private static String askForShortAnswer() {
        return Console.readLine("Type your answer:");
    }

    private static String askForNumericAnswer() {
        return Console.readLine("Type your answer:");
    }

    private static String askForTrueOrFalseAnswer() {
        return Console.readLine("Answer with True/False:");
    }

    protected static Grade takeExamByFile(Exam exam) throws IOException {
        showExam(exam);

        String path = Console.readLine("Insert file path:");
        File file = controller.importFile(path);

        Grade grade = null;
        if (file.exists()) {
            grade = controller.correctExamByFile(exam, file);
        }

        return grade;
    }

    private static void showExam(Exam exam) {
        List<Section> sectionList = exam.sections();
        for (int i = 0; i < sectionList.size(); i++) {
            Section section = sectionList.get(i);
            System.out.println();
            System.out.printf("Section %d - %s:%n", i + 1, section.description());

            List<Question> questionList = section.questions();
            for (int j = 0; j < questionList.size(); j++) {
                Question question = questionList.get(j);
                System.out.printf("Question %d - %s%n", j + 1, question);
            }
        }
    }

}
