package application.base.domain.sharedBoardManagement;

import eapli.framework.validations.Preconditions;

public class CellBuilder {

    private Cell theCell;
    private Row row;
    private SharedBoardColumn sharedBoardColumn;

    public CellBuilder inRow (final Row row){
        this.row = row;
        return this;
    }

    public CellBuilder inColumn (final SharedBoardColumn sharedBoardColumn){
        this.sharedBoardColumn = sharedBoardColumn;
        return this;
    }


    public Cell build(){
        Preconditions.noneNull(row, sharedBoardColumn);

        theCell = new Cell(
                row,
                sharedBoardColumn
        );

        return theCell;
    }
}
