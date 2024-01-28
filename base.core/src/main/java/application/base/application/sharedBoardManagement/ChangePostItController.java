package application.base.application.sharedBoardManagement;

import application.base.domain.sharedBoardManagement.*;
import application.base.infrastructure.persistence.PersistenceContext;
import application.base.repositories.sharedBoardManagement.CellRepository;
//import application.base.repositories.sharedBoardManagement.PostItHistoryRepository;
import application.base.repositories.sharedBoardManagement.PostItHistoryRepository;
import application.base.repositories.sharedBoardManagement.PostItRepository;
import application.base.repositories.sharedBoardManagement.SharedBoardInvitationRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class ChangePostItController {
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private SharedBoardInvitationRepository sharedBoardInvitationRepository = PersistenceContext.repositories().sharedBoardInvitationRepository();

    private AddPostItController theController = new AddPostItController();

    private PostItRepository postItRepository = PersistenceContext.repositories().postItRepository();

    private PostItHistoryRepository postItHistoryRepository = PersistenceContext.repositories().postItHistoryRepository();

    private CellRepository cellRepository = PersistenceContext.repositories().cellRepository();

    public Iterable<SharedBoard> findSharedBoard(SystemUser user){
        return theController.findSharedBoard(user);
    }

    public Iterable<PostIt> findAllPostIts (SharedBoard sharedBoard, SystemUser user){
        return postItRepository.findPostItsFromSharedBoard(sharedBoard, user);
    }

    public Iterable<Cell> findFreeCells (SharedBoard sharedBoard){
        return theController.findFreeCells(sharedBoard);
    }

    public void movePostItToOtherCell (PostIt postIt, Cell cell){
        Date timeStamp = Date.from(Instant.now());
        PostItHistory postItHistory= new PostItHistoryBuilder()
                .withPostIt(postIt)
                .withTimeStamp(timeStamp)
                .withOldContent(postIt.content())
                .withOldCell(postIt.cell())
                .withNewContent(postIt.content())
                .withNewCell(cell)
                .build();
        postItHistoryRepository.save(postItHistory);
        postIt.changeCell(cell);
        postItRepository.save(postIt);
    }

    public void changeContentOfPostIt(PostIt postIt, String content){
        Date timeStamp = Date.from(Instant.now());
        PostItHistory postItHistory= new PostItHistoryBuilder()
                .withPostIt(postIt)
                .withTimeStamp(timeStamp)
                .withOldContent(postIt.content())
                .withOldCell(postIt.cell())
                .withNewContent(content)
                .withNewCell(postIt.cell())
                .build();

        postItHistoryRepository.save(postItHistory);
        postIt.changeContent(content);
        postItRepository.save(postIt);
    }

    public boolean validateContent(String content){
        if(content.length() <= 1 || content.length() > 50){
            return false;
        }
        return true;
    }
}
