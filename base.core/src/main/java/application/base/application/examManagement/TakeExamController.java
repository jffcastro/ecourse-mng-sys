package application.base.application.examManagement;

import application.base.domain.enrollmentManagement.ExamEnrollment;
import application.base.domain.enrollmentManagement.Grade;
import application.base.domain.examManagement.Exam;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.repositories.examManagement.ExamRepository;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.repositories.StudentRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TakeExamController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();
    private ExamEnrollmentRepository examEnrollmentRepository = PersistenceContext.repositories().examEnrollmentRepository();
    private ExamRepository examRepository = PersistenceContext.repositories().examRepository();

    /**
     * This method finds the exams available for the student to take,
     * which are the exam in which he has an exam enrollment with
     * status 'ENROLLED'
     *
     * @return exams available for student to take
     */
    public Iterable<Exam> findExamsAvailableForStudentToTake() {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)) {
            Student student = studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser());

            Iterable<Long> examsIDs = examEnrollmentRepository.findIdsOfExamAvailableForStudentToTake(student);
            return findExamsWithExamsIds(examsIDs);
        }
        return null;
    }

    /**
     * This method receives a list of exam IDs, from the method findExamsAvailableForStudentToTake() and
     * returns a list with exams, after finding each exam corresponding to each ID.
     *
     * @param examsIDs - list of exam IDs to find exams of
     * @return list of exams
     */
    private Iterable<Exam> findExamsWithExamsIds(Iterable<Long> examsIDs) {
        List<Exam> exams = new ArrayList<>();

        for (Long examId : examsIDs) {
            Optional<Exam> exam = examRepository.findById(examId);
            if (exam.isPresent()) {
                exams.add(exam.get());
            }
        }

        return exams;
    }

    /**
     * This method saves the grade of the student logged in the system in the exam enrollment
     * related to that student in the exam taken and also changes its status to
     *
     * @param exam - exam to change grade of
     * @param grade - grade obtained by student in the given exam
     */
    public void saveStudentGradeInExam(Exam exam, Grade grade) {
        if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)) {
            Student student = studentRepository.findBySystemUser(authorizationService.session().get().authenticatedUser());
            Optional<ExamEnrollment> examEnrollmentOpt = examEnrollmentRepository.findExamEnrollOfStudentInExam(student, exam);
            if (examEnrollmentOpt.isPresent()) {
                ExamEnrollment examEnrollment = examEnrollmentOpt.get();
                examEnrollment.changeExamGrade(grade);
                examEnrollment.changeExamEnrollmentStatusToPresent();
                examEnrollmentRepository.save(examEnrollment);
            }
        }
    }

}