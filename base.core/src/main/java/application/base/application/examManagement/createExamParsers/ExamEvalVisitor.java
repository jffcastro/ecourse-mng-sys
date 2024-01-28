package application.base.application.examManagement.createExamParsers;

import application.base.domain.examManagement.Section;
import application.base.domain.examManagement.SectionBuilder;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.HeaderBuilder;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionBuilder;
import application.base.domain.questionManagement.QuestionType;
import org.antlr.v4.runtime.tree.ParseTree;
import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.ExamBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamEvalVisitor extends ExamBaseVisitor<Integer> {

    private Course course;
    private Exam exam;
    private ExamBuilder examBuilder = new ExamBuilder();
    private HeaderBuilder headerBuilder = new HeaderBuilder();

    private Section section;
    private SectionBuilder sectionBuilder = new SectionBuilder();
    private List<Section> sections = new ArrayList<>();

    private QuestionBuilder questionBuilder = new QuestionBuilder();
    private List<Question> questionList;

    public Exam buildExam(ParseTree tree, Course c) {
        this.course = c;
        examBuilder.ofCourse(course);
        visit(tree);
        exam.changeSections(sections);
        return exam;
    }

    @Override
    public Integer visitTitle(ExamParser.TitleContext ctx) {
        examBuilder.withTitle(ctx.TEXT().getText().trim());
        return super.visitTitle(ctx);
    }

    @Override
    public Integer visitDescription(ExamParser.DescriptionContext ctx) {
        examBuilder.withDescription(ctx.TEXT().getText().trim());
        return super.visitDescription(ctx);
    }

    @Override
    public Integer visitOpenDate(ExamParser.OpenDateContext ctx) {
        String dateString = String.valueOf(ctx.date().getText()); // Assuming ctx.date() returns a valid date string

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Specify the desired date format

        try {
            Date date = dateFormat.parse(dateString);
            examBuilder.withOpenDate(date);
        } catch (ParseException e) {
            // Handle any parse exception if necessary
            e.printStackTrace();
        }
        return super.visitOpenDate(ctx);
    }

    @Override
    public Integer visitCloseDate(ExamParser.CloseDateContext ctx) {
        String dateString = String.valueOf(ctx.date().getText()); // Assuming ctx.date() returns a valid date string

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Specify the desired date format

        try {
            Date date = dateFormat.parse(dateString);
            examBuilder.withCloseDate(date);
        } catch (ParseException e) {
            // Handle any parse exception if necessary
            e.printStackTrace();
        }
        return super.visitCloseDate(ctx);
    }

    @Override
    public Integer visitLanguage(ExamParser.LanguageContext ctx) {
        examBuilder.withLanguage(new Language(ctx.TEXT().getText().trim()));
        return super.visitLanguage(ctx);
    }

    @Override
    public Integer visitHeader(ExamParser.HeaderContext ctx) {
        headerBuilder.withDescription(ctx.headerDescription().getText());
        headerBuilder.withFeedbackType(FeedbackType.valueOf(ctx.feedbackType().getText()));
        headerBuilder.withGradeType(GradeType.valueOf(ctx.feedbackType().getText()));

        examBuilder.withHeader(headerBuilder.build());
        exam = examBuilder.build();

        return super.visitHeader(ctx);
    }

    /**
     * This method receives the string related to the feedback type from the grammar
     * and converts it into a FeedbackType object.
     *
     * @param feedbackTypeStr - string to be converted
     * @return FeedbackType converted
     */
    private FeedbackType convertFeedbackType(String feedbackTypeStr) {
        if (feedbackTypeStr.equals(FeedbackType.NONE.toString())) {
            return FeedbackType.NONE;
        } else if (feedbackTypeStr.equals(FeedbackType.ON_SUBMISSION.toString())) {
            return FeedbackType.ON_SUBMISSION;
        } else {
            return FeedbackType.AFTER_CLOSING;
        }
    }

    /**
     * This method receives the string related to the grade type from the grammar
     * and converts it into a GradeType object.
     *
     * @param gradeTypeStr - string to be converted
     * @return GradeType converted
     */
    private GradeType convertGradeType(String gradeTypeStr) {
        if (gradeTypeStr.equals(GradeType.NONE.toString())) {
            return GradeType.NONE;
        } else if (gradeTypeStr.equals(GradeType.ON_SUBMISSION.toString())) {
            return GradeType.ON_SUBMISSION;
        } else {
            return GradeType.AFTER_CLOSING;
        }
    }

    @Override
    public Integer visitStructure(ExamParser.StructureContext ctx) {
        return super.visitStructure(ctx);
    }

    @Override
    public Integer visitSection(ExamParser.SectionContext ctx) {
        sectionBuilder.withDescription(ctx.TEXT().getText());
        sectionBuilder.ofExam(exam);
        section = sectionBuilder.build();

        questionList = new ArrayList<>();
        visitSectionStructure(ctx.sectionStructure());
        section.changeQuestions(questionList);

        sections.add(section);
        return null;
    }

    @Override
    public Integer visitSectionStructure(ExamParser.SectionStructureContext ctx) {
        return super.visitSectionStructure(ctx);
    }

    @Override
    public Integer visitQuestion(ExamParser.QuestionContext ctx) {
        Question question = buildQuestion(ctx.questiontype());
        questionList.add(question);
        return super.visitQuestion(ctx);
    }

    private Question buildQuestion(ExamParser.QuestiontypeContext ctx) {
        questionBuilder.ofCourse(course);
        questionBuilder.ofSection(section);
        if (ctx.matching() != null) {
            return buildMatchingQuestion(ctx.matching());
        } else if (ctx.missingWord() != null) {
            return buildMissingWordQuestion(ctx.missingWord());
        } else if (ctx.multipleChoice()!= null) {
            return buildMultipleChoiceQuestion(ctx.multipleChoice());
        } else if (ctx.numeric() != null) {
            return buildNumericQuestion(ctx.numeric());
        } else if (ctx.short_() != null) {
            return buildShortQuestion(ctx.short_());
        } else if (ctx.trueOrFalse() != null) {
            return buildTrueOrFalseQuestion(ctx.trueOrFalse());
        }
        return null;
    }

    private Question buildMatchingQuestion(ExamParser.MatchingContext ctx){
        questionBuilder.ofQuestionType(QuestionType.MATCHING);
        questionBuilder.withQuestion(ctx.matchingText().getText());
        questionBuilder.withCorrectAnswer(ctx.matchingAnswers().getText());
        questionBuilder.withQuotation(Integer.valueOf(ctx.quotation().getText()));
        return questionBuilder.build();
    }

    private Question buildMissingWordQuestion(ExamParser.MissingWordContext ctx){
        questionBuilder.ofQuestionType(QuestionType.MISSING_WORD);
        questionBuilder.withQuestion(ctx.missingWordText().getText());
        questionBuilder.withCorrectAnswer(ctx.missingWordAnswer().getText());
        questionBuilder.withQuotation(Integer.valueOf(ctx.quotation().getText()));
        return questionBuilder.build();
    }

    private Question buildMultipleChoiceQuestion(ExamParser.MultipleChoiceContext ctx){
        questionBuilder.ofQuestionType(QuestionType.MULTIPLE_CHOICE);
        questionBuilder.withQuestion(ctx.multipleChoiceText().getText());
        questionBuilder.withCorrectAnswer(ctx.multipleChoiceAnswer().getText());
        questionBuilder.withQuotation(Integer.valueOf(ctx.quotation().getText()));
        return questionBuilder.build();
    }

    private Question buildNumericQuestion(ExamParser.NumericContext ctx){
        questionBuilder.ofQuestionType(QuestionType.NUMERIC);
        questionBuilder.withQuestion(ctx.numericText().getText());
        questionBuilder.withCorrectAnswer(ctx.numericAnswer().getText());
        questionBuilder.withQuotation(Integer.valueOf(ctx.quotation().getText()));
        return questionBuilder.build();
    }

    private Question buildShortQuestion(ExamParser.ShortContext ctx){
        questionBuilder.ofQuestionType(QuestionType.SHORT);
        questionBuilder.withQuestion(ctx.shortText().getText());
        questionBuilder.withCorrectAnswer(ctx.shortAnswer().getText());
        questionBuilder.withQuotation(Integer.valueOf(ctx.quotation().getText()));
        return questionBuilder.build();
    }

    private Question buildTrueOrFalseQuestion(ExamParser.TrueOrFalseContext ctx){
        questionBuilder.ofQuestionType(QuestionType.TRUE_OR_FALSE);
        questionBuilder.withQuestion(ctx.trueOrFalseText().getText());
        questionBuilder.withCorrectAnswer(ctx.trueOrFalseAnswer().getText());
        questionBuilder.withQuotation(Integer.valueOf(ctx.quotation().getText()));
        return questionBuilder.build();
    }
}

