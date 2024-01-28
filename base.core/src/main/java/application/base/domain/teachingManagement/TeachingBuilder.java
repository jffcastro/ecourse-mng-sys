package application.base.domain.teachingManagement;

import application.base.domain.courseManagement.Course;
import application.base.usermanagement.domain.Teacher;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class TeachingBuilder implements DomainFactory<Teaching> {

    /**
     * creat a teaching
     */

    private Teaching theTeaching;

    private Teacher teachingTeacher;

    private Course teachingCourse;

    private boolean teachingPrimary;

    public TeachingBuilder ofTeacher(final Teacher teachingTeacher ) {
        this.teachingTeacher = teachingTeacher;
        return this;
    }

    public TeachingBuilder inCourse(final Course teachingCourse ) {
        this.teachingCourse = teachingCourse;
        return this;
    }

    public TeachingBuilder asPrimary(final boolean teachingPrimary ) {
        this.teachingPrimary = teachingPrimary;
        return this;
    }

    @Override
    public Teaching build() {

        Preconditions.noneNull(teachingTeacher, teachingCourse, teachingPrimary);

        theTeaching = new Teaching(teachingTeacher,
                teachingCourse,
                teachingPrimary);

        return theTeaching;
    }



}
