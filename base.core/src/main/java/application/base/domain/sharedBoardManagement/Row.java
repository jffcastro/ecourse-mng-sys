package application.base.domain.sharedBoardManagement;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "SharedBoardRow")
public class Row implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long rowID;

    @Column
    private Integer rowNumber;
    @ManyToOne
    @JoinColumn(name = "sharedBoard_id")
    private SharedBoard sharedBoard;

    @Column(unique = true, nullable = false)
    private String title;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    private List<Cell> cellList;

    protected Row() {}

    public Row (final Integer rowNumber, final SharedBoard sharedBoard, final String title) {
        Preconditions.noneNull(rowNumber, sharedBoard, title);
        this.rowNumber = rowNumber;
        this.sharedBoard = sharedBoard;
        this.title = title;
        this.cellList = new ArrayList<>();
    }
    public Integer rowNumber() {return rowNumber;}
    public String title() {
        return title;
    }

    public List<Cell> cellList() {
        return cellList;
    }

    @Override
    public String toString() {
        return "Row " + rowNumber + '\'' +
                " - " + title + '\'' +
                ", cellList=" + cellList;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Row that = (Row) other;
        return rowNumber.equals(that.rowNumber) &&
                title.equals(that.title) &&
                sharedBoard.equals(that.sharedBoard);
    }

    @Override
    public Long identity() {
        return null;
    }
}
