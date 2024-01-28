package application.base.UI.sharedBoardManagement;

import application.base.application.sharedBoardManagement.CreateSharedBoardController;
import application.base.domain.sharedBoardManagement.SharedBoardColumn;
import application.base.domain.sharedBoardManagement.Row;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateSharedBoardUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(CreateSharedBoardUI.class);

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();;

    private CreateSharedBoardController theController = new CreateSharedBoardController();
    @Override
    protected boolean doShow() {

        String title = null;
        boolean titleValid = false;
        while (!titleValid) {
            title = Console.readLine("Title: ");
            titleValid = theController.validateTitle(title);
            if (!titleValid) {
                System.out.println("Title must have more than 3 characters and less than 50!");
            }
        }

        Integer maxNumberOfRows = null;
        boolean maxNumberOfRowsValid = false;
        while (!maxNumberOfRowsValid) {
            maxNumberOfRows = Console.readInteger("Max number of rows: ");
            maxNumberOfRowsValid = theController.validateMaxNumberOfRows(maxNumberOfRows);
            if (!maxNumberOfRowsValid) {
                System.out.println("Maximum number of rows must be bigger than 0 and less or equal than 20!");
            }
        }
        Integer maxNumberOfColumns = null;
        boolean maxNumberOfColumnsValid = false;
        while (!maxNumberOfColumnsValid) {
            maxNumberOfColumns = Console.readInteger("Max number of columns: ");
            maxNumberOfColumnsValid = theController.validateMaxNumberOfColumns(maxNumberOfColumns);
            if (!maxNumberOfColumnsValid) {
                System.out.println("Maximum number of columns must be bigger than 0 and less or equal than 10!");
            }
        }

        try {
            SharedBoard sharedBoard = theController.createSharedBoard(title, maxNumberOfRows, maxNumberOfColumns);
            for (int i = 0; i < sharedBoard.maxNumberOfRows(); i++) {
                String rowTitle = Console.readLine("Insert title for row " + (i+1) + ":");
                Row row = theController.createRow(i+1, sharedBoard, rowTitle);
                sharedBoard.rows().add(row);
            }
            for (int i = 0; i < sharedBoard.maxNumberOfColumns(); i++) {
                String columnTitle = Console.readLine("Insert title for column " + (i+1) + ":");
                SharedBoardColumn sharedBoardColumn = theController.createColumn(i+1, sharedBoard, columnTitle);
                sharedBoard.columns().add(sharedBoardColumn);
            }


            theController.createCells(sharedBoard);
            theController.saveSharedBoard(sharedBoard);
            System.out.println("Shared Board Created!!");
            System.out.printf(String.valueOf(sharedBoard));
            System.out.println("");
        }
        catch (final IntegrityViolationException e) {
            System.out.println("Your shared board is not valid!");
        }
        catch (final ConcurrencyException e) {
            LOGGER.error("Error performing the operation", e);
            System.out.println("Unfortunately, there was an unexpected error in the application. " +
                    "Please try again, and if the problem persists, contact your system administrator.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Create a shared board";
    }
}
