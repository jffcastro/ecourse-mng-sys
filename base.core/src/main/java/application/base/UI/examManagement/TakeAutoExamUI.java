package application.base.UI.examManagement;

import application.base.domain.enrollmentManagement.Grade;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class TakeAutoExamUI extends AbstractUI {

    private CreateUpdateAutoExamUI createUpdateAutoExamUI = new CreateUpdateAutoExamUI();

    @Override
    protected boolean doShow() {
        try {
            Exam exam = createUpdateAutoExamUI.createAutoExam();
            if (exam != null) {
                Grade grade = CommonTakeExamUI.takeExam(exam);
                if (grade != null) {
                    System.out.printf("Final exam grade: %s%n", grade);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String headline() {
        return "Take Automatic Exam";
    }
}
