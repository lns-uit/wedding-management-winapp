package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AddStaffController implements Initializable {
	@FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private Label warningText;

    @FXML
    private TextField identityCard;
    
	@FXML
	ComboBox<String> typeStaff;
	
	
	ObservableList<String> list = FXCollections.observableArrayList("nhân viên lao công", "nhân viên lễ tân", "nhân viên phục vụ");
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		StaffHolder holder = StaffHolder.getInstance();
		Staff staff  = holder.getStaff();
		String typeStaffString = staff.getType();
		if (typeStaffString.equals("admin")) {
			System.out.print("abacasc");
			list.add("quản lý");
		}
        typeStaff.setItems(list);
    }
	
	@FXML
	public void CommitAddStaff(ActionEvent event) throws SQLException {
		String newPhoneString = phone.getText();
		warningText.setVisible(false);
		if (StaffModel.findStaffByPhone(newPhoneString) != null) {
			//check số điện thoại
			warningText.setText("Số điện thoại đã tồn tại");
			warningText.setVisible(true);
		} else {
			String message = Validator();
			if (message=="success") {
				warningText.setVisible(false);
				Staff newStaff = new Staff("", name.getText(), address.getText(), phone.getText(), identityCard.getText(), "2001-02-14", typeStaff.getValue());
				String messageAddString = StaffModel.addStaff(newStaff);
    			if (messageAddString.equals("true")) {
    				AlertNotification("Thêm nhân viên thành công");
    				Stage currentScene = (Stage) name.getScene().getWindow();
    				currentScene.close();
    			}
    			else {
    				AlertNotification("Thêm nhân viên thất bại, vui lòng thử lại");
    			}
		
			} else {
				warningText.setText(message);
				warningText.setVisible(true);
			}
		}
	}
	
	public void AlertNotification(String content) throws SQLException {
		HolderManager holderManager = HolderManager.getInstance();
		holderManager.getIndexController().updateStaffTView();
		holderManager.setAlertInit("addStaff",content, 1);
		AlertScene alertScene = new AlertScene();
		Stage stage = new Stage();
		alertScene.start(stage);
	}
	
	public String Validator() {
		String messageString="success";
	    
		if ((name.getText().length()==0)||(phone.getText().length()==0)||(identityCard.getText().length()==0)||(address.getText().length()==0)||(typeStaff.getValue()==null)) {
			System.out.println("is empty");
			return messageString="Vui lòng nhập đầy đủ thông tin !";
		}
		
		Pattern pattern = Pattern.compile("^\\d{10}$");
	    Matcher matcher = pattern.matcher(phone.getText());
	    Pattern patternIdentityCard = Pattern.compile("^\\d{9}$");
	    Matcher matcherIdentityCard = patternIdentityCard.matcher(identityCard.getText());
		if ((matcher.matches()==false)) {
			return messageString="Số điện thoại định dạng sai !";
		}
		if ((!matcherIdentityCard.matches())) return messageString = "Chứng minh thư sai định dạng";
		return messageString;
	}
	
	 /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) name.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) name.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) name.getScene().getWindow();
    	primaryStage.close();
    }
    /*************** END WINDOW CONTROLLER *************/
}
