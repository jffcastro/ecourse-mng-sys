package application.base.persistence.impl.inmemory;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.domain.examManagement.Exam;
import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.usermanagement.domain.Student;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryExamEnrollmentRepository extends InMemoryDomainRepository<ExamEnrollment, Long>
        implements ExamEnrollmentRepository {

    @Override
    public Iterable<ExamEnrollment> findStudentExamEnrollmentsWithEnrolledStatus(Student student) {
        return null;
    }

    @Override
    public Iterable<ExamEnrollment> findExamGradesOfStudent(Student student) {
        return null;
    }

    @Override
    public Iterable<ExamEnrollment> findExamEnrollsOfExam(Exam exam) {
        return null;
    }

    @Override
    public Iterable<Long> findIdsOfExamAvailableForStudentToTake(Student student) {
        return null;
    }

    @Override
    public Optional<ExamEnrollment> findExamEnrollOfStudentInExam(Student student, Exam exam) {
        return null;
    }

    @Override
    public Iterable<ExamEnrollment> findGradesOfCourse(Course course) {
        return null;
    }
}
