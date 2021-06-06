package application;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class UpdateStaffScene {

	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("updateStaffUI.fxml"));
			Scene scene = new Scene(root,951,391);
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.initModality(Modality.APPLICATION_MODAL); 
			primaryStage.setScene(scene);
			primaryStage.show();
			ScaleTransition trans = new ScaleTransition(Duration.seconds(0.2), root);
			FadeTransition transfade = new FadeTransition(Duration.seconds(0.2), root);
			
			transfade.setFromValue(.5);
	        transfade.setToValue(1.0);
	        transfade.setCycleCount(1);
	        transfade.setAutoReverse(false);
			trans.setFromX(0.85);
			trans.setFromY(0.85);
			trans.setToX(1);
			trans.setToY(1);
	        trans.setCycleCount(1);
	        trans.setAutoReverse(false);
	        trans.play();
	        transfade.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
