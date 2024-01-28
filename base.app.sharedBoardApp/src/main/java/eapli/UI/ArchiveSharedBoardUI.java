package eapli.UI;

import eapli.Controller.ArchiveSharedBoardController;
import eapli.SharedBoardClient;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchiveSharedBoardUI extends AbstractUI {

    ArchiveSharedBoardController theController = new ArchiveSharedBoardController();
    @Override
    protected boolean doShow() {
        try {
            List<String> listSharedBoards = theController.listSharedBoards(SharedBoardClient.userName);
            chooseSharedBoard("Select a shared board", listSharedBoards);

            if (SharedBoardClient.sharedBoard != null) {
                System.out.println("Archiving Shared Board " + SharedBoardClient.sharedBoard);
                theController.archiveSharedBoard(SharedBoardClient.userName,SharedBoardClient.sharedBoard);
            } else {
                System.out.println("Null SharedBoard!");
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

    @Override
    public String headline() {
        return "Archive Shared Board";
    }
}
