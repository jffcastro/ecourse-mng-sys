package application.base.infrastructure.bootstrapers.demo;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.enrollmentManagement.EnrollmentBuilder;
import application.base.domain.enrollmentManagement.EnrollmentsStatus;
import application.base.domain.teachingManagement.Teaching;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.repositories.teachingManagment.TeachingRepository;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import application.base.usermanagement.domain.valueobjects.Acronym;
import application.base.usermanagement.domain.valueobjects.MecanographicNumber;
import application.base.usermanagement.repositories.StudentRepository;
import application.base.usermanagement.repositories.TeacherRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeachingsBootrsapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

    private static final CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();
    private static final TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();
    private static final TeachingRepository teachingRepository = PersistenceContext.repositories().teachingRepository();

    @Override
    public boolean execute() {
        Teacher teacher = teacherRepository.findByAcronym(new Acronym("emb")).get();
        Course course = courseRepository.findByCode("MATCP");
        Teaching teaching = new Teaching(teacher, course, false);

        teachingRepository.save(teaching);

        return true;
    }
}
