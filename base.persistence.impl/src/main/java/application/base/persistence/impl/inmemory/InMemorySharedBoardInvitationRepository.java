package application.base.persistence.impl.inmemory;

import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardInvitation;
import application.base.repositories.sharedBoardManagement.SharedBoardInvitationRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySharedBoardInvitationRepository extends InMemoryDomainRepository<SharedBoardInvitation, Long>
        implements SharedBoardInvitationRepository {
    @Override
    public SharedBoardInvitation findSharedBoardInvitationOfUser(SharedBoard sharedBoard, SystemUser user) {
        return null;
    }

    @Override
    public Iterable<SharedBoardInvitation> findSharedBoardInvitationsOfSharedBoard(SharedBoard sharedBoard) {
        return null;
    }

    @Override
    public Iterable<SharedBoard> findSharedBoardsAvailableForUser(SystemUser user) {
        return null;
    }
}
