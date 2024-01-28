package application.base.application.questionManagement.questionParsers;
import application.base.domain.courseManagement.Course;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionBuilder;
import application.base.domain.questionManagement.QuestionType;
import org.antlr.v4.runtime.tree.ParseTree;

public class EvalVisitor extends QuestionBaseVisitor<Integer> {

    private QuestionBuilder questionBuilder = new QuestionBuilder();

    public Question builQuestion(ParseTree tree, Course course) {
        questionBuilder.ofCourse(course);
        visit(tree);
        return questionBuilder.build();
    }


    //Matching Question
    @Override
    public Integer visitMatching(QuestionParser.MatchingContext ctx) {
        questionBuilder.ofQuestionType(QuestionType.MATCHING);
        return super.visitMatching(ctx);
    }
    @Override
    public Integer visitMatchingText(QuestionParser.MatchingTextContext ctx) {
        questionBuilder.withQuestion(ctx.getText().trim());
        return super.visitMatchingText(ctx);
    }

    @Override
    public Integer visitMatchingAnswers(QuestionParser.MatchingAnswersContext ctx){
        questionBuilder.withCorrectAnswer(ctx.getText().trim());
        return super.visitMatchingAnswers(ctx);
    }


    //Missing Word Question
    @Override
    public Integer visitMissingWord(QuestionParser.MissingWordContext ctx) {
        questionBuilder.ofQuestionType(QuestionType.MISSING_WORD);
        return super.visitMissingWord(ctx);
    }

    @Override
    public Integer visitMissingWordText(QuestionParser.MissingWordTextContext ctx) {
        questionBuilder.withQuestion(ctx.getText().trim());
        return super.visitMissingWordText(ctx);
    }

    @Override
    public Integer visitMissingWordAnswer(QuestionParser.MissingWordAnswerContext ctx){
        questionBuilder.withCorrectAnswer(ctx.getText().trim());
        return super.visitMissingWordAnswer(ctx);
    }


    //Multiple Choice
    @Override
    public Integer visitMultipleChoice(QuestionParser.MultipleChoiceContext ctx) {
        questionBuilder.ofQuestionType(QuestionType.MULTIPLE_CHOICE);
        return super.visitMultipleChoice(ctx);
    }

    @Override
    public Integer visitMultipleChoiceText(QuestionParser.MultipleChoiceTextContext ctx){
        questionBuilder.withQuestion(ctx.getText().trim());
        return super.visitMultipleChoiceText(ctx);
    }

    @Override
    public Integer visitMultipleChoiceAnswer(QuestionParser.MultipleChoiceAnswerContext ctx){
        questionBuilder.withCorrectAnswer(ctx.getText().trim());
        return super.visitMultipleChoiceAnswer(ctx);
    }


    //Numeric Question
    @Override
    public Integer visitNumeric(QuestionParser.NumericContext ctx) {
        questionBuilder.ofQuestionType(QuestionType.NUMERIC);
        return super.visitNumeric(ctx);
    }

    @Override
    public Integer visitNumericText(QuestionParser.NumericTextContext ctx) {
        questionBuilder.withQuestion(ctx.getText().trim());
        return super.visitNumericText(ctx);
    }

    @Override
    public Integer visitNumericAnswer(QuestionParser.NumericAnswerContext ctx){
        questionBuilder.withCorrectAnswer(ctx.getText().trim());
        return super.visitNumericAnswer(ctx);
    }


    //Short Question
    @Override
    public Integer visitShort(QuestionParser.ShortContext ctx) {
        questionBuilder.ofQuestionType(QuestionType.SHORT);
        return super.visitShort(ctx);
    }

    @Override
    public Integer visitShortText(QuestionParser.ShortTextContext ctx) {
        questionBuilder.withQuestion(ctx.getText().trim());
        return super.visitShortText(ctx);
    }

    @Override
    public Integer visitShortAnswer(QuestionParser.ShortAnswerContext ctx){
        questionBuilder.withCorrectAnswer(ctx.getText().trim());
        return super.visitShortAnswer(ctx);
    }


    // True or False Question
    @Override
    public Integer visitTrueOrFalse(QuestionParser.TrueOrFalseContext ctx) {
        questionBuilder.ofQuestionType(QuestionType.TRUE_OR_FALSE);
        return super.visitTrueOrFalse(ctx);
    }

    @Override
    public Integer visitTrueOrFalseText(QuestionParser.TrueOrFalseTextContext ctx) {
        questionBuilder.withQuestion(ctx.getText().trim());
        return super.visitTrueOrFalseText(ctx);
    }

    @Override
    public Integer visitTrueOrFalseAnswer(QuestionParser.TrueOrFalseAnswerContext ctx){
        questionBuilder.withCorrectAnswer(ctx.getText().trim());
        return super.visitTrueOrFalseAnswer(ctx);
    }

    //Quotation
    @Override
    public Integer visitQuotation(QuestionParser.QuotationContext ctx) {
        questionBuilder.withQuotation(Integer.parseInt(ctx.NUM().getText()));
        return super.visitQuotation(ctx);
    }

}