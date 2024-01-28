package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.valueObjects.ExamStatus;
import application.base.repositories.examManagement.ExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaExamRepository extends JpaAutoTxRepository<Exam, Long, Long> implements ExamRepository {

    public JpaExamRepository(final TransactionalContext autoTx) {
        super(autoTx, "courseId");
    }

    public JpaExamRepository(final String name) {
        super(name, Application.settings().getExtendedPersistenceProperties(), "courseID");
    }

    @Override
    public Iterable<Exam> findExamsOfCourse(Course course) {
        TypedQuery<Exam> query = entityManager().createQuery(
                "SELECT exam FROM Exam exam WHERE exam.course = :course",
                Exam.class);
        query.setParameter("course", course);
        return query.getResultList();
    }

    @Override
    public Optional<Exam> findExamByTitle(String title) {
        TypedQuery<Exam> query = entityManager().createQuery(
                "SELECT exam FROM Exam exam WHERE exam.title = :title",
                Exam.class);
        query.setParameter("title", title);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Exam> findById(Long id) {
        TypedQuery<Exam> query = entityManager().createQuery(
                "SELECT exam FROM Exam exam WHERE exam.examID = :id",
                Exam.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public boolean existsByTitle(String title) {
        TypedQuery<Long> query = entityManager().createQuery(
                "SELECT COUNT(exam) FROM Exam exam WHERE exam.title = :title",
                Long.class);
        query.setParameter("title", title);
        Long count = query.getSingleResult();
        return count > 0;
    }

    @Override
    public Iterable<Exam> findFinishedExamsOfCourse(Course course) {
        TypedQuery<Exam> query = entityManager().createQuery(
                "SELECT exam FROM Exam exam WHERE exam.course = :course and exam.examStatus = :examStatus",
                Exam.class);
        query.setParameter("course", course);
        query.setParameter("examStatus", ExamStatus.FINISHED);
        return query.getResultList();    }


}
