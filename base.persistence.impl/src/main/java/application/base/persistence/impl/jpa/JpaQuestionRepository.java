package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.examManagement.Exam;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionType;
import application.base.repositories.questionManagement.QuestionRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaQuestionRepository extends JpaAutoTxRepository<Question, Long, Long> implements QuestionRepository {

    public JpaQuestionRepository(final TransactionalContext autoTx) {
        super(autoTx, "questionID");
    }

    public JpaQuestionRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "questionID");
    }

    @Override
    public boolean existsByQuestion(String question) {
        TypedQuery<Long> query = entityManager().createQuery(
                "SELECT COUNT(question) FROM Question question WHERE question.question = :question",
                Long.class);
        query.setParameter("question", question);
        Long count = query.getSingleResult();
        return count > 0;
    }

    @Override
    public Iterable<Question> findByCourseAndType(String courseCode, QuestionType type) {
        TypedQuery<Question> query = entityManager().createQuery(
                "SELECT question FROM Question question WHERE question.course.courseCode = :courseCode AND question.questionType = :type",
                Question.class);
        query.setParameter("courseCode", courseCode);
        query.setParameter("type", type);
        return query.getResultList();
    }


}