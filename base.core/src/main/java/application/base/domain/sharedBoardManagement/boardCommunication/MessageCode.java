package application.base.domain.sharedBoardManagement.boardCommunication;

public enum MessageCode {

    COMMTEST(0),
    DISCONN(1),
    ACK(2),
    ERR(3),
    AUTH(4),
    CREATE_SHAREDBOARD(5),
    SEND_MESSAGE(6),
    SHAREDBOARD_CREATED(7),
    SHARED_BOARDS_OF_USER(8),
    SHARE_SHAREDBOARD(9),
    LIST_USERS(10),
    CREATE_POSTIT(11),
    LIST_CELLS(12),
    HISTORY_SHAREBOARD(13),
    LIST_POSTITS(14),
    CHANGE_POSTIT_CELL(15),
    CHANGE_POSTIT_CONTENT(16),
    UNDO_POSTIT(17),
    ARCHIVE_SHAREDBOARD(18),
    FIND_MYSHAREDBOARD(19),
    ALL_BOARDS(20),

    SHARED_BOARD_OWNER(21);




    private final int code;

    MessageCode(int code){
        this.code = code;
    }

    public int value() {
        return code;
    }


}
