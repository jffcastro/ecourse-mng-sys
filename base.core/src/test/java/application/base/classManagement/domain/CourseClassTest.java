package application.base.classManagement.domain;

import application.base.courseManagement.domain.CourseTest;
import application.base.domain.classManagement.CourseClass;
import application.base.domain.classManagement.CourseClassBuilder;
import application.base.domain.courseManagement.Course;
import application.base.domain.courseManagement.CourseBuilder;
import application.base.domain.courseManagement.CourseName;
import application.base.usermanagement.domain.*;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import net.bytebuddy.asm.Advice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseClassTest {

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

    LocalDate dateIn1Month = today.plusMonths(1);
    Date date = Date.from(dateIn3Day.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
    Date setDate = Date.from(dateIn1Month.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));

    LocalTime now = LocalTime.now();
    LocalTime after = now.plusHours(1);



    @org.junit.Test
    public void ensureCantCreateCourseClassWithNullTeacher() {

        students.add(student);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(null, "Class Title", date, "MONDAY", now, after, course, students);
        });
    }

    @org.junit.Test
    public void ensureCantCreateCourseClassWithNullClassTitle() {

        students.add(student);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, null, date, "MONDAY", now, after, course, students);
        });

    }

    @org.junit.Test
    public void ensureCantCreateCourseClassWithNullDate() {

        students.add(student);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, "Class Title", null, "MONDAY", now, after, course, students);
        });
    }

    @org.junit.Test
    public void ensureCantCreateCourseClassWithNullWeekDays() {

        students.add(student);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, "Class Title", date, null, now, after, course, students);
        });
    }

    @org.junit.Test
    public void ensureCantCreateCourseClassWithNullStartTime() {

        students.add(student);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, "Class Title", date, "MONDAY", null, after, course, students);
        });
    }

    @org.junit.Test
    public void ensureCantCreateCourseClassWithNullEndTime() {

        students.add(student);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, "Class Title", date, "MONDAY", now, null, course, students);
        });
    }

    @org.junit.Test
    public void ensureCantCreateCourseClassWithNullCourse() {

        students.add(student);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, "Class Title", date, "MONDAY", now, after, null, students);
        });
    }

    @org.junit.Test
    public void ensureCantCreateCourseClassWithNullStudents() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, "Class Title", date, "MONDAY", now, after, course, null);
        });
    }

    @org.junit.Test
    public void ensureTitleMustBeBetween3And30Characters() {
        students.add(student);
        String weekDay = "MONDAY";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, "AB", date, weekDay, now, after, course, students);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, "A Title That Exceeds Maximum Character Limit", date, weekDay, now, after, course, students);
        });

        Assertions.assertDoesNotThrow(() -> {
            new CourseClass(teacher, "Valid Title", date, weekDay, now, after, course, students);
        });
    }

    @org.junit.Test
    public void ensureDateOfCourseClassMustBeAfterCurrentDate() {
        students.add(student);
        String weekDay = "MONDAY";
        String classTitle = "Class Title";

        LocalDate today = LocalDate.now();
        LocalDate dateBefore = today.minusDays(3);
        LocalDate dateAfter = today.plusDays(3);
        Date pastDate = Date.from(dateBefore.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        Date futureDate = Date.from(dateAfter.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));

                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    new CourseClass(teacher, classTitle, pastDate, weekDay, now, after, course, students);
                });

        Assertions.assertDoesNotThrow(() -> {
            new CourseClass(teacher, classTitle, futureDate, weekDay, now, after, course, students);
        });
    }

    @org.junit.Test
    public void ensureClassTimeMustBeValid() {
        students.add(student);
        String weekDay = "MONDAY";
        String classTitle = "Class Title";

        LocalTime invalidStartTime =  LocalTime.of(23, 59, 59); // create an invalid start time (23:59:59
                LocalTime invalidEndTime = LocalTime.of(0, 0, 0); // create an invalid end time (00:00:00)

                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    new CourseClass(teacher, classTitle, date, weekDay, invalidStartTime, after, course, students);
                });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, classTitle, date, weekDay, now, invalidEndTime, course, students);
        });

        Assertions.assertDoesNotThrow(() -> {
            new CourseClass(teacher, classTitle, date, weekDay, now, after, course, students);
        });
    }

    @org.junit.Test
    public void ensureWeekDaysMustBeValid() {
        students.add(student);

        String invalidWeekDay = "WRONG";
        String weekDay = "MONDAY";
        String classTitle = "Class Title";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CourseClass(teacher, classTitle, date, invalidWeekDay, now, after, course, students);
        });

        Assertions.assertDoesNotThrow(() -> {
            new CourseClass(teacher, classTitle, date, weekDay, now, after, course, students);
        });

        String[] weekDays = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
        for (String day : weekDays) {
            Assertions.assertTrue(CourseClass.isValidWeekday(day));
        }
    }

    @org.junit.Test
    public void ensureGetWeekDayIsCorrect() {
        String classTitle = "Class Title";
        students.add(student);

        String[] validWeekdays = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

        CourseClass test = new CourseClass(teacher, classTitle, date, validWeekdays[0], now, after, course, students);
        Assertions.assertEquals("MONDAY", test.getWeekDay());
    }

    @org.junit.Test
    public void ensureGetStartTimeIsCorrect() {
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertEquals(now, test.getStartTime());
        Assertions.assertNotEquals(test.getStartTime(), after);
    }

    @org.junit.Test
    public void ensureGetDateOfCourseClass() {
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertEquals(date, test.getDateOfCourseClass());
    }

    @org.junit.Test
    public void ensureGetCourse() {
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertEquals(course, test.getCourse());
    }

    @org.junit.Test
    public void ensureGetEndTime() {
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertEquals(after, test.getEndTime());
        Assertions.assertNotEquals(test.getEndTime(), now);
    }

    @org.junit.Test
    public void ensureGetStudents() {
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertEquals(students, test.getStudents());
    }

    @org.junit.Test
    public void ensureGetClassTitle() {
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertEquals(classTitle, test.classTitle());
    }

    @org.junit.Test
    public void ensureGetTeacher() {
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertEquals(teacher, test.getTeacher());
    }

    @org.junit.Test
    public void ensureSetStartTime() {
        String classTitle = "Class Title";
        students.add(student);

        LocalTime newStartTime = LocalTime.of(10, 0, 0);
        LocalTime newEndTime = LocalTime.of(11, 0, 0);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        test.setStartTime(newStartTime);
        Assertions.assertEquals(newStartTime, test.getStartTime());
        Assertions.assertNotEquals(newStartTime, test.getEndTime());
        Assertions.assertNotEquals(newEndTime, test.getStartTime());
    }

    @org.junit.Test
    public void ensureSetEndTime() {
        String classTitle = "Class Title";
        students.add(student);

        LocalTime newStartTime = LocalTime.of(10, 0, 0);
        LocalTime newEndTime = LocalTime.of(11, 0, 0);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        test.setEndTime(newEndTime);
        Assertions.assertEquals(newEndTime, test.getEndTime());
        Assertions.assertNotEquals(newEndTime, test.getStartTime());
        Assertions.assertNotEquals(newStartTime, test.getEndTime());
    }

    @org.junit.Test
    public void ensureIdentity() {
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);

        Assertions.assertEquals(test, test);
        Assertions.assertNotEquals(test, null);
        Assertions.assertNotEquals(test, new Object());
    }

    @org.junit.Test
    public void ensureSetDateOfCourseClass() {
        //ensure that the date of the course class is set correctly
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);

        test.setDateOfCourseClass(setDate);
        Assertions.assertEquals(setDate, test.getDateOfCourseClass());
    }

    @org.junit.Test
    public void ensureSetWeekDay() {
        //ensure that the weekday of the course class is set correctly
        String classTitle = "Class Title";
        students.add(student);

        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);

        String setWeekDay = "TUESDAY";

        test.setWeekDay(setWeekDay);
        Assertions.assertEquals(setWeekDay, test.getWeekDay());
    }

    @org.junit.Test
    public void ensureTestEquals() {
        String classTitle = "Class Title";
        students.add(student);
        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        CourseClass test2 = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertEquals(test, test2);
    }

    @org.junit.Test
    public void ensureSameAs() {
        String classTitle = "Class Title";
        students.add(student);
        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        CourseClass test2 = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        Assertions.assertTrue(test.sameAs(test2));

    }

    @org.junit.Test
    public void ensureTestToString() {
        String classTitle = "Class Title";
        students.add(student);
        CourseClass test = new CourseClass(teacher, classTitle, date, "MONDAY", now, after, course, students);
        String expected = "ClassClass Title at: MONDAY starts at: "+now+" and ends at: "+after;
        Assertions.assertEquals(expected, test.toString());
    }

}
