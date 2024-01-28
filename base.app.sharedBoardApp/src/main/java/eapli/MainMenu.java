package eapli;

import eapli.UI.*;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class MainMenu extends AbstractUI {

    private static final String RETURN = "Return";
    private static final int EXIT = 0;
    private static final int CREATE_BOARD = 2;

    private static final int SHARE_SHAREDBOARD = 3;

    private static final int CREATE_POSTIT = 4;
    private static final int VIEW_HISTORY = 5;

    private static final int CHANGE_POSTIT = 6;

    private static final int UNDO_POSTIT = 7;

    private static final int ARCHIVE_SHAREDBOARD = 8;
    


    @Override
    protected boolean doShow() {
        Menu menu = new Menu();
        menu.addItem(1, "Test the connection", new ConnectionUI()::show);

        menu.addItem(CREATE_BOARD, "Create Shared Board", new CreateSharedBoardUI()::show);
        menu.addItem(SHARE_SHAREDBOARD, "Share a Shared Board", new ShareSharedBoardUI()::show);
        menu.addItem(CREATE_POSTIT, "Create Post It", new CreatePostItUI()::show);
        menu.addItem(VIEW_HISTORY, "View Shared Board History", new ViewBoardHistoryOfUpdatesUI()::show);
        menu.addItem(CHANGE_POSTIT, "Change Post It", new ChangePostItUI()::show);
        menu.addItem(UNDO_POSTIT, "Undo Post It", new UndoPostItUI()::show);
        menu.addItem(ARCHIVE_SHAREDBOARD, "Archive Shared Board", new ArchiveSharedBoardUI()::show);
        menu.addItem(0, "Disconnect", new DisconnectUI()::show);

        MenuRenderer menuRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);

        return menuRenderer.render();
    }

    @Override
    public String headline() {
        return "Shared Board Menu";
    }
}
