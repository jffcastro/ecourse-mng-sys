package application.base.application.examManagement;

import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.valueObjects.ExamStatus;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.repositories.examManagement.ExamRepository;
import application.base.usermanagement.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class ListFutureExamsService {

    private ExamEnrollmentRepository examEnrollmentRepository = PersistenceContext.repositories().examEnrollmentRepository();

    private ExamRepository examRepository = PersistenceContext.repositories().examRepository();

    public List<Exam> findListStudentFutureExams(Student student){
        Iterable<ExamEnrollment> studentExamsEnrollments = examEnrollmentRepository.findStudentExamEnrollmentsWithEnrolledStatus(student);
        List<Exam> studentFutureExams = new ArrayList<>();
        for(ExamEnrollment examEnrollment : studentExamsEnrollments){
            Exam exam = examRepository.findById((examEnrollment.examID())).get();
            if(exam.examStatus().equals(ExamStatus.CREATED)){
                studentFutureExams.add(exam);
            }
        }
        return studentFutureExams;
    }



}
