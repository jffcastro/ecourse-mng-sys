package eapli.Controller;

import application.base.domain.sharedBoardManagement.boardCommunication.MessageCode;
import application.base.domain.sharedBoardManagement.boardCommunication.RequestMessage;
import eapli.Convert;
import eapli.SharedBoardClient;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static eapli.SharedBoardClient.sIn;
import static eapli.SharedBoardClient.sOut;

public class ShareSharedBoardController {
    byte version = 1;
    public List<String> sendRequestToShare (String user) throws IOException {
        String data = String.format("%s", user);

        RequestMessage request = new RequestMessage(version, MessageCode.SHAREDBOARD_CREATED, data);
        byte[] messageBytes = request.getBytes();
        SharedBoardClient.sOut.writeInt(messageBytes.length);
        sOut.write(messageBytes);

        // Receive the server response
        int responseLength = sIn.readInt();
        byte[] responseBytes = sIn.readNBytes(responseLength);
        RequestMessage response = new RequestMessage(responseBytes);

        if(response.code() == 6){
            List<String> sharedBoardList = extractList(response);
            return sharedBoardList;
        }
        else if (response.code() == 3){
            throw new IllegalArgumentException(Convert.convertBytesToString(response.data()));
        }
        return null;
    }



    public List<String> listUsers (String user, String sharedBoard) throws IOException {
        String data = String.format("%s;%s", user, sharedBoard);

        RequestMessage request = new RequestMessage(version, MessageCode.LIST_USERS, data);
        byte[] messageBytes = request.getBytes();
        SharedBoardClient.sOut.writeInt(messageBytes.length);
        sOut.write(messageBytes);

        // Receive the server response
        int responseLength = sIn.readInt();
        byte[] responseBytes = sIn.readNBytes(responseLength);
        RequestMessage response = new RequestMessage(responseBytes);

        if(response.code() == 6){
            List<String> usersList = extractList(response);
            return usersList;
        }
        else if (response.code() == 3){
            throw new IllegalArgumentException(Convert.convertBytesToString(response.data()));
        }
        return null;
    }


    public boolean createSharedBoardInvitation(String user, String sharedBoard, String permission) throws IOException{
        String data = String.format("%s;%s;%s", user, sharedBoard, permission);

        RequestMessage request = new RequestMessage(version, MessageCode.SHARE_SHAREDBOARD, data);
        byte[] messageBytes = request.getBytes();
        SharedBoardClient.sOut.writeInt(messageBytes.length);
        sOut.write(messageBytes);

        // Receive the server response
        int responseLength = sIn.readInt();
        byte[] responseBytes = sIn.readNBytes(responseLength);
        RequestMessage response = new RequestMessage(responseBytes);

        if(response.code() == 2){
            return true;
        }
        else if(response.code() == 3){
            throw new IllegalArgumentException(Convert.convertBytesToString(response.data()));
        }
        return false;
    }



    public List<String> extractList(RequestMessage response) {
        byte[] dataBytes = response.data();

        List<String> listToString = List.of(Convert.convertBytesToString(dataBytes).split("\n"));
        return listToString;
    }
}
