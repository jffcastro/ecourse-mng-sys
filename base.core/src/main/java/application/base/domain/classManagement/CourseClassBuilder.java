package application.base.domain.classManagement;

import application.base.domain.courseManagement.Course;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;


import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * The type Course class builder.
 */
public class CourseClassBuilder implements DomainFactory<CourseClass> {

    private Teacher teacher;
    private String classTitle;
    private Date dateOfCourseClass;

    private String weekDay;

    private LocalTime startTime;

    private LocalTime endTime;
    private Course course;

    private List<Student> students;

    /**
     * With teacher course class builder.
     *
     * @param teacher the teacher
     * @return the course class builder
     */
    public CourseClassBuilder withTeacher(Teacher teacher) {
        Preconditions.nonNull(teacher, "Teacher cannot be null");
        this.teacher=teacher;
        return this;
    }

    /**
     * With class title course class builder.
     *
     * @param classTitle the class title
     * @return the course class builder
     */
    public CourseClassBuilder withClassTitle(String classTitle) {
        Preconditions.nonEmpty(classTitle, "Class title cannot be empty");
        this.classTitle = classTitle;
        return this;
    }

    /**
     * With date of course class course class builder.
     *
     * @param dateOfCourseClass the date of course class
     * @return the course class builder
     */
    public CourseClassBuilder withDateOfCourseClass(Date dateOfCourseClass) {
        Preconditions.nonNull(dateOfCourseClass, "Start date cannot be null");
        this.dateOfCourseClass = dateOfCourseClass;
        return this;
    }

    /**
     * With course course class builder.
     *
     * @param course the course
     * @return the course class builder
     */
    public CourseClassBuilder withCourse(Course course) {
        this.course = course;
        return this;
    }

    /**
     * With students course class builder.
     *
     * @param students the students
     * @return the course class builder
     */
    public CourseClassBuilder withStudents(List<Student> students) {
        // Optional: You can perform any additional validations or modifications here.
        // For simplicity, I assume the provided list is valid.
        // If not provided, an empty list will be used.
        this.students = students;
        return this;
    }

    /**
     * With week days course class builder.
     *
     * @param weekDays the week days
     * @return the course class builder
     */
    public CourseClassBuilder withWeekDays(String weekDays) {
        Preconditions.nonNull(weekDays, "Week days cannot be null");
        this.weekDay = weekDays;
        return this;
    }

    /**
     * With start time course class builder.
     *
     * @param startTime the start time
     * @return the course class builder
     */
    public CourseClassBuilder withStartTime(LocalTime startTime) {
        Preconditions.nonNull(startTime, "Start time cannot be null");
        this.startTime = startTime;
        return this;
    }

    /**
     * With end time course class builder.
     *
     * @param endTime the end time
     * @return the course class builder
     */
    public CourseClassBuilder withEndTime(LocalTime endTime) {
        Preconditions.nonNull(endTime, "End time cannot be null");
        this.endTime = endTime;
        return this;
    }

    @Override
    public CourseClass build() {
        Preconditions.nonNull(teacher, "Teacher cannot be null");
        Preconditions.nonNull(classTitle, "Class title cannot be null");
        Preconditions.nonNull(dateOfCourseClass, "Start date cannot be null");
        Preconditions.nonNull(course, "Course cannot be null");
        Preconditions.nonNull(weekDay, "Week days cannot be null");
        Preconditions.nonNull(startTime, "Start time cannot be null");
        Preconditions.nonNull(endTime, "End time cannot be null");


        return new CourseClass(teacher, classTitle, dateOfCourseClass, weekDay, startTime,  endTime, course, students);
    }

}
