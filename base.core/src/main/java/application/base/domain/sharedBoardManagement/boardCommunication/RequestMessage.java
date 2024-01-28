package application.base.domain.sharedBoardManagement.boardCommunication;

import java.util.Arrays;

public class RequestMessage {

    private final byte version;
    private final byte code;
    private byte d_Lenght_1;
    private byte d_Lenght_2;
    private final byte[] data;

    public RequestMessage(byte version, MessageCode code, String data){
        byte codeByte = getCodeFromString(code);
        if(codeByte < 0 || codeByte > MessageCode.values().length)
            throw new IllegalArgumentException("Code is not valid!");

        this.version = version;
        this.code = codeByte;
        this.data = data.getBytes();

        dLenghts();
    }

    public RequestMessage(byte version, MessageCode code){
        byte codeByte = getCodeFromString(code);
        if(codeByte < 0 || codeByte > MessageCode.values().length)
            throw new IllegalArgumentException("Code is not valid!");

        this.version = version;
        this.code = codeByte;
        this.data = new byte[0];

        dLenghts();

    }

    public RequestMessage(byte[] message){
        if(message[1] < 0)
            throw new IllegalArgumentException("Code is not valid!");

        this.version = message[0];
        this.code = message[1];
        this.d_Lenght_1 = message[2];
        this.d_Lenght_2 = message[3];
        this.data = Arrays.copyOfRange(message, 4, message.length);
    }

    public void dLenghts() {
        int dataLenght = data.length;
        d_Lenght_1 = (byte) (dataLenght >> 8);
        d_Lenght_2 = (byte) (dataLenght & 0xFF);
    }

    public static byte getCodeFromString(MessageCode code){
        byte codeByte = -1;
        switch (code) {
            case COMMTEST:
                return 0;
            case DISCONN:
                return 1;
            case ACK:
                return 2;
            case ERR:
                return 3;
            case AUTH:
                return 4;
            case CREATE_SHAREDBOARD:
                return 5;
            case SEND_MESSAGE:
                return 6;
            case SHAREDBOARD_CREATED:
                return 7;
            case SHARED_BOARDS_OF_USER:
                return 8;
            case SHARE_SHAREDBOARD:
                return 9;
            case LIST_USERS:
                return 10;
            case CREATE_POSTIT:
                return 11;
            case LIST_CELLS:
                return 12;
            case HISTORY_SHAREBOARD:
                return 13;
            case LIST_POSTITS:
                return 14;
            case CHANGE_POSTIT_CELL:
                return 15;
            case CHANGE_POSTIT_CONTENT:
                return 16;
            case UNDO_POSTIT:
                return 17;
            case ARCHIVE_SHAREDBOARD:
                return 18;
            case FIND_MYSHAREDBOARD:
                return 19;
            case ALL_BOARDS:
                return 20;
            case SHARED_BOARD_OWNER:
                return 21;
            default:
                return codeByte;
        }
    }

    public byte version() {
        return version;
    }

    public byte code() {
        return code;
    }

    public byte d_Lenght_1() {
        return d_Lenght_1;
    }

    public byte d_Lenght_2() {
        return d_Lenght_2;
    }

    public byte[] data() {
        return data;
    }

    public byte[] getBytes() {
        int messageLength;
        messageLength = 4 + data.length;
        byte[] message = new byte[messageLength];

        message[0] = version;
        message[1] = code;
        message[2] = d_Lenght_1;
        message[3] = d_Lenght_2;

        System.arraycopy(data, 0, message, 4, data.length);

        return message;
    }

    @Override
    public String toString(){
        if(code < 3){
            return "Code: " + code + "\n";
        }

        return "Code: " + code + "\n" +
                "Data: " + new String(data) + "\n";
    }
}
