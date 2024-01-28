package application.base.UI.sharedBoardManagement;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class SharedBoardMenu extends Menu {
    private static final String MENU_TITLE = "Shared Boards >";

    private static final int EXIT_OPTION = 0;

    // MY USER
    private static final int CREATE_SHARED_BOARD_OPTION = 1;

    private static final int SHARE_SHAREDBOARD = 2;

    private static final int CREATE_POST_IT = 3;

    private static final int CHANGE_POST_IT = 4;

    private static final int VIEW_BOARD_UPDATES_HISTORY = 5;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public SharedBoardMenu() {
        super(MENU_TITLE);
        buildSharedBoardMenu(null);
    }

    public SharedBoardMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildSharedBoardMenu(onlyWithThis);
    }

    private void buildSharedBoardMenu(final Role onlyWithThis) {
        if (authz.hasSession()) {
            addItem(MenuItem.of(CREATE_SHARED_BOARD_OPTION, "Create Shared Board", new CreateSharedBoardUI()::show));
            addItem(MenuItem.of(SHARE_SHAREDBOARD, "Share your Shared Board", new ShareSharedBoardUI()::show));
            addItem(MenuItem.of(CREATE_POST_IT, "Create a Post-It", new AddPostItUI()::show));
            addItem(MenuItem.of(CHANGE_POST_IT, "Change a Post-It", new ChangePostItUI()::show));
            addItem(MenuItem.of(VIEW_BOARD_UPDATES_HISTORY, "View history of updates of a Shared Borad", new ViewBoardHistoryOfUpdatesUI()::show));
        }

        addItem(MenuItem.of(EXIT_OPTION, "Return ", Actions.SUCCESS));
    }
}
