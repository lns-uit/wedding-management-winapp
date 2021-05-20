package application;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UpdateFoodController {

    @FXML
    private TextField tfNameFood;

    @FXML
    private TextField tfPriceFood;

    @FXML
    private ComboBox<String> cbcTypeFood;
    
    ObservableList<String> list = FXCollections.observableArrayList("khai vị","tráng miệng","món chính", "thức uống");
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
    	else if (foodSelect.getType().equals("thức uống")) indexSelectFoodType = 3;
    	cbcTypeFood.getSelectionModel().select(indexSelectFoodType);
    	idFood = foodSelect.getId();
    }

    @FXML
    void onUpdateFood(ActionEvent event) throws NumberFormatException, SQLException {
    	String message = Validator();
    	if (message.equals("success")) {
    		String messageUpdateFood = FoodModel.updateFood(idFood ,tfNameFood.getText(), Integer.parseInt(tfPriceFood.getText()), cbcTypeFood.getValue());
    		if (messageUpdateFood.equals("true")) {
    			Stage currentScene = (Stage) tfNameFood.getScene().getWindow();
    			showAlertWithoutHeaderText("Chỉnh sửa thành công");
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
		
		if ((tfPriceFood.getText().equals("")) ||(tfNameFood.getText().equals("")) ||(cbcTypeFood.getValue()==null)) {
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
