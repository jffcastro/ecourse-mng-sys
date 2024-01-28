package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.domain.enrollmentManagement.ExamEnrollmentStatus;
import application.base.domain.examManagement.Exam;
import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.usermanagement.domain.Student;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaExamEnrollmentRepository extends JpaAutoTxRepository<ExamEnrollment, Long, Long> implements ExamEnrollmentRepository {

    public JpaExamEnrollmentRepository(final TransactionalContext autoTx) {super(autoTx, "examEnrollmentID");}

    public JpaExamEnrollmentRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "examEnrollmentID");
    }

    @Override
    public Iterable<ExamEnrollment> findStudentExamEnrollmentsWithEnrolledStatus(Student student) {
        TypedQuery<ExamEnrollment> query = entityManager().createQuery(
                "SELECT examEnrollment FROM ExamEnrollment examEnrollment  WHERE examEnrollment.examEnrollmentStatus = : status AND examEnrollment.enrollment.student =: student" ,
                ExamEnrollment.class);
        query.setParameter("student", student);
        query.setParameter("status", ExamEnrollmentStatus.ENROLLED);
        return query.getResultList();
        }

    @Override
    public Iterable<ExamEnrollment> findExamGradesOfStudent(Student student) {
        TypedQuery<ExamEnrollment> query = entityManager().createQuery(
                "SELECT examEnrollment FROM ExamEnrollment examEnrollment  WHERE examEnrollment.enrollment.student = :student",
                ExamEnrollment.class);
        query.setParameter("student", student);
        return query.getResultList();
    }

    @Override
    public Iterable<ExamEnrollment> findExamEnrollsOfExam(Exam exam) {
        TypedQuery<ExamEnrollment> query = entityManager().createQuery(
                "SELECT examEnrollment FROM ExamEnrollment examEnrollment " +
                        "WHERE examEnrollment.examID = :examID",
                ExamEnrollment.class);
        query.setParameter("examID", exam.examID());
        return query.getResultList();
    }

    @Override
    public Iterable<Long> findIdsOfExamAvailableForStudentToTake(Student student) {
        TypedQuery<Long> query = entityManager().createQuery(
                "SELECT examEnrollment.examID FROM ExamEnrollment examEnrollment " +
                        "WHERE examEnrollment.enrollment.student = :student " +
                        "AND examEnrollment.examEnrollmentStatus = :examEnrollmentStatus",
                Long.class);
        query.setParameter("student", student);
        query.setParameter("examEnrollmentStatus", ExamEnrollmentStatus.ENROLLED);
        return query.getResultList();
    }

    @Override
    public Optional<ExamEnrollment> findExamEnrollOfStudentInExam(Student student, Exam exam) {
        TypedQuery<ExamEnrollment> query = entityManager().createQuery(
                "SELECT examEnrollment FROM ExamEnrollment examEnrollment" +
                        " WHERE examEnrollment.enrollment.student = :student" +
                        " AND examEnrollment.examID = :examID",
                ExamEnrollment.class);
        query.setParameter("student", student);
        query.setParameter("examID", exam.examID());
        return Optional.ofNullable(query.getSingleResult());    }

    @Override
    public Iterable<ExamEnrollment> findGradesOfCourse(Course course) {
        TypedQuery<ExamEnrollment> query = entityManager().createQuery(
                "SELECT examEnrollment FROM ExamEnrollment examEnrollment" +
                        " WHERE examEnrollment.enrollment.course = :course" +
                        " AND examEnrollment.examEnrollmentStatus = :status",
                ExamEnrollment.class);
        query.setParameter("course", course);
        query.setParameter("status", ExamEnrollmentStatus.PRESENT);

        return query.getResultList();
    }

}