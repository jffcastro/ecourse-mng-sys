package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.domain.classManagement.CourseClass;
import application.base.domain.meetingManagement.Meeting;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.classManagement.CourseClassRepository;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JpaCourseClassRepository extends JpaAutoTxRepository<CourseClass, Long, Long> implements CourseClassRepository {

    public JpaCourseClassRepository(final TransactionalContext autoTx) {
        super(autoTx, "classID");
    }

    public JpaCourseClassRepository(final String name){
        super(name, Application.settings().getExtendedPersistenceProperties(), "classID");
    }

    @Override
    public Iterable<CourseClass> findAllCourseClasses() {
        final TypedQuery<CourseClass> query = entityManager().createQuery(
                "SELECT courseClass FROM CourseClass courseClass",
                CourseClass.class);

        return query.getResultList();
    }

    @Override
    public CourseClass findCourseClassByTitle(String className) {
        TypedQuery<CourseClass> query = entityManager().createQuery(
                "SELECT courseClass FROM CourseClass courseClass WHERE courseClass.classTitle = :title", CourseClass.class);
        query.setParameter("title", className);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // CourseClass not found
        }
    }

    @Override
    public Iterable<CourseClass> findClassesOfStudent(Student studentToCheck) {
        List<CourseClass> coursesClassesStudent = new ArrayList<>();
        CourseClassRepository courseClassRepository = PersistenceContext.repositories().courseClassRepository();
        List<CourseClass> courseClasses = (List<CourseClass>) courseClassRepository.findAllCourseClasses();
        StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();
        Student student = studentRepository.findByMecanographicNumber(studentToCheck.identity());
        for (CourseClass courseClass : courseClasses) {
            if (courseClass.getStudents().contains(student)) {
                coursesClassesStudent.add(courseClass);
            }
        }
        return coursesClassesStudent;
    }

    @Override
    public Iterable<CourseClass> findClassesOfStudentOnDate(Student studentToCheck, Date date) {
        List<CourseClass> coursesClassesStudent = new ArrayList<>();
        CourseClassRepository courseClassRepository = PersistenceContext.repositories().courseClassRepository();
        List<CourseClass> courseClasses = (List<CourseClass>) courseClassRepository.findAllCourseClasses();
        StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();
        Student student = studentRepository.findByMecanographicNumber(studentToCheck.identity());
        for (CourseClass courseClass : courseClasses) {
            if (courseClass.getStudents().contains(student) && courseClass.getDateOfCourseClass().equals(date)) {
                coursesClassesStudent.add(courseClass);
            }
        }
        return coursesClassesStudent;
    }

    @Override
    public Iterable<CourseClass> findClassesOfTeacher(Teacher teacherToCheck) {
        List<CourseClass> coursesClassesTeacher = new ArrayList<>();
        CourseClassRepository courseClassRepository = PersistenceContext.repositories().courseClassRepository();
        List<CourseClass> courseClasses = (List<CourseClass>) courseClassRepository.findAllCourseClasses();
        for (CourseClass courseClass : courseClasses) {
            if (courseClass.getTeacher().identity().equals(teacherToCheck.identity())) {
                coursesClassesTeacher.add(courseClass);
            }
        }
        return coursesClassesTeacher;
    }

    @Override
    public Iterable<CourseClass> findClassesOfTeacherOnDate(Teacher teacherToCheck, Date date) {
        List<CourseClass> coursesClassesTeacher = new ArrayList<>();
        CourseClassRepository courseClassRepository = PersistenceContext.repositories().courseClassRepository();
        List<CourseClass> courseClasses = (List<CourseClass>) courseClassRepository.findAllCourseClasses();
        for (CourseClass courseClass : courseClasses) {
            if (courseClass.getTeacher().identity().equals(teacherToCheck.identity()) && courseClass.getDateOfCourseClass().equals(date)) {
                coursesClassesTeacher.add(courseClass);
            }
        }
        return coursesClassesTeacher;
    }

    @Override
    public Iterable<CourseClass> findClassOfTitleAndDate(String title, Date date) {

        List<CourseClass> coursesClassesTitleAndDate = new ArrayList<>();
        CourseClassRepository courseClassRepository = PersistenceContext.repositories().courseClassRepository();
        List<CourseClass> courseClasses = (List<CourseClass>) courseClassRepository.findAllCourseClasses();
        for (CourseClass courseClass : courseClasses) {
            if (courseClass.classTitle().equals(title) && courseClass.getDateOfCourseClass().equals(date)) {
                coursesClassesTitleAndDate.add(courseClass);
            }
        }
        return coursesClassesTitleAndDate;
    }

}
