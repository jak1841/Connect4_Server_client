import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class game_info_controller {


    private Server server = null;

    @FXML
    private ListView game_info_listview;

    @FXML
    private Label num_client_label;


    // Allows server object created in opening controller to be used in this class
    public void set_server (Server server) {
        this.server = server;
        this.server.setCallback_game_info_listview(data -> {
            Platform.runLater(() -> {
                game_info_listview.getItems().add(data.toString());
            });
        });
        this.server.setCallback_client_count_label(data -> {
            Platform.runLater(() -> {
                num_client_label.setText("Number of Clients: " + data);
            });
        });
        this.server.start_the_server();
    }


}
