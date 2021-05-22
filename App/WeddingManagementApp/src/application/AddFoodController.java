package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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

    ObservableList<String> list = FXCollections.observableArrayList("khai vị","tráng miệng","món chính", "thức uống");
    
    public void initialize() {
    	tfTypeFood.setItems(list);
    }
    
    @FXML
    void onSubmitAddFood(ActionEvent event) throws SQLException {
    	String message = Validator();
    	if (message.equals("success")) {
    		System.out.println(tfNameFood.getText() + tfTypeFood.getValue()+ " "+ Integer.parseInt(tfPriceFood.getText()));
    		
    		String messageAddFood = FoodModel.addFood(tfNameFood.getText(), Integer.parseInt(tfPriceFood.getText()),  tfTypeFood.getValue());
    		if (messageAddFood.equals("true")) {
    			System.out.println("Thêm thành công");
    			Stage currentScene = (Stage) tfNameFood.getScene().getWindow();
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
		
		if ((tfPriceFood.getText().equals("")) ||(tfNameFood.getText().equals("")) ||(tfTypeFood.getValue()==null)) {
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