package application.base.domain.sharedBoardManagement.builders;

import application.base.domain.sharedBoardManagement.Cell;
import application.base.domain.sharedBoardManagement.Row;
import application.base.domain.sharedBoardManagement.SharedBoard;
import eapli.framework.validations.Preconditions;

import java.util.List;

public class RowBuilder {

    private Row theRow;

    private Integer rowNumber;
    private SharedBoard sharedBoard;
    private String title;

    public RowBuilder withRowNumber(final Integer rowNumber){
        this.rowNumber = rowNumber;
        return this;
    }
    public RowBuilder ofSharedBoard(final SharedBoard sharedBoard){
        this.sharedBoard = sharedBoard;
        return this;
    }

    public RowBuilder withTitle (final String title){
        this.title = title;
        return this;
    }

    public Row build(){
        Preconditions.noneNull(rowNumber, sharedBoard);

        theRow = new Row(
                rowNumber,
                sharedBoard,
                title);

        return theRow;
    }
}
