package application.base.UI.enrollmentManagement;

import application.base.application.enrollmentManagement.BulkEnrollStudentsController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;

public class BulkEnrollStudentsUI extends AbstractUI {

    private BulkEnrollStudentsController controller = new BulkEnrollStudentsController();

    @Override
    public String headline() {
        return "Bulk Enroll Students";
    }

    @Override
    protected boolean doShow() {
        String path = Console.readLine("Insert file path. \nFile must have only two collumns" +
                ", the first named 'Student', which contains the student's mecanographic number " +
                ", and the second named 'Course', which contains the course's name:");

        File file = controller.importFile(path);

        boolean fileIsValid = controller.validateFile(file);

        if (fileIsValid) {
            System.out.println("Processing data...");
            Integer enrollmentsCreated = controller.bulkEnrollStudents(file);
            System.out.printf("Operation successful. %d enrollments created.\n", enrollmentsCreated);
        } else {
            System.out.println("File not valid.");
        }

        return true;
    }

}