package application.base.domain.enrollmentManagement;

import eapli.framework.validations.Preconditions;

public class ExamEnrollmentBuilder {

    private Long examId;
    private Enrollment enrollment;
    private ExamEnrollmentStatus examEnrollmentStatus;

    public ExamEnrollmentBuilder ofExam(Long examId) {
        this.examId = examId;
        return this;
    }

    public ExamEnrollmentBuilder ofEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public ExamEnrollmentBuilder withExamEnrollmentStatus(ExamEnrollmentStatus examEnrollmentStatus) {
        this.examEnrollmentStatus = examEnrollmentStatus;
        return this;
    }

    public ExamEnrollment build() {
        Preconditions.noneNull(examId,enrollment, examEnrollmentStatus);
        return new ExamEnrollment(examId, enrollment, examEnrollmentStatus);
    }
}

