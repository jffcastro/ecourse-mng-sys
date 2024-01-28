package application.base.infrastructure.bootstrapers.demo;

import application.base.application.courseManagement.CreateCourseController;
import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseName;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.usermanagement.domain.valueobjects.Acronym;
import application.base.usermanagement.repositories.TeacherRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

    private static final CourseRepository repo = PersistenceContext.repositories().courseRepository();

    private static final TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();
    private static final CreateCourseController createCourseController = new CreateCourseController();

    private Date getDate(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    @Override
    public boolean execute() {
        createCourseController.createCourse("EAPLI", CourseName.valueOf("Engenharia de Aplicações"),
                "Java based course. Learn to apply DDD patterns.", 50, 200);
        createCourseController.createCourse("SCOMP", CourseName.valueOf("Sistemas de Computadores"),
                "Learn process management techniques.", 50, 200);
        createCourseController.createCourse("LPROG", CourseName.valueOf("Linguagens e Programação"),
                "Learn lexic, syntactic and semantical analysis.", 50, 200);
        createCourseController.createCourse("RCOMP", CourseName.valueOf("Redes de Computadores"),
                "Learn network architectures and application protocols.", 50, 200);

        createCourseController.createCourse("MATCP", CourseName.valueOf("Curso aberto"),
                "curso aberto 12/2023", 50, 200);

        Course course = repo.findByCode("MATCP");
        course.openCourse(getDate("31/12/2023"));
        course.openEnrollments(getDate("31/06/2023"));
        repo.save(course);
        return true;
    }



}
