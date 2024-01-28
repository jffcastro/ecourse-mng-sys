package application.base.domain.classManagement;

import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.validations.Preconditions;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The type Course class.
 */
@Entity
public class CourseClass implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String classTitle;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateOfCourseClass;
    @Column(nullable = false)
    private String weekDay;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany
    private List<Student> students;

    @ManyToOne
    private Course course;

    /**
     * Instantiates a new Course class.
     */
    protected CourseClass(){}

    /**
     * Instantiates a new Course class.
     *
     * @param teacher           the teacher
     * @param classTitle        the class title
     * @param dateOfCourseClass the date of course class
     * @param weekDay           the week day
     * @param startTime         the start time
     * @param endTime           the end time
     * @param course            the course
     * @param students          the students
     */
    public CourseClass(final Teacher teacher, final String classTitle, final Date dateOfCourseClass,final String weekDay, final LocalTime startTime, final LocalTime endTime ,
                       final Course course, final List<Student> students) {
        Preconditions.noneNull(classTitle, dateOfCourseClass, weekDay,startTime,endTime,course, students,teacher);
        Preconditions.ensure(validateTitle(classTitle), "The title must be between 3 and 30 characters");
        Preconditions.ensure(isDateAfterCurrentDate(dateOfCourseClass), "The date of the course class must be after the current date");
        Preconditions.ensure(validateClassTime(startTime, endTime), "The class time must be valid");
        Preconditions.ensure(isValidWeekday(weekDay), "The week days must be valid");

        this.teacher = teacher;
        this.classTitle = classTitle;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
        this.students = students;
        this.dateOfCourseClass = dateOfCourseClass;
    }

    /**
     * Is valid weekday boolean.
     *
     * @param weekday the weekday
     * @return the boolean
     */
    public static boolean isValidWeekday(String weekday) {
        String[] validWeekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String validWeekday : validWeekdays) {
            if (validWeekday.equalsIgnoreCase(weekday)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateClassTime(LocalTime startTime, LocalTime endTime) {
        if (startTime == null || endTime == null) {
            return false;
        }

        if (startTime.isAfter(endTime)) {
            return false; // Start time should be before end time
        }

        // Add any additional validation rules specific to class time

        return true;
    }

    private boolean isDateAfterCurrentDate(Date date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate.isAfter(currentDate);
    }

    private boolean validateTitle(String title){
        int minLength = 3;
        int maxLength = 30;

        int length = title.length();

        return length >= minLength && length <= maxLength;
    }


    /**
     * Gets week day.
     *
     * @return the week day
     */
    public String getWeekDay() {
        return weekDay;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets date of course class.
     *
     * @return the date of course class
     */
    public Date getDateOfCourseClass() {
        return dateOfCourseClass;
    }

    /**
     * Gets course.
     *
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Gets students.
     *
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Class title string.
     *
     * @return the string
     */
    public String classTitle() {
        return classTitle;
    }

    /**
     * Gets teacher.
     *
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }



    public Long identity() {
        return id;
    }

    /**
     * Sets date of course class.
     *
     * @param dateOfCourseClass the date of course class
     */
    public void setDateOfCourseClass(Date dateOfCourseClass) {
        this.dateOfCourseClass = dateOfCourseClass;
    }

    /**
     * Sets week day.
     *
     * @param weekDay the week day
     */
    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual((DomainEntity<?>) this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode((DomainEntity<?>) this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }


    @Override
    public String toString() {
        return "Class" + classTitle +
                " at: " + weekDay +
                " starts at: " + startTime +
                " and ends at: " + endTime;
    }


}
