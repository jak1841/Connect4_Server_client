import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class GameBoard_controller {

    @FXML
    BorderPane root;

    @FXML
    GridPane connect4_board_gridpane;

    @FXML
    ListView game_info_listview;


    private ArrayList<GameButton> gameButtons = new ArrayList<>();      // Store all game buttons

    private Client client;

    public void set_client (Client client) {
        this.client = client;
        setup_client();
    }

    // Method called when fxml class is iniitialized allows for dynamically adding widgets to fxml
    @FXML
    public void initialize () {
        EventHandler<ActionEvent> button_function = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameButton btn = (GameButton) actionEvent.getSource();
                send_row_column_clicked_on_to_server(btn.getRow(), btn.getCol());

                if (is_game_over()) {
                    change_scene_to_win_tie();
                }
            }
        };

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                GameButton button = new GameButton(i, j);
                button.setPrefSize(40, 40);
                button.change_color_gray();
                button.setOnAction(button_function);
                connect4_board_gridpane.add(button, j, i);

                gameButtons.add(button);
            }
        }

        disable_all_game_buttons();

    }


    // Someone has won or Lost
    private boolean is_game_over() {
        return false;
    }


    private void change_scene_to_win_tie () {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("win_or_tie.fxml"));
            Parent root_invalid = loader.load();

            WinOrTie_controller wotc = loader.getController();
            wotc.set_client(client);


            root.getScene().setRoot(root_invalid);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // Initializes the client
    private void setup_client() {
        this.client.setCallback(data -> {
            Platform.runLater(() -> {
                game_info_listview.getItems().add(data.toString());
            });
        });
        this.client.setGbc(this);
        this.client.start();

    }

    private void send_row_column_clicked_on_to_server(int row, int col) {
        CFourInfo msg = new CFourInfo("Player Clicked: ", row, col, true, true, null);
        this.client.send(msg);
    }

    // Disables all the game buttons to be not clickable
    public void disable_all_game_buttons () {
        for (int i = 0; i < gameButtons.size(); i++) {
            gameButtons.get(i).setDisable(true);
        }

    }

    // Enables user to click on all buttons
    public void enable_all_game_buttons () {
        for (int i = 0; i < gameButtons.size(); i++) {
            if (gameButtons.get(i).color.equals("G")) {
                gameButtons.get(i).setDisable(false);
            }
        }
    }

    // Given a CFourInfo object updates the Gameboard
    public void update_gameboard(CFourInfo move) {
        // Not a move just a message
        if (move.gameBoard == null) {
            return;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                String color = move.gameBoard[i][j];

                if (color.equals("R")) {
                    gameButtons.get(7*i + j).change_color_red();
                } else if (color.equals("Y")) {
                    gameButtons.get(7*i + j).change_color_yellow();
                } else {
                    gameButtons.get(7*i + j).change_color_gray();
                }
            }
        }



    }

    // Given a CFourINfo object that is assumed to have connect4 winning positions update the gameboard
    public void update_gameboard_with_winning_connect4_positions(CFourInfo data) {
        ArrayList<ArrayList<Integer>> winning_position = data.getWinning_connect_4_positions();
        if (winning_position == null) {
            return;
        }

        for (int i = 0; i < winning_position.size(); i++) {
            int row = winning_position.get(i).get(0);
            int col = winning_position.get(i).get(1);

            gameButtons.get(7*row + col).change_color_high_light();
        }

        try {
            Thread.sleep(5000);
            change_scene_to_win_tie();
        } catch (Exception e) {

        }

    }

}
