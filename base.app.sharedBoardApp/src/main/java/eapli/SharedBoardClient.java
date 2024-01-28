package eapli;

import eapli.HTTP.BrowserThread;
import eapli.UI.LoginUI;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SharedBoardClient {

    static InetAddress serverIP;
    public static Socket sock;

    protected static int SERVER_PORT = 9999;

    public static String userName;
    public static String userNameInvited;

    public static String sharedBoard;
    public static String cell;
    public static String postIt;


    public static DataOutputStream sOut;
    public static DataInputStream sIn;


    public static void main(String args[]) throws Exception {
        if(args.length!=1) {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        try {
            serverIP = InetAddress.getByName(args[0]);
        }
        catch(UnknownHostException ex) {
            System.out.println("Invalid server specified: " + args[0]);
            System.exit(1);
        }
        // Connection
        try {
            sock = new Socket(serverIP, SERVER_PORT);
        }
        catch(IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }


        sOut = new DataOutputStream(sock.getOutputStream());
        sIn = new DataInputStream(sock.getInputStream());

        new Thread(new BrowserThread()).start();

        LoginUI loginUI = new LoginUI();
        if(loginUI.show()) {
            MainMenu menuUI = new MainMenu();
            menuUI.mainLoop();
        }

        sock.close();
    }
}