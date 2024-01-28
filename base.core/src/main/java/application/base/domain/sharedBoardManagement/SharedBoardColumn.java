package application.base.domain.sharedBoardManagement;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "SharedBoardColumn")
public class SharedBoardColumn implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long columnID;

    @Column(nullable = false)
    private Integer columnNumber;

    @Column(unique = true, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "sharedBoard_id")
    private SharedBoard sharedBoard;

    @OneToMany(mappedBy = "sharedBoardColumn", cascade = CascadeType.ALL)
    private List<Cell> cellList;

    protected SharedBoardColumn() {}

    /**
     * Constructor
     * @param title
     */

    public SharedBoardColumn(final Integer columnNumber, final SharedBoard sharedBoard, final String title) {
        Preconditions.noneNull(columnNumber, sharedBoard, title);
        this.columnNumber = columnNumber;
        this.sharedBoard = sharedBoard;
        this.title = title;
        this.cellList = new ArrayList<>();
    }

    public Integer columnNumber() {return columnNumber;}
    public String title() {
        return title;
    }

    public List<Cell> cellList() {
        return cellList;
    }

    @Override
    public String toString() {
        return "Column{" +
                "title='" + title + '\'' +
                ", cellList=" + cellList +
                '}';
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        SharedBoardColumn that = (SharedBoardColumn) other;
        return columnNumber.equals(that.columnNumber) &&
                title.equals(that.title) &&
                sharedBoard.equals(that.sharedBoard);
    }


    @Override
    public Long identity() {
        return null;
    }
}

