package eapli.UI;

import eapli.Controller.CreatePostItController;
import eapli.SharedBoardClient;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreatePostItUI extends AbstractUI {

    private static final CreatePostItController theController = new CreatePostItController();


    @Override
    public String headline() {
        return "Create Post-It";
    }


    @Override
    protected boolean doShow() {
        try {
            List<String> sharedBoardList = theController.listSharedBoards(SharedBoardClient.userName);
            chooseSharedBoard("Select a Shared Board", sharedBoardList);

            if (SharedBoardClient.sharedBoard != null) {
                List<String> cellList = theController.listCells(SharedBoardClient.sharedBoard);
                String c = chooseCell("Select a free cell", cellList);

                if (c != null) {
                    System.out.println("To add a post-it insert:");

                    String content = Console.readNonEmptyLine("Content: ", "You must put content!");
                    while (content.length() <= 1 || content.length() > 500) {
                        System.out.println("Content must have characters but less than 500!");
                        content = Console.readNonEmptyLine("Content: ", "You must put content!");
                    }

                    try {
                        theController.createPostIt(SharedBoardClient.userName, content, SharedBoardClient.cell);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
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

    private String chooseCell(String message, List<String> cellList) {
        final SelectWidget<String> selector = new SelectWidget<>(message, cellList);
        selector.show();
        int selectedIndex = selector.selectedOption();

        if (selectedIndex == 0) {
            return null;
        } else if (selectedIndex > 0 && selectedIndex <= cellList.size()) {
            String cell = cellList.get(selectedIndex - 1);
            SharedBoardClient.cell = cell;
            return cell;
        } else {
            System.out.println("Invalid Option!");
            return null;
        }
    }
}
