package application.base.domain.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.domain.examManagement.valueObjects.*;
import application.base.domain.examManagement.valueObjects.header.Header;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Exam")
public class Exam implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long examID;

    @ManyToOne
    private Course course;

    @Column(unique = true)
    private String title;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date openDate;

    @Temporal(TemporalType.DATE)
    private Date closeDate;

    @Enumerated(EnumType.STRING)
    private ExamStatus examStatus;

    @Enumerated(EnumType.STRING)
    private ExamFlag examFlag;

    private Language language;

    @Embedded
    private Header header;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Section> sections;

    protected Exam() {
    }

    public Exam(final Course course, final String title, final String description,
                final Date openDate, final Date closeDate, final Language language,
                final Header header) {
        Preconditions.noneNull(course,title, description, openDate,closeDate,language,header);
        Preconditions.ensure(openDate.after(Date.from(Instant.now())) && closeDate.after(openDate));

        this.course = course;
        this.title = title;
        this.description = description;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.language = language;
        this.header = header;
        this.examStatus = ExamStatus.CREATED;
        this.examFlag = ExamFlag.MANUAL;
        this.sections = new ArrayList<>();
    }

    public Exam(final Course course, final String title, final String description,
                 final Language language, final Header header) {
        Preconditions.noneNull(course,title, description,language,header);

        this.course = course;
        this.title = title;
        this.description = description;
        this.language = language;
        this.header = header;
        this.examStatus = ExamStatus.CREATED;
        this.examFlag = ExamFlag.AUTO;
        this.sections = new ArrayList<>();
    }

    public Long examID() {
        return examID;
    }

    public Course course(){
        return course;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public Date openDate(){
        return openDate;
    }

    public ExamStatus examStatus() {
        return examStatus;
    }

    public Language language() {
        return language;
    }

    public Header header(){
        return header;
    }

    public List<Section> sections() {
        return sections;
    }

    public void changeExamId(Long id){
        this.examID = id;
    }

    public void changeSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean sameAs(final Object o) {
        if (!(o instanceof Exam)) {
            return false;
        }

        final Exam that = (Exam) o;
        if (this == that) {
            return true;
        }

        return title.equals(that.title);
    }

    @Override
    public Long identity() {
        return this.examID;
    }

    @Override
    public String toString() {
        return "Exam" +
                " with title: " + title +
                ", with description: " + description +
                ", with status: " + examStatus +
                ", with open date:" + openDate+
                " and close date:" +  closeDate;
    }

}