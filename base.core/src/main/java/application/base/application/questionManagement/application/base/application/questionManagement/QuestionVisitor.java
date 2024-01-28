// Generated from /Users/mariainessilvapinto/sem4pi-22-23-6/base.core/src/main/java/application/base/application/questionManagement/Question.g4 by ANTLR 4.12.0
package application.base.application.questionManagement;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QuestionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QuestionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QuestionParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QuestionParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#quotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuotation(QuestionParser.QuotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#questionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionType(QuestionParser.QuestionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching(QuestionParser.MatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matchingText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingText(QuestionParser.MatchingTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matchingOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingOptions(QuestionParser.MatchingOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matchingAnswers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingAnswers(QuestionParser.MatchingAnswersContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matchingAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingAnswer(QuestionParser.MatchingAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missingWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWord(QuestionParser.MissingWordContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missingWordText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordText(QuestionParser.MissingWordTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#mwText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMwText(QuestionParser.MwTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missingWordOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordOptions(QuestionParser.MissingWordOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missingWordOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordOption(QuestionParser.MissingWordOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missingWordAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordAnswer(QuestionParser.MissingWordAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#multipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoice(QuestionParser.MultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#multipleChoiceText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceText(QuestionParser.MultipleChoiceTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#multipleChoiceOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceOption(QuestionParser.MultipleChoiceOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#multipleChoiceAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceAnswer(QuestionParser.MultipleChoiceAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#numeric}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric(QuestionParser.NumericContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#numericText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericText(QuestionParser.NumericTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#numericAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericAnswer(QuestionParser.NumericAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#short}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort(QuestionParser.ShortContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#shortText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortText(QuestionParser.ShortTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#shortAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortAnswer(QuestionParser.ShortAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#trueOrFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueOrFalse(QuestionParser.TrueOrFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#trueOrFalseText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueOrFalseText(QuestionParser.TrueOrFalseTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#trueOrFalseAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueOrFalseAnswer(QuestionParser.TrueOrFalseAnswerContext ctx);
}