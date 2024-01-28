package application.base.application.examManagement.createAutoExamParsers;

import application.base.application.examManagement.createExamParsers.ExamParser;
import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.ExamBuilder;
import application.base.domain.examManagement.Section;
import application.base.domain.examManagement.SectionBuilder;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.HeaderBuilder;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionBuilder;
import application.base.domain.questionManagement.QuestionType;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.questionManagement.QuestionRepository;
import org.antlr.v4.runtime.tree.ParseTree;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AutoExamEvalVisitor extends AutoExamBaseVisitor<Integer>{

    private QuestionRepository questionRepository = PersistenceContext.repositories().questionRepository();
    private Course course;
    private Exam exam;
    private ExamBuilder examBuilder = new ExamBuilder();
    private HeaderBuilder headerBuilder = new HeaderBuilder();

    private Section section;
    private SectionBuilder sectionBuilder = new SectionBuilder();
    private List<Section> sections = new ArrayList<>();

    private QuestionBuilder questionBuilder = new QuestionBuilder();
    private List<Question> questionList;

    int counterMatching = -1;
    int counterMissingWord = -1;
    int counterMultipleChoice = -1;
    int counterNumeric = -1;
    int counterShort = -1;
    int counterTrueOrFalse = -1;

    public Exam buildExam(ParseTree tree, Course c) {
        this.course = c;
        examBuilder.ofCourse(course);
        visit(tree);
        exam.changeSections(sections);
        return exam;
    }

    @Override
    public Integer visitTitle(AutoExamParser.TitleContext ctx) {
        examBuilder.withTitle(ctx.TEXT().getText().trim());
        return super.visitTitle(ctx);
    }

    @Override
    public Integer visitDescription(AutoExamParser.DescriptionContext ctx) {
        examBuilder.withDescription(ctx.TEXT().getText().trim());
        return super.visitDescription(ctx);
    }



    @Override
    public Integer visitLanguage(AutoExamParser.LanguageContext ctx) {
        examBuilder.withLanguage(new Language(ctx.TEXT().getText().trim()));
        return super.visitLanguage(ctx);
    }

    @Override
    public Integer visitHeader(AutoExamParser.HeaderContext ctx) {
        headerBuilder.withDescription(ctx.headerDescription().getText());
        headerBuilder.withFeedbackType(FeedbackType.valueOf(ctx.feedbackType().getText()));
        headerBuilder.withGradeType(GradeType.valueOf(ctx.feedbackType().getText()));

        examBuilder.withHeader(headerBuilder.build());
        exam = examBuilder.buildAuto();

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
    public Integer visitStructure(AutoExamParser.StructureContext ctx) {
        return super.visitStructure(ctx);
    }


    @Override
    public Integer visitSection(AutoExamParser.SectionContext ctx) {
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
    public Integer visitSectionStructure(AutoExamParser.SectionStructureContext ctx) {
        return super.visitSectionStructure(ctx);
    }

    @Override
    public Integer visitQuestion(AutoExamParser.QuestionContext ctx) {

        Question question = buildQuestion(ctx.questiontype());
        questionList.add(question);
        return super.visitQuestion(ctx);
    }

    private Question buildQuestion(AutoExamParser.QuestiontypeContext ctx) {

        if (ctx.matching() != null) {
            counterMatching++;
            return buildMatchingQuestion(ctx.matching(),counterMatching);
        } else if (ctx.missingWord() != null) {
            counterMissingWord++;
            return buildMissingWordQuestion(ctx.missingWord(),counterMissingWord);
        } else if (ctx.multipleChoice()!= null) {
            counterMultipleChoice++;
            return buildMultipleChoiceQuestion(ctx.multipleChoice(),counterMultipleChoice);
        } else if (ctx.numeric() != null) {
            counterNumeric++;
            return buildNumericQuestion(ctx.numeric(),counterNumeric);
        } else if (ctx.short_() != null) {
            counterShort++;
            return buildShortQuestion(ctx.short_(),counterShort);
        } else if (ctx.trueOrFalse() != null) {
            counterTrueOrFalse++;
            return buildTrueOrFalseQuestion(ctx.trueOrFalse(),counterTrueOrFalse);
        }
        return null;
    }

    private Question buildMatchingQuestion(AutoExamParser.MatchingContext ctx, int counter) {
        List<Question> questions = (List<Question>) questionRepository.findByCourseAndType(this.course.courseCode(), QuestionType.MATCHING);

        if (counter < questions.size()) {
            Question question = questions.get(counter);
            question.setSection(this.section);
            return question;
        } else {
            throw new RuntimeException("No question available at position " + counter + " for course " + this.course.courseCode() +" with type " + QuestionType.MATCHING);
        }
    }


    private Question buildMissingWordQuestion(AutoExamParser.MissingWordContext ctx, int counter) {
        List<Question> questions = (List<Question>) questionRepository.findByCourseAndType(this.course.courseCode(), QuestionType.MISSING_WORD);

        if (counter < questions.size()) {
            Question question = questions.get(counter);
            question.setSection(this.section);
            return question;
        } else {
            throw new RuntimeException("No question available at position " + counter + " for course " + this.course.courseCode() +" with type " + QuestionType.MISSING_WORD);
        }
    }


    private Question buildMultipleChoiceQuestion(AutoExamParser.MultipleChoiceContext ctx, int counter) {
        List<Question> questions = (List<Question>) questionRepository.findByCourseAndType(this.course.courseCode(), QuestionType.MULTIPLE_CHOICE);

        if (counter < questions.size()) {
            Question question = questions.get(counter);
            question.setSection(this.section);
            return question;
        } else {
            throw new RuntimeException("No question available at position " + counter + " for course " + this.course.courseCode() +" with type " + QuestionType.MULTIPLE_CHOICE);
        }
    }


    private Question buildNumericQuestion(AutoExamParser.NumericContext ctx, int counter) {
        List<Question> questions = (List<Question>) questionRepository.findByCourseAndType(this.course.courseCode(), QuestionType.NUMERIC);

        if (counter < questions.size()) {
            Question question = questions.get(counter);
            question.setSection(this.section);
            return question;
        } else {
            throw new RuntimeException("No question available at position " + counter + " for course " + this.course.courseCode() +" with type " + QuestionType.NUMERIC);
        }
    }


    private Question buildShortQuestion(AutoExamParser.ShortContext ctx, int counter) {
        List<Question> questions = (List<Question>) questionRepository.findByCourseAndType(this.course.courseCode(), QuestionType.SHORT);

        if (counter < questions.size()) {
            Question question = questions.get(counter);
            question.setSection(this.section);
            return question;
        } else {
            throw new RuntimeException("No question available at position " + counter + " for course " + this.course.courseCode() +" with type " + QuestionType.SHORT);
        }
    }


    private Question buildTrueOrFalseQuestion(AutoExamParser.TrueOrFalseContext ctx, int counter) {
        List<Question> questions = (List<Question>) questionRepository.findByCourseAndType(this.course.courseCode(), QuestionType.TRUE_OR_FALSE);

        if (counter < questions.size()) {
            Question question = questions.get(counter);
            question.setSection(this.section);
            return question;
        } else {
            throw new RuntimeException("No question available at position " + counter + " for course " + this.course.courseCode() +" with type " + QuestionType.TRUE_OR_FALSE);
        }
    }


}
