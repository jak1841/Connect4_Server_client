import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class WinOrTie_controller {
    @FXML
    VBox root;

    Client client;

    public void set_client (Client client) {
        this.client = client;
    }

    private void add_another_client(Client client) {

    }


    public void play_again(ActionEvent actionEvent) {
        change_scene_to_game_board();
    }

    public void exit_program(ActionEvent actionEvent) {
        System.exit(1);
    }

    private void change_scene_to_game_board () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game_board.fxml"));
            Parent root_invalid = loader.load();
            GameBoard_controller gbc = loader.getController();
            client = new Client(client.IP, client.port);
            gbc.set_client(client);
            root.getScene().setRoot(root_invalid);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
