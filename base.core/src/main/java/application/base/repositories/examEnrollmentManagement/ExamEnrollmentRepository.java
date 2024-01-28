package application.base.repositories.examEnrollmentManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.domain.examManagement.Exam;
import application.base.usermanagement.domain.Student;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ExamEnrollmentRepository extends DomainRepository<Long, ExamEnrollment> {

    /**
     * This method is used by US 2002 and returns the list the exam enrollments with status ENROLLED that the student logged has
     * @param student
     * @return list exam enrollments with status ENROLLED
     */
    Iterable<ExamEnrollment> findStudentExamEnrollmentsWithEnrolledStatus(Student student);

    /**
     * This method is used by US 2005 and returns the list of exam enrollment of a student
     * @param student
     * @return list of exam enrollment of a student
     */
    Iterable<ExamEnrollment> findExamGradesOfStudent(Student student);

    /**
     * This method is used in US 2004 and returns the list of exam enrollments
     * of a given exam.
     *
     * @param exam - exam to find exam enrollments for
     * @return iterable of exam enrollments of a given exam
     */
    Iterable<ExamEnrollment> findExamEnrollsOfExam(Exam exam);

    /**
     * This method is used in US 2004 and returns the list of exams
     * available for the student to take (exam enrollment status is
     * 'ENROLLED')
     *
     * @param student - student to find exams available for student to take
     * @return list of exams available for student to take
     */
    Iterable<Long> findIdsOfExamAvailableForStudentToTake(Student student);

    /**
     * This method is used in US 2004 and returns the exam enrollment
     * of a given student in a given exam.
     *
     * @param student - student to find exam enrollment of exam
     * @param exam    - exam to find exam enrollment of student
     * @return exam enrollment of a given student in a given exam
     */
    Optional<ExamEnrollment> findExamEnrollOfStudentInExam(Student student, Exam exam);

    /**
     * This method is used in US 2006 and return the list of exam enrollments
     * of a course which have grade (status as 'Present')
     *
     * @param course - course to find grades of
     * @return list of exam enrolls in the given course
     */
    Iterable<ExamEnrollment> findGradesOfCourse(Course course);

}
