import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class InvalidPortnumIp_controller {


    @FXML
    VBox root;

    public void try_again(ActionEvent actionEvent) {
        change_scene_to_opening();
    }

    private void change_scene_to_opening () {
        try {
            Parent root_invalid = FXMLLoader.load(getClass().getResource("opening.fxml"));
            root.getScene().setRoot(root_invalid);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
