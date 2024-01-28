package application.base.courseManagement.domain;

import application.base.domain.courseManagement.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Calendar;

public class CourseBuilderTest {

    @Test
    public void ensureCanBuildCourseWithCodeNameDescriptionMinMaxStudentsTeacherInCharge(){
        CourseBuilder builder = new CourseBuilder();
        Course actual = builder
                .withCode("JAVA-1")
                .withName(CourseName.valueOf("Eapli-22/23"))
                .withDescription("Java programming")
                .hasMinStudents(15)
                .hasMaxStudents(25)
                .build();

        Course expected = new Course("JAVA-1", new CourseName("Eapli-22/23"), "Java programming", 15, 25);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ensureCannotBuildWithNullCode(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Course(null, new CourseName("Eapli-22/23"), "Java programming", 15, 25);
        });
    }

    @Test
    public void ensureCannotBuildWithNullName(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Course("JAVA-1", null, "Java programming", 15, 25);
        });
    }

    @Test
    public void ensureCannotBuildWithNullDescription(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Course("JAVA-1", new CourseName("Eapli-22/23"), null, 15, 25);
        });
    }

    @Test
    public void ensureCannotBuildWithNullMinStudents(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Course("JAVA-1", new CourseName("Eapli-22/23"), "Java programming", null, 25);
        });
    }

    @Test
    public void ensureCannotBuildWithNullMaxStudents(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Course(null, new CourseName("Eapli-22/23"), "Java programming", 15, null);
        });
    }

}