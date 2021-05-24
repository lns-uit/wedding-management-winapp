package application;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class LoginController {

    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfUsername;
    @FXML
    private Button btnLogin;
    @FXML
    private Label loginWarningTxt;
    @FXML
    private ProgressIndicator process;

    @FXML
    private AnchorPane box;
    @FXML
    void LoginToApp(ActionEvent event) throws SQLException, InterruptedException {
       	String usernameString = tfUsername.getText() ;
    	String passwordString	 = tfPassword.getText() ;
    	loginWarningTxt.setVisible(false);
    	if (ValiDateForm(usernameString, passwordString)) {
    		btnLogin.setDisable(true);
    		process.setVisible(true);
    		LoginVeritify(); 
    	}     	
    }
    void LoginVeritify() throws SQLException {
       	String usernameString = tfUsername.getText() ;
    	String passwordString	 = tfPassword.getText() ;
    	Staff staffLogin = AccountModel.Login(usernameString, passwordString);
		if ( staffLogin!=null) {
			
			StaffHolder holder = StaffHolder.getInstance();
			holder.setStaff(staffLogin);
			indexScene mainScene = new indexScene();
			Stage stage = new Stage();
			mainScene.start(stage);
		
			Stage currentScene = (Stage) btnLogin.getScene().getWindow();
			currentScene.close();
			
		} else {
			loginWarningTxt.setText("Tên đăng nhập hoặc mật khẩu sai!");
			loginWarningTxt.setVisible(true);
			btnLogin.setDisable(false);
    		process.setVisible(false);
		}
    }
    boolean ValiDateForm(String username,String password) {
    	if (username.length()== 0 ) {
    		loginWarningTxt.setText("Tên đăng nhập còn trống !");
    		loginWarningTxt.setVisible(true);
    		return false;
    	} 
    	
    	if (password.length()== 0) {
    		loginWarningTxt.setText("Mật khẩu còn trống !");
    		loginWarningTxt.setVisible(true);
    		return false;
    	}    	
    	return true;
    }
    /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) btnLogin.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) btnLogin.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) btnLogin.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) btnLogin.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
}