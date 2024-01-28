package eapli.UI;

import eapli.SharedBoardClient;
import eapli.SharedBoardClientController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateSharedBoardUI extends AbstractUI {
    private static final Logger LOGGER = LogManager.getLogger(application.base.UI.sharedBoardManagement.CreateSharedBoardUI.class);

    private static final SharedBoardClientController theController = new SharedBoardClientController();
    @Override
    protected boolean doShow() {

        String title = Console.readNonEmptyLine("Title: ", "You must put a title!");
        while (title.length() <= 3 || title.length() > 50) {
            System.out.println("Title must have more than 3 characters and less than 50!");
            title = Console.readNonEmptyLine("Title: ", "You must put a title!");
        }


        Integer maxNumberOfRows = Console.readInteger("Max number of rows: ");
        while(maxNumberOfRows <= 0 || maxNumberOfRows > 20){
            System.out.println("Max number of rows must be between 1 and 20!");
            maxNumberOfRows = Console.readInteger("Max number of rows: ");
        }

        Integer maxNumberOfColumns = Console.readInteger("Max number of columns: ");
        while(maxNumberOfColumns <= 0 || maxNumberOfColumns > 10) {
            System.out.println("Max number of columns must be between 1 and 10!");
            maxNumberOfColumns = Console.readInteger("Max number of columns: ");
        }

        List<String> rows = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        try {
            for (int i = 0; i < maxNumberOfRows; i++) {
                String rowTitle = Console.readLine("Insert title for row " + (i+1) + ":");
                rows.add(rowTitle);
            }
            for (int i = 0; i < maxNumberOfColumns; i++) {
                String columnTitle = Console.readLine("Insert title for column " + (i+1) + ":");
                columns.add(columnTitle);
            }

            theController.createSharedBoard(SharedBoardClient.userName, title, maxNumberOfRows, maxNumberOfColumns, rows, columns);

            System.out.println("Shared Board Created!!");

        }
        catch (final IntegrityViolationException e) {
            System.out.println("Your shared board is not valid!");
        }
        catch (final ConcurrencyException e) {
            LOGGER.error("Error performing the operation", e);
            System.out.println("Unfortunately, there was an unexpected error in the application. " +
                    "Please try again, and if the problem persists, contact your system administrator.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Create a shared board";
    }
}
