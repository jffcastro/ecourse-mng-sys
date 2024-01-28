package application.base.application.examManagement.takeExamParsers;


import application.base.domain.enrollmentManagement.Grade;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.Section;
import application.base.domain.questionManagement.Question;
import org.antlr.v4.runtime.tree.ParseTree;

public class TakeExamEvalVisitor extends TakeExamBaseVisitor<Integer> {

    private Exam exam;
    private int sectionCount = 0;
    private Section examSection;
    private Float grade = 0F;

    public Grade correctExam(ParseTree tree, Exam exam) {
        this.exam = exam;
        visit(tree);
        return new Grade(grade);
    }

    @Override
    public Integer visitStructure(TakeExamParser.StructureContext ctx) {
        return super.visitStructure(ctx);
    }

    @Override
    public Integer visitSection(TakeExamParser.SectionContext ctx) {
        if (exam.sections().size() > sectionCount) {
            examSection = exam.sections().get(sectionCount);
            sectionCount++;
        }

        return super.visitSection(ctx);
    }

    @Override
    public Integer visitQuestion(TakeExamParser.QuestionContext ctx) {
        if (examSection.questions().size() > Integer.parseInt(ctx.NUM().getText()) - 1) {
            Question examQuestion = examSection.questions().get(Integer.parseInt(ctx.NUM().getText()) - 1);
            String studentAnswer = ctx.questionAnswer().getText();
            if (studentAnswer.replace(" ", "")
                    .equalsIgnoreCase(examQuestion.answer().replace(" ", ""))) {
                grade += examQuestion.quotation();
            }
        }
        return super.visitQuestion(ctx);
    }

}