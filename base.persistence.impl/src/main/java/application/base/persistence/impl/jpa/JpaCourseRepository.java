package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseStatus;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.teachingManagement.Teaching;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.repositories.teachingManagment.TeachingRepository;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaCourseRepository extends JpaAutoTxRepository<Course, Long, Long> implements CourseRepository {

    public JpaCourseRepository(final TransactionalContext autoTx) {
        super(autoTx, "courseId");
    }

    public JpaCourseRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "courseID");
    }

    public Iterable<Course> findAll(){
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT course FROM Course course",
                Course.class);

        return query.getResultList();
    }

    @Override
    public Course findByCode(String courseCode) {
        TypedQuery<Course> query = entityManager().createQuery(
                "SELECT course FROM Course course WHERE course.courseCode = :code", Course.class);
        query.setParameter("code", courseCode);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Course not found
        }
    }

    @Override
    public Iterable<Course> findCourseWithOpenStatus() {
        final TypedQuery<Course> course = entityManager().createQuery(
                ("SELECT course FROM Course course WHERE course.courseStatus = :status"),
                Course.class);
        course.setParameter("status", CourseStatus.OPEN);
        return course.getResultList();
    }

    @Override
    public Iterable<Course> findCourseWithEnrollStatus() {
        final TypedQuery<Course> course = entityManager().createQuery(
                ("SELECT course FROM Course course WHERE course.courseStatus = :status"),
                Course.class);
        course.setParameter("status", CourseStatus.ENROLL);
        return course.getResultList();
    }

    @Override
    public Iterable<Course> findCoursesAvailableToOpen(){
        TypedQuery<Course> query = entityManager().createQuery(
                "SELECT course FROM Course course WHERE course.courseStatus = :status" +
                        " AND course.openCourseDate IS NULL", Course.class);
        query.setParameter("status", CourseStatus.CLOSE);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findCoursesAvailableToClose(){
        TypedQuery<Course> query = entityManager().createQuery(
                "SELECT course FROM Course course WHERE course.courseStatus != :status", Course.class);
        query.setParameter("status", CourseStatus.CLOSE);

        return query.getResultList();
    }


    @Override
    public Iterable<Course> findCourseListForStudentWhereHeCanEnroll(Student student) {
        TypedQuery<Course> courseQuery = entityManager().createQuery(
                "SELECT course FROM Course course WHERE course.courseStatus = :status",
                Course.class);
        courseQuery.setParameter("status", CourseStatus.ENROLL);

        return courseQuery.getResultList();
    }

    @Override
    public Iterable<Course> findCourseListForStudent(SystemUser student) {
        return null;
    }


}
