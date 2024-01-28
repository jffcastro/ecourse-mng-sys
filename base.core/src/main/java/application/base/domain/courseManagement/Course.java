package application.base.domain.courseManagement;

import application.base.domain.examManagement.Exam;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;


@Entity
@Table(name = "Course")
public class Course implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long courseId;

    @Column(unique = true, nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private CourseName courseName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer minStudents;

    @Column(nullable = false)
    private Integer maxStudents;

    @Temporal(TemporalType.DATE)
    private Date openCourseDate;

    @Temporal(TemporalType.DATE)
    private Date closeCourseDate;

    @Temporal(TemporalType.DATE)
    private Date openEnrollmentsDate;

    @Temporal(TemporalType.DATE)
    private Date closeEnrollmentsDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus courseStatus;


    protected Course() {
    }

    public Course(final String courseCode, final CourseName courseName, final String description,
                  final Integer minStudents, final Integer maxStudents) {
        Preconditions.noneNull(courseCode, courseName, description, minStudents);
        Preconditions.ensure(courseCode.length() > 3 && courseCode.length() <= 10);
        Preconditions.ensure(description.length() > 10 && description.length() <= 150);
        Preconditions.ensure(minStudents > 0 && maxStudents > minStudents);


        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
        this.courseStatus = CourseStatus.CLOSE;
    }

    public String courseCode() {return courseCode;}

    public CourseName courseName() {
        return courseName;
    }

    public String description() {
        return description;
    }

    public CourseStatus courseStatus() {
        return courseStatus;
    }

    public Date closeCourseDate() {
        return closeCourseDate;
    }

    public Long identity() {
        return courseId;
    }

    public boolean sameAs(final Object o) {
        if (!(o instanceof Course)) {
            return false;
        }

        final Course that = (Course) o;
        if (this == that) {
            return true;
        }

        return courseCode.equals(that.courseCode) && courseName.equals(that.courseName)
                && description.equals(that.description);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual((DomainEntity<?>) this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode((DomainEntity<?>) this);
    }

    /**
     * This method is used in US 1003, and it's used to open enrollments in a course.
     * Notes:
     * - Enrollments can only be open if course is also open.
     * - When opening enrollments, it must be defined an opening date (time of system)
     * and a close date, inserted by the user
     *
     * @param endDateEnroll - Calendar date when the enrollments in the chosen course will close
     */
    public void openEnrollments(Date endDateEnroll) {
        if (courseStatus.equals(CourseStatus.OPEN)) {
            this.courseStatus = CourseStatus.ENROLL;
            this.openEnrollmentsDate = Date.from(Instant.now());
            this.closeEnrollmentsDate = endDateEnroll;
        }
    }

    /**
     * This method is used in US 1003, and it's used to close enrollments in a course.
     * Notes:
     * - Enrollments can only be close if course has an enroll status.
     * - When closing the enrollments in a course, the system must set the close date to the time of the system
     *
     */
    public void closeEnrollments() {
        if (courseStatus.equals(CourseStatus.ENROLL)) {
            this.courseStatus = CourseStatus.IN_PROGRESS;
            this.closeEnrollmentsDate = Date.from(Instant.now());
        }
    }



    /**
     * This method is used in US 1004, and it's used to open a course.
     *
     * Notes:
     * - A course can only be open if closed.
     * - When opening a course, it must be defined an opening date (system date)
     * and a close date inserted by the user (must be later than system date)
     *
     * @param closeCourseDate - date when the course will close
     */
    public void openCourse(Date closeCourseDate){
        if (courseStatus.equals(CourseStatus.CLOSE) && closeCourseDate.after(Date.from(Instant.now()))){
            this.courseStatus = CourseStatus.OPEN;
            this.openCourseDate = Date.from(Instant.now());
            this.closeCourseDate = closeCourseDate;
        }
    }

    /**
     * This method is used in US 1004, and it's used to close a course.
     *
     * Notes:
     * - A course can be closed if in any state, except if already closed.
     * - When closing a course, the system must set the close date to the time of the system
     */
    public void closeCourse(){
        if (!courseStatus.equals(CourseStatus.CLOSE)){
            this.courseStatus = CourseStatus.CLOSE;
            this.closeCourseDate = Date.from(Instant.now());
        }
    }


    @Override
    public String toString() {
        return "Course: " + courseCode +
                " - " + courseName +
                "(" + description + ")" +
                ",\n with minimum " + minStudents + " students" +
                " and maximum " + maxStudents + " students" +
                ",\n with status: " + courseStatus;
    }
}