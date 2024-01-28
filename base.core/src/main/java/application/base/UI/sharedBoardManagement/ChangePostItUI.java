package application.base.UI.sharedBoardManagement;

import application.base.application.sharedBoardManagement.ChangePostItController;
import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.PostIt;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Scanner;

public class ChangePostItUI extends AbstractUI {
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private ChangePostItController theController = new ChangePostItController();


    @Override
    protected boolean doShow() {
        SystemUser user = null;
        if (authorizationService.session().isPresent()) {
            user = this.authorizationService.session().get().authenticatedUser();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Chose a change action!");
            System.out.println("1. Move post-it to another cell.");
            System.out.println("2. Change post-it content.");
            System.out.println("0. Exit");

            final int option = scanner.nextInt();
            scanner.nextLine();

            if(option==0){return false;}

            Iterable<SharedBoard> sharedBoardList = theController.findSharedBoard(user);
            if (sharedBoardList.iterator().hasNext()) {
                SharedBoard sharedBoard = chooseSharedBoard("Select Shared Board", sharedBoardList);
                if (sharedBoard == null) {
                    return false;
                }
                Iterable<PostIt> postItsList = theController.findAllPostIts(sharedBoard, user);
                if (postItsList.iterator().hasNext()) {
                    PostIt postIt = choosePostIt("Select a post-it", postItsList);
                    if (postIt == null) {
                        return false;
                    }

                    switch (option) {
                        case 1:
                            Iterable<Cell> cellList = theController.findFreeCells(sharedBoard);
                            if (cellList.iterator().hasNext()) {
                                Cell cell = chooseCell("Select a free cell", cellList);
                                if (cell == null) {
                                    return false;
                                }
                                theController.movePostItToOtherCell(postIt, cell);
                            } else {
                                System.out.println("There no free cells available to move the post-it");
                            }
                            break;
                        case 2:
                            //NAO ESQUECER QUE NAO É ASSIM A VALIDAÇÃO

                            String content = null;
                            boolean contentValid = false;
                            while (!contentValid) {
                                content = Console.readLine("Content: ");
                                contentValid = theController.validateContent(content);
                                if (!contentValid) {
                                    System.out.println("Content must have more than 3 characters and less than 50!");
                                }
                            }
                            theController.changeContentOfPostIt(postIt, content);
                            break;

                        default:
                            System.out.println("Invalid option");
                    }
                } else{
                    System.out.println("There are no post-it's in this shared board!");
                }
            } else {
                System.out.println("There are no shared boards available for you to change a post-it!");
            }
        }
        return false;
    }

    private SharedBoard chooseSharedBoard(String message, Iterable<SharedBoard> sharedBoardList) {
        final SelectWidget<SharedBoard> selector = new SelectWidget<>(message, sharedBoardList);
        selector.show();
        return selector.selectedElement();
    }

    private PostIt choosePostIt(String message, Iterable<PostIt> postItsList) {
        final SelectWidget<PostIt> selector = new SelectWidget<>(message, postItsList);
        selector.show();
        return selector.selectedElement();
    }

    private Cell chooseCell(String message, Iterable<Cell> cellList) {
        final SelectWidget<Cell> selector = new SelectWidget<>(message, cellList);
        selector.show();
        return selector.selectedElement();
    }

    @Override
    public String headline() {
        return "Change Post-It";
    }
}
