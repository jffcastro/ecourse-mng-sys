package eapli.UI;

import eapli.Controller.ViewBoardHistoryOfUpdatesController;
import eapli.SharedBoardClient;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewBoardHistoryOfUpdatesUI extends AbstractUI {

    private static final ViewBoardHistoryOfUpdatesController controller = new ViewBoardHistoryOfUpdatesController();

    @Override
    public String headline() {
        return "View History of Updates of a Board";
    }

    @Override
    protected boolean doShow() {
        try {
            List<String> sharedBoardList = controller.listSharedBoards(SharedBoardClient.userName);

            chooseSharedBoard("Select a shared board:", sharedBoardList);

            List<String> data = controller.findHistoryOfUpdatesOfSharedBoard(SharedBoardClient.sharedBoard);
            System.out.printf("Shared Board History: %n");
            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public String chooseSharedBoard(String message, List<String> sharedBoardList) {
        List<String> options = new ArrayList<>();
        for (String sharedBoard : sharedBoardList) {
            options.add(sharedBoard);
        }

        final SelectWidget<String> selector = new SelectWidget<>(message, options);
        selector.show();
        int selectedIndex = selector.selectedOption();

        if (selectedIndex == 0) {
            return null;
        } else if (selectedIndex > 0 && selectedIndex <= sharedBoardList.size()) {
            String sharedBoardName = sharedBoardList.get(selectedIndex - 1);
            SharedBoardClient.sharedBoard = sharedBoardName;
            return sharedBoardName;
        } else {
            System.out.println("Invalid Option!");
            return null;
        }
    }
}
