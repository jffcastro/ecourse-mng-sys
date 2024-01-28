package eapli.UI;

import eapli.Controller.ChangePostItController;
import eapli.SharedBoardClient;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangePostItUI extends AbstractUI {

    private static final ChangePostItController controller = new ChangePostItController();

    @Override
    public String headline() {
        return "Change Post-It";
    }

    @Override
    protected boolean doShow() {
        try {
            List<String> listSharedBoards = controller.listSharedBoards(SharedBoardClient.userName);
            chooseSharedBoard("Select a shared board", listSharedBoards);

            if (SharedBoardClient.sharedBoard != null) {
                List<String> postItsList = controller.listPostIts(SharedBoardClient.userName, SharedBoardClient.sharedBoard);
                choosePostIt("Select a post it", postItsList);
                if (SharedBoardClient.postIt != null) {
                    System.out.println("Chose a change action!");
                    System.out.println("1. Move post-it to another cell.");
                    System.out.println("2. Change post-it content.");
                    System.out.println("0. Exit");

                    final int option = Console.readOption(1, 2, 0);

                    if (option == 0) {
                        return false;
                    }


                    switch (option) {
                        case 1:
                            List<String> cellsList = controller.listCells(SharedBoardClient.sharedBoard);
                            chooseCell("Select a cell", cellsList);
                            if (SharedBoardClient.cell != null) {
                                controller.movePostItToOtherCell(SharedBoardClient.postIt, SharedBoardClient.cell);
                            }
                            break;

                        case 2:
                            String content = Console.readNonEmptyLine("Content: ", "You must put content!");
                            while (content.length() <= 1 || content.length() > 500) {
                                System.out.println("Content must have characters but less than 500!");
                                content = Console.readNonEmptyLine("Content: ", "You must put content!");
                            }
                            controller.changeContentOfPostIt(SharedBoardClient.postIt, content);
                            break;

                        default:
                            System.out.println("Invalid option");
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

    private String choosePostIt(String message, List<String> postItList) {
        if (!postItList.isEmpty()) {
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
        }else{
            System.out.println("No post-it's available");
            return null;}
    }

    private String chooseCell(String message, List<String> cellList) {
        if (!cellList.isEmpty()) {
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
        } else
            System.out.println("No cells available");
        return null;
    }

}