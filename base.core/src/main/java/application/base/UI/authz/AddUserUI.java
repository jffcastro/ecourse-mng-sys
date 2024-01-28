package application.base.UI.authz;

import application.base.UI.clientuser.AcceptRefuseSignupRequestUI;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddUserUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptRefuseSignupRequestUI.class);

    @Override
    protected boolean doShow() {
        System.out.println("1. Add Manager");
        System.out.println("2. Add Teacher");
        System.out.println("3. Add Student");
        System.out.println("0. Exit");

        final int option = Console.readOption(1, 3, 0);

        try {
            switch (option) {
                case 1:
                    AddManagerUI addManagerUI = new AddManagerUI();
                    addManagerUI.doShow();
                    break;
                case 2:
                    AddTeacherUI addTeacherUI = new AddTeacherUI();
                    addTeacherUI.doShow();
                    break;
                case 3:
                    AddStudentUI addStudentUI = new AddStudentUI();
                    addStudentUI.doShow();
                    break;
                default:
                    System.out.println("No valid option selected.");
                    break;
            }
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }
        return true;
    }

    public String headline() {
        return "Add User";
    }

}
