package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateStaffController implements Initializable {
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
    private Button btnUpdateStaff;
    
	@FXML
	ComboBox<String> typeStaff;
	
	
	ObservableList<String> list = FXCollections.observableArrayList("nhân viên lao công", "nhân viên lễ tân", "nhân viên phục vụ");
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		StaffHolder holder = StaffHolder.getInstance();
		Staff staff  = holder.getStaff();
		String typeStaffString = staff.getType();
		if (typeStaffString.equals("admin")) {
			list.add("quản lý");
		}
        typeStaff.setItems(list);
        //Đổ data cũ
        phone.setText(holder.getSelectStaff().getPhoneNumber());
        identityCard.setText(holder.getSelectStaff().getIdentityCard());
        address.setText(holder.getSelectStaff().getAddress());
        name.setText(holder.getSelectStaff().getName());
        int indexSelectStaffType = 0;
        if (holder.getSelectStaff().getType().equals("nhân viên lao công")) indexSelectStaffType = 0;
        else if (holder.getSelectStaff().getType().equals("nhân viên lễ tân")) indexSelectStaffType = 1;
        else if (holder.getSelectStaff().getType().equals("nhân viên phục vụ")) indexSelectStaffType = 2;
        else if (holder.getSelectStaff().getType().equals("quản lý")) indexSelectStaffType = 3;
        typeStaff.getSelectionModel().select(indexSelectStaffType);
        phone.setDisable(true);
        identityCard.setDisable(true);
    }
	
	@FXML
	public void CommitUpdateStaff(ActionEvent event) throws SQLException {
		
		StaffHolder holder = StaffHolder.getInstance();
		String idStaff = holder.getSelectStaff().getId();
		String message = Validator();
		HolderManager holderManager = HolderManager.getInstance();
		warningText.setVisible(false);
		if (message=="success") {
			String messageUpdate = StaffModel.updateStaff(idStaff, name.getText(), address.getText(), typeStaff.getValue());
			
			if (messageUpdate.equals("true")) {
				
				holderManager.getIndexController().updateStaffTView();
				warningText.setVisible(false);
				Stage currentScene = (Stage) name.getScene().getWindow();
				currentScene.close();
				holderManager.AlertNotification(" ","Sửa thông tin nhân viên thành công !",1);	
			} else {
				holderManager.AlertNotification(" ","Đã có lỗi xảy ra. Vui lòng thử lai sau",1);
			}		
		} else {
			warningText.setVisible(true);
		}
	}

	public String Validator() {
		String messageString="success";
	    
		if ((name.getText().length()==0)||(phone.getText().length()==0)||(identityCard.getText().length()==0)||(address.getText().length()==0)||(typeStaff.getValue()==null)) {
			System.out.println("is empty");
			return messageString="error";
		}
		
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
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) name.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
}
