// Generated from C:/Users/pedro/OneDrive/Ambiente de Trabalho/universidade/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/takeExamParsers\TakeExam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.takeExamParsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TakeExamParser}.
 */
public interface TakeExamListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TakeExamParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(TakeExamParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link TakeExamParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(TakeExamParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link TakeExamParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterStructure(TakeExamParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TakeExamParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitStructure(TakeExamParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TakeExamParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(TakeExamParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TakeExamParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(TakeExamParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TakeExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 */
	void enterSectionStructure(TakeExamParser.SectionStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TakeExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 */
	void exitSectionStructure(TakeExamParser.SectionStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TakeExamParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(TakeExamParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TakeExamParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(TakeExamParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TakeExamParser#questionAnswer}.
	 * @param ctx the parse tree
	 */
	void enterQuestionAnswer(TakeExamParser.QuestionAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link TakeExamParser#questionAnswer}.
	 * @param ctx the parse tree
	 */
	void exitQuestionAnswer(TakeExamParser.QuestionAnswerContext ctx);
}