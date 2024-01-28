package application.base.teachingManagement.domain;


import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.domain.teachingManagement.Teaching;
import application.base.domain.teachingManagement.TeachingBuilder;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Teacher;
import application.base.usermanagement.domain.TeacherBuilder;
import application.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Assert;
import org.junit.Test;

public class TeachingBuilderTest {
    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    /**
     * The Organizer.
     */
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

    private final boolean teachingPrimary = true;
    @Test
    public void ensureCanBuildTeaching(){
        TeachingBuilder builder = new TeachingBuilder();
        Teaching actual = builder
                .ofTeacher(teacher1)
                .inCourse(course)
                .asPrimary(teachingPrimary)
                .build();

        Teaching expected = new Teaching(teacher1,course,teachingPrimary);
        Assert.assertTrue(expected.sameAs(actual));
    }

    @Test
    public void ensureCannotBuildWithNullTeacher(){
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Teaching(null, course, teachingPrimary);
        });
    }

    @Test
    public void ensureCannotBuildWithNullCourse(){
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Teaching(teacher1, null, teachingPrimary);
        });
    }

}
