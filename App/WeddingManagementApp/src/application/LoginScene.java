package application;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginScene {

	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("loginUI.fxml"));
			Scene scene = new Scene(root,745,488);
			primaryStage.setResizable(false);
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setTitle("Đăng nhập");
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
			
			ScaleTransition trans = new ScaleTransition(Duration.seconds(0.2), root);
			FadeTransition transfade = new FadeTransition(Duration.seconds(0.2), root);
			
			transfade.setFromValue(.5);
	        transfade.setToValue(1.0);
	        transfade.setCycleCount(1);
	        transfade.setAutoReverse(false);
			trans.setFromX(0.6);
			trans.setFromY(0.6);
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
