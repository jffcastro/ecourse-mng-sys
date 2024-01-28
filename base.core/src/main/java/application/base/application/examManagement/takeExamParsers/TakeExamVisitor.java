// Generated from C:/Users/pedro/OneDrive/Ambiente de Trabalho/universidade/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/takeExamParsers\TakeExam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.takeExamParsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TakeExamParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TakeExamVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TakeExamParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(TakeExamParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link TakeExamParser#structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructure(TakeExamParser.StructureContext ctx);
	/**
	 * Visit a parse tree produced by {@link TakeExamParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(TakeExamParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TakeExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSectionStructure(TakeExamParser.SectionStructureContext ctx);
	/**
	 * Visit a parse tree produced by {@link TakeExamParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(TakeExamParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TakeExamParser#questionAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionAnswer(TakeExamParser.QuestionAnswerContext ctx);
}