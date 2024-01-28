package application.base.repositories.examManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.examManagement.Exam;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ExamRepository extends DomainRepository<Long, Exam> {

    /**
     * This method returns the exams of a given course.
     * Used in:
     * US2001 - Create/Update an Exam and
     * US2003 - I want to view a list of all exams in a course
     *
     * @param course - course to find the exams for
     * @return iterable of exams of course
     */
    Iterable<Exam>findExamsOfCourse(Course course);

    Optional<Exam> findExamByTitle(String title);

    Optional<Exam> findById(Long id);

    boolean existsByTitle(String title);

    /**
     * This method is used in US 2006 and returns the list of finished
     * exams of a given course.
     *
     * @param course - course to find finished exams for
     * @return iterable of finished exams of the given course
     */
    Iterable<Exam> findFinishedExamsOfCourse(Course course);
}
