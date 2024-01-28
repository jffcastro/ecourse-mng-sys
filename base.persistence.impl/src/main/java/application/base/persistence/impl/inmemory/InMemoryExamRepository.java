package application.base.persistence.impl.inmemory;

import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import application.base.repositories.examManagement.ExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryExamRepository extends InMemoryDomainRepository<Exam, Long>
        implements ExamRepository {

    @Override
    public Iterable<Exam> findExamsOfCourse(Course course) {
        return null;
    }

    @Override
    public Optional<Exam> findExamByTitle(String title) {
        return null;
    }

    @Override
    public boolean existsByTitle(String title) {
        return false;
    }

    @Override
    public Iterable<Exam> findFinishedExamsOfCourse(Course course) {
        return null;
    }
}
