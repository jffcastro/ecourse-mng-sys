package eapli;


import application.base.domain.sharedBoardManagement.*;
import application.base.domain.sharedBoardManagement.boardCommunication.MessageCode;
import application.base.domain.sharedBoardManagement.boardCommunication.RequestMessage;
import application.base.domain.sharedBoardManagement.builders.SharedBoardBuilder;
import application.base.domain.sharedBoardManagement.builders.SharedBoardInvitationBuilder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.repositories.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TcpServerMessageHandler implements Runnable {
    private final Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    UserRepository userRepository = new UserRepository();
    SharedBoardRepository sharedBoardRepository = new SharedBoardRepository("eapli.base");
    SharedBoardInvitationRepository sharedBoardInvitationRepository = new SharedBoardInvitationRepository("eapli.base");
    CellRepository cellRepository = new CellRepository("eapli.base");
    PostItRepository postItRepository = new PostItRepository("eapli.base");
    PostItHistoryRepository postItHistoryRepository = new PostItHistoryRepository("eapli.base");


    public TcpServerMessageHandler(Socket clientSocket) {
        socket = clientSocket;
    }

    public void run() {
        InetAddress clientIP;

        clientIP = socket.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + socket.getPort());

        try {
            while (true) {
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

                // Read the length of the message
                int length = in.readInt();

                // Read the message
                byte[] message = in.readNBytes(length);
                byte version = message[0];
                byte code = message[1];
                String data = new String(Arrays.copyOfRange(message, 4, message.length), StandardCharsets.UTF_8);
                System.out.println("Version: " + version);
                System.out.println("Code: " + code);
                System.out.println("Data: " + data);

                // Act upon the code
                codeFactory(code, version, data);
            }

        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }


    /**
     * Acts upon the code received from the client
     *
     * @param code    - Message's code
     * @param version - Message's version
     * @param data    - Message's data
     * @throws IOException - If an I/O error occurs
     */
    public void codeFactory(byte code, byte version, String data) throws IOException {
        switch (code) {
            case 0:
                sendSuccessResponse(version, out);
                break;
            case 1:
                sendSuccessResponse(version, out);
                disconnect();
                break;
            case 4:
                authenticationHandler(version, data, out);
                break;
            case 5:
                createSharedBoard(version, data, out);
                break;
            case 7:
                findSharedBoardsUserCreate(version, data, out);
                break;
            case 8:
                findSharedBoardsOfUser(version, data, out);
                break;
            case 9:
                createSharedBoardInvitation(version, data, out);
                break;
            case 10:
                listUsers(version, data, out);
                break;
            case 11:
                createPostIt(version, data, out);
                break;
            case 12:
                findFreeCells(version, data, out);
                break;
            case 13:
                findPostItHistory(version, data, out);
                break;
            case 14:
                findPostIt(version, data, out);
                break;
            case 15:
                movePostItToOtherCell(version, data, out);
                break;
            case 16:
                changeContentOfPostIt(version, data, out);
                break;
            case 17:
                undoPostIt(version, data, out);
                break;
            case 18:
                archiveSharedBoard(version, data, out);
                break;
            case 19:
                mySharedBoard(version, data, out);
                break;
            case 20:
                findSharedBoardsForUser(version, data, out);
                break;
            case 21:
                findSharedBoardsForOwner(version, data, out);
                break;
            default:
                sendErrorResponse(version, "Invalid Option", out);
        }
    }


    /**
     * Handles the authentication request
     *
     * @throws IOException - If an I/O error occurs
     */
    private void authenticationHandler(byte version, String data, DataOutputStream out) throws IOException {
        String[] auth = data.split(" ");
        if (!userRepository.findByIdPass(auth[0], auth[1])) {
            sendErrorResponse(version, "Username or Password wrong!", out);
        } else {
            System.out.println("Login was a success!");
            sendSuccessResponse(version, out);
        }
    }


    /**
     * Disconnect the client and the server
     */
    private void disconnect() throws IOException {
        byte version = 1;
        try {
            System.out.println("Disconnecting client...");
            socket.close();
            System.out.println("Disconnected server...");
            System.exit(0);
            sendSuccessResponse(version, out);
        } catch (IOException ex) {
            String error = "Error disconnecting!";
            sendErrorResponse(version, error, out);
        }
    }


    /**
     * Send a response to the client - success
     *
     * @param version Version of the message
     * @param out     Output stream
     * @throws IOException If an I/O error occurs
     */
    private void sendSuccessResponse(byte version, DataOutputStream out) throws IOException {
        // Create a success response
        RequestMessage response = new RequestMessage(version, MessageCode.ACK);

        // Send response
        byte[] responseBytes = response.getBytes();
        out.writeInt(responseBytes.length);
        out.write(responseBytes);
    }


    /**
     * Send a response to the client - error
     *
     * @param version Version of the message
     * @param out     Output stream
     * @param data    Data to send
     * @throws IOException If an I/O error occurs
     */
    private void sendErrorResponse(byte version, String data, DataOutputStream out) throws IOException {
        // Create an error response
        RequestMessage response = new RequestMessage(version, MessageCode.ERR, data);

        // Send response
        byte[] responseBytes = response.getBytes();
        out.writeInt(responseBytes.length);
        out.write(responseBytes);
    }


    // SEND DATA NEEDED

    /**
     * Sends to the client the info asked
     *
     * @param version
     * @param out
     * @param data
     * @throws IOException
     */
    private void sendMessage(byte version, DataOutputStream out, String data) throws IOException {

        RequestMessage response = new RequestMessage(version, MessageCode.SEND_MESSAGE, data);

        // Send response
        byte[] responseBytes = response.getBytes();
        out.writeInt(responseBytes.length);
        System.out.println(data);
        out.write(responseBytes);
    }


    // CREATE SHARED BOARD

    /**
     * Creates a shared board and saves it on the repository
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void createSharedBoard(byte version, String data, DataOutputStream out) throws IOException {
        String[] lines = data.split("\n");
        System.out.println("lines " + data);
        SystemUser owner = userRepository.findById(Username.valueOf(lines[0]));
        if (owner == null) {
            String error = String.format("Owner not found!");
            sendErrorResponse(version, error, out);
        }
        String title = lines[1];
        Integer maxNumberOfRows = Integer.valueOf(lines[2]);
        Integer maxNumberOfColumns = Integer.valueOf(lines[3]);
        SharedBoard newSharedBoard = new SharedBoardBuilder()
                .ownedBy(owner)
                .withTitle(title)
                .hasMaxNumberOfRows(maxNumberOfRows)
                .hasMaxNumberOfColumns(maxNumberOfColumns)
                .build();
        newSharedBoard = createRows(newSharedBoard, lines[4]);
        newSharedBoard = createColumns(newSharedBoard, lines[5]);
        newSharedBoard = createCells(newSharedBoard);

        try {
            sharedBoardRepository.save(newSharedBoard);
            System.out.println("Shared Board Created!!");
            sendSuccessResponse(version, out);
        } catch (IOException e) {
            String error = ("Could not create the shared board!");
            sendErrorResponse(version, error, out);
        }
    }


    /**
     * Create Rows of the shared board to be created and saves them in the repository
     *
     * @param sharedBoard
     * @param rowsStr
     * @return
     */
    private SharedBoard createRows(SharedBoard sharedBoard, String rowsStr) {
        String[] rows = rowsStr.split(";");

        for (int i = 0; i < rows.length; i++) {
            Row row = new Row(i + 1, sharedBoard, rows[i]);
            sharedBoard.rows().add(row);
        }

        return sharedBoard;
    }

    /**
     * Create Columns of the shared board to be created and saves them in the repository
     *
     * @param sharedBoard
     * @param columnsStr
     * @return
     */
    private SharedBoard createColumns(SharedBoard sharedBoard, String columnsStr) {
        String[] columns = columnsStr.split(";");

        for (int i = 0; i < columns.length; i++) {
            SharedBoardColumn sharedBoardColumn = new SharedBoardColumn(i + 1, sharedBoard, columns[i]);
            sharedBoard.columns().add(sharedBoardColumn);
        }

        return sharedBoard;
    }

    /**
     * Create Cells of the shared board to be created and saves them in the repository
     *
     * @param sharedBoard
     * @return
     */
    private SharedBoard createCells(SharedBoard sharedBoard) {
        for (Row row : sharedBoard.rows()) {
            for (SharedBoardColumn sharedBoardColumn : sharedBoard.columns()) {
                final Cell cell = new CellBuilder()
                        .inRow(row)
                        .inColumn(sharedBoardColumn)
                        .build();
                row.cellList().add(cell);
                sharedBoardColumn.cellList().add(cell);
            }
        }
        return sharedBoard;
    }

    /**
     * Sends to the user the list of shared boards available to him
     * Shared boards that he create and shared boards he was invited to (with both permissions)
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void findSharedBoardsForUser(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            SystemUser user = userRepository.findById(Username.valueOf(data));
            if (user == null) {
                String error = String.format("Owner not found!");
                sendErrorResponse(version, error, out);
            } else {
                List<SharedBoard> sharedBoardListInvited = (List<SharedBoard>) sharedBoardInvitationRepository.findSharedBoardsUserWasInvite(user);
                List<SharedBoard> availableSharedBoards = (List<SharedBoard>) sharedBoardRepository.findSharedBoardOfUser(user);

                availableSharedBoards.addAll(sharedBoardListInvited);

                if (!availableSharedBoards.isEmpty()) {
                    String sharedBoards = "";
                    for (SharedBoard list : availableSharedBoards) {
                        sharedBoards += list.title() + "\n";
                    }
                    sendMessage(version, out, sharedBoards);
                } else sendErrorResponse(version, "You have no shared boards!", out);
            }
        } else {
            sendErrorResponse(version, "Null data", out);
        }
    }

    private void findSharedBoardsForOwner(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            SystemUser user = userRepository.findById(Username.valueOf(data));
            if (user == null) {
                String error = String.format("Owner not found!");
                sendErrorResponse(version, error, out);
            } else {
                List<SharedBoard> availableSharedBoards = (List<SharedBoard>) sharedBoardRepository.findSharedBoardOfUser(user);

                if (!availableSharedBoards.isEmpty()) {
                    String sharedBoards = "";
                    for (SharedBoard list : availableSharedBoards) {
                        sharedBoards += list.title() + "\n";
                    }
                    sendMessage(version, out, sharedBoards);
                } else sendErrorResponse(version, "You have no shared boards!", out);
            }
        } else {
            sendErrorResponse(version, "Null data", out);
        }
    }

    public synchronized void mySharedBoard(byte version, String data, DataOutputStream out) throws IOException {
        SharedBoard sharedBoard = sharedBoardRepository.findSharedBoard(data);

        List<SharedBoardColumn> columns = sharedBoard.columns();
        List<Row> rows = sharedBoard.rows();
        String textHtml = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<title>Shared Board</title>" +
                "<script>" + CssReader.ReadFile(System.getProperty("user.dir") + "/base.app.sharedBoardServer/src/main/java/eapli/BoardAjax.js") + "</script>" +
                "</head>" +
                "<body onload=\"updateBoard()\">" +
                "<div id=\"table\">" +
                "<h1> Your Shared Board </h1>" +
                "<h1>"+ sharedBoard.title() +"</h1>" +
                "<style>" +
                CssReader.ReadFile(System.getProperty("user.dir") + "/base.app.sharedBoardServer/src/main/java/eapli/viewBoard.css") +
                "</style>" +
                "<table>";


        textHtml += "<tr><td></td>";
        for (SharedBoardColumn column : columns) {
            textHtml += "<td><b>" + column.title() + "</b></td>";
        }
        textHtml += "</tr>";

        for(Row row: rows){
            textHtml += "<tr><td><b>" + row.title() + "</b></td>";
            for(SharedBoardColumn column : columns){
                Cell cell = cellRepository.findCell(row.title(), column.title());
                PostIt postIt = postItRepository.findPostIt(cell);
                if(postIt == null){
                    textHtml += "<td></td>";
                }
                else{
                    String content = postIt.content();
                    String img = "https://";

                    if(content.substring(0,Math.min(content.length(), 8)).equals(img)){
                        textHtml += "<td><img src=\"" + postIt.content() + "\"width=\"50%\" height=\"50%\"></td>";
                    }
                    else {
                        textHtml += "<td><div><p>" + postIt.content() + "</p></div></td>";
                    }
                }

            }

            textHtml += "</tr>";
        }

        textHtml += "</table></div></body></html>";

        sendMessage(version, out, textHtml);

    }

    // SHARE SHARED BOARD

    /**
     * Sends to the user the list of shared boards he create
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void findSharedBoardsUserCreate(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            SystemUser user = userRepository.findById(Username.valueOf(data));
            if (user == null) {
                String error = String.format("Owner not found!");
                sendErrorResponse(version, error, out);
            } else {
                List<SharedBoard> availableSharedBoards = (List<SharedBoard>) sharedBoardRepository.findSharedBoardOfUser(user);

                if (!availableSharedBoards.isEmpty()) {
                    String sharedBoards = "";
                    for (SharedBoard list : availableSharedBoards) {
                        sharedBoards += list.title() + "\n";
                    }
                    sendMessage(version, out, sharedBoards);
                } else sendErrorResponse(version, "You have no shared boards!", out);
            }
        } else {
            sendErrorResponse(version, "Null data", out);
        }
    }

    /**
     * Sends to the user the list of available users to share his shared board with
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void listUsers(byte version, String data, DataOutputStream out) throws IOException {
        String[] info = data.split(";");
        if (info[0] != null && info[1] != null) {
            SystemUser user = userRepository.findById(Username.valueOf(info[0]));
            if (user == null) {
                sendErrorResponse(version, "Owner not found!", out);
            }

            List<SystemUser> allUsers = userRepository.findAll();
            allUsers.remove(user);

            SharedBoard sharedBoard = sharedBoardRepository.findSharedBoard(info[1]);

            Iterable<SharedBoardInvitation> list = sharedBoardInvitationRepository.findSharedBoardInvitationsOfSharedBoard(sharedBoard);

            for (SharedBoardInvitation sharedBoardInvitation : list) {
                allUsers.remove(sharedBoardInvitation.userInvite());
            }

            if (!allUsers.isEmpty()) {
                String users = "";
                for (SystemUser listUsers : allUsers) {
                    users += listUsers.username() + "\n";
                }
                sendMessage(version, out, users);
            }

        } else {
            sendErrorResponse(version, "Data Null", out);
        }
    }

    /**
     * Creates the shared board invitation and saves it in the repository
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void createSharedBoardInvitation(byte version, String data, DataOutputStream out) throws IOException {
        String[] info = data.split(";");
        if (info[0] != null && info[1] != null) {
            SystemUser user = userRepository.findById(Username.valueOf(info[0]));
            if (user == null) {
                sendErrorResponse(version, "Owner not found!", out);
            }
            SharedBoard sharedBoard = sharedBoardRepository.findSharedBoard(info[1]);
            UserPermission userPermission = UserPermission.valueOf(info[2]);

            SharedBoardInvitation sharedBoardInvitation = new SharedBoardInvitationBuilder()
                    .withSharedBoard(sharedBoard)
                    .withUser(user)
                    .withUserPermission(userPermission)
                    .build();

            sharedBoardInvitationRepository.save(sharedBoardInvitation);

            if (!sharedBoardInvitation.equals(null)) {
                System.out.println("Shared Board Invitation Created!!");
                sendSuccessResponse(version, out);
            } else {
                sendErrorResponse(version, "Could not create the shared board invitation!", out);
            }

        } else {
            sendErrorResponse(version, "Data Null", out);
        }
    }


    // CREATE POST-IT

    /**
     * Sends to the user the list of shared boards available to him
     * Shared boards that he create and shared boards he was invite to
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void findSharedBoardsOfUser(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            SystemUser user = userRepository.findById(Username.valueOf(data));
            if (user == null) {
                String error = String.format("Owner not found!");
                sendErrorResponse(version, error, out);
            } else {
                List<SharedBoard> sharedBoardListInvited = (List<SharedBoard>) sharedBoardInvitationRepository.findSharedBoardsAvailableForUser(user);
                List<SharedBoard> availableSharedBoards = (List<SharedBoard>) sharedBoardRepository.findSharedBoardOfUser(user);

                availableSharedBoards.addAll(sharedBoardListInvited);

                if (!availableSharedBoards.isEmpty()) {
                    String sharedBoards = "";
                    for (SharedBoard list : availableSharedBoards) {
                        sharedBoards += list.title() + "\n";
                    }
                    sendMessage(version, out, sharedBoards);
                } else sendErrorResponse(version, "You have no shared boards!", out);
            }
        } else {
            sendErrorResponse(version, "Null data", out);
        }
    }

    /**
     * Sends to the user the cells that don´t have any post it yet
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void findFreeCells(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            SharedBoard sharedBoard = sharedBoardRepository.findSharedBoard(data);
            if (sharedBoard == null) {
                sendErrorResponse(version, "Shared board not found!", out);
            } else {
                List<Cell> allFreeCells = (List<Cell>) cellRepository.findAllFreeCellsFromSharedBoard(sharedBoard);
                if (allFreeCells != null) {
                    String cellsAvailable = "";
                    for (Cell list : allFreeCells) {
                        cellsAvailable += list + "\n";
                    }
                    sendMessage(version, out, cellsAvailable);

                } else {
                    sendErrorResponse(version, "There are no free cells!", out);
                }
            }
        } else {
            sendErrorResponse(version, "Null data Shared Board", out);
        }
    }

    /**
     * Creates the post it and saves it in to the repository
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void createPostIt(byte version, String data, DataOutputStream out) throws IOException {
        String[] info = data.split(";");

        if (info[0] != null && info[1] != null && info[2] != null) {

            String content = info[1];
            String splitCell = info[2];
            String[] lala = splitCell.split(",");

            String rowTitle = lala[0];
            String columAndId = lala[1];

            String[] cellPos = columAndId.split("=>");
            String columTitle = cellPos[0];
            String cellId = cellPos[1];

            int c = Integer.parseInt(cellId);

            PostIt postIt = null;
            SharedBoardServer.addToLock(c);

            Map.Entry entry = SharedBoardServer.findEntry(c);
            synchronized (entry) {
                if (SharedBoardServer.lock.get(c).equals("Unlocked")) {

                    Cell cell = cellRepository.findCell(rowTitle, columTitle);

                    cell.changeTheFreeStatus(false);
                    cell = cellRepository.save(cell);
                    SystemUser user = userRepository.findById(Username.valueOf(info[0]));
                    if (user == null) {
                        sendErrorResponse(version, "Owner not found!", out);
                    }
                    postIt = new PostItBuilder()
                            .withOwner(user)
                            .withContent(content)
                            .withCell(cell)
                            .build();

                    postIt = postItRepository.save(postIt);


                } else {
                    System.out.println("That cell is currently occupied.");
                }

                if (postIt != null) {
                    System.out.println("Post-It Created!!");
                    sendSuccessResponse(version, out);
                } else {
                    sendErrorResponse(version, "Could not create the post-it!", out);
                }

                SharedBoardServer.lock.replace(c, "Locked");
            }
        } else {
            sendErrorResponse(version, "Data Null", out);
        }

    }


    //CHANGE POST-IT

    /**
     * Sends to the user the postIts that exist in the shared board
     *
     * @param version
     * @param data
     * @param out
     * @throws IOException
     */
    private void findPostIt(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            String[] splited = data.split(";");
            SystemUser user = userRepository.findById(Username.valueOf(splited[0]));
            String sharedBoardTitle = splited[1];
            SharedBoard sharedBoard = sharedBoardRepository.findSharedBoard(sharedBoardTitle);
            if (sharedBoard != null) {
                List<PostIt> postItsList = (List<PostIt>) postItRepository.findPostItsFromSharedBoard(sharedBoard, user);
                if (postItsList != null) {
                    String postIts = "";
                    for (PostIt postIt : postItsList) {
                        String postItSplited = postIt.toString().substring(8);
                        System.out.println(postItSplited);
                        postIts += postItSplited + "\n";
                    }
                    sendMessage(version, out, postIts);
                } else {
                    sendErrorResponse(version, "You have no post-its!", out);
                }
            } else {
                sendErrorResponse(version, "Null data Shared Board", out);
            }
        } else {
            sendErrorResponse(version, "Null data", out);
        }
    }

    private void undoPostIt(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            String[] info = data.split("-");
            String content = info[0];
            String user = info[1];
            String cell = info[2];
            String[] granularCell = cell.split("=>");
            String rowAndColumn = granularCell[0];
            String cellId = granularCell[1];
            String[] cellSplited = rowAndColumn.split(",");
            String row = cellSplited[0];
            String column = cellSplited[1];

            int c = Integer.parseInt(cellId);

            SharedBoardServer.addToLock(c);
            if (SharedBoardServer.lock.get(c).equals("Unlocked")) {
                SharedBoardServer.lock.replace(c, "Locked");
            }
            Map.Entry entry = SharedBoardServer.findEntry(c);
            synchronized (entry) {
                if (SharedBoardServer.lock.get(c).equals("Locked")) {

                    SystemUser username = userRepository.findById(Username.valueOf(user));
                    PostIt postIt = postItRepository.findPostItFromUserAndContentAndCell(username, content, row, column);
                    if (postIt == null) {
                        sendErrorResponse(version, "Post-It not found!", out);
                    }
                    PostItHistory lastPostItHistory = postItHistoryRepository.findLastChangeOfPostIt(postIt.getPostItID());
                    if (lastPostItHistory == null) {
                        postItRepository.delete(postIt);
                        System.out.println("Post-It Deleted because it has no previous history!");
                        sendSuccessResponse(version, out);
                    } else {
                        postIt.changeContent(lastPostItHistory.getOldDescription());
                        String newCell = (lastPostItHistory.getOldCell().identity()).toString();
                        int newCellPos = Integer.parseInt(newCell);
                        SharedBoardServer.addToLock(newCellPos);
                        Map.Entry entry2 = SharedBoardServer.findEntry(newCellPos);
                        synchronized (entry2) {
                            if (SharedBoardServer.lock.get(newCellPos).equals("Unlocked")) {
                                Cell cellNova = postIt.cell();

                                Cell cellOld = lastPostItHistory.getOldCell();
                                cellOld.changeTheFreeStatus(false);
                                cellOld = cellRepository.save(cellOld);
                                    postIt.changeCell(lastPostItHistory.getOldCell());
                                    postIt = postItRepository.save(postIt);

                                cellNova.changeTheFreeStatus(true);
                                cellRepository.save(cellNova);

                                    SharedBoardServer.lock.replace(newCellPos, "Locked");
                                    SharedBoardServer.lock.replace(c, "Unlocked");
                                System.out.println("Post-It Created!!");
                                sendSuccessResponse(version, out);
                            } else {
                                sendErrorResponse(version, "Cell already occupied", out);
                            }
                        }
                    }
                } else {
                    sendErrorResponse(version, "There is no post-it here", out);
                }
            }
        } else {
            sendErrorResponse(version, "Null data", out);
        }
    }

    public void archiveSharedBoard(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            String[] info = data.split(";");
            String user = info[0];
            String sharedBoardTitle = info[1];
            SystemUser username = userRepository.findById(Username.valueOf(user));
            SharedBoard sharedBoard = sharedBoardRepository.findSharedBoard(sharedBoardTitle);
            if (sharedBoard == null) {
                sendErrorResponse(version, "Shared Board not found!", out);
            }
            if (username == null) {
                sendErrorResponse(version, "User not found!", out);
            }
            if (sharedBoard.getOwner().equals(username)) {
                sharedBoard.setStatus(SharedBoardStatus.ARCHIVED);
                sharedBoardRepository.save(sharedBoard);
                sendSuccessResponse(version, out);
            } else {
                sendErrorResponse(version, "You are not the owner of this shared board!", out);
            }
        } else {
            sendErrorResponse(version, "Null data", out);
        }
    }

    private void movePostItToOtherCell(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {

            String[] splited = data.split(";");
            String postIt = splited[0];
            String[] postItSplited = postIt.split("-");
            String oldContent = postItSplited[0];
            String oldCellAndId = postItSplited[2];
            String newCellAndId = splited[1];

            String[] celleId = oldCellAndId.split("=>");
            String oldCell = celleId[0];
            String cellId = celleId[1];

            String[] cellSplited = oldCell.split(",");
            String oldRow = cellSplited[0];
            String oldColumn = cellSplited[1];

            String[] newCelleId = newCellAndId.split("=>");
            String newCell = newCelleId[0];
            String newCellId = newCelleId[1];

            String[] cellSplitedNew = newCell.split(",");
            String newRow = cellSplitedNew[0];
            String newColumn = cellSplitedNew[1];

            int cellPos = Integer.parseInt(cellId);

            int newCellPos = Integer.parseInt(newCellId);

            SharedBoardServer.addToLock(cellPos);
            if (SharedBoardServer.lock.get(cellPos).equals("Unlocked")) {
                SharedBoardServer.lock.replace(cellPos, "Locked");
            }

            Map.Entry oldEntry = SharedBoardServer.findEntry(cellPos);


            synchronized (oldEntry) {

                if (SharedBoardServer.lock.get(cellPos).equals("Locked")) {
                    SharedBoardServer.addToLock(newCellPos);
                    Map.Entry entry = SharedBoardServer.findEntry(newCellPos);
                    synchronized (entry) {
                        if (SharedBoardServer.lock.get(newCellPos).equals("Unlocked")) {
                            Cell c = cellRepository.findCell(oldRow, oldColumn);
                            Cell c1 = cellRepository.findCell(newRow, newColumn);
                            PostIt p = postItRepository.findPostIt(c);

                            if (p != null) {
                                c1.changeTheFreeStatus(false);
                                c1 = cellRepository.save(c1);
                                Date timeStamp = Date.from(Instant.now());
                                PostItHistory postItHistory = new PostItHistoryBuilder()
                                        .withPostIt(p)
                                        .withTimeStamp(timeStamp)
                                        .withOldContent(p.content())
                                        .withOldCell(c)
                                        .withNewContent(p.content())
                                        .withNewCell(c1)
                                        .build();

                                postItHistory = postItHistoryRepository.save(postItHistory);
                                p.changeCell(c1);
                                c.changeTheFreeStatus(true);
                                c = cellRepository.save(c);

                                p = postItRepository.save(p);

                                SharedBoardServer.lock.replace(cellPos, "Unlocked");
                                SharedBoardServer.lock.replace(newCellPos, "Locked");

                                sendSuccessResponse(version, out);
                            } else {
                                sendErrorResponse(version, "Post-It is null", out);
                            }
                        } else {
                            sendErrorResponse(version, "This cell is not free anymore!", out);
                        }
                    }

                } else {
                    sendErrorResponse(version, "There is no post-it to change anymore!", out);
                }
            }
        } else {
            sendErrorResponse(version, "Data Null", out);
        }

    }

    private void changeContentOfPostIt(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            String[] splited = data.split(";");
            String postIt = splited[0];
            String[] postItSplited = postIt.split("-");
            String postItCell = postItSplited[2];
            String oldContent = postItSplited[0];
            String newContent = splited[1];

            String[] celleId = postItCell.split("=>");
            String cellId = celleId[1];

            String oldCell = celleId[0];
            String[] cellSplited = oldCell.split(",");
            String row = cellSplited[0];
            String column = cellSplited[1];

                    Cell c = cellRepository.findCell(row, column);

                    PostIt p = postItRepository.findPostIt(c);

                        System.out.println("postit certo");
                        Date timeStamp = Date.from(Instant.now());
                        PostItHistory postItHistory = new PostItHistoryBuilder()
                                .withPostIt(p)
                                .withTimeStamp(timeStamp)
                                .withOldContent(p.content())
                                .withOldCell(p.cell())
                                .withNewContent(newContent)
                                .withNewCell(p.cell())
                                .build();
                        postItHistory = postItHistoryRepository.save(postItHistory);
                        p.changeContent(newContent);
                        p = postItRepository.save(p);

                        System.out.println("Post-It Changed!!");
                        sendSuccessResponse(version, out);


        } else {
            sendErrorResponse(version, "Data Null", out);
        }
    }


    private void findPostItHistory(byte version, String data, DataOutputStream out) throws IOException {
        if (data != null) {
            SharedBoard sharedBoard = sharedBoardRepository.findSharedBoard(data);
            if (sharedBoard == null) {
                sendErrorResponse(version, "Shared board not found!", out);
            } else {
                List<PostItHistory> allPostItHistory = (List<PostItHistory>) postItHistoryRepository.findUpdatesOfSharedBoard(sharedBoard);
                if (allPostItHistory != null) {
                    String postIt = "";
                    for (PostItHistory list : allPostItHistory) {
                        postIt += list.toString() + "\n";
                    }
                    sendMessage(version, out, postIt);

                } else sendErrorResponse(version, "There are no postIt history!", out);
            }
        } else {
            sendErrorResponse(version, "Null data Shared Board", out);
        }
    }


}
