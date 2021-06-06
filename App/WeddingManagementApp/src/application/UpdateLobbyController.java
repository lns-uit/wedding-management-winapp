package application;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateLobbyController {
	@FXML
    private TextField tfNote;

    @FXML
    private ComboBox<String> cbbTypeLobby;

    @FXML
    private TextField tfPriceTable;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNumberTable;

    @FXML
    private TextField tfPriceLobby;
    ObservableList<String> listType = FXCollections.observableArrayList("Thường", "Vip");
    @FXML
    public void initialize() {
		cbbTypeLobby.setItems(listType);
		
		HolderManager holderManager = HolderManager.getInstance();
		tfNote.setText(holderManager.getLobby().getNote()); 
		tfName.setText(holderManager.getLobby().getName());  
		tfNumberTable.setText(holderManager.getLobby().getTableNumber().toString());
		tfPriceTable.setText(holderManager.getLobby().getPriceTable().toString());
		tfPriceLobby.setText(holderManager.getLobby().getPriceLobby().toString());
		int indexType = 0;
		if (holderManager.getLobby().getType().equals("Thường")) indexType = 0;
		else if (holderManager.getLobby().getType().equals("Vip")) indexType = 1;
		cbbTypeLobby.getSelectionModel().select(indexType);
    }
    @FXML
    private Label warningLb;
    @FXML
    public void onUpdateLobby(ActionEvent event) throws NumberFormatException, SQLException {
    	// Update Lobby
    	String message = Validator();
    	HolderManager holderManager = HolderManager.getInstance();
    	Lobby selectLobby = holderManager.getLobby();
    	warningLb.setVisible(false);
    	if (message.equals("success")) {
    		String messageUpdate = LobbyModel.updateLobby(
    				selectLobby.getId(), 
    				tfName.getText(), 
    				cbbTypeLobby.getValue(), 
    				Integer.parseInt(tfNumberTable.getText()), 
    				Integer.parseInt(tfPriceTable.getText()), 
    				Integer.parseInt(tfPriceLobby.getText())
    				);
    		
        	if (messageUpdate.equals("true")) {
        		holderManager.getIndexController().ViewLobbyTbView();
        		
        		holderManager.AlertNotification(" ","Chỉnh sửa thông tin sảnh thành công",1);
        		Stage currentScene = (Stage) tfName.getScene().getWindow();
    			currentScene.close();
        		
        	} else {
        		holderManager.AlertNotification(" ","Đã có lỗi xảy ra, vui lòng thử lại sau", 1);
        	}
    	} else {
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
}
