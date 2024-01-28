package application.base.repositories.enrollmentManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.examManagement.Exam;
import application.base.usermanagement.domain.Student;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface EnrollmentRepository  extends DomainRepository<Long, Enrollment>{

    List<Student> findUsersOfCourse(Course course);

    /**
     * This method is used by US 1006 and returns the list of courses where the student is enrolled.
     *
     * @param student - student to find
     * @return list courses where the student is enrolled
     */
    Iterable<Course> findCoursesStudentIsEnroll(Student student);

    /**
     * This method is used by US 1007 and returns an enrollment with a given student
     * and a given course, if it exists, else returns null.
     *
     * @param student - student to find
     * @param course - course to find
     * @return enrollment found (or null)
     */
    Enrollment findStudentEnrollmentInCourse(Student student, Course course);

    /**
     * This method is used by US 1009 and returns the enrollment requests, which
     * are the enrollments where the status is 'UNDER_APPRECIATION'
     *
     * @return iterable of enrollment requets found
     */
    Iterable<Enrollment> findEnrollmentsRequests();

    /**
     * This method is used in US 2001 and returns the enrollments of a given course.
     *
     * @param course - course to filter the enrollments for
     * @return list of enrollments of the given course
     */
    Iterable<Enrollment> findEnrollmentsOfCourse(Course course);
}
