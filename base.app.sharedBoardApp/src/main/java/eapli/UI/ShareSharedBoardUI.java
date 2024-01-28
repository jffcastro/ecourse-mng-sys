package eapli.UI;

import application.base.domain.sharedBoardManagement.UserPermission;
import eapli.Controller.ShareSharedBoardController;
import eapli.SharedBoardClient;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShareSharedBoardUI extends AbstractUI {

    private static final ShareSharedBoardController theController = new ShareSharedBoardController();
    @Override
    protected boolean doShow() {
        try {
            List<String> sharedBoardList = theController.sendRequestToShare(SharedBoardClient.userName);

            chooseSharedBoard("Choose a Shared Board", sharedBoardList);

            if (SharedBoardClient.sharedBoard != null){
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
                        List<String> userList = theController.listUsers(SharedBoardClient.userName, SharedBoardClient.sharedBoard);
                        chooseUser("Choose a user", userList);

                            System.out.println("What permission should the user have?");
                            System.out.println("1. Write");
                            System.out.println("2. Ready Only");

                            final int option1 = scanner.nextInt();

                            switch (option1) {
                                case 1:
                                    if(theController.createSharedBoardInvitation(SharedBoardClient.userNameInvited, SharedBoardClient.sharedBoard, String.valueOf(UserPermission.WRITE))){
                                        System.out.println("Shared Board Invitation Created with Write Permission!!");}
                                    break;

                                case 2:
                                    if(theController.createSharedBoardInvitation(SharedBoardClient.userNameInvited, SharedBoardClient.sharedBoard, String.valueOf(UserPermission.READ_ONLY))){
                                        System.out.println("Shared Board Invitation Created with Read Only Permission!!");}
                                    break;

                                default:
                                    System.out.println("Invalid Option!");
                                    break;
                            }
                            break;
                    case 0:
                        keepAddingUsers = false;
                        break;
                    default:
                        System.out.println("Invalid Option!");
                        break;
                }
            }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * This method shows to the user all the shared boards that he created
     * @param message
     * @param sharedBoardList
     */
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

    /**
     * This method shows to the user all the available users to invite and asks to select one
     * @param message
     * @param userList
     */
    protected String chooseUser(String message, List<String> userList) {
        final SelectWidget<String> selector = new SelectWidget<>(message, userList);
        selector.show();
        int selectedIndex = selector.selectedOption();

        if(selectedIndex == 0){
            return null;
        }
        else if (selectedIndex > 0 && selectedIndex <= userList.size()) {
            String userNameInvited = userList.get(selectedIndex - 1);
            SharedBoardClient.userNameInvited = userNameInvited;
            return userNameInvited;
        } else {
            System.out.println("Invalid Option!");
            return null;
        }
    }


    @Override
    public String headline() {
        return "Share Shared Board";
    }
}
