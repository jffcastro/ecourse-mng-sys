package application.base.infrastructure.bootstrapers.demo;
import application.base.domain.questionManagement.Question;
import application.base.domain.questionManagement.QuestionType;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.questionManagement.QuestionRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

    private static final CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private static final QuestionRepository repo = PersistenceContext.repositories().questionRepository();

    @Override
    public boolean execute() {
        shortQuestions();
        mcQuestions();
        matchingQuestions();
        mwQuestions();
        tfQuestions();
        numericQuestions();
        return true;
    }

    public void shortQuestions(){
        Question question1 = new Question(courseRepository.findByCode("MATCP"), QuestionType.SHORT, "What is the capital of Portugal", "Lisbon", 3);
        Question question2 = new Question(courseRepository.findByCode("MATCP"), QuestionType.SHORT, "What is the capital of Spain", "Madrid", 2);
        Question question3 = new Question(courseRepository.findByCode("MATCP"), QuestionType.SHORT, "What is the capital of Italy", "Rome", 2);

        repo.save(question1);
        repo.save(question2);
        repo.save(question3);
    }


    public void mcQuestions(){
        Question question1 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MULTIPLE_CHOICE, "How tall is Serra da Estrela?;" +
                "100metros," +
                "2000metros," +
                "7000metros;",
                "2000metros",
                3);
        Question question2 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MULTIPLE_CHOICE, "How many countries does the European Union have?;" +
                "20," +
                "25," +
                "27;",
                "27",
                2);
        Question question3 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MULTIPLE_CHOICE, "Portugal has a border with which country?;" +
                "Morocco," +
                "Spain," +
                "France;",
                "2000metros",
                2);

        repo.save(question1);
        repo.save(question2);
        repo.save(question3);
    }

    public void matchingQuestions(){
        Question question1 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MATCHING, "Match the translation;" +
                "cao,gato;" +
                "cat,dog;",
                "cao->dog," +
                "gato->cat",
                3);
        Question question2 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MATCHING, "Connect countries to the language they mostly speak;" +
                "Argentina,Brazil;" +
                "Portuguese,Spanish;",
                "Argentina->Spanish," +
                        "Brazil->Portuguese",
                2);
        Question question3 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MATCHING, "Connect cities to their country;" +
                "Porto,Barcelona;" +
                "Spain,Portugal;",
                "Porto->Portugal," +
                        "Barcelona->Spain",
                2);

        repo.save(question1);
        repo.save(question2);
        repo.save(question3);
    }

    public void mwQuestions(){
        Question question1 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MISSING_WORD, "Um *** e uma estrutura de controlo que permite a *** repetida de um bloco de codigo enquanto uma condicao especificada for verdadeira;" +
                "objeto - laco - grafo - servico," +
                "execucao - gestao - repeticao - organizacao",
                "laco,execucao",
                3);
        Question question2 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MISSING_WORD, "Porto is a city in the *** of Portugal;" +
                "North - South - Center",
                "North",
                2);
        Question question3 = new Question(courseRepository.findByCode("MATCP"), QuestionType.MISSING_WORD, "Barcelona is in the region of ****;" +
                "Basque - Galiza - Catalunia",
                "Catalunia",
                2);

        repo.save(question1);
        repo.save(question2);
        repo.save(question3);
    }

    public void tfQuestions(){
        Question question1 = new Question(courseRepository.findByCode("MATCP"), QuestionType.TRUE_OR_FALSE, "Porto has 2 Champions Leagues?;", "True", 3);
        Question question2 = new Question(courseRepository.findByCode("MATCP"), QuestionType.TRUE_OR_FALSE, "Portugal won the 2016 European Championship;", "True", 3);
        Question question3 = new Question(courseRepository.findByCode("MATCP"), QuestionType.TRUE_OR_FALSE, "Is the Everest the tallest mountain in the world?;", "True", 3);
        
        repo.save(question1);
        repo.save(question2);
        repo.save(question3);
    }

    public void numericQuestions(){
        Question question1 = new Question(courseRepository.findByCode("MATCP"), QuestionType.NUMERIC, "In what year did Argentina won their third World Cup?;", "2022", 3);
        Question question2 = new Question(courseRepository.findByCode("MATCP"), QuestionType.NUMERIC, "In what year was the Lisbon Earthquake?;", "1755", 3);
        Question question3 = new Question(courseRepository.findByCode("MATCP"), QuestionType.NUMERIC, "In what year was the revolution that sent down the dictactorship in Portugal?;", "1974", 3);

        repo.save(question1);
        repo.save(question2);
        repo.save(question3);
    }
}
