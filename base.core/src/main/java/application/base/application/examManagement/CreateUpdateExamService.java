package application.base.application.examManagement;

import application.base.application.courseManagement.ListAvailableCoursesController;
import application.base.application.examManagement.createAutoExamParsers.AutoExamEvalVisitor;
import application.base.application.examManagement.createAutoExamParsers.AutoExamLexer;
import application.base.application.examManagement.createAutoExamParsers.AutoExamParser;
import application.base.application.examManagement.createExamParsers.ExamEvalVisitor;
import application.base.application.examManagement.createExamParsers.ExamLexer;
import application.base.application.examManagement.createExamParsers.ExamParser;
import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.*;
import application.base.domain.examManagement.Exam;
import application.base.domain.examManagement.Section;
import application.base.domain.examManagement.valueObjects.ExamStatus;
import application.base.domain.questionManagement.Question;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.repositories.examManagement.ExamRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class CreateUpdateExamService {

    private ExamRepository examRepository = PersistenceContext.repositories().examRepository();
    private EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();
    private ExamEnrollmentRepository examEnrollmentRepository = PersistenceContext.repositories().examEnrollmentRepository();
    private ListAvailableCoursesController listAvailableCoursesController = new ListAvailableCoursesController();

    /**
     * This method receives a path and imports the file relative to that path.
     *
     * @param path - file path
     * @return file imported from path
     */
    public File importFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("File does not exist: " + path);
            return null;
        }
        return file;
    }

    /**
     * This method finds the courses available for the teacher to create an exam for,
     * which are the courses where he teaches. This method calls a controller previously
     * used in another US, that returns this list.
     *
     * @return iterable of course available for teacher to create an exam for
     */
    public Iterable<Course> findCoursesAvailableForTeacher() {
        return listAvailableCoursesController.listCoursesAvailableForTeacher();
    }

    public Iterable<Course> findCoursesAvailableForStudent() {
        return listAvailableCoursesController.listCoursesAvailableForStudent();
    }

    /**
     * This method creates an exam from an imported file. The exam is generated
     * by the generateExam(exam) method.
     *
     * @param file - file to generate the exam
     */
    public Exam createExam(Course course, File file) throws IOException {
        Exam exam = generateExam(course, file);

        if (exam != null && !examRepository.existsByTitle(exam.title()) && examHasQuotationOf20(exam)) {
                examRepository.save(exam);
                createExamEnrollments(examRepository.findExamByTitle(exam.title()).get());
                return exam;
        }

        return null;
    }

    public Exam createAutoExam(Course course, File file) throws IOException {
        Exam exam = generateAutoExam(course, file);

        if (examHasQuotationOf20(exam)) {
            return exam;
        }

        else return null;

    }

    /**
     * This method calculates the total quotation of an exam and returns true
     * if it is 20 or false, if not
     *
     * @param exam - exam to calculate the quotation of
     * @return true, if quotation is 20, or false, if not
     */
    private boolean examHasQuotationOf20(Exam exam) {
        Long quotation = 0L;
        for (Section section : exam.sections()){
            for (Question question : section.questions()){
                quotation += question.quotation();
            }
        }
        return quotation == 20;
    }

    /**
     * This method creates an exam enrollment for each enrollment for the course of this exam.
     *
     * @param exam - exam to create exam enrollments for
     */
    private void createExamEnrollments(Exam exam) {
        Iterable<Enrollment> enrollmentsOfCourse = enrollmentRepository.findEnrollmentsOfCourse(exam.course());
        for (Enrollment enrollment : enrollmentsOfCourse){
            ExamEnrollment examEnrollment = new ExamEnrollmentBuilder().ofExam(exam.examID())
                    .ofEnrollment(enrollment).withExamEnrollmentStatus(ExamEnrollmentStatus.ENROLLED).build();
            examEnrollmentRepository.save(examEnrollment);
        }
    }

    /**
     * This method finds the exams available for a teacher to update, which are the exams of the courses
     * where he teaches that can be updated (status is 'CREATED', not 'FINISHED')
     *
     * @return iterable of exams available for teacher to update
     */
    public Iterable<Exam> findExamsAvailableForTeacher() {
        Iterable<Course> coursesForTeacher = listAvailableCoursesController.listCoursesAvailableForTeacher();
        List<Exam> examsAvailableForTeacher = new ArrayList<>();

        for (Course course : coursesForTeacher) {
            Iterable<Exam> examsOfCourse = examRepository.findExamsOfCourse(course);
            for (Exam exam : examsOfCourse) {
                if (exam.examStatus().equals(ExamStatus.CREATED)) {
                    examsAvailableForTeacher.add(exam);
                }
            }
        }

        return examsAvailableForTeacher;
    }




    /**
     * This method generates an Exam, after validating the file import with the grammar from ANTLR.
     *
     * @param file - file to generate exam
     * @return exam generated
     */
    private Exam generateExam(Course course, File file) throws IOException {
        ExamLexer lexer = new ExamLexer(CharStreams.fromFileName(file.getPath()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExamParser parser = new ExamParser(tokens);
        ParseTree tree = parser.stat();
        ExamEvalVisitor visitor = new ExamEvalVisitor();

        return visitor.buildExam(tree, course);
    }

    private Exam generateAutoExam(Course course, File file) throws IOException {
        AutoExamLexer lexer = new AutoExamLexer(CharStreams.fromFileName(file.getPath()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AutoExamParser parser = new AutoExamParser(tokens);
        ParseTree tree = parser.stat();
        AutoExamEvalVisitor visitor = new AutoExamEvalVisitor();

        return visitor.buildExam(tree, course);
    }

    public Exam updateExam(Exam exam, File file) throws IOException {
        Exam newExam = generateExam(exam.course(), file);

        if (newExam != null && examHasQuotationOf20(newExam)) {
            deleteExam(exam);
            examRepository.save(newExam);
            createExamEnrollments(examRepository.findExamByTitle(newExam.title()).get());
        }
        return newExam;
    }

    private void deleteExam(Exam exam){
        Iterable<ExamEnrollment> examEnrollmentsOfExam = examEnrollmentRepository.findExamEnrollsOfExam(exam);
        for (ExamEnrollment examEnrollment : examEnrollmentsOfExam){
            examEnrollmentRepository.delete(examEnrollment);
        }
        examRepository.delete(exam);
    }

}
