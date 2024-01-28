package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.courseManagement.Course;
import application.base.domain.teachingManagement.Teaching;
import application.base.repositories.teachingManagment.TeachingRepository;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class JpaTeachingRepository extends JpaAutoTxRepository<Teaching, Long, Long> implements TeachingRepository {

    public JpaTeachingRepository(final TransactionalContext autoTx) {super(autoTx, "id");}

    public JpaTeachingRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "id");
    }
    public Iterable<Teaching> findAllTeachings(){
        TypedQuery<Teaching> query = entityManager().createQuery(
                "SELECT teaching FROM Teaching teaching", Teaching.class
        );
        return query.getResultList();
    }

    @Override
    public Iterable<Course> findCoursesWhereTeacherTeaches(Teacher teacher) {
        TypedQuery<Course> query = entityManager().createQuery(
                "SELECT teaching.course FROM Teaching teaching WHERE teaching.teacher = :teacher" ,
                Course.class);
        query.setParameter("teacher", teacher);
        return query.getResultList();
    }

    @Override
    public Iterable<Teacher> findTeachersOfCourse(Course course) {
        TypedQuery<Teacher> query = entityManager().createQuery(
                "SELECT teaching.teacher FROM Teaching teaching WHERE teaching.course = :course" ,
                Teacher.class);
        query.setParameter("course", course);
        return query.getResultList();
    }

    @Override
    public Teacher findTeacherInChargeOfCourse(Course course) {
        TypedQuery<Teacher> query = entityManager().createQuery(
                "SELECT teaching.teacher FROM Teaching teaching WHERE teaching.course = :course AND teaching.isPrimary = :isPrimary" ,
                Teacher.class);
        query.setParameter("course", course);
        query.setParameter("isPrimary", true);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
