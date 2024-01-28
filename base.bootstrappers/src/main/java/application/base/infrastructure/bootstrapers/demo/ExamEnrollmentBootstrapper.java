package application.base.infrastructure.bootstrapers.demo;

import application.base.domain.enrollmentManagement.*;
import application.base.domain.examManagement.Exam;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.repositories.examManagement.ExamRepository;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.valueobjects.MecanographicNumber;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamEnrollmentBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

    private static final CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private static final ExamRepository examRepository = PersistenceContext.repositories().examRepository();
    private static final StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();
    private static final EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();
    private static final ExamEnrollmentRepository examEnrollmentRepository = PersistenceContext.repositories().examEnrollmentRepository();

    @Override
    public boolean execute() {
        Student student = studentRepository.findByMecanographicNumber(new MecanographicNumber("isep111"));
        Enrollment enrollment = enrollmentRepository.findStudentEnrollmentInCourse(student, courseRepository.findByCode("MATCP"));
        Exam exam = examRepository.findExamByTitle("test_dont_work").get();
        ExamEnrollment examEnrollment = new ExamEnrollment(exam.identity(), enrollment, ExamEnrollmentStatus.ENROLLED);
        examEnrollmentRepository.save(examEnrollment);

        return true;
    }
}
