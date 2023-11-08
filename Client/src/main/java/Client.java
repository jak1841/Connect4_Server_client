import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Client extends Thread{

    Socket socketClient;

    ObjectOutputStream out;
    ObjectInputStream in;

    Consumer<Serializable> callback;

    GameBoard_controller gbc;   // Allows access to methods in GameBoard_controller from within the client class

    public void setGbc(GameBoard_controller gbc) {
        this.gbc = gbc;

    }

    public void setCallback(Consumer<Serializable> callback) {
        this.callback = callback;
    }

    public Consumer<Serializable> getCallback() {
        return callback;
    }

    String IP;
    int port;

    public Client(String IP, int port) throws IOException {
        this.IP = IP;
        this.port = port;

        socketClient = new Socket(IP, port);
        out = new ObjectOutputStream(socketClient.getOutputStream());
        in = new ObjectInputStream(socketClient.getInputStream());
        socketClient.setTcpNoDelay(true);
    }

    @Override
    public void run() {
        // Listen indefinitly
        while(true) {
            try {
                CFourInfo data = (CFourInfo) in.readObject();
                handle_CFourInfo_data_from_server(data);
            }
            catch(Exception e) {}
        }
    }

    // Sends CFourInfo object to Server
    public void send(CFourInfo data) {

        try {
            out.writeObject(data);
            gbc.update_gameboard(data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void handle_CFourInfo_data_from_server (CFourInfo data) {
        callback.accept(data.getMessage());
        handle_turn(data);
        gbc.update_gameboard(data);
        gbc.update_gameboard_with_winning_connect4_positions(data);

    }


    // Given an CFourInfo object has boolean isTurn true will output message and enable buttons
    // Given an CFourInfo object has boolean isTurn false will output message and disable buttons
    private void handle_turn (CFourInfo data) {
        if (data.isTurn()) {
            gbc.enable_all_game_buttons();
        } else {
            gbc.disable_all_game_buttons();
        }
    }


}
