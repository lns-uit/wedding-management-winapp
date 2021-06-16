package application;

	
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class indexScene {

	public void start(Stage primaryStage) {
		try { 
			 
		          
		  
			FXMLLoader loader = new FXMLLoader(getClass().getResource("indexUI.fxml"));
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root,1210,730);
			
			indexController ctrl = (indexController) loader.getController();
			HolderManager holderManager = HolderManager.getInstance();
			holderManager.setIndexController(ctrl);
		
			scene.setFill(Color.TRANSPARENT);
		//	primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();

			ScaleTransition trans = new ScaleTransition(Duration.seconds(0.2), root);
			FadeTransition transfade = new FadeTransition(Duration.seconds(0.2), root);
			
			transfade.setFromValue(.5);
	        transfade.setToValue(1.0);
	        transfade.setCycleCount(1);
	        transfade.setAutoReverse(false);
			trans.setFromX(0.9);
			trans.setFromY(0.9);
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
