// Generated from C:/Users/pedro/OneDrive/Ambiente de Trabalho/universidade/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/examParsers\Exam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.createExamParsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExamParser}.
 */
public interface ExamListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExamParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(ExamParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(ExamParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(ExamParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(ExamParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#openDate}.
	 * @param ctx the parse tree
	 */
	void enterOpenDate(ExamParser.OpenDateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#openDate}.
	 * @param ctx the parse tree
	 */
	void exitOpenDate(ExamParser.OpenDateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#closeDate}.
	 * @param ctx the parse tree
	 */
	void enterCloseDate(ExamParser.CloseDateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#closeDate}.
	 * @param ctx the parse tree
	 */
	void exitCloseDate(ExamParser.CloseDateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#date}.
	 * @param ctx the parse tree
	 */
	void enterDate(ExamParser.DateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#date}.
	 * @param ctx the parse tree
	 */
	void exitDate(ExamParser.DateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#language}.
	 * @param ctx the parse tree
	 */
	void enterLanguage(ExamParser.LanguageContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#language}.
	 * @param ctx the parse tree
	 */
	void exitLanguage(ExamParser.LanguageContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(ExamParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(ExamParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#headerDescription}.
	 * @param ctx the parse tree
	 */
	void enterHeaderDescription(ExamParser.HeaderDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#headerDescription}.
	 * @param ctx the parse tree
	 */
	void exitHeaderDescription(ExamParser.HeaderDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#feedbackType}.
	 * @param ctx the parse tree
	 */
	void enterFeedbackType(ExamParser.FeedbackTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#feedbackType}.
	 * @param ctx the parse tree
	 */
	void exitFeedbackType(ExamParser.FeedbackTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#gradeType}.
	 * @param ctx the parse tree
	 */
	void enterGradeType(ExamParser.GradeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#gradeType}.
	 * @param ctx the parse tree
	 */
	void exitGradeType(ExamParser.GradeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterStructure(ExamParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitStructure(ExamParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(ExamParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(ExamParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 */
	void enterSectionStructure(ExamParser.SectionStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 */
	void exitSectionStructure(ExamParser.SectionStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(ExamParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(ExamParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#questiontype}.
	 * @param ctx the parse tree
	 */
	void enterQuestiontype(ExamParser.QuestiontypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#questiontype}.
	 * @param ctx the parse tree
	 */
	void exitQuestiontype(ExamParser.QuestiontypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#quotation}.
	 * @param ctx the parse tree
	 */
	void enterQuotation(ExamParser.QuotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#quotation}.
	 * @param ctx the parse tree
	 */
	void exitQuotation(ExamParser.QuotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 */
	void enterMatching(ExamParser.MatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 */
	void exitMatching(ExamParser.MatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#matchingText}.
	 * @param ctx the parse tree
	 */
	void enterMatchingText(ExamParser.MatchingTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#matchingText}.
	 * @param ctx the parse tree
	 */
	void exitMatchingText(ExamParser.MatchingTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#matchingOption}.
	 * @param ctx the parse tree
	 */
	void enterMatchingOption(ExamParser.MatchingOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#matchingOption}.
	 * @param ctx the parse tree
	 */
	void exitMatchingOption(ExamParser.MatchingOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#matchingAnswers}.
	 * @param ctx the parse tree
	 */
	void enterMatchingAnswers(ExamParser.MatchingAnswersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#matchingAnswers}.
	 * @param ctx the parse tree
	 */
	void exitMatchingAnswers(ExamParser.MatchingAnswersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#matchingAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMatchingAnswer(ExamParser.MatchingAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#matchingAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMatchingAnswer(ExamParser.MatchingAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#missingWord}.
	 * @param ctx the parse tree
	 */
	void enterMissingWord(ExamParser.MissingWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#missingWord}.
	 * @param ctx the parse tree
	 */
	void exitMissingWord(ExamParser.MissingWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#missingWordText}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordText(ExamParser.MissingWordTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#missingWordText}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordText(ExamParser.MissingWordTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#mwText}.
	 * @param ctx the parse tree
	 */
	void enterMwText(ExamParser.MwTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#mwText}.
	 * @param ctx the parse tree
	 */
	void exitMwText(ExamParser.MwTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#missingWordOptions}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordOptions(ExamParser.MissingWordOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#missingWordOptions}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordOptions(ExamParser.MissingWordOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#missingWordOption}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordOption(ExamParser.MissingWordOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#missingWordOption}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordOption(ExamParser.MissingWordOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#missingWordAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordAnswer(ExamParser.MissingWordAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#missingWordAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordAnswer(ExamParser.MissingWordAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoice(ExamParser.MultipleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoice(ExamParser.MultipleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#multipleChoiceText}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceText(ExamParser.MultipleChoiceTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#multipleChoiceText}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceText(ExamParser.MultipleChoiceTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#multipleChoiceOption}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceOption(ExamParser.MultipleChoiceOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#multipleChoiceOption}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceOption(ExamParser.MultipleChoiceOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#multipleChoiceAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceAnswer(ExamParser.MultipleChoiceAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#multipleChoiceAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceAnswer(ExamParser.MultipleChoiceAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#numeric}.
	 * @param ctx the parse tree
	 */
	void enterNumeric(ExamParser.NumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#numeric}.
	 * @param ctx the parse tree
	 */
	void exitNumeric(ExamParser.NumericContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#numericText}.
	 * @param ctx the parse tree
	 */
	void enterNumericText(ExamParser.NumericTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#numericText}.
	 * @param ctx the parse tree
	 */
	void exitNumericText(ExamParser.NumericTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#numericAnswer}.
	 * @param ctx the parse tree
	 */
	void enterNumericAnswer(ExamParser.NumericAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#numericAnswer}.
	 * @param ctx the parse tree
	 */
	void exitNumericAnswer(ExamParser.NumericAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#short}.
	 * @param ctx the parse tree
	 */
	void enterShort(ExamParser.ShortContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#short}.
	 * @param ctx the parse tree
	 */
	void exitShort(ExamParser.ShortContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#shortText}.
	 * @param ctx the parse tree
	 */
	void enterShortText(ExamParser.ShortTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#shortText}.
	 * @param ctx the parse tree
	 */
	void exitShortText(ExamParser.ShortTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void enterShortAnswer(ExamParser.ShortAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void exitShortAnswer(ExamParser.ShortAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#trueOrFalse}.
	 * @param ctx the parse tree
	 */
	void enterTrueOrFalse(ExamParser.TrueOrFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#trueOrFalse}.
	 * @param ctx the parse tree
	 */
	void exitTrueOrFalse(ExamParser.TrueOrFalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#trueOrFalseText}.
	 * @param ctx the parse tree
	 */
	void enterTrueOrFalseText(ExamParser.TrueOrFalseTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#trueOrFalseText}.
	 * @param ctx the parse tree
	 */
	void exitTrueOrFalseText(ExamParser.TrueOrFalseTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#trueOrFalseAnswer}.
	 * @param ctx the parse tree
	 */
	void enterTrueOrFalseAnswer(ExamParser.TrueOrFalseAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#trueOrFalseAnswer}.
	 * @param ctx the parse tree
	 */
	void exitTrueOrFalseAnswer(ExamParser.TrueOrFalseAnswerContext ctx);
}