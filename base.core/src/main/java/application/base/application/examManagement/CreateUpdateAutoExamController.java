package application.base.application.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.examManagement.ExamRepository;

import java.io.File;
import java.io.IOException;

public class CreateUpdateAutoExamController {

    private CreateUpdateExamService svc = new CreateUpdateExamService();
    ExamRepository examRepository = PersistenceContext.repositories().examRepository();

    public File importFile(String path){
        return svc.importFile(path);
    }

    public Iterable<Course> findCoursesAvailableForStudent() {
        return svc.findCoursesAvailableForStudent();
    }

    public Exam createAutoExam(Course course, File file) throws IOException {
        return svc.createAutoExam(course, file);
    }

    public Iterable<Exam> findExamsAvailableForTeacher() {
        return svc.findExamsAvailableForTeacher();
    }
    public Exam checkExam(String id){
        return examRepository.findById(Long.parseLong(id)).orElse(null);
    }

}
