package application.base.infrastructure.bootstrapers.demo;

import application.base.application.sharedBoardManagement.CreateSharedBoardController;
import application.base.domain.sharedBoardManagement.Row;
import application.base.domain.sharedBoardManagement.SharedBoard;
import application.base.domain.sharedBoardManagement.SharedBoardColumn;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.sharedBoardManagement.SharedBoardRepository;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedBoardBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

    private static final SharedBoardRepository sharedBoardRepository = PersistenceContext.repositories().sharedBoardRepository();
    private static final UserRepository userRepo = PersistenceContext.repositories().userRepository();
    private CreateSharedBoardController createSharedBoardController = new CreateSharedBoardController();

    @Override
    public boolean execute() {
        Iterable<SystemUser> listUsers = userRepo.findByActive(true);
        listUsers.iterator().next();
        SystemUser systemUser = listUsers.iterator().next();

        SharedBoard sharedBoard = new SharedBoard("sb_title", systemUser, 3, 3, null);
        Row row1 =  createSharedBoardController.createRow(1, sharedBoard, "row1");
        Row row2 =  createSharedBoardController.createRow(2, sharedBoard, "row2");
        Row row3 =  createSharedBoardController.createRow(3, sharedBoard, "row3");

        SharedBoardColumn col1 =  createSharedBoardController.createColumn(1, sharedBoard, "col1");
        SharedBoardColumn col2 =  createSharedBoardController.createColumn(2, sharedBoard, "col2");
        SharedBoardColumn col3 =  createSharedBoardController.createColumn(3, sharedBoard, "col3");
        sharedBoard.rows().add(row1);
        sharedBoard.rows().add(row2);
        sharedBoard.rows().add(row3);

        sharedBoard.columns().add(col1);
        sharedBoard.columns().add(col2);
        sharedBoard.columns().add(col3);

        sharedBoard = createSharedBoardController.createCells(sharedBoard);

        sharedBoardRepository.save(sharedBoard);
        return true;
    }
}
