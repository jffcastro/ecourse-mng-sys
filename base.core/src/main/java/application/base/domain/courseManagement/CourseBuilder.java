package application.base.domain.courseManagement;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

public class CourseBuilder implements DomainFactory<Course> {

    /**
     * creat a course
     */
    private Course theCourse;

    private String courseCode;
    private CourseName courseName;
    private String description;
    private Integer minStudents;
    private Integer maxStudents;

    public CourseBuilder withCode(final String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    public CourseBuilder withName(final String courseName) {
        this.courseName = new CourseName(courseName);
        return this;
    }

    public CourseBuilder withName(final CourseName courseName) {
        this.courseName = courseName;
        return this;
    }

    public CourseBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    public CourseBuilder hasMinStudents(final Integer minStudents) {
        this.minStudents = minStudents;
        return this;
    }

    public CourseBuilder hasMaxStudents(final Integer maxStudents) {
        this.maxStudents = maxStudents;
        return this;
    }

    @Override
    public Course build() {

        Preconditions.noneNull(courseCode, courseName, description, minStudents, maxStudents);

        theCourse = new Course(courseCode,
                courseName,
                description,
                minStudents,
                maxStudents);

        return theCourse;
    }

}