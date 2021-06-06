package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private Label warningLb;
    @FXML
    void onAddLobby(ActionEvent event) throws NumberFormatException, SQLException {
    	String message = Validator();
    	HolderManager holderManager = HolderManager.getInstance();
    	warningLb.setVisible(false);
    	if (message.equals("success")) {
    		
    		String messageAddLobby = LobbyModel.addLobby(
    				tfName.getText(), 
    				cbbTypeLobby.getValue(), 
    				Integer.parseInt(tfNumberTable.getText()), 
    				Integer.parseInt(tfPriceTable.getText()), 
    				Integer.parseInt(tfPriceLobby.getText())
    			);
    		
    		if (messageAddLobby.equals("true")) {
    			
    			holderManager.AlertNotification("addLobby","Thêm sảnh thành công !", 1);
    			holderManager.getIndexController().ViewLobbyTbView();
    			Stage currentScene = (Stage) tfName.getScene().getWindow();
				currentScene.close();
    		} else  {
    			holderManager.AlertNotification("addLobby","Thêm sảnh thất bại !", 1);
    		}
    	}
    	else {
    		warningLb.setVisible(true);
    	}
    }
    public String Validator() {
		String messageString="success";
		
		if ((tfName.getText().equals("")) || (cbbTypeLobby.getValue().equals("")) ||(tfNumberTable.getText().equals("")) || (tfPriceTable.getText().equals("")) || (tfPriceLobby.getText().equals(""))) {
			return messageString = "Feild còn trống !";
		}
		
		return messageString;
	}
    
}
