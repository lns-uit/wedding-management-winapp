package application;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AlertScene {
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("alertUI.fxml"));
			Scene scene = new Scene(root,428,262);
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL); 
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
		    primaryStage.show();
			Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 1.8);

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
