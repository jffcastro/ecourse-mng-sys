package application.base.domain.sharedBoardManagement;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PostIt implements AggregateRoot<Long> {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long postItID;

    private String content;

    @ManyToOne
    private SystemUser owner;

    @OneToOne
    @JoinColumn(name = "cell_Id")
    private Cell cell;


    public PostIt(String content, SystemUser owner, Cell cell) {
        Preconditions.nonNull(content);
        this.content = content;
        this.owner = owner;
        this.cell = cell;
    }

    public PostIt() {}

    public String content() {
        return content;
    }

    public Long getPostItID() {
        return postItID;
    }

    public SystemUser owner() {
        return owner;
    }

    public Cell cell() {
        return cell;
    }


    public void changeCell(Cell cell) {this.cell = cell;}

    public void changeContent(String content) {this.content = content;}

    @Override
    public String toString() {
        return "PostIt: " + content + '-' + owner.username() +
                "-" +  cell;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof PostIt)) {
            return false;
        }
        final PostIt that = (PostIt) other;
        if (this == that) {
            return true;
        }

        return identity()==that.identity()
                && postItID == that.postItID
                && cell == that.cell
                && owner == that.owner;
    }

    @Override
    public Long identity() {return postItID;}
}
