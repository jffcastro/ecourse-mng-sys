package application.base.repositories.questionManagement;


import application.base.domain.examManagement.Exam;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionType;
import eapli.framework.domain.repositories.DomainRepository;

public interface QuestionRepository extends DomainRepository<Long, Question> {

    /**
     * This method is used by US 2007 and returns the list of all the existing questions
     *
     * @return list all questions
     */
    Iterable<Question> findAll();

    boolean existsByQuestion(String question);

    Iterable<Question> findByCourseAndType(String courseCode, QuestionType type);

}
