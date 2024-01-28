package application.base.application.enrollmentManagement;

import application.base.domain.courseManagement.CourseStatus;
import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.valueobjects.MecanographicNumber;
import application.base.usermanagement.repositories.StudentRepository;
import application.base.domain.courseManagement.Course;
import application.base.domain.enrollmentManagement.Enrollment;
import application.base.domain.enrollmentManagement.EnrollmentBuilder;
import application.base.domain.enrollmentManagement.EnrollmentsStatus;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class BulkEnrollStudentsService {

    private StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();

    private CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();

    private EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();

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
     * This method receives a file and validates it regarding this US implementation.
     * Notes:
     * - The file must have more than 1 line (header)
     * - The first two columns must be relative to the data needed:
     * - The first column must be named "Student" and have the student's mecanographic numbers.
     * - The second column must be nammed "Course" and have the course's mecanographic number.
     *
     * @param file - file to be validated
     * @return file validation
     */
    public boolean validateFile(File file) {
        if (file == null) {
            return false;
        }

        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) {
                return false;
            }

            String headerRow = scanner.nextLine();
            String[] columns = headerRow.split(";");

            if (columns.length != 2
                    || !columns[0].toLowerCase().equals("student")
                    || !columns[1].toLowerCase().equals("course")) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * This method receives a file, already validated, and processes the data.
     * It creates an enrollment if:
     * - the student mecanographic number and course codes are valid,
     * - the course status is 'ENROLL'
     * - there isn't already an enrollment with the same student and course
     *
     * @param file - file with the data
     * @return number of enrollments created
     */
    public Integer bulkEnrollStudents(File file) {
        Integer enrollmentsCreated = 0;

        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine(); // Skip the first row

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line != "") {
                    String[] columns = line.split(";");
                    MecanographicNumber studentMecanographicNumber = new MecanographicNumber(columns[0].trim());
                    String courseCode = columns[1].trim();

                    try {
                        Student student = studentRepository.findByMecanographicNumber(studentMecanographicNumber);
                        Course course = courseRepository.findByCode(courseCode);

                        if (student != null && course != null
                                && course.courseStatus().equals(CourseStatus.ENROLL)
                                && enrollmentRepository.findStudentEnrollmentInCourse(student, course) == null) {
                            Enrollment enrollment = new EnrollmentBuilder()
                                    .withEnrollmentStatus(EnrollmentsStatus.ACCEPTED)
                                    .ofStudent(student)
                                    .inCourse(course)
                                    .build();
                            enrollmentRepository.save(enrollment);
                            enrollmentsCreated++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            scanner.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        return enrollmentsCreated;
    }
}