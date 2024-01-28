package eapli.UI;

import eapli.SharedBoardClient;
import eapli.SharedBoardClientController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class LoginUI extends AbstractUI {

    private static final int DEFAULT_MAX_ATTEMPTS = 3;

    private final SharedBoardClientController controller = new SharedBoardClientController();

    @Override
    protected boolean doShow() {
        int attempts = 0;
        String user, password;
        try {
            while (attempts < DEFAULT_MAX_ATTEMPTS) {
                user = Console.readLine("Username: ");
                password = Console.readLine("Password: ");
                if (controller.confirmAuth(user, password)) {
                    SharedBoardClient.userName = user;
                    System.out.printf("Welcome %s!\n", user);
                    return true;
                }
                System.out.printf("Incorrect password or username!!! You have %d more tries.\n\n", DEFAULT_MAX_ATTEMPTS - attempts -1);
                attempts++;
            }
            System.out.println("You have reached the maximum attempts. The program will close!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error connecting to the server!!!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Login";
    }
}
