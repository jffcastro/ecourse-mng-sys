package application.base.domain.sharedBoardManagement;

import application.base.domain.courseManagement.Course;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
@Table(name = "Cell")
public class Cell implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long cellID;

    @ManyToOne
    @JoinColumn(name = "row_id")
    private Row row;

    @ManyToOne
    @JoinColumn(name = "sharedBoardColumn_id")
    private SharedBoardColumn sharedBoardColumn;

    private boolean isFree;

    protected Cell(){}

    public Cell(final Row row, final SharedBoardColumn sharedBoardColumn) {
        Preconditions.noneNull(row, sharedBoardColumn, isFree);
        this.row = row;
        this.sharedBoardColumn = sharedBoardColumn;
        this.isFree=true;
    }

    public boolean isFree() { return isFree; }

    public void changeTheFreeStatus(boolean free) {
        isFree = free;
    }

    @Override
    public String toString() {
        return row.title() + "," + sharedBoardColumn.title() + "=>" +cellID;
    }

    public boolean equals(final Object o){return DomainEntities.areEqual((DomainEntity<?>) this, o);}

    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Cell that = (Cell) other;
        return row.equals(that.row) &&
                sharedBoardColumn.equals(that.sharedBoardColumn);
    }


    @Override
    public Long identity() {return cellID;}
}
