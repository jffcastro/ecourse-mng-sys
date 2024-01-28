package application.base.application.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;

import java.io.File;
import java.io.IOException;

public class CreateUpdateExamController {

    private CreateUpdateExamService svc = new CreateUpdateExamService();

    public File importFile(String path){
        return svc.importFile(path);
    }

    public Iterable<Course> findCoursesAvailableForTeacher() {
        return svc.findCoursesAvailableForTeacher();
    }

    public Exam createExam(Course course, File file) throws IOException {
        return svc.createExam(course, file);
    }

    public Iterable<Exam> findExamsAvailableForTeacher() {
        return svc.findExamsAvailableForTeacher();
    }

    public Exam updateExam(Exam exam, File file) throws IOException {
        return svc.updateExam(exam, file);
    }

}
