package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddLobbyUI {
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("addLobbyUI.fxml"));
			Scene scene = new Scene(root,998,567);
			primaryStage.setTitle("Thêm Sảnh");
			primaryStage.setScene(scene);
			primaryStage.initModality(Modality.APPLICATION_MODAL); 
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
