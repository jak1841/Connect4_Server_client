import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InvalidPortNum_controller {
    @FXML
    VBox root;

    // Switches Scene back to opening 
    public void try_again(ActionEvent actionEvent) {
        try {
            Parent root_opening = FXMLLoader.load(getClass().getResource("opening.fxml"));
            root.getScene().setRoot(root_opening);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
