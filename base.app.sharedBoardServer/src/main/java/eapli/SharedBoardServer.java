package eapli;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class SharedBoardServer {
    static ServerSocket socket;
    private static int SERVER_PORT = 9999;

    public static HashMap<Integer, String> lock = new HashMap<>();


    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try {
            socket = new ServerSocket(SERVER_PORT);
        }
        catch(IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        System.out.println("Waiting for connection...");

        while(true) {
            cliSock = socket.accept();
            new Thread(new TcpServerMessageHandler(cliSock)).start();
        }
    }

    public static Map.Entry findEntry(int id){
        for (Map.Entry<Integer, String> entry : lock.entrySet()) {
            if (entry.getKey() == id) {
                return entry;
            }
        }
        return null;
    }

    public synchronized static void addToLock(int id){
        if(!lock.containsKey(id))
        lock.put(id, "Unlocked");
    }


}
