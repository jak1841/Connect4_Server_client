import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class initialize_project extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Project #2");

		Parent root = FXMLLoader.load(getClass().getResource("opening.fxml"));
	     Scene scene = new Scene(root, 400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
	}

}
