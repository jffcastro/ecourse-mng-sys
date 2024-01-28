package eapli;

import application.base.domain.sharedBoardManagement.boardCommunication.MessageCode;
import application.base.domain.sharedBoardManagement.boardCommunication.RequestMessage;
import eapli.Controller.ShareSharedBoardController;

import java.io.IOException;
import java.util.List;

import static eapli.SharedBoardClient.*;

public class SharedBoardClientController {

    ShareSharedBoardController shareSharedBoardController = new ShareSharedBoardController();
    byte version = 1;

            public boolean commtest() throws IOException {
                RequestMessage request = new RequestMessage(version, MessageCode.COMMTEST);

                // Send the message to the server
                byte[] messageBytes = request.getBytes();
                sOut.writeInt(messageBytes.length);
                sOut.write(messageBytes);
                sOut.flush();

                // Receive the server response
                int responseLength = sIn.readInt();
                byte[] responseBytes = sIn.readNBytes(responseLength);
                RequestMessage response = new RequestMessage(responseBytes);
                System.out.println(response);

                if(response.code() == 2){
                    return true;
                }
                else if (response.code() == 3){
                    throw new IllegalArgumentException(Convert.convertBytesToString(response.data()));
                }
                return false;
            }



            public boolean disconnect() throws IOException {
                RequestMessage request = new RequestMessage(version, MessageCode.DISCONN);
                byte[] messageBytes = request.getBytes();
                sOut.writeInt(messageBytes.length);
                sOut.write(messageBytes);

                // Receive the server response
                int responseLength = sIn.readInt();
                byte[] responseBytes = sIn.readNBytes(responseLength);
                RequestMessage response = new RequestMessage(responseBytes);
                System.out.println(response);

                if(response.code() == 2){
                    sock.close();
                    System.exit(0);
                    return true;
                }
                else if (response.code() == 3){
                    throw new IllegalArgumentException(Convert.convertBytesToString(response.data()));
                }
                return false;
            }



            public boolean confirmAuth(String user, String password) throws IOException {
                String data = String.format("%s %s", user, password);

                //Send
                RequestMessage request = new RequestMessage(version, MessageCode.AUTH, data);
                byte[] messageBytes = request.getBytes();
                sOut.writeInt(messageBytes.length);
                sOut.write(messageBytes);

                // Receive the server response
                int responseLength = sIn.readInt();
                byte[] responseBytes = sIn.readNBytes(responseLength);
                RequestMessage response = new RequestMessage(responseBytes);
                System.out.println(response);

                if(response.code() == 2){
                    System.out.println("Login was a success!");
                    return true;
                }
                else if(response.code() == 3){
                    return false;
                }
                return false;
            }

            public boolean createSharedBoard(String owner, String title, Integer maxNumberOfRows, Integer maxNumberOfColumns, List<String> rows, List<String> columns) throws IOException {
                String dataCreateSharedBoard = String.format("%s\n%s\n%d\n%d\n", owner, title, maxNumberOfRows, maxNumberOfColumns);
                for(String rowTitle: rows){
                    dataCreateSharedBoard += rowTitle + ";";
                }
                dataCreateSharedBoard += "\n";

                for(String columnTitle: columns){
                    dataCreateSharedBoard += columnTitle + ";";
                }
                RequestMessage request = new RequestMessage(version, MessageCode.CREATE_SHAREDBOARD, dataCreateSharedBoard);
                byte[] messageBytes = request.getBytes();
                sOut.writeInt(messageBytes.length);
                sOut.write(messageBytes);

                // Receive the server response
                int responseLength = sIn.readInt();
                byte[] responseBytes = sIn.readNBytes(responseLength);
                RequestMessage response = new RequestMessage(responseBytes);

                if(response.code() == 2){
                    return true;
                }
                else if (response.code() == 3){
                    throw new IllegalArgumentException(Convert.convertBytesToString(response.data()));
                }
                return false;
            }

    public String listSharedBoards(String user) throws IOException {
        String data = String.format("%s", user);

        RequestMessage request = new RequestMessage(version, MessageCode.ALL_BOARDS, data);
        byte[] messageBytes = request.getBytes();
        sOut.writeInt(messageBytes.length);
        sOut.write(messageBytes);

        // Receive the server response
        int responseLength = sIn.readInt();
        byte[] responseBytes = sIn.readNBytes(responseLength);
        RequestMessage response = new RequestMessage(responseBytes);

        if(response.code() == 6){
            byte[] dataBytes = response.data();
            List<String> listToString = List.of(Convert.convertBytesToString(dataBytes).split("\n"));
            String html = mySharedBoards(listToString);
            return html;
        }
        else if (response.code() == 3){
            return null;
        }
        return null;
    }

    public static synchronized String mySharedBoards(List<String> sharedBoardsList) {
                String username = userName;
        String textHTML = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>" +
                CssReader.ReadFile(System.getProperty("user.dir") + "/base.app.sharedBoardApp/src/main/java/eapli/HTTP/www/listBoards.css") +
                "</style>" +
                "<script>" + CssReader.ReadFile(System.getProperty("user.dir") + "/base.app.sharedBoardApp/src/main/java/eapli/HTTP/www/ListBoardsAjax.js") + "</script>" +
                "</head>\n" +
                "<body onload=\"updateBoardsList()\"> \n" +
                "<div id=\"boards\">\n" +
                "<h1>Your Shared Boards - " + username +"</h1>\n" +
                "<h2>Choose one!</h2>\n" +
                "<div class=\"container\">";
        for (String boards : sharedBoardsList) {
            textHTML += "<div class=\"container\">" +
                    "  <img src=\"https://images.kkeu.de/is/image/BEG/Apresenta%C3%A7%C3%A3o_e_confer%C3%AAncia/Quadros_de_planeamento/Quadro_Scrum_pdplarge-mrd--540371_PDK_00_00_00_18745545.jpg\" class=\"image\">\n" +
                    "    <div class=\"text\"><a href=\"http://localhost/sharedBoard/" + boards.replace(" ", "_") + "\">" + boards + "</a></div>\n" +
                    "</div>\n";
        }

        textHTML += "</div>\n" +
                "</body>\n" +
                "</html>";

        return textHTML;
    }

    public String findMySharedBoardInfo(String sharedBoard) throws IOException {
        String data = String.format("%s", sharedBoard);

        RequestMessage request = new RequestMessage(version, MessageCode.FIND_MYSHAREDBOARD, data);
        byte[] messageBytes = request.getBytes();
        SharedBoardClient.sOut.writeInt(messageBytes.length);
        sOut.write(messageBytes);

        // Receive the server response
        int responseLength = sIn.readInt();
        byte[] responseBytes = sIn.readNBytes(responseLength);
        RequestMessage response = new RequestMessage(responseBytes);

        if(response.code() == 6){
            byte[] dataBytes = response.data();
            String htmlToString = Convert.convertBytesToString(dataBytes);
            return htmlToString;
        }
        else if (response.code() == 3){
            return null;
        }
        return null;
    }


}
