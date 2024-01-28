package application.base.application.questionManagement;
import application.base.application.courseManagement.ListAvailableCoursesController;
import application.base.application.questionManagement.questionParsers.EvalVisitor;
import application.base.application.questionManagement.questionParsers.QuestionLexer;
import application.base.application.questionManagement.questionParsers.QuestionParser;
import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.valueObjects.ExamStatus;
import application.base.domain.questionManagement.Question;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.questionManagement.QuestionRepository;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.persistence.Column;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateUpdateQuestionService {
    private QuestionRepository questionRepository = PersistenceContext.repositories().questionRepository();

    private ListAvailableCoursesController listAvailableCoursesController = new ListAvailableCoursesController();

    /**
     * This method receives a path and imports the file relative to that path.
     *
     * @param path - file path
     * @return file imported from path
     */
    public File importFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("File does not exist: " + path);
            return null;
        }
        return file;
    }

    /**
     * This method creates a question from an imported file. The question is generated
     * by the generateQuestion method.
     *
     * @param file - file to generate the question
     */
    public Question createQuestion(File file, Course course) throws IOException {
        Question question = generateQuestion(file, course);
        if(!questionRepository.existsByQuestion(question.question())) {
            questionRepository.save(question);
            return question;
        }
        return null;
    }

    /**
     * This method updates a question choosen by the teacher. First, it calls the generateQuestion
     * method, that generates a question from the file imported, and then updates on the repository
     * the question choosen.
     *
     * @param question - question to be updated
     * @param file - file to generate the question
     */
    public Question updateQuestion(Question question, File file, Course course) throws IOException {
        Question newQuestion = generateQuestion(file, course);

        if (newQuestion != null) {
            questionRepository.delete(question);
            questionRepository.save(newQuestion);
        }
        return newQuestion;
    }

    /**
     * This method generates a Question, after validating the file import with the grammar from ANTLR.
     *
     * @param file - file to generate question
     * @return question generated
     */
    private Question generateQuestion(File file, Course course) throws IOException {
        QuestionLexer lexer = new QuestionLexer(CharStreams.fromFileName(file.getPath()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QuestionParser parser = new QuestionParser(tokens);
        ParseTree tree = parser.question();
        EvalVisitor visitor = new EvalVisitor();

        return visitor.builQuestion(tree, course);
    }

    public Iterable<Course> findCoursesAvailableForTeacher() {
        return listAvailableCoursesController.listCoursesAvailableForTeacher();
    }
}
