// Generated from C:/Repositories/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/examParsers\AutoExam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.createAutoExamParsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AutoExamParser}.
 */
public interface AutoExamListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(AutoExamParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(AutoExamParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(AutoExamParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(AutoExamParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(AutoExamParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(AutoExamParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#language}.
	 * @param ctx the parse tree
	 */
	void enterLanguage(AutoExamParser.LanguageContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#language}.
	 * @param ctx the parse tree
	 */
	void exitLanguage(AutoExamParser.LanguageContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(AutoExamParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(AutoExamParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#headerDescription}.
	 * @param ctx the parse tree
	 */
	void enterHeaderDescription(AutoExamParser.HeaderDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#headerDescription}.
	 * @param ctx the parse tree
	 */
	void exitHeaderDescription(AutoExamParser.HeaderDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#feedbackType}.
	 * @param ctx the parse tree
	 */
	void enterFeedbackType(AutoExamParser.FeedbackTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#feedbackType}.
	 * @param ctx the parse tree
	 */
	void exitFeedbackType(AutoExamParser.FeedbackTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#gradeType}.
	 * @param ctx the parse tree
	 */
	void enterGradeType(AutoExamParser.GradeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#gradeType}.
	 * @param ctx the parse tree
	 */
	void exitGradeType(AutoExamParser.GradeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterStructure(AutoExamParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitStructure(AutoExamParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(AutoExamParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(AutoExamParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 */
	void enterSectionStructure(AutoExamParser.SectionStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#sectionStructure}.
	 * @param ctx the parse tree
	 */
	void exitSectionStructure(AutoExamParser.SectionStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(AutoExamParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(AutoExamParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#questiontype}.
	 * @param ctx the parse tree
	 */
	void enterQuestiontype(AutoExamParser.QuestiontypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#questiontype}.
	 * @param ctx the parse tree
	 */
	void exitQuestiontype(AutoExamParser.QuestiontypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#matching}.
	 * @param ctx the parse tree
	 */
	void enterMatching(AutoExamParser.MatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#matching}.
	 * @param ctx the parse tree
	 */
	void exitMatching(AutoExamParser.MatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#missingWord}.
	 * @param ctx the parse tree
	 */
	void enterMissingWord(AutoExamParser.MissingWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#missingWord}.
	 * @param ctx the parse tree
	 */
	void exitMissingWord(AutoExamParser.MissingWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoice(AutoExamParser.MultipleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoice(AutoExamParser.MultipleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#numeric}.
	 * @param ctx the parse tree
	 */
	void enterNumeric(AutoExamParser.NumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#numeric}.
	 * @param ctx the parse tree
	 */
	void exitNumeric(AutoExamParser.NumericContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#short}.
	 * @param ctx the parse tree
	 */
	void enterShort(AutoExamParser.ShortContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#short}.
	 * @param ctx the parse tree
	 */
	void exitShort(AutoExamParser.ShortContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutoExamParser#trueOrFalse}.
	 * @param ctx the parse tree
	 */
	void enterTrueOrFalse(AutoExamParser.TrueOrFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutoExamParser#trueOrFalse}.
	 * @param ctx the parse tree
	 */
	void exitTrueOrFalse(AutoExamParser.TrueOrFalseContext ctx);
}