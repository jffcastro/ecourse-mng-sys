package eapli.HTTP;

import eapli.SharedBoardClient;
import eapli.SharedBoardClientController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BrowserThread extends Thread{
    public static ServerSocket serverHTTP;
    Socket sock;
    DataInputStream inS;
    DataOutputStream outS;

    SharedBoardClientController sharedBoardClientController = new SharedBoardClientController();
    public void run() {
        try {
            serverHTTP = new ServerSocket(80);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true){
            try {
                sock = serverHTTP.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                outS = new DataOutputStream(sock.getOutputStream());
                inS = new DataInputStream(sock.getInputStream());
            } catch (IOException ex) {
                System.out.println("Thread error on data streams creation");
            }
            try {
                HTTPmessage request = new HTTPmessage(inS);
                HTTPmessage response = new HTTPmessage();
                // System.out.println(request.getURI());

                if (request.getMethod().equals("GET")) {
                    if(SharedBoardClient.userName == null){
                        response.setContentFromFile("base.app.sharedBoardApp/src/main/java/eapli/HTTP/www/login.html");
                        response.setResponseStatus("405 Method Not Allowed");
                    }
                    else if(request.getURI().startsWith("/sharedBoards")){
                        String user = String.format("%s", SharedBoardClient.userName);
                        String html = sharedBoardClientController.listSharedBoards(user);
                        response.setContentFromString(html, "text/html");
                        response.setResponseStatus("200 Ok");
                    }
                    else if(request.getURI().startsWith("/sharedBoard/")){
                        String uri = request.getURI();
                        int lastSlashIndex = uri.lastIndexOf("/");

                        if (lastSlashIndex != -1 && lastSlashIndex < uri.length() - 1) {
                            String sharedBoardTitle = uri.substring(lastSlashIndex + 1);
                            sharedBoardTitle = sharedBoardTitle.replace("_", " ");
                            String html1 = sharedBoardClientController.findMySharedBoardInfo(sharedBoardTitle);
                            response.setContentFromString(html1, "text/html");
                            response.setResponseStatus("200 Ok");
                        }
                    }
                    response.send(outS);
                    sock.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
