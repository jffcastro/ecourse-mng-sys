package application.base.sharedBoardManagement.domain;

import application.base.domain.sharedBoardManagement.boardCommunication.MessageCode;
import application.base.domain.sharedBoardManagement.boardCommunication.RequestMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

import static application.base.domain.sharedBoardManagement.boardCommunication.RequestMessage.getCodeFromString;

public class RequesteMessageTest {

    byte version = 1;

    @Test
    public void testRequestMessageWithData() {

        RequestMessage requestMessage = new RequestMessage(version, MessageCode.SEND_MESSAGE, "Send Message");
        System.out.println("Version: " + requestMessage.version());
        System.out.println("Code: " + requestMessage.code());
        System.out.println("D_Lenght_1: " + requestMessage.d_Lenght_1());
        System.out.println("D_Lenght_2: " + requestMessage.d_Lenght_2());
        System.out.println("Data: " + Arrays.toString(requestMessage.data()));
    }
    @Test
    public void testRequestMessageByteMessage() {
        byte[] message = { 1, 2, 3, 4, 5, 6, 7, 8 };

        RequestMessage requestMessage = new RequestMessage(message);

        System.out.println("Version: " + requestMessage.version());
        System.out.println("Code: " + requestMessage.code());
        System.out.println("D_Lenght_1: " + requestMessage.d_Lenght_1());
        System.out.println("D_Lenght_2: " + requestMessage.d_Lenght_2());
        System.out.println("Data: " + Arrays.toString(requestMessage.data()));
    }

    @Test
    public void testGetCodeFromString() {
        MessageCode testCode = MessageCode.COMMTEST;
        byte result = 0;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected = getCodeFromString(MessageCode.COMMTEST);
        Assert.assertEquals(result, expected);

        testCode = MessageCode.DISCONN;
        result = 1;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected1 = getCodeFromString(MessageCode.DISCONN);
        Assert.assertEquals(result, expected1);

        testCode = MessageCode.ACK;
        result = 2;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected2 = getCodeFromString(MessageCode.ACK);
        Assert.assertEquals(result, expected2);

        testCode = MessageCode.ERR;
        result = 3;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected3 = getCodeFromString(MessageCode.ERR);
        Assert.assertEquals(result, expected3);

        testCode = MessageCode.AUTH;
        result = 4;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected4 = getCodeFromString(MessageCode.AUTH);
        Assert.assertEquals(result, expected4);

        testCode = MessageCode.CREATE_SHAREDBOARD;
        result = 5;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected5 = getCodeFromString(MessageCode.CREATE_SHAREDBOARD);
        Assert.assertEquals(result, expected5);

        testCode = MessageCode.SEND_MESSAGE;
        result = 6;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected6 = getCodeFromString(MessageCode.SEND_MESSAGE);
        Assert.assertEquals(result, expected6);

        testCode = MessageCode.SHAREDBOARD_CREATED;
        result = 7;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected7 = getCodeFromString(MessageCode.SHAREDBOARD_CREATED);
        Assert.assertEquals(result, expected7);

        testCode = MessageCode.SHARED_BOARDS_OF_USER;
        result = 8;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected8 = getCodeFromString(MessageCode.SHARED_BOARDS_OF_USER);
        Assert.assertEquals(result, expected8);

        testCode = MessageCode.SHARE_SHAREDBOARD;
        result = 9;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected9 = getCodeFromString(MessageCode.SHARE_SHAREDBOARD);
        Assert.assertEquals(result, expected9);

        testCode = MessageCode.LIST_USERS;
        result = 10;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected10 = getCodeFromString(MessageCode.LIST_USERS);
        Assert.assertEquals(result, expected10);

        testCode = MessageCode.CREATE_POSTIT;
        result = 11;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected11 = getCodeFromString(MessageCode.CREATE_POSTIT);
        Assert.assertEquals(result, expected11);

        testCode = MessageCode.LIST_CELLS;
        result = 12;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected12 = getCodeFromString(MessageCode.LIST_CELLS);
        Assert.assertEquals(result, expected12);

        testCode = MessageCode.HISTORY_SHAREBOARD;
        result = 13;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected13 = getCodeFromString(MessageCode.HISTORY_SHAREBOARD);
        Assert.assertEquals(result, expected13);

        testCode = MessageCode.LIST_POSTITS;
        result = 14;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected14 = getCodeFromString(MessageCode.LIST_POSTITS);
        Assert.assertEquals(result, expected14);

        testCode = MessageCode.CHANGE_POSTIT_CELL;
        result = 15;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected15 = getCodeFromString(MessageCode.CHANGE_POSTIT_CELL);
        Assert.assertEquals(result, expected15);

        testCode = MessageCode.CHANGE_POSTIT_CONTENT;
        result = 16;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected16 = getCodeFromString(MessageCode.CHANGE_POSTIT_CONTENT);
        Assert.assertEquals(result, expected16);

        testCode = MessageCode.UNDO_POSTIT;
        result = 17;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected17 = getCodeFromString(MessageCode.UNDO_POSTIT);
        Assert.assertEquals(result, expected17);

        testCode = MessageCode.ARCHIVE_SHAREDBOARD;
        result = 18;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected18 = getCodeFromString(MessageCode.ARCHIVE_SHAREDBOARD);
        Assert.assertEquals(result, expected18);

        testCode = MessageCode.FIND_MYSHAREDBOARD;
        result = 19;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected19 = getCodeFromString(MessageCode.FIND_MYSHAREDBOARD);
        Assert.assertEquals(result, expected19);

        testCode = MessageCode.FIND_MYSHAREDBOARD;
        result = 20;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected20 = getCodeFromString(MessageCode.ALL_BOARDS);
        Assert.assertEquals(result, expected20);

        testCode = MessageCode.SHARED_BOARD_OWNER;
        result = 21;
        System.out.println("Código para " + testCode + ": " + result);
        byte expected21 = getCodeFromString(MessageCode.SHARED_BOARD_OWNER);
        Assert.assertEquals(result, expected21);

    }

    @Test
    public void testToStringCodeLessThan3() {
        MessageCode code = MessageCode.COMMTEST;
        RequestMessage requestMessageNoData = new RequestMessage(version, code);

        byte code1 = getCodeFromString(code);

        String expected = "Code: " + code1 + "\n";
        Assertions.assertEquals(requestMessageNoData.toString(), expected);
    }

    @Test
    public void testToStringCodeBiggerThan3() {
        String data = "login";
        MessageCode code = MessageCode.AUTH;

        RequestMessage requestMessageWithData = new RequestMessage(version, code, data);

        byte code1 = getCodeFromString(code);
        String expected = "Code: " + code1 + "\n" +
                "Data: " + data + "\n";
        Assertions.assertEquals(requestMessageWithData.toString(), expected);
    }


}
