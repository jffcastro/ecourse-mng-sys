package application.base.persistence.impl.inmemory;

import application.base.domain.examManagement.Exam;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionType;
import application.base.repositories.questionManagement.QuestionRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryQuestionRepository extends InMemoryDomainRepository<Question, Long>
        implements QuestionRepository{

    @Override
    public boolean existsByQuestion(String stucture) {
        return false;
    }

    @Override
    public Iterable<Question> findByCourseAndType(String courseCode, QuestionType type) {
        return null;
    }


}
