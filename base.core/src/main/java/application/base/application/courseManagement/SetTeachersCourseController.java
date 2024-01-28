package application.base.application.courseManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.teachingManagement.Teaching;
import application.base.domain.teachingManagement.TeachingBuilder;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.teachingManagment.TeachingRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Teacher;
import application.base.usermanagement.domain.valueobjects.Acronym;
import application.base.usermanagement.repositories.TeacherRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Optional;

public class SetTeachersCourseController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();

    private TeachingRepository teachingRepository = PersistenceContext.repositories().teachingRepository();


    public Iterable<Course> findCourseWithOpenStatus() {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER)) {
            return courseRepository.findCourseWithOpenStatus();
        }
        return null;
    }

    public List<Teacher> findTeachersAvailableForCourse(Course course) {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER)) {
            List<Teacher> allTeachers = (List<Teacher>) teacherRepository.findAll();
            List<Teacher> teachersOfCourse = (List<Teacher>) teachingRepository.findTeachersOfCourse(course);
            for (Teacher teacher : teachersOfCourse) {
                if (allTeachers.contains(teacher)){
                    allTeachers.remove(teacher);
                }
            }
            return allTeachers;
        }
        return null;
    }

    public boolean courseHasTeacherInCharge(Course course){
        Teacher teacherInCharge = teachingRepository.findTeacherInChargeOfCourse(course);
        if (teacherInCharge != null) {
            return true;
        }
        return false;
    }


    public void saveTeacherInCharge(Teacher teacherInCharge, Course course) {
        Teaching newTeaching;
        newTeaching = new TeachingBuilder().ofTeacher(teacherInCharge).inCourse(course).asPrimary(true).build();
        teachingRepository.save(newTeaching);
    }

    public String saveTeacher(Teacher teacher, Course course) {
            Teaching newTeaching = new TeachingBuilder().ofTeacher(teacher).inCourse(course).asPrimary(false).build();
            teachingRepository.save(newTeaching);
            return "Teacher set was successful";
    }
}
