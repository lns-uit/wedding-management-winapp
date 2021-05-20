package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateServiceScene {
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("addServiceUI.fxml"));
			Scene scene = new Scene(root,459,311);
			primaryStage.setTitle("Chỉnh sửa thông tin dịch vụ");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL); 
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
