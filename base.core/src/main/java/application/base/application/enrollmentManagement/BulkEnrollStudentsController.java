package application.base.application.enrollmentManagement;

import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.File;

public class BulkEnrollStudentsController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private BulkEnrollStudentsService svc = new BulkEnrollStudentsService();

    /**
     * This method receives a path and imports the file relative to that path, from the service.
     *
     * @param path - file path
     * @return file imported from path
     */
    public File importFile(String path) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        return svc.importFile(path);
    }

    /**
     * This method receives a file and validates it regarding this US implementation, from the service.
     * Notes:
     * - The file must have more than 1 line (header)
     * - The first two columns must be relative to the data needed:
     *      - The first column must be named "Student" and have the student's mecanographic numbers.
     *      - The second column must be nammed "Course" and have the course's mecanographic number.
     *
     * @param file - file to be validated
     * @return file validation
     */
    public boolean validateFile(File file) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        return svc.validateFile(file);
    }

    /**
     * This method receives a file, already validated, and processes the data, from the service.
     * It creates an enrollment if:
     * - the student mecanographic number and course codes are valid,
     * - the course status is 'ENROLL'
     * - there isn't already an enrollment with the same student and course
     *
     * @param file - file with the data
     * @return number of enrollments created
     */
    public Integer bulkEnrollStudents(File file) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        return svc.bulkEnrollStudents(file);
    }
}