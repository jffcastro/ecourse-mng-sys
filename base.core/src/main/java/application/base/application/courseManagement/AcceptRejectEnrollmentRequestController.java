package application.base.application.courseManagement;

import application.base.domain.enrollmentManagement.Enrollment;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AcceptRejectEnrollmentRequestController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private EnrollmentRepository enrollmentRepository = PersistenceContext.repositories().enrollmentRepository();

    /**
     * This method accesses the enrollment repository and returns the list of courses available
     * to be open, which are the courses where the status is 'UNDER_APPRECIATION'
     *
     * @return iterable of enrollment requets found
     */
    public Iterable<Enrollment> findEnrollmentRequests() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        return enrollmentRepository.findEnrollmentsRequests();
    }

    /**
     * This method accepts the enrollment (changes status to 'ACCEPTED')
     * and saves the object in the repository
     *
     * @param enrollment - enrollment to be accepted
     */
    public void acceptEnrollment(Enrollment enrollment) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        enrollment.acceptEnrollment();
        enrollmentRepository.save(enrollment);
    }

    /**
     * This method rejects the enrollment (changes status to 'NOT_ACCEPTED')
     * and saves the object in the repository
     *
     * @param enrollment - enrollment to be rejected
     */
    public void rejectEnrollment(Enrollment enrollment) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        enrollment.rejectEnrollment();
        enrollmentRepository.save(enrollment);
    }
}
