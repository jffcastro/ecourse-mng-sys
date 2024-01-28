package application.base.infrastructure.bootstrapers.demo;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.enrollmentManagement.EnrollmentBuilder;
import application.base.domain.enrollmentManagement.EnrollmentsStatus;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.valueobjects.MecanographicNumber;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnrollmentsBootsrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

    private static final CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();
    private static final StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();
    private static final EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();

    @Override
    public boolean execute() {
        Student student = studentRepository.findByMecanographicNumber(new MecanographicNumber("isep111"));
        Course course = courseRepository.findByCode("MATCP");
        Enrollment enrollment = new EnrollmentBuilder().withEnrollmentStatus(EnrollmentsStatus.ACCEPTED).ofStudent(student).inCourse(course).build();

        enrollmentRepository.save(enrollment);

        return true;
    }
}
