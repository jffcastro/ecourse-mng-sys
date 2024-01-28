package application.base.domain.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.valueObjects.*;
import application.base.domain.examManagement.valueObjects.header.Header;
import eapli.framework.validations.Preconditions;

import java.util.Date;
import java.util.List;

public class ExamBuilder {

    private Course course;
    private String title;
    private String description;
    private Date openDate;
    private Date closeDate;
    private Language language;
    private Header header;

    public ExamBuilder() {
    }

    public ExamBuilder ofCourse(Course course) {
        this.course = course;
        return this;
    }

    public ExamBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ExamBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ExamBuilder withOpenDate(Date openDate){
        this.openDate = openDate;
        return this;
    }

    public ExamBuilder withCloseDate(Date closeDate){
        this.closeDate = closeDate;
        return this;
    }

    public ExamBuilder withLanguage(Language language){
        this.language = language;
        return this;
    }

    public ExamBuilder withHeader(Header header){
        this.header = header;
        return this;
    }

    public Exam build() {
        Preconditions.noneNull(course, title, description, openDate, closeDate, language, header);
        return new Exam(course, title, description, openDate, closeDate, language, header);
    }

    public Exam buildAuto() {
        Preconditions.noneNull(course, title, description, language, header);
        return new Exam(course, title, description, language, header);
    }

}
