package eapli.UI;

import eapli.SharedBoardClientController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class ConnectionUI extends AbstractUI {
    private final SharedBoardClientController controller = new SharedBoardClientController();

    @Override
    protected boolean doShow(){
        try {
            System.out.println("Connection");

            if(!controller.commtest()){
                System.out.println("Invalid code or response!");
            }
            System.out.println("Connection established!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Connection Test";
    }
}
