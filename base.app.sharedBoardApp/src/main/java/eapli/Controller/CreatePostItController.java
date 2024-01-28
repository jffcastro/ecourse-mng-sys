package eapli.Controller;

import application.base.domain.sharedBoardManagement.boardCommunication.MessageCode;
import application.base.domain.sharedBoardManagement.boardCommunication.RequestMessage;
import eapli.Convert;
import eapli.SharedBoardClient;

import java.io.IOException;
import java.util.List;

import static eapli.SharedBoardClient.sIn;
import static eapli.SharedBoardClient.sOut;

public class CreatePostItController {


    byte version = 1;

    public List<String> listSharedBoards(String user) throws IOException {
        String data = String.format("%s", user);

        RequestMessage request = new RequestMessage(version, MessageCode.SHARED_BOARDS_OF_USER, data);
        byte[] messageBytes = request.getBytes();
        SharedBoardClient.sOut.writeInt(messageBytes.length);
        sOut.write(messageBytes);

        // Receive the server response
        int responseLength = sIn.readInt();
        byte[] responseBytes = sIn.readNBytes(responseLength);
        RequestMessage response = new RequestMessage(responseBytes);

        if(response.code() == 6){
            return extractList(response);
        }
        else if (response.code() == 3){
            throw new IllegalArgumentException(Convert.convertBytesToString(response.data()));
        }
        return null;
    }

    public List<String> extractList(RequestMessage response) {
        byte[] dataBytes = response.data();

        List<String> listToString = List.of(Convert.convertBytesToString(dataBytes).split("\n"));
        return listToString;
    }

    public List<String> listCells(String sharedBoardName) throws IOException {
        String data = String.format("%s", sharedBoardName);

        RequestMessage request = new RequestMessage(version, MessageCode.LIST_CELLS, data);
        byte[] messageBytes = request.getBytes();
        SharedBoardClient.sOut.writeInt(messageBytes.length);
        sOut.write(messageBytes);

        // Receive the server response
        int responseLength = sIn.readInt();
        byte[] responseBytes = sIn.readNBytes(responseLength);
        RequestMessage response = new RequestMessage(responseBytes);

        if(response.code() == 6){
            return extractCellList(response);
        }
        else if (response.code() == 3){
            throw new IllegalArgumentException(Convert.convertBytesToString(response.data()));
        }
        return null;
    }

    private List<String> extractCellList(RequestMessage response) {
        byte[] dataBytes = response.data();

        List<String> cellListString = List.of(Convert.convertBytesToString(dataBytes).split("\n"));
        return cellListString;
    }

    public boolean createPostIt(String user, String content, String cell) throws IOException{
        String data = String.format("%s;%s;%s", user, content, cell);

        RequestMessage request = new RequestMessage(version, MessageCode.CREATE_POSTIT, data);

        byte[] messageBytes = request.getBytes();
        SharedBoardClient.sOut.writeInt(messageBytes.length);
        sOut.write(messageBytes);

        // Receive the server response
        int responseLength = sIn.readInt();
        byte[] responseBytes = sIn.readNBytes(responseLength);
        RequestMessage response = new RequestMessage(responseBytes);

        if(response.code() == 2){
            System.out.println("Post-It Created!");
            return true;
        }
        else if (response.code() == 3){
            System.out.println("This cell already has a Post-It");
        }
        return false;
    }

}
