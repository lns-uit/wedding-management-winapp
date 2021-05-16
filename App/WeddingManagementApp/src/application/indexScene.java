package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class indexScene {
	
	public void start(Stage primaryStage) {
		try { 
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("indexUI.fxml"));
			Scene scene = new Scene(root,1120,681);
			primaryStage.setTitle("HOME");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
