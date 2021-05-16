package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
public class AddStaffController implements Initializable {
	@FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextArea address;

    @FXML
    private Label warningText;

    @FXML
    private TextField identityCard;
    
	@FXML
	ComboBox<String> typeStaff;
	
	ObservableList<String> list = FXCollections.observableArrayList("Nhân viên quản lý", "Nhân viên lễ tân", "Nhân viên phục vụ");
	@Override
    public void initialize(URL location, ResourceBundle resources) {
	
        typeStaff.setItems(list);
        System.out.print(typeStaff.getValue());
    }
	@FXML
	public void ComboBoxChanged (ActionEvent event){
        System.out.print(typeStaff.getValue()=="Nhân viên quản lý");
    }
	@FXML
	public void CommitAddStaff(ActionEvent event) {
		if (name.getText()!="" && phone.getText()!="" && identityCard.getText()!=null  && address.getText()!="") {
			warningText.setVisible(false);
			Stage currentScene = (Stage) name.getScene().getWindow();
			currentScene.close();
			showAlertWithoutHeaderText();
			System.out.print("Commit Success");
		} else {
			warningText.setVisible(true);
		}
	}
	private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Thêm thành công");
 
        alert.showAndWait();
	}
}
