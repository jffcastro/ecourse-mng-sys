package application.base.questionManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionBuilder;
import application.base.domain.questionManagement.QuestionType;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class QuestionTest {

    final QuestionBuilder questionBuilder = new QuestionBuilder();

    final CourseBuilder courseBuilder = new CourseBuilder();

    Integer quotation = 8;

    Course course = courseBuilder
            .withName("Course")
            .withCode("DESC")
            .withDescription("description")
            .hasMinStudents(10)
            .hasMaxStudents(20)
            .build();
    Question question = questionBuilder
            .ofCourse(course)
            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
            .withQuestion("question")
            .withCorrectAnswer("correct")
            .withQuotation(quotation)
            .build();

    Question question1 = questionBuilder
            .ofCourse(course)
            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
            .withQuestion("question")
            .withCorrectAnswer("correct")
            .withQuotation(quotation)
            .build();

    @Test
    public void testFailedBecauseCourseIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(null)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion("question")
                            .withCorrectAnswer("correct")
                            .withQuotation(quotation)
                            .build();
                }
        );
    }

    @Test
    public void testFailedBecauseQuestionTypeIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(course)
                            .ofQuestionType(null)
                            .withQuestion("question")
                            .withCorrectAnswer("correct")
                            .withQuotation(quotation)
                            .build();
                }
        );
    }
    @Test
    public void testFailedBecauseQuestionIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(course)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion(null)
                            .withCorrectAnswer("correct")
                            .withQuotation(quotation)
                            .build();
                }
        );
    }
    @Test
    public void testFailedBecauseCorrectAnswerIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(course)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion("question")
                            .withCorrectAnswer(null)
                            .withQuotation(quotation)
                            .build();
                }
        );
    }
    @Test
    public void testFailedBecauseQuotationIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    questionBuilder.ofCourse(course)
                            .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                            .withQuestion("question")
                            .withCorrectAnswer("correct")
                            .withQuotation(null)
                            .build();
                }
        );
    }

    /*@Test
    public void testMatchingToString() {
        Question question = questionBuilder.ofCourse(course)
                .ofQuestionType(QuestionType.MATCHING)
                .withQuestion("match the translation;cao, gato;cat, dog")
                .withCorrectAnswer("answer")
                .withQuotation(10)
                .build();

        String expected = "Matching Question: match the translation\n" +
                "cao         - cat       \n" +
                " gato      -  dog      \n";
        Assertions.assertEquals(expected, question.toString());
    }*/

    @Test
    public void testMissingWordToString() {
        Question question = questionBuilder.ofCourse(course)
                .ofQuestionType(QuestionType.MISSING_WORD)
                .withQuestion("Um * e uma estrutura de controlo que permite a * repetida de um bloco de codigo enquanto uma condicao especificada for verdadeira;objeto - laco - grafo - servico,execucao - gestao - repeticao - organizacao")
                .withCorrectAnswer("answer")
                .withQuotation(10)
                .build();

        String expected = "Missing Word Question: Um * e uma estrutura de controlo que permite a * repetida de um bloco de codigo enquanto uma condicao especificada for verdadeira\n" +
                "1. objeto - laco - grafo - servico\n" +
                "2. execucao - gestao - repeticao - organizacao\n";
        Assertions.assertEquals(expected, question.toString());
    }

    @Test
    public void testMultipleChoiceToString() {
        Question question = questionBuilder.ofCourse(course)
                .ofQuestionType(QuestionType.MULTIPLE_CHOICE)
                .withQuestion("how tall is the everest?;100metros,2000metros,7000metros")
                .withCorrectAnswer("answer")
                .withQuotation(10)
                .build();

        String expected = "Multiple Choice Question: how tall is the everest?\n" +
                "1. 100metros\n" +
                "2. 2000metros\n" +
                "3. 7000metros\n";
        Assertions.assertEquals(expected, question.toString());
    }

    @Test
    public void testNumericToString() {
        Question question = questionBuilder.ofCourse(course)
                .ofQuestionType(QuestionType.NUMERIC)
                .withQuestion("how tall is everest?")
                .withCorrectAnswer("answer")
                .withQuotation(10)
                .build();

        String expected = "Numeric Question: how tall is everest?\n";
        Assertions.assertEquals(expected, question.toString());
    }

    @Test
    public void testShortQuestionToString() {
        Question question = questionBuilder.ofCourse(course)
                .ofQuestionType(QuestionType.SHORT)
                .withQuestion("how tall is everest?")
                .withCorrectAnswer("answer")
                .withQuotation(10)
                .build();

        String expected = "Short Question: how tall is everest?\n";
        Assertions.assertEquals(expected, question.toString());
    }

    @Test
    public void testTrueOrFalseToString() {
        Question question = questionBuilder.ofCourse(course)
                .ofQuestionType(QuestionType.TRUE_OR_FALSE)
                .withQuestion("how tall is everest?")
                .withCorrectAnswer("answer")
                .withQuotation(10)
                .build();

        String expected = "True/False Question: how tall is everest?\n";
        Assertions.assertEquals(expected, question.toString());
    }

    @Test
    public void testSameAs() {
        Assertions.assertTrue(question.sameAs(question1));
        Assertions.assertTrue(question.sameAs(question));
        Assertions.assertFalse(question.sameAs("other"));
    }
}
