package application.base.domain.enrollmentManagement;

import application.base.domain.courseManagement.Course;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
@Table(name = "ExamEnrollment")
public class ExamEnrollment implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long examID;

    @ManyToOne
    private Enrollment enrollment;

    private Grade examGrade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExamEnrollmentStatus examEnrollmentStatus;

    protected ExamEnrollment(){}

    public ExamEnrollment(final Long examID, final Enrollment enrollment, final ExamEnrollmentStatus examEnrollmentStatus){
        Preconditions.noneNull(examID, enrollment, examEnrollmentStatus);

        this.examID = examID;
        this.enrollment  = enrollment;
        this.examEnrollmentStatus = examEnrollmentStatus;
    }

    public Long examID(){return examID;}

    public Enrollment enrollment(){return enrollment;}

    public Grade examGrade(){ return examGrade;}

    public ExamEnrollmentStatus examEnrollmentStatus(){
        return examEnrollmentStatus;
    }

    public void changeExamGrade(Grade grade){
        this.examGrade = grade;
        this.examEnrollmentStatus = ExamEnrollmentStatus.PRESENT;
    }

    public void changeExamEnrollmentStatusToPresent(){
        this.examEnrollmentStatus = ExamEnrollmentStatus.PRESENT;
    }

    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public boolean sameAs(final Object o) {
        if (!(o instanceof ExamEnrollment)) {
            return false;
        }

        final ExamEnrollment that = (ExamEnrollment) o;
        if (this == that) {
            return true;
        }

        return examID.equals(that.examID)
                && enrollment.equals(enrollment)
                && examEnrollmentStatus.equals(examEnrollmentStatus);
    }
}
