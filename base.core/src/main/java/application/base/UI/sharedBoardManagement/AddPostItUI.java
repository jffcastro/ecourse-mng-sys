package application.base.UI.sharedBoardManagement;

import application.base.application.sharedBoardManagement.AddPostItController;
import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.PostIt;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AddPostItUI extends AbstractUI {


    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private AddPostItController theController = new AddPostItController();

    @Override
    protected boolean doShow() {

        SystemUser user = null;
        if (authorizationService.session().isPresent()) {
            user = this.authorizationService.session().get().authenticatedUser();
            Iterable<SharedBoard> sharedBoardList = theController.findSharedBoard(user);
            if (sharedBoardList.iterator().hasNext()) {
                SharedBoard sharedBoard = chooseSharedBoard("Select Shared Board", sharedBoardList);
                if(sharedBoard==null){
                    return false;
                }

                System.out.println("To add a post-it insert:");

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

                Iterable<Cell> cellList = theController.findFreeCells(sharedBoard);
                if(cellList.iterator().hasNext()) {
                    Cell cell = chooseCell("Select a free cell", cellList);
                    PostIt postIt = theController.createPostIt(content, user, cell);
                    System.out.println("PostIt created with success:");
                    System.out.println(postIt);
                }else{
                    System.out.println("There are no cells available for you to add a post-it");
                }
            } else {
                System.out.println("There are no Shared Boards available for you to add a post-it");
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Add a Post-it";
    }

    private SharedBoard chooseSharedBoard(String message, Iterable<SharedBoard> sharedBoardList) {
        final SelectWidget<SharedBoard> selector = new SelectWidget<>(message, sharedBoardList);
        selector.show();
        return selector.selectedElement();
    }

    private Cell chooseCell(String message, Iterable<Cell> cellList) {
        final SelectWidget<Cell> selector = new SelectWidget<>(message, cellList);
        selector.show();
        return selector.selectedElement();
    }

}
