package eapli.UI;

import eapli.SharedBoardClient;
import eapli.SharedBoardClientController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class DisconnectUI extends AbstractUI {
    private final SharedBoardClientController controller = new SharedBoardClientController();
    @Override
    protected boolean doShow() {
        try {
            if(!controller.disconnect()){
                System.out.println("Invalid code or response!");
            }
            System.out.println("Disconnect with success!!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String headline() {
        return "Disconnect";
    }
}
