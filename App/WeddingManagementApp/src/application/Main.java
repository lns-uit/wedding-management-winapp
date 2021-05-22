package application;
	
import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	public static Connection connection;
	
	public void initDatabase() throws ClassNotFoundException, SQLException {
		connection = ConnectDB.getOracleConnection();
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			LoginScene mainScene = new LoginScene();
			Stage stage = new Stage();
			mainScene.start(stage);
			initDatabase();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
