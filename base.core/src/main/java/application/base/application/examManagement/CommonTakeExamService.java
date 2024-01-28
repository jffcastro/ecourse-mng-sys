package application.base.application.examManagement;

import application.base.application.examManagement.takeExamParsers.TakeExamEvalVisitor;
import application.base.application.examManagement.takeExamParsers.TakeExamLexer;
import application.base.application.examManagement.takeExamParsers.TakeExamParser;
import application.base.domain.enrollmentManagement.Grade;
import application.base.domain.examManagement.Exam;
import application.base.domain.questionManagement.Question;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;

public class CommonTakeExamService {

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
     * This method receives a question and a student answer
     * and checks if the student answer is correct. It's used
     * when the student answers the exam by the menus.
     *
     * @param question      - question to correct
     * @param studentAnswer - student answer for that question
     * @return true, if questions is correct, or false, if not
     */
    public boolean correctQuestion(Question question, String studentAnswer) {
        return studentAnswer.replace(" ", "")
                .equalsIgnoreCase(question.answer().replace(" ", ""));
    }

    /**
     * This method corrects an exam by the file imported and returns the grade obtained.
     *
     * @param exam - exam that student took
     * @param file - file with the student exam's realization
     * @return grade obtained from the file
     */
    public Grade correctExamByFile(Exam exam, File file) throws IOException {
        TakeExamLexer lexer = new TakeExamLexer(CharStreams.fromFileName(file.getPath()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TakeExamParser parser = new TakeExamParser(tokens);
        ParseTree tree = parser.stat();
        TakeExamEvalVisitor visitor = new TakeExamEvalVisitor();

        return visitor.correctExam(tree, exam);
    }
}
