package application;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateFoodController {

    @FXML
    private TextField tfNameFood;

    @FXML
    private TextField tfPriceFood;

    @FXML
    private ComboBox<String> cbcTypeFood;
    
    ObservableList<String> list = FXCollections.observableArrayList("khai vị","tráng miệng","món chính", "nước uống");
    String idFood = null;
    
    public void initialize() {
    	Food foodSelect = HolderManager.getInstance().getFood();
    	cbcTypeFood.setItems(list);
    	// đổ data cũ
    	tfNameFood.setText(foodSelect.getName());
    	tfPriceFood.setText(foodSelect.getPrice().toString());
        int indexSelectFoodType = 0;
    	if (foodSelect.getType().equals("tráng miệng")) indexSelectFoodType = 1;
    	else if (foodSelect.getType().equals("món chính")) indexSelectFoodType =2;
    	else if (foodSelect.getType().equals("nước uống")) indexSelectFoodType = 3;
    	cbcTypeFood.getSelectionModel().select(indexSelectFoodType);
    	idFood = foodSelect.getId();
    }
    @FXML
    private Label warningLb;
    @FXML
    void onUpdateFood(ActionEvent event) throws NumberFormatException, SQLException {
    	HolderManager holderManager = HolderManager.getInstance();
    	String message = Validator();
    	warningLb.setVisible(false);
    	if (message.equals("success")) {
    	
    		String messageUpdateFood = FoodModel.updateFood(idFood ,tfNameFood.getText(), Integer.parseInt(tfPriceFood.getText()), cbcTypeFood.getValue());
    		if (messageUpdateFood.equals("true")) {
    			
				holderManager.getIndexController().ViewFoodTbView();
				holderManager.AlertNotification(" ","Sửa thông tin món ăn thành công !",1);	
    			Stage currentScene = (Stage) tfNameFood.getScene().getWindow();
				currentScene.close();
    		} else  {
    			holderManager.AlertNotification(" ","Đã có lỗi xảy ra. Vui lòng thử lại sau",1);
    		}
    	}
    	else {
    		warningLb.setVisible(true);
    	}
    }
    
    public String Validator() {
		String messageString="success";
		
		if ((tfPriceFood.getText().equals("")) ||(tfNameFood.getText().equals("")) ||(cbcTypeFood.getValue()==null)) {
			return messageString = "Feild còn trống !";
		}
		
		return messageString;
	}
    /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) tfNameFood.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) tfNameFood.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) tfNameFood.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) tfNameFood.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
}
