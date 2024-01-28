package application.base.application.courseManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CreateCourseController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    public void createCourse(String courseCode, CourseName courseName, String description, Integer minStudents, Integer maxStudents){
        final Course newCourse = new CourseBuilder().withCode(courseCode)
                .withName(courseName)
                .withDescription(description)
                .hasMinStudents(minStudents)
                .hasMaxStudents(maxStudents).build();

        courseRepository.save(newCourse);
    }

    /**
     * This method validates the course code of the course that is going to be created.
     * It must have more than 3 characters and less than 50 characters.
     * @param courseCode
     * @return courseCode validation
     */
    public boolean validateCourseCode(String courseCode){
        if(courseCode.length() <= 3 || courseCode.length() > 10){
            return false;
        }
        return true;
    }

    /**
     * This method validates the course name of the course that is going to be created.
     * It must have more than 3 characters and less than 30 characters.
     * @param courseName
     * @return courseName validation
     */
    public boolean validateCourseName(CourseName courseName){
        if(courseName.length() <= 3 || courseName.length() > 30){
            return false;
        }
        return true;
    }

    /**
     * This method validates the course description of the course that is going to be created.
     * It must have more than 10 characters and less than 150 characters.
     * @param description
     * @return description validation
     */
    public boolean validateCourseDescription(String description){
        if(description.length() <= 10 || description.length() > 150){
            return false;
        }
        return true;
    }

    /**
     * This method validates the course minStudents of the course that is going to be created.
     * It can´t negative or zero.
     * @param minStudents
     * @return minStudents validation
     */
    public boolean validateMinStudents(Integer minStudents){
        if(minStudents <= 0){
            return false;
        }
        return true;
    }

    /**
     * This method validates the course maxStudents of the course that is going to be created.
     * It can´t negative or zero.
     * It can´t be less than the minimum students.
     * @param maxStudents
     * @return maxStudents validation
     */
    public boolean validateMaxStudents(Integer minStudents, Integer maxStudents){
        if(maxStudents <= 0){
            return false;
        } else if(minStudents > maxStudents){
            return false;
        }
        return true;
    }
}