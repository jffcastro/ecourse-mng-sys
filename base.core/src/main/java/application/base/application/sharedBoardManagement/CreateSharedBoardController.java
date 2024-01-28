package application.base.application.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.*;
import application.base.domain.sharedBoardManagement.builders.ColumnBuilder;
import application.base.domain.sharedBoardManagement.builders.RowBuilder;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.sharedBoardManagement.SharedBoardRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CreateSharedBoardController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private SharedBoardRepository sharedBoardRepository = PersistenceContext.repositories().sharedBoardRepository();

    public SharedBoard createSharedBoard(String title, Integer maxNumberOfRows, Integer maxNumberOfColumns){
        SystemUser user = null;
        if (authorizationService.session().isPresent()) {
            user = this.authorizationService.session().get().authenticatedUser();
        }

        final SharedBoard newSharedBoard = new SharedBoardBuilder().withTitle(title)
                .ownedBy(user)
                .hasMaxNumberOfRows(maxNumberOfRows)
                .hasMaxNumberOfColumns(maxNumberOfColumns)
                .withInvitationList(null)
                .build();

        return newSharedBoard;
    }

    /**
     * This method validates the title of the shared board entered by the user who is creating it.
     * This must be at least 3 characters long and can only be a maximum of 50 characters long.
     * @param title
     * @return title validation
     */
    public boolean validateTitle(String title){
        if(title.length() <= 3 || title.length() > 50){
            return false;
        }
        return true;
    }
    /**
     * This method validates the maximum number of lines that the created shared board can have.
     * It must have more than zero lines and cannot exceed 20.
     * @param maxNumberOfRows
     * @return number of rows validation
     */
    public boolean validateMaxNumberOfRows(Integer maxNumberOfRows){
        if(maxNumberOfRows <= 0 || maxNumberOfRows > 20){
            return false;
        }
        return true;
    }

    /**
     * This method validates the maximum number of columns that the created shared board can have.
     * It must have more than zero columns and cannot exceed 10.
     * @param maxNumberOfColumns
     * @return number of columns validation
     */
    public boolean validateMaxNumberOfColumns(Integer maxNumberOfColumns){
        if(maxNumberOfColumns <= 0 || maxNumberOfColumns > 10){
            return false;
        }
        return true;
    }

    public Row createRow(Integer rowNumber, SharedBoard sharedBoard, String title){

        final Row newRows = new RowBuilder()
                .withRowNumber(rowNumber)
                .ofSharedBoard(sharedBoard)
                .withTitle(title)
                .build();

        return newRows;
    }

    public SharedBoardColumn createColumn(Integer columnNumber, SharedBoard sharedBoard, String title){

        final SharedBoardColumn newColumns = new ColumnBuilder()
                .withColumnNumber(columnNumber)
                .ofSharedBoard(sharedBoard)
                .withTitle(title)
                .build();

        return newColumns;
    }

    public SharedBoard createCells(SharedBoard sharedBoard){
        for(Row row : sharedBoard.rows()){
            for (SharedBoardColumn sharedBoardColumn : sharedBoard.columns()){
                final Cell cell = new CellBuilder()
                        .inRow(row)
                        .inColumn(sharedBoardColumn)
                        .build();
                row.cellList().add(cell);
                sharedBoardColumn.cellList().add(cell);
            }
        }

        return sharedBoard;
    }

    public void saveSharedBoard(SharedBoard sharedBoard){
        sharedBoardRepository.save(sharedBoard);
    }
}
