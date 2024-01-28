package application.base.courseManagement.domain;

import application.base.domain.courseManagement.CourseName;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CourseNameTest {
    CourseName courseName = new CourseName("course name 1");
    CourseName courseName1 = new CourseName("course name 1");

    @Test
    public void testEquals() {
        Assertions.assertTrue(courseName.equals(courseName1));
        Assertions.assertTrue(courseName.equals(courseName));
        Assertions.assertFalse(courseName.equals("other"));
    }

    @Test
    public void testToString() {
        String expected = "course name 1";
        Assertions.assertEquals(courseName.toString(), expected);
    }

    @Test
    public void testCompareTo() {
        Assertions.assertEquals(courseName.compareTo(courseName), 0);
        Assertions.assertEquals(courseName1.compareTo(courseName1), 0);
    }

    @Test
    public void testLenght() {
        int expected = courseName.length();
        Assertions.assertEquals(courseName.length(), expected);
    }
}
