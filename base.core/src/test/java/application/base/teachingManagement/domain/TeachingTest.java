package application.base.teachingManagement.domain;

import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.teachingManagement.Teaching;
import application.base.domain.teachingManagement.TeachingBuilder;
import application.base.usermanagement.domain.*;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TeachingTest {

    //Teacher
    /**
     * The User builder.
     */
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();

    final SystemUser teacher = userBuilder
            .withUsername("Teacher")
            .withEmail("Teacher@email.com")
            .withPassword("Password1")
            .withName("TeacherFstName", "TeachertLstName")
            .withRoles(BaseRoles.TEACHER)
            .build();

    final Teacher teacher1 = new TeacherBuilder()
            .withSystemUser(teacher)
            .withAcronym("irs")
            .build();

    //Course
    final Course course = new CourseBuilder()
            .withCode("JAVA-1")
            .withName(new CourseName("EAPLI"))
            .withDescription("Java Programming")
            .hasMinStudents(10)
            .hasMaxStudents(20)
            .build();

    //Teaching
    private final boolean isPrimary = true;
    TeachingBuilder builder = new TeachingBuilder();
    Teaching teaching1 = builder
            .asPrimary(isPrimary)
            .inCourse(course)
            .ofTeacher(teacher1)
            .build();

    Teaching teaching2 = builder
            .asPrimary(isPrimary)
            .inCourse(course)
            .ofTeacher(teacher1)
            .build();

    @Test
    void teacher() {Assert.assertEquals(teacher1,teaching1.teacher());
    }

    @Test
    public void course() {Assert.assertEquals(course,teaching1.course());
    }

    @Test
    void isPrimary() {Assert.assertEquals(isPrimary,teaching1.isPrimary());
    }

    @org.junit.Test
    @Test
    public void sameAs() {
        Assert.assertTrue(teaching1.sameAs(teaching1));
        Assert.assertTrue(teaching1.sameAs(teaching2));
        Assert.assertFalse(teaching1.sameAs("o"));
    }

    @Test
    void testToString() {
        String expected = "Teaching" +
                " of teacher: " + teacher1.acronym() +
                " in course: " + course.courseCode();

        Assert.assertEquals(expected, teaching1.toString());
    }
}

