package application.base.UI.sharedBoardManagement;

import application.base.application.sharedBoardManagement.ShareSharedBoardController;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardInvitation;
import application.base.domain.sharedBoardManagement.UserPermission;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShareSharedBoardUI extends AbstractUI {

    ShareSharedBoardController theController = new ShareSharedBoardController();

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        Iterable<SharedBoard> listSharedBoards = theController.listMySharedBoards(authorizationService.session().get().authenticatedUser());
        if(!listSharedBoards.iterator().hasNext()){
            System.out.println("You haven't created any shared boards yet!");
        }
        else{
            SharedBoard theSharedBoard = chooseSharedBoard("What is the shared board that you want to share?", listSharedBoards);

            Iterable<SystemUser> usersAlreadyInvite = theController.listSharedBoardInvitationsOfSharedBoard(theSharedBoard);
            List<SystemUser> users = (List<SystemUser>) theController.listAvailableUsers(usersAlreadyInvite);

            List<SharedBoardInvitation> share = new ArrayList<>();

            if (users.isEmpty()) {
                System.out.println("There are no users to share the shared board.");
                return false;
            }

            users.remove(authorizationService.session().get().authenticatedUser());

            boolean keepAddingUsers = true;

            while (keepAddingUsers) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Let's share your shared board!");
                System.out.println("1. Share with other users");
                System.out.println("0. Exit");

                final int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        SystemUser user = chooseUser("Select a user to share your board: \n", users);
                        if (user != null) {
                            users.remove(user);

                            System.out.println("What permission should the user have?");
                            System.out.println("1. Write");
                            System.out.println("2. Ready Only");

                            int selected = scanner.nextInt();
                            scanner.nextLine();

                            switch (selected) {
                                case 1:
                                    theController.createSharedBoardInvitation(theSharedBoard, UserPermission.WRITE, user);
                                    SharedBoardInvitation invitationWrite = theController.findSharedBoardInvitation(theSharedBoard, user);
                                    share.add(invitationWrite);
                                    System.out.printf("Shared with success to user %s, with %s permission\n", invitationWrite.userInvite().username(), invitationWrite.userPermission());
                                    break;
                                case 2:
                                    theController.createSharedBoardInvitation(theSharedBoard, UserPermission.READ_ONLY, user);
                                    SharedBoardInvitation invitationRead = theController.findSharedBoardInvitation(theSharedBoard, user);
                                    share.add(invitationRead);
                                    System.out.printf("Shared with success to user %s, with %s permission\n", invitationRead.userInvite().username(), invitationRead.userPermission());
                                    break;
                                default:
                                    System.out.println("Invalid option!");
                            }
                        }
                        break;
                    case 0:
                        keepAddingUsers = false;
                        if(!share.isEmpty()) {
                            System.out.println("Shared the shared board with the following users:");
                            for (int i = 0; i < share.size(); i++) {
                                System.out.println(share.get(i).userInvite().username() + " with " + share.get(i).userPermission() + " permission!");
                            }
                        }
                        break;

                    default:
                        System.out.println("Invalid option!");
                }
            }
        }
    return true;
    }

    private SharedBoard chooseSharedBoard(String message, Iterable<SharedBoard> SharedBoardsList){
        final SelectWidget<SharedBoard> selector = new SelectWidget<>(message, SharedBoardsList);
        selector.show();
        return selector.selectedElement();
    }

    private SystemUser chooseUser(String message, Iterable<SystemUser> users) {
        while (users.iterator().hasNext()) {
            int i = 1;
            for (SystemUser user : users) {
                System.out.printf("%d. User ID: %s, Name: %s, Email: %s, Role: %s\n",
                        i, user.identity(), user.name(), user.email(), user.roleTypes());
                i++;
            }
            int option = Console.readOption(1, i+1, 0);
            if (option == 0) {
                return null;
            } else if (option <= i) {
                i = 0;
                for (SystemUser user : users) {
                    i++;
                    if (i == option) {
                        return user;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String headline() {
        return "Share a Shared Board";
    }
}
