package application.base.UI.examManagement;

import application.base.application.examManagement.TakeExamController;
import application.base.domain.enrollmentManagement.Grade;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class TakeExamUI extends AbstractUI {

    private TakeExamController controller = new TakeExamController();

    @Override
    public String headline() {
        return "Take Exam";
    }

    @Override
    protected boolean doShow() {
        Iterable<Exam> examsAvailableList = controller.findExamsAvailableForStudentToTake();

        if (examsAvailableList.iterator().hasNext()){
            Exam exam = chooseExam(examsAvailableList);
            if (exam != null) {
                Grade grade = CommonTakeExamUI.takeExam(exam);
                controller.saveStudentGradeInExam(exam, grade);
                if (exam.header().gradeType().equals(GradeType.ON_SUBMISSION)) {
                    System.out.printf("Final exam grade: %s%n", grade);
                } else if (exam.header().gradeType().equals(GradeType.AFTER_CLOSING)){
                    System.out.println("Exam grade will be returned when the exam closes.");
                } else if (exam.header().gradeType().equals(GradeType.NONE)){
                    System.out.println("No exam grade will be returned for this exam.");
                }
            }
        } else {
            System.out.println("No exams available to take.");
        }
        return false;
    }

    /**
     * Method used to show the list of exams available for the student to take and
     * ask to choose one option.
     *
     * @return exam selected
     */
    private Exam chooseExam(Iterable<Exam> examsList) {
        final SelectWidget<Exam> selector = new SelectWidget<>("Select an exam to take:", examsList);
        selector.show();
        return selector.selectedElement();
    }
}
