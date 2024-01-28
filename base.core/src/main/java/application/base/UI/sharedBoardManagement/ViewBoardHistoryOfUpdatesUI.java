package application.base.UI.sharedBoardManagement;

import application.base.application.sharedBoardManagement.ViewBoardHistoryOfUpdatesController;
import application.base.domain.sharedBoardManagement.PostItHistory;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ViewBoardHistoryOfUpdatesUI extends AbstractUI {

    private ViewBoardHistoryOfUpdatesController controller = new ViewBoardHistoryOfUpdatesController();

    @Override
    public String headline() {
        return "View History of Updates of a Board";
    }

    @Override
    protected boolean doShow() {
        Iterable<SharedBoard> sharedBoardsAvailable = controller.findSharedBoardsAvailableForUser();

        if (sharedBoardsAvailable.iterator().hasNext()){
            SharedBoard sharedBoard = chooseSharedBoard(sharedBoardsAvailable);
            if (sharedBoard != null) {
                Iterable<PostItHistory> sharedBoardUpdatesHistory = controller.findHistoryOfUpdatesOfSharedBoard(sharedBoard);
                if (sharedBoardUpdatesHistory.iterator().hasNext()) {
                    System.out.printf("History of Updates on Shared Board - %s:\n", sharedBoard.title());
                    for (PostItHistory postItHistory : sharedBoardUpdatesHistory) {
                        System.out.println(postItHistory);
                    }
                }else {
                        System.out.println("Shared Board selected has no updates history.");
                    }
            }
        }
        return false;
    }

    /**
     * Method used to show shared board list and select one shared board.
     *
     * @param sharedBoardsList - shared board list to choose from
     * @return course selected
     */
    private SharedBoard chooseSharedBoard(Iterable<SharedBoard> sharedBoardsList) {
        final SelectWidget<SharedBoard> selector = new SelectWidget<>("Select a shared board:", sharedBoardsList);
        selector.show();
        return selector.selectedElement();
    }

}