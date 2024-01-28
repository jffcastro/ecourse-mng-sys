package application.base.infrastructure.bootstrapers.demo;

import application.base.domain.examManagement.*;
import application.base.domain.examManagement.valueObjects.Language;
import application.base.domain.examManagement.valueObjects.header.FeedbackType;
import application.base.domain.examManagement.valueObjects.header.GradeType;
import application.base.domain.examManagement.valueObjects.header.Header;
import application.base.domain.examManagement.valueObjects.header.HeaderBuilder;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.examManagement.ExamRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

    private static final ExamRepository repo = PersistenceContext.repositories().examRepository();

    private static final CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private Date getDate(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    @Override
    public boolean execute() {
        Exam exam = new ExamBuilder().ofCourse(courseRepository.findByCode("EAPLI")).withTitle("title")
                .withDescription("teste").withOpenDate(getDate("30/07/2023")).withCloseDate(getDate("30/08/2023"))
                .withLanguage(new Language("language")).withHeader(new HeaderBuilder().withDescription("header").withFeedbackType(FeedbackType.NONE)
                        .withGradeType(GradeType.ON_SUBMISSION).build()).build();

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(new SectionBuilder().ofExam(exam).withDescription("teste").build());

        exam.changeSections(sectionList);
        repo.save(exam);


        Exam exam2 = new Exam(courseRepository.findByCode("MATCP"), "test_dont_work", "test that doesnt work",
                getDate("30/07/2023"), getDate("01/08/2023"), new Language("language"),
                new Header("header2", FeedbackType.AFTER_CLOSING, GradeType.AFTER_CLOSING));
        repo.save(exam2);

        return true;
    }


}
