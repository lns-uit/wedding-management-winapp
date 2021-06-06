package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ChangePassController {
    @FXML
    private PasswordField tfCurrentPassword;
    @FXML
    private PasswordField tfNewPassword;
    @FXML
    private PasswordField tfConfirmPassword;
	
	@FXML
	private Button btnCommit;
	 /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) btnCommit.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) btnCommit.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) btnCommit.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) btnCommit.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER 
     * @throws SQLException *************/
    @FXML
    void onSubmitChange(ActionEvent event) throws SQLException {
    	
    	String message = Validator();
    	
    	if (message.equals("success")) {
    		
    		HolderManager holder = HolderManager.getInstance();
    		Staff currentStaff = holder.getCurrentStaff();
    		try {
    			String messageChangePass = AccountModel.changePassword(currentStaff.getPhoneNumber(), tfCurrentPassword.getText(), tfNewPassword.getText());
        		
        		if (messageChangePass.equals("true")) {
        			holder.AlertNotification("", "Thay đổi mật khẩu thành công", 1);
        			Stage currentScene = (Stage) tfConfirmPassword.getScene().getWindow();
    				currentScene.close();
        		} else {
        			holder.AlertNotification("", "Mật khẩu không đúng !", 1);
        		}
			} catch (Exception e) {
    			holder.AlertNotification("", "Đã có lỗi xảy ra, vui lòng thử lại sau !", 1);
			}
    		
    	} else {
    		warningLb.setText(message);
    	}
    	
    }
    @FXML
    private Label warningLb;
    
    public String Validator() {
		String messageString="success";
		
		if ((tfCurrentPassword.getText().equals("")) || (tfNewPassword.getText().equals("")) || (tfConfirmPassword.getText().equals(""))) {
			return messageString = "Vui lòng điền đầy đủ thông tin !";
		}
		
		if (tfCurrentPassword.getText().length()<5 || tfNewPassword.getText().length()<5) {
			return messageString = "Mật khẩu phải có ít nhất 5 kí tự";
		}
		
		if (tfNewPassword.getText().equals(tfConfirmPassword.getText()) == false) {
			return messageString = "Xác nhận mật khẩu không khớp";
		}
		
		return messageString;
	}
}
