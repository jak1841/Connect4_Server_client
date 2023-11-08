import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Server {

    TheServer the_server;
    int count = 1;
    private ArrayList<ClientThread> client_list = new ArrayList<>();


    private Consumer<Serializable> callback_game_info_listview;

    private Consumer<Serializable> callback_client_count_label;

    public Server(int port) throws IOException {
        the_server = new TheServer(port);
        gameBoard = new Connect4GameBoard();
    }

    public void start_the_server () {
        the_server.start();
    }

    public void setCallback_game_info_listview(Consumer<Serializable> callback_game_info_listview) {
        this.callback_game_info_listview = callback_game_info_listview;
    }

    public void setCallback_client_count_label (Consumer<Serializable> callback_client_count_label) {
        this.callback_client_count_label = callback_client_count_label;
    }

    // Game board which will be a two dimensional array list where characters represent pieces.
    // R -> red piece
    // Y -> yellow piece
    // G -> Blank square
    private Connect4GameBoard gameBoard;




    class TheServer extends Thread{
        int port;
        ServerSocket serverSocket;
        public TheServer(int port) throws IOException {
            this.port = port;
            serverSocket = new ServerSocket(this.port);
        }

        @Override
        public void run() {
            try {
                callback_game_info_listview.accept("Server is waiting for Clients");

                while (true) {
                    ClientThread c = new ClientThread(serverSocket.accept(), count);
                    client_list.add(c);
                    c.start();
                    callback_game_info_listview.accept("Accepted a client (ClientID : " + count + ")");
                    count++;
                    callback_client_count_label.accept(client_list.size());

                    c.handle_if_there_two_players();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    class ClientThread extends Thread {
        Socket connection;
        ObjectInputStream in;
        ObjectOutputStream out;


        int id;
        public ClientThread(Socket s, int id) {
            this.connection = s;
            this.id = id;

            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

        }

        @Override
        public void run() {

            while(true) {
                try {
                    CFourInfo data = (CFourInfo) in.readObject();
                    handle_CFour_Info_from_client(data);

                }
                catch(Exception e) {
                    break;
                }
            }
        //end of run
        }


        // Once player makes a move that is correct disable clients button
        // Update the gameboard for both clients
        // enable other client button
        // Send respective messages to them informing the move was made
        private void handle_move(CFourInfo move) {
            update_game_board(move);

            callback_game_info_listview.accept("Client " + this.id + " placed " + get_piece_color_from_client() + " at " +
                    move.getRow() + "," + move.getCol());

            if (gameBoard.is_gameover()) {
                handle_gameover();
                return;
            }



            send_CFour_Info_to_client(new CFourInfo("Waiting for opponent move", -1, -1, true, false, gameBoard.getGameBoard()));
            send_CFourInfo_To_other_Client(new CFourInfo("Opponent moved piece to row: " + move.getRow() + " col: " + move.getCol(), move.getRow(), move.getCol(), true, false, gameBoard.getGameBoard()));
            send_CFourInfo_To_other_Client(new CFourInfo("You can move a piece", -1, -1, true, true, gameBoard.getGameBoard()));



        }

        private boolean is_Move_legal (int row, int col) {
            return gameBoard.is_move_legal(row, col);
        }

        // Method handles all the CFourInfo objects recieved from client
        private void handle_CFour_Info_from_client (CFourInfo data) {

            if (is_Move_legal(data.getRow(), data.getCol())) {
                handle_move(data);
            } else {
                send_CFour_Info_to_client(new CFourInfo("Move not legal please click somewhere else", -1, -1, true, true, gameBoard.getGameBoard()));
            }

        }

        // Method which sends CFour_Info_Object to client
        private void send_CFour_Info_to_client (CFourInfo data) {
            try {
                out.writeObject(data);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failure to send message");

            }
        }


        // Method will see if there are two players and if it is send to clients game is ready to start
        // Else we gotta send to client we have to wait
        private void handle_if_there_two_players () {
            if (client_list.size() == 2) {
                callback_game_info_listview.accept("Two Players connected starting game");
                send_CFourInfo_to_all_clients(new CFourInfo("All players Connected, Game Starting", -1, -1, true, false, null));
                send_CFour_Info_to_client(new CFourInfo("You can move a piece", -1, -1, true, true, null));
            } else {
                callback_game_info_listview.accept("Waiting for another client");
                send_CFourInfo_to_all_clients(new CFourInfo("Wait for other client to connect", -1, -1, false, false, null));

            }
        }

        // Method sends the same CFourInfo to all clients
        private void send_CFourInfo_to_all_clients (CFourInfo data) {
            for (int i = 0; i < client_list.size(); i++) {
                client_list.get(i).send_CFour_Info_to_client(data);
            }
        }

        // Method sends CFourInfo to other client
        private void send_CFourInfo_To_other_Client (CFourInfo data) {
            for (int i = 0; i < client_list.size(); i++) {
                if (client_list.get(i) != this) {
                    client_list.get(i).send_CFour_Info_to_client(data);
                }
            }
        }



        // Given a CFourInfo object with a move updates the gameboard
        private void update_game_board (CFourInfo move) {
            String color = get_piece_color_from_client();
            gameBoard.update_GameBoard(move.getRow(), move.getCol(), color);
            gameBoard.print_gameboard();

        }

        // Returns the color of the piece for this specefic client
        private String get_piece_color_from_client() {
            if (this == client_list.get(0)) {
                return "Y";
            } else {
                return "R";
            }
        }

        private void handle_gameover () {
            send_CFourInfo_to_all_clients(new CFourInfo("Game is over...", -1, -1, true, false, gameBoard.getGameBoard()));


            if (gameBoard.is_game_tie()) {
                send_CFourInfo_to_all_clients(new CFourInfo("Game was a tie", -1, -1, true, false, gameBoard.getGameBoard()));
                callback_game_info_listview.accept("Game Resulted in a Tie");
            } else {

                String winning_piece = gameBoard.get_piece_color_who_won();
                callback_game_info_listview.accept(winning_piece + " has won");

                CFourInfo winning_msg = new CFourInfo("You Win", -1, -1, true, false, gameBoard.getGameBoard());
                CFourInfo losing_msg = new CFourInfo("You Lose", -1, -1, true, false, gameBoard.getGameBoard());
                winning_msg.setWinning_connect_4_positions(gameBoard.get_winning_connect4_positions());
                losing_msg.setWinning_connect_4_positions(gameBoard.get_winning_connect4_positions());

                if (get_piece_color_from_client().equals(winning_piece)) {
                    send_CFour_Info_to_client(winning_msg);
                    send_CFourInfo_To_other_Client(losing_msg);
                } else {
                    send_CFour_Info_to_client(losing_msg);
                    send_CFourInfo_To_other_Client(winning_msg);
                }
            }

            gameBoard.clear_gameboard();
            client_list.clear();
            callback_game_info_listview.accept("Both clients disconnected");
            callback_game_info_listview.accept("Server waiting for client...");
            callback_client_count_label.accept("0");

        }


    }


}
