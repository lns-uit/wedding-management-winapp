package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddLobbyController {

	  /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) tfName.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) tfName.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) tfName.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) tfName.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNumberTable;

    @FXML
    private ComboBox<String> cbbTypeLobby;

    @FXML
    private TextField tfPriceTable;

    @FXML
    private TextField tfPriceLobby;

    @FXML
    private TextField tfNote;
    
    ObservableList<String> listType = FXCollections.observableArrayList("Thường", "Vip");
    @FXML
    public void initialize() {
		cbbTypeLobby.setItems(listType);
    }
    @FXML
    void onAddLobby(ActionEvent event) throws NumberFormatException, SQLException {
    	System.out.println("Press");
    	String message = Validator();
    	System.out.println(message);
    	if (message.equals("success")) {
    		
    		String messageAddLobby = LobbyModel.addLobby(
    				tfName.getText(), 
    				cbbTypeLobby.getValue(), 
    				Integer.parseInt(tfNumberTable.getText()), 
    				Integer.parseInt(tfPriceTable.getText()), 
    				Integer.parseInt(tfPriceLobby.getText())
    			);
    		
    		if (messageAddLobby.equals("true")) {
    			System.out.println("Thêm thành công");
    			Stage currentScene = (Stage) tfName.getScene().getWindow();
    			showAlertWithoutHeaderText("Thêm thành công");
				currentScene.close();
    		} else  {
    			System.out.println("thêm không thành công");
    		}
    	}
    	else {
    		System.out.println(message);
    	}
    }
    
    public String Validator() {
		String messageString="success";
		
		if ((tfName.getText().equals("")) || (cbbTypeLobby.getValue().equals("")) ||(tfNumberTable.getText().equals("")) || (tfPriceTable.getText().equals("")) || (tfPriceLobby.getText().equals(""))) {
			return messageString = "Feild còn trống !";
		}
		
		return messageString;
	}
    
    private void showAlertWithoutHeaderText(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
	}

}
