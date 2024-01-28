// Generated from C:/Repositories/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/examParsers\AutoExam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.createAutoExamParsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AutoExamParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AutoExamVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(AutoExamParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(AutoExamParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(AutoExamParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#language}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguage(AutoExamParser.LanguageContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(AutoExamParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#headerDescription}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderDescription(AutoExamParser.HeaderDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#feedbackType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedbackType(AutoExamParser.FeedbackTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#gradeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGradeType(AutoExamParser.GradeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructure(AutoExamParser.StructureContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(AutoExamParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSectionStructure(AutoExamParser.SectionStructureContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(AutoExamParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#questiontype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestiontype(AutoExamParser.QuestiontypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#matching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching(AutoExamParser.MatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#missingWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWord(AutoExamParser.MissingWordContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoice(AutoExamParser.MultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#numeric}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric(AutoExamParser.NumericContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#short}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort(AutoExamParser.ShortContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutoExamParser#trueOrFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueOrFalse(AutoExamParser.TrueOrFalseContext ctx);
}