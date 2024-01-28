package application.base.enrollmentManagement.domain;

import application.base.domain.enrollmentManagement.Grade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class GradeTest {

    @Test
    public void ensureCanCreateGrade() {
        try {
            new Grade(10F);
        } catch (IllegalArgumentException e) {
            assert false : "Expected no exception, but got IllegalArgumentException";
        }
    }

    @Test
    public void ensureCannotCreadeGradeSmallerThan0() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Grade(-10F);
                }
        );
    }

    @Test
    public void ensureCannotCreadeGradeBiggerThan20() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                {
                    new Grade(30F);
                }
        );
    }

    @Test
    public void testCompareToForSameObject(){
        Grade grade = new Grade(10F);
        Assert.assertTrue(grade.compareTo(grade) == 0);
    }

    @Test
    public void testCompareToForIdenticalObject(){
        Grade grade = new Grade(10F);
        Grade grade1 = new Grade(10F);
        Assert.assertTrue(grade.compareTo(grade1) == 0);
    }

    @Test
    public void testCompareToReturnNegative(){
        Grade grade = new Grade(10F);
        Grade grade1 = new Grade(20F);
        Assert.assertTrue(grade.compareTo(grade1) < 0);
    }

    @Test
    public void testCompareToReturnPositive(){
        Grade grade = new Grade(20F);
        Grade grade1 = new Grade(10F);
        Assert.assertTrue(grade.compareTo(grade1) > 0);
    }

    @Test
    public void testToString(){
        Grade grade = new Grade(20F);
        Assert.assertEquals("20.0", grade.toString());
    }
}