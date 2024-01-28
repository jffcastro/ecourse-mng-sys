package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.enrollmentManagement.EnrollmentsStatus;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.Student;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaEnrollmentRepository extends JpaAutoTxRepository<Enrollment, Long, Long> implements EnrollmentRepository {

    public JpaEnrollmentRepository(final TransactionalContext autoTx) {super(autoTx, "enrollmentID");}

    public JpaEnrollmentRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "enrollmentID");
    }

    @Override
    public List<Student> findUsersOfCourse(Course course) {
        TypedQuery<Student> query = entityManager().createQuery(
                "SELECT enrollment.student FROM Enrollment enrollment WHERE enrollment.course = :course",
                Student.class
        );
        query.setParameter("course", course);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findCoursesStudentIsEnroll(Student student) {
        TypedQuery<Course> query = entityManager().createQuery(
                "SELECT enrollment.course FROM Enrollment enrollment WHERE enrollment.student = :student AND (enrollment.enrollmentsStatus = :acceptedStatus OR enrollment.enrollmentsStatus = :underAppreciationStatus)" ,
                Course.class);
        query.setParameter("student", student);
        query.setParameter("acceptedStatus", EnrollmentsStatus.ACCEPTED);
        query.setParameter("underAppreciationStatus", EnrollmentsStatus.UNDER_APPRECIATION);
        return query.getResultList();
    }

    @Override
    public Enrollment findStudentEnrollmentInCourse(Student student, Course course) {
        TypedQuery<Enrollment> query = entityManager().createQuery(
                "SELECT enrollment FROM Enrollment enrollment WHERE enrollment.student = :student AND enrollment.course = :course " +
                "AND (enrollment.enrollmentsStatus = :acceptedStatus OR enrollment.enrollmentsStatus = :underAppreciationStatus)",
                Enrollment.class);
        query.setParameter("student", student);
        query.setParameter("course", course);
        query.setParameter("acceptedStatus", EnrollmentsStatus.ACCEPTED);
        query.setParameter("underAppreciationStatus", EnrollmentsStatus.UNDER_APPRECIATION);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Iterable<Enrollment> findEnrollmentsRequests() {
        TypedQuery<Enrollment> query = entityManager().createQuery(
                "SELECT enrollment FROM Enrollment enrollment WHERE enrollment.enrollmentsStatus = :status",
                Enrollment.class
        );
        query.setParameter("status", EnrollmentsStatus.UNDER_APPRECIATION);
        return query.getResultList();
    }

    @Override
    public Iterable<Enrollment> findEnrollmentsOfCourse(Course course) {
        TypedQuery<Enrollment> query = entityManager().createQuery(
                "SELECT enrollment FROM Enrollment enrollment" +
                        " WHERE enrollment.course = :course" +
                        " AND enrollment.enrollmentsStatus = :status",
                Enrollment.class
        );
        query.setParameter("course", course);
        query.setParameter("status", EnrollmentsStatus.ACCEPTED);

        return query.getResultList();
    }

}
