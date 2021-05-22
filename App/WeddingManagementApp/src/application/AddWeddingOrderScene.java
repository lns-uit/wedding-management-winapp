package application;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddWeddingOrderScene {

	public void start(Stage primaryStage) {
		try {
			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("addWeddingUI.fxml"));
			Scene scene = new Scene(root,1120,681);
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.initModality(Modality.APPLICATION_MODAL); 
			primaryStage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
