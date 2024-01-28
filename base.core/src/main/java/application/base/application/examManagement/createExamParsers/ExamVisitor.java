// Generated from C:/Users/pedro/OneDrive/Ambiente de Trabalho/universidade/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/examParsers\Exam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.createExamParsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExamParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExamVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExamParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(ExamParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(ExamParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#openDate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpenDate(ExamParser.OpenDateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#closeDate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCloseDate(ExamParser.CloseDateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate(ExamParser.DateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#language}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguage(ExamParser.LanguageContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(ExamParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#headerDescription}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderDescription(ExamParser.HeaderDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#feedbackType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedbackType(ExamParser.FeedbackTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#gradeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGradeType(ExamParser.GradeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructure(ExamParser.StructureContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(ExamParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSectionStructure(ExamParser.SectionStructureContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(ExamParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#questiontype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestiontype(ExamParser.QuestiontypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#quotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuotation(ExamParser.QuotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching(ExamParser.MatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#matchingText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingText(ExamParser.MatchingTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#matchingOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingOption(ExamParser.MatchingOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#matchingAnswers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingAnswers(ExamParser.MatchingAnswersContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#matchingAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingAnswer(ExamParser.MatchingAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#missingWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWord(ExamParser.MissingWordContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#missingWordText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordText(ExamParser.MissingWordTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#mwText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMwText(ExamParser.MwTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#missingWordOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordOptions(ExamParser.MissingWordOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#missingWordOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordOption(ExamParser.MissingWordOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#missingWordAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordAnswer(ExamParser.MissingWordAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoice(ExamParser.MultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#multipleChoiceText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceText(ExamParser.MultipleChoiceTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#multipleChoiceOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceOption(ExamParser.MultipleChoiceOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#multipleChoiceAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceAnswer(ExamParser.MultipleChoiceAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#numeric}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric(ExamParser.NumericContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#numericText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericText(ExamParser.NumericTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#numericAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericAnswer(ExamParser.NumericAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#short}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort(ExamParser.ShortContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#shortText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortText(ExamParser.ShortTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#shortAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortAnswer(ExamParser.ShortAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#trueOrFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueOrFalse(ExamParser.TrueOrFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#trueOrFalseText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueOrFalseText(ExamParser.TrueOrFalseTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#trueOrFalseAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueOrFalseAnswer(ExamParser.TrueOrFalseAnswerContext ctx);
}