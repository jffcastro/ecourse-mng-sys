package application.base.domain.courseManagement;

public enum CourseStatus {
    /**
     * Indicates that the course is open
     */
    OPEN,

    /**
     * Indicates that the course is enroll, enrollments were open
     */
    ENROLL,

    /**
     * IIndicates that the course is enroll, enrollments were closed
     */
    IN_PROGRESS,

    /**
     * Indicates that the course is close
     */

    CLOSE;
}
