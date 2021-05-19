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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class UpdateStaffController implements Initializable {
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
	
	
	ObservableList<String> list = FXCollections.observableArrayList("Nhân viên lao công", "Nhân viên lễ tân", "Nhân viên phục vụ");
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		StaffHolder holder = StaffHolder.getInstance();
		Staff staff  = holder.getStaff();
		String typeStaffString = staff.getType();
		if (typeStaffString.equals("admin")) {
			System.out.print("abacasc");
			list.add("Nhân viên quản lý");
		}
        typeStaff.setItems(list);
        phone.setText(holder.getSelectStaff().getPhoneNumber());
        identityCard.setText(holder.getSelectStaff().getIdentityCard());
        address.setText(holder.getSelectStaff().getAddress());
        name.setText(holder.getSelectStaff().getName());
        int indexSelectStaffType = 0;
        if (holder.getSelectStaff().getType().equals("Nhân viên lao công")) indexSelectStaffType = 0;
        else if (holder.getSelectStaff().getType().equals("Nhân viên lễ tân")) indexSelectStaffType = 1;
        else if (holder.getSelectStaff().getType().equals("Nhân viên phục vụ")) indexSelectStaffType = 2;
        else if (holder.getSelectStaff().getType().equals("Nhân viên quản lý ")) indexSelectStaffType = 3;
        typeStaff.getSelectionModel().select(indexSelectStaffType);
    }
	
	@FXML
	public void CommitAddStaff(ActionEvent event) throws SQLException {
		String newPhoneString = phone.getText();
		String message = Validator();
		if (message=="success") {
			warningText.setVisible(false);
			Stage currentScene = (Stage) name.getScene().getWindow();
			currentScene.close();
			showAlertWithoutHeaderText("Sửa thành công");
		} else {
			warningText.setText(message);
			warningText.setVisible(true);
		}
	}
	private void showAlertWithoutHeaderText(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(message);
 
        alert.showAndWait();
	}
	
	public String Validator() {
		String messageString="success";
	    
		if ((name.getText().length()==0)||(phone.getText().length()==0)||(identityCard.getText().length()==0)||(address.getText().length()==0)||(typeStaff.getValue()==null)) {
			System.out.println("is empty");
			return messageString="Field còn trống !";
		}
		
		Pattern pattern = Pattern.compile("^\\d{10}$");
	    Matcher matcher = pattern.matcher(phone.getText());
	    
	    System.out.println(matcher.matches());
		
		if ((matcher.matches()==false)) {
			return messageString="Số điện thoại định dạng sai !";
		}
		
		return messageString;
	}
}
