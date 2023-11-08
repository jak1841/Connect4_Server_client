import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.awt.*;

public class Opening_controller {


    @FXML
    VBox root;

    @FXML
    TextField port_num_textfield, ip_address_textfield;

    private Client client;

    public void connect(ActionEvent actionEvent) {
        String port_number = port_num_textfield.getText();
        String ip_address = ip_address_textfield.getText();

        if (is_port_and_ip_valid(ip_address, port_number)) {
            change_scene_to_game_board();
        } else {
            change_scene_to_invalid();
        }
    }

    // Given port number and ip address as strings will check that the data and connection is valid
    private boolean is_port_and_ip_valid (String ip, String portNum) {
        try {
            client = new Client(ip, Integer.parseInt(portNum));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void change_scene_to_invalid () {
        try {
            Parent root_invalid = FXMLLoader.load(getClass().getResource("invalid_portnum_ip.fxml"));
            root.getScene().setRoot(root_invalid);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void change_scene_to_game_board () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game_board.fxml"));
            Parent root_invalid = loader.load();
            GameBoard_controller gbc = loader.getController();
            gbc.set_client(client);
            root.getScene().setRoot(root_invalid);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
