package application.base.domain.sharedBoardManagement.builders;

import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardColumn;
import eapli.framework.validations.Preconditions;

public class ColumnBuilder {

    private SharedBoardColumn theSharedBoardColumn;

    private Integer columnNumber;

    private SharedBoard sharedBoard;

    private String title;

    public ColumnBuilder withColumnNumber(final Integer columnNumber){
        this.columnNumber = columnNumber;
        return this;
    }
    public ColumnBuilder ofSharedBoard (final SharedBoard sharedBoard){
        this.sharedBoard = sharedBoard;
        return this;
    }

    public ColumnBuilder withTitle (final String title){
        this.title = title;
        return this;
    }


    public SharedBoardColumn build(){
        Preconditions.noneNull(columnNumber, sharedBoard);

        theSharedBoardColumn = new SharedBoardColumn(
                columnNumber,
                sharedBoard,
                title
        );

        return theSharedBoardColumn;
    }
}

