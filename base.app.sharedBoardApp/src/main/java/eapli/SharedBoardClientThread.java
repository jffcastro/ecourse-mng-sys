package eapli;

import eapli.UI.LoginUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SharedBoardClientThread extends Thread{

    protected static Socket socket;
    protected static DataOutputStream sOut;
    protected static DataInputStream sIn;
    private final SharedBoardClientController theController = new SharedBoardClientController();

    @Override
    public void run(){
        try {
            socket = new Socket(SharedBoardClient.serverIP, SharedBoardClient.SERVER_PORT);
        }
        catch (IOException e){
            System.out.println("Can´t connect to that server address and server port!");
            System.exit(1);
        }

        try {
            sOut = new DataOutputStream(socket.getOutputStream());
            sIn = new DataInputStream(socket.getInputStream());
        }
        catch (IOException e){
            System.out.println("Could not access to the socket´s streams!");

            try {
                socket.close();
            }
            catch (IOException exception){
                System.out.println("Could not close the socket!");
            }

            System.exit(1);
        }

        if(new LoginUI().show()){
            MainMenu mainMenu = new MainMenu();
            mainMenu.mainLoop();
            System.out.println("Bye...");
        }
        else{
            try {
                if(theController.disconnect()){
                    socket.close();
                }
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

}
