package application;


import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddFoodController {

    @FXML
    private Button btnAddFood;
    @FXML
    private TextField tfNameFood;
    @FXML
    private ComboBox<String> tfTypeFood;
    @FXML
    private TextField tfPriceFood;

    ObservableList<String> list = FXCollections.observableArrayList("khai vị","tráng miệng","món chính", "nước uống");
    
    public void initialize() {
    	tfTypeFood.setItems(list);
    }
    @FXML
    private Label warningLb;
    @FXML
    void onSubmitAddFood(ActionEvent event) throws SQLException {
    	HolderManager holderManager = HolderManager.getInstance();
    	warningLb.setVisible(false);
    	String message = Validator();
    	if (message.equals("success")) {
    		String messageAddFood = FoodModel.addFood(tfNameFood.getText(), Integer.parseInt(tfPriceFood.getText()),  tfTypeFood.getValue());
    		if (messageAddFood.equals("true")) {
    			Stage currentScene = (Stage) tfNameFood.getScene().getWindow();
    			holderManager.AlertNotification(" ","Thêm món ăn thành công",1);    			
    			
    			holderManager.getIndexController().ViewFoodTbView();
    			
				currentScene.close();
    		} else  {
    			holderManager.AlertNotification(" ","Đã có lỗi xảy ra vui lòng thử lại sau",1);
    		}
    	}
    	else {
    		warningLb.setVisible(true);
    	}
    }
	
    
    public String Validator() {
		String messageString="success";
		
		if ((tfPriceFood.getText().equals("")) ||(tfNameFood.getText().equals("")) ||(tfTypeFood.getValue()==null)) {
			return messageString = "Feild còn trống !";
		}
		
		return messageString;
	}
    

    /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) btnAddFood.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) btnAddFood.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) btnAddFood.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) btnAddFood.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
}