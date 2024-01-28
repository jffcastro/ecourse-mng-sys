package eapli.UI;

import eapli.Controller.CreatePostItController;
import eapli.Controller.UndoPostItController;
import eapli.SharedBoardClient;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UndoPostItUI extends AbstractUI {

    private static final UndoPostItController theController = new UndoPostItController();


    @Override
    protected boolean doShow() {
        try {
            List<String> listSharedBoards = theController.listSharedBoards(SharedBoardClient.userName);
            chooseSharedBoard("Select a shared board", listSharedBoards);

            if (SharedBoardClient.sharedBoard != null) {

                List<String> postItsList = theController.listPostIts(SharedBoardClient.userName, SharedBoardClient.sharedBoard);
                choosePostIt("Select a post it", postItsList);
                System.out.println("\nChecking...\n");
                if (SharedBoardClient.postIt != null) {
                    System.out.println("Undoing post-it " + SharedBoardClient.postIt + " on shared board " + SharedBoardClient.sharedBoard + "...");
                    theController.undoPostIt(SharedBoardClient.postIt);
                } else {
                    System.out.println("Null PostIt!");
                }
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

    private String choosePostIt(String message, List<String> postItList) {

        final SelectWidget<String> selector = new SelectWidget<>(message, postItList);
        selector.show();
        int selectedIndex = selector.selectedOption();


        if (selectedIndex == 0) {
            return null;
        } else if (selectedIndex > 0 && selectedIndex <= postItList.size()) {
            String postIt = postItList.get(selectedIndex - 1);
            SharedBoardClient.postIt = postIt;
            return postIt;
        } else {
            System.out.println("Invalid Option!");
            return null;
        }
    }

    @Override
    public String headline() {
        return "Undo Post-It";
    }
}
