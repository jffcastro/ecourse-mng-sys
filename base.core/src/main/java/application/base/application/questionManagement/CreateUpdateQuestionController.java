package application.base.application.questionManagement;


import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import application.base.domain.questionManagement.Question;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.questionManagement.QuestionRepository;

import java.io.File;
import java.io.IOException;

public class CreateUpdateQuestionController {
    private CreateUpdateQuestionService svc = new CreateUpdateQuestionService();

    private QuestionRepository questionRepository = PersistenceContext.repositories().questionRepository();

    public File importFile(String path){
        return svc.importFile(path);
    }

    public Iterable<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Question createQuestion(File file, Course course) throws IOException {
        return svc.createQuestion(file, course);
    }


    public Question updateQuestion(Question question, File file, Course course) throws IOException {
        return svc.updateQuestion(question, file, course);
    }

    public Iterable<Course> findCoursesAvailableForTeacher() {
        return svc.findCoursesAvailableForTeacher();
    }
}
