package application;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AddWeddingOrderScene {

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("addWeddingUI.fxml"));
			StackPane root = (StackPane) loader.load();
			Scene scene = new Scene(root,1120,681);
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.initModality(Modality.APPLICATION_MODAL); 
			
			Task<Void> task = new Task<Void>() {
			    @Override
			    public Void call() throws Exception {
			    	AddWeddingOrderController ctrl = (AddWeddingOrderController) loader.getController();
			    	ctrl.initialize();
			        return null ;
			    }
			};
			task.setOnSucceeded(e -> {
				HolderManager holderManager = HolderManager.getInstance();
				holderManager.getIndexController().LoadingOrderWedding(false);
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
			});
			new Thread(task).start();
			
			
		
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
