// Generated from /Users/mariainessilvapinto/sem4pi-22-23-6/base.core/src/main/java/application/base/application/questionManagement/Question.g4 by ANTLR 4.12.0
package application.base.application.questionManagement.questionParsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuestionParser}.
 */
public interface QuestionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuestionParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QuestionParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QuestionParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#quotation}.
	 * @param ctx the parse tree
	 */
	void enterQuotation(QuestionParser.QuotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#quotation}.
	 * @param ctx the parse tree
	 */
	void exitQuotation(QuestionParser.QuotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterQuestionType(QuestionParser.QuestionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitQuestionType(QuestionParser.QuestionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#matching}.
	 * @param ctx the parse tree
	 */
	void enterMatching(QuestionParser.MatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matching}.
	 * @param ctx the parse tree
	 */
	void exitMatching(QuestionParser.MatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#matchingText}.
	 * @param ctx the parse tree
	 */
	void enterMatchingText(QuestionParser.MatchingTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matchingText}.
	 * @param ctx the parse tree
	 */
	void exitMatchingText(QuestionParser.MatchingTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#matchingOptions}.
	 * @param ctx the parse tree
	 */
	void enterMatchingOptions(QuestionParser.MatchingOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matchingOptions}.
	 * @param ctx the parse tree
	 */
	void exitMatchingOptions(QuestionParser.MatchingOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#matchingAnswers}.
	 * @param ctx the parse tree
	 */
	void enterMatchingAnswers(QuestionParser.MatchingAnswersContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matchingAnswers}.
	 * @param ctx the parse tree
	 */
	void exitMatchingAnswers(QuestionParser.MatchingAnswersContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#matchingAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMatchingAnswer(QuestionParser.MatchingAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matchingAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMatchingAnswer(QuestionParser.MatchingAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missingWord}.
	 * @param ctx the parse tree
	 */
	void enterMissingWord(QuestionParser.MissingWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missingWord}.
	 * @param ctx the parse tree
	 */
	void exitMissingWord(QuestionParser.MissingWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missingWordText}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordText(QuestionParser.MissingWordTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missingWordText}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordText(QuestionParser.MissingWordTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#mwText}.
	 * @param ctx the parse tree
	 */
	void enterMwText(QuestionParser.MwTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#mwText}.
	 * @param ctx the parse tree
	 */
	void exitMwText(QuestionParser.MwTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missingWordOptions}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordOptions(QuestionParser.MissingWordOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missingWordOptions}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordOptions(QuestionParser.MissingWordOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missingWordOption}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordOption(QuestionParser.MissingWordOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missingWordOption}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordOption(QuestionParser.MissingWordOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missingWordAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordAnswer(QuestionParser.MissingWordAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missingWordAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordAnswer(QuestionParser.MissingWordAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoice(QuestionParser.MultipleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoice(QuestionParser.MultipleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#multipleChoiceText}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceText(QuestionParser.MultipleChoiceTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#multipleChoiceText}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceText(QuestionParser.MultipleChoiceTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#multipleChoiceOption}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceOption(QuestionParser.MultipleChoiceOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#multipleChoiceOption}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceOption(QuestionParser.MultipleChoiceOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#multipleChoiceAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceAnswer(QuestionParser.MultipleChoiceAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#multipleChoiceAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceAnswer(QuestionParser.MultipleChoiceAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#numeric}.
	 * @param ctx the parse tree
	 */
	void enterNumeric(QuestionParser.NumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#numeric}.
	 * @param ctx the parse tree
	 */
	void exitNumeric(QuestionParser.NumericContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#numericText}.
	 * @param ctx the parse tree
	 */
	void enterNumericText(QuestionParser.NumericTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#numericText}.
	 * @param ctx the parse tree
	 */
	void exitNumericText(QuestionParser.NumericTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#numericAnswer}.
	 * @param ctx the parse tree
	 */
	void enterNumericAnswer(QuestionParser.NumericAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#numericAnswer}.
	 * @param ctx the parse tree
	 */
	void exitNumericAnswer(QuestionParser.NumericAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#short}.
	 * @param ctx the parse tree
	 */
	void enterShort(QuestionParser.ShortContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#short}.
	 * @param ctx the parse tree
	 */
	void exitShort(QuestionParser.ShortContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#shortText}.
	 * @param ctx the parse tree
	 */
	void enterShortText(QuestionParser.ShortTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#shortText}.
	 * @param ctx the parse tree
	 */
	void exitShortText(QuestionParser.ShortTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void enterShortAnswer(QuestionParser.ShortAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void exitShortAnswer(QuestionParser.ShortAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#trueOrFalse}.
	 * @param ctx the parse tree
	 */
	void enterTrueOrFalse(QuestionParser.TrueOrFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#trueOrFalse}.
	 * @param ctx the parse tree
	 */
	void exitTrueOrFalse(QuestionParser.TrueOrFalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#trueOrFalseText}.
	 * @param ctx the parse tree
	 */
	void enterTrueOrFalseText(QuestionParser.TrueOrFalseTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#trueOrFalseText}.
	 * @param ctx the parse tree
	 */
	void exitTrueOrFalseText(QuestionParser.TrueOrFalseTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#trueOrFalseAnswer}.
	 * @param ctx the parse tree
	 */
	void enterTrueOrFalseAnswer(QuestionParser.TrueOrFalseAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#trueOrFalseAnswer}.
	 * @param ctx the parse tree
	 */
	void exitTrueOrFalseAnswer(QuestionParser.TrueOrFalseAnswerContext ctx);
}