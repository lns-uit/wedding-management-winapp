package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddLobbyController {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNumberTable;

    @FXML
    private TextField tfTypeLobby;

    @FXML
    private TextField tfPriceTable;

    @FXML
    private TextField tfPriceLobby;

    @FXML
    private TextArea tfNote;

    @FXML
    void onAddLobby(ActionEvent event) throws NumberFormatException, SQLException {
    	System.out.println("Press");
    	String message = Validator();
    	System.out.println(message);
    	if (message.equals("success")) {
    		
    		String messageAddLobby = LobbyModel.addLobby(
    				tfName.getText(), 
    				tfTypeLobby.getText(), 
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
		
		if ((tfName.getText().equals("")) || (tfTypeLobby.getText().equals("")) ||(tfNumberTable.getText().equals("")) || (tfPriceTable.getText().equals("")) || (tfPriceLobby.getText().equals(""))) {
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
