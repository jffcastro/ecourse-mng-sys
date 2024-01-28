package application.base.domain.sharedBoardManagement;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "SharedBoard")
public class SharedBoard implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long sharedBoardId;

    @Column (unique = true, nullable = false)
    private String title;

    @OneToOne
    private SystemUser owner;

    @Column(nullable = false)
    private Integer maxNumberOfRows;

    @Column(nullable = false)
    private Integer maxNumberOfColumns;

    @OneToMany(mappedBy = "sharedBoard", cascade = CascadeType.ALL)
    private List<Row> rows;

    @OneToMany(mappedBy = "sharedBoard", cascade = CascadeType.ALL)
    private List<SharedBoardColumn> sharedBoardColumns;

    @OneToMany(mappedBy = "sharedBoard")
    private List<SharedBoardInvitation> sharedBoardInvitationList;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SharedBoardStatus status;

    protected SharedBoard(){}

    public SharedBoard(String title, SystemUser owner, Integer maxNumberOfRows, Integer maxNumberOfColumns,
                       List<SharedBoardInvitation> sharedBoardInvitationList) {
        Preconditions.noneNull(title, owner, maxNumberOfRows, maxNumberOfColumns);
        Preconditions.ensure(title.length() > 3 && title.length() <= 50);
        Preconditions.ensure(maxNumberOfRows > 0 && maxNumberOfColumns > 0);

        this.title = title;
        this.owner = owner;
        this.maxNumberOfRows = maxNumberOfRows;
        this.maxNumberOfColumns = maxNumberOfColumns;
        this.status = SharedBoardStatus.OPEN;
        this.sharedBoardInvitationList = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.sharedBoardColumns = new ArrayList<>();
    }



    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        SharedBoard that = (SharedBoard) other;
        return owner.equals(that.owner) &&
                title.equals(that.title) &&
                maxNumberOfRows.equals(that.maxNumberOfRows) &&
                maxNumberOfColumns.equals(that.maxNumberOfColumns);
    }

    public SystemUser getOwner() {
        return owner;
    }

    public String title() {
        return title;
    }

    public Integer maxNumberOfRows() {
        return maxNumberOfRows;
    }

    public Integer maxNumberOfColumns() {
        return maxNumberOfColumns;
    }

    public List<Row> rows(){
        return this.rows;
    }

    public List<SharedBoardColumn> columns(){
        return this.sharedBoardColumns;
    }

    public void setStatus(SharedBoardStatus status) {
        this.status = status;
    }


    @Override
    public Long identity() {
        return null;
    }


    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();

        System.out.printf("Title: %s \n", title);
        System.out.printf("Shared Board Status: %s\n", status);

        int numColumns = sharedBoardColumns.size();

        for (SharedBoardColumn column : sharedBoardColumns) {
            if(column.equals(sharedBoardColumns.get(0))){
                table.append(String.format("| %-15s "," " ));
                table.append(String.format("| %-15s ", column.title()));
            } else{
                table.append(String.format("| %-15s ", column.title()));
            }
        }
        table.append("|\n");

        for (int i = 0; i < numColumns+1; i++) {
            table.append("+-----------------");
        }
        table.append("+\n");

        for (Row row: rows) {
            table.append(String.format("| %-15s ", row.title()));
            for (int column = 0; column < numColumns; column++) {
                table.append("|                 ");
            }
            table.append("|\n");
        }

        // Imprimir separador de colunas
        for (int i = 0; i < numColumns+1; i++) {
            table.append("+-----------------");
        }

        table.append("+\n");

        return table.toString();
    }


}
