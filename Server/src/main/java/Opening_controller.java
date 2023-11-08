import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Opening_controller {

    @FXML
    VBox root;
    @FXML
    TextField port_number_textfield;

    Server server;

    public void TurnOn(ActionEvent actionEvent) {

        String port_num = port_number_textfield.getText();

        if (is_port_num_valid(port_num)) {
            change_scene_to_game_info();
        } else {
            change_scene_to_invalid_port_num();
        }
    }

    private boolean is_port_num_valid (String portNumber) {
        try {
            server = new Server(Integer.parseInt(portNumber));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void change_scene_to_game_info() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game_info.fxml"));
            Parent root_game_info = fxmlLoader.load();
            game_info_controller gic = fxmlLoader.getController();
            gic.set_server(server);     // Passes server to controller
            root.getScene().setRoot(root_game_info);

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void change_scene_to_invalid_port_num () {
        try {
            Parent root_game_info = FXMLLoader.load(getClass().getResource("invalid_port_num.fxml"));
            root.getScene().setRoot(root_game_info);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
