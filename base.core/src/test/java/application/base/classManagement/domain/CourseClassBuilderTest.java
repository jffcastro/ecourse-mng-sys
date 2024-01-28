package application.base.classManagement.domain;

import application.base.domain.classManagement.CourseClass;
import application.base.domain.classManagement.CourseClassBuilder;
import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.usermanagement.domain.*;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseClassBuilderTest {

    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    final TeacherBuilder teacherBuilder = new TeacherBuilder();
    final StudentBuilder studentBuilder = new StudentBuilder();

    final List<Student> students = new ArrayList<>();
    final SystemUser teacher1 = userBuilder
            .withUsername("Teacher")
            .withEmail("Teacher@email.com")
            .withPassword("Password1")
            .withName("TeacherFstName", "TeacherLstName")
            .withRoles(BaseRoles.TEACHER)
            .build();

    final SystemUser student1 = userBuilder
            .withUsername("Student")
            .withEmail("Student@email.com")
            .withPassword("Password1")
            .withName("StudentFstName", "StudentLstName")
            .withRoles(BaseRoles.STUDENT)
            .build();

    Teacher teacher = teacherBuilder.withSystemUser(teacher1).withAcronym("acr").build();
    Student student = studentBuilder.withSystemUser(student1).withMecanographicNumber("1212121").build();
    Course course = new CourseBuilder()
            .withCode("JAVA-1")
            .withName(new CourseName("course name"))
            .withDescription("course description")
            .hasMinStudents(10)
            .hasMaxStudents(20)
            .build();

    LocalDate today = LocalDate.now();
    LocalDate dateIn3Day = today.plusDays(3);
    Date date = Date.from(dateIn3Day.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));

    LocalTime now = LocalTime.now();
    LocalTime after = now.plusHours(1);

    @Test
    public void ensureCanCreateCourseClassWithCourseClassBuilder() {

        students.add(student);

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        CourseClass courseClass = courseClassBuilder.withTeacher(teacher).withClassTitle("Class Title")
                .withDateOfCourseClass(date).withWeekDays("MONDAY").withStartTime(now).withEndTime(after)
                .withCourse(course).withStudents(students).build();

        CourseClass expected = new CourseClass(teacher, "Class Title", date, "MONDAY", now, after, course, students);

        assertEquals(expected, courseClass);
    }

    @Test
    public void ensureCannotCreateCourseClassWithNullTeacher() {

        students.add(student);

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            courseClassBuilder.withTeacher(null).withClassTitle("Class Title")
                    .withDateOfCourseClass(date).withWeekDays("MONDAY").withStartTime(now).withEndTime(after)
                    .withCourse(course).withStudents(students).build();
        });
    }

    @Test
    public void ensureCannotCreateCourseClassWithNullClassTitle() {

        students.add(student);

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            courseClassBuilder.withTeacher(teacher).withClassTitle(null)
                    .withDateOfCourseClass(date).withWeekDays("MONDAY").withStartTime(now).withEndTime(after)
                    .withCourse(course).withStudents(students).build();
        });
    }

    @Test
    public void ensureCannotCreateCourseClassWithNullDateOfCourseClass() {

        students.add(student);

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            courseClassBuilder.withTeacher(teacher).withClassTitle("Class Title")
                    .withDateOfCourseClass(null).withWeekDays("MONDAY").withStartTime(now).withEndTime(after)
                    .withCourse(course).withStudents(students).build();
        });
    }

    @Test
    public void ensureCannotCreateCourseClassWithNullWeekDays() {

        students.add(student);

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            courseClassBuilder.withTeacher(teacher).withClassTitle("Class Title")
                    .withDateOfCourseClass(date).withWeekDays(null).withStartTime(now).withEndTime(after)
                    .withCourse(course).withStudents(students).build();
        });
    }

    @Test
    public void ensureCannotCreateCourseClassWithNullStartTime() {

        students.add(student);

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            courseClassBuilder.withTeacher(teacher).withClassTitle("Class Title")
                    .withDateOfCourseClass(date).withWeekDays("MONDAY").withStartTime(null).withEndTime(after)
                    .withCourse(course).withStudents(students).build();
        });
    }

    @Test
    public void ensureCannotCreateCourseClassWithNullEndTime() {

        students.add(student);

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            courseClassBuilder.withTeacher(teacher).withClassTitle("Class Title")
                    .withDateOfCourseClass(date).withWeekDays("MONDAY").withStartTime(now).withEndTime(null)
                    .withCourse(course).withStudents(students).build();
        });
    }

    @Test
    public void ensureCannotCreateCourseClassWithNullCourse() {

        students.add(student);

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            courseClassBuilder.withTeacher(teacher).withClassTitle("Class Title")
                    .withDateOfCourseClass(date).withWeekDays("MONDAY").withStartTime(now).withEndTime(after)
                    .withCourse(null).withStudents(students).build();
        });
    }

    @Test
    public void ensureCannotCreateCourseClassWithNullStudents() {

        CourseClassBuilder courseClassBuilder = new CourseClassBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            courseClassBuilder.withTeacher(teacher).withClassTitle("Class Title")
                    .withDateOfCourseClass(date).withWeekDays("MONDAY").withStartTime(now).withEndTime(after)
                    .withCourse(course).withStudents(null).build();
        });
    }



}