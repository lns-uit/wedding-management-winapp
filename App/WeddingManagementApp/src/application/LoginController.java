package application;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
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
    void LoginToApp(ActionEvent event) throws SQLException {
    	String usernameString = tfUsername.getText() ;
    	String passwordString	 = tfPassword.getText() ;
    	
    	if (ValiDateForm(usernameString, passwordString)) {
    		int type = AccountModel.Login(usernameString, passwordString);
    		if ( type > -1) {
    			System.out.println("Login success with type " + type);
    			indexScene mainScene = new indexScene();
    			Stage stage = new Stage();
    			mainScene.start(stage);
    			Stage currentScene = (Stage) btnLogin.getScene().getWindow();
    			currentScene.close();
    		} else {
    			loginWarningTxt.setText("Wrong email or password!");
    			loginWarningTxt.setVisible(true);
    		}
    	} else {
    		System.out.println("please retype");
    	}
    	
    }
    
    boolean ValiDateForm(String username,String password) {
    	if (username.length()== 0 ) {
    		System.out.println("Email is required");
    		loginWarningTxt.setText("Email is required!");
    		loginWarningTxt.setVisible(true);
    		return false;
    	} 
    	
    	if (password.length()== 0) {
    		System.out.println("Password is required");
    		loginWarningTxt.setText("Password is required!");
    		loginWarningTxt.setVisible(true);
    		return false;
    	}else if (password.length()<6) {
    		System.out.println("Password is better 6 character");
    		loginWarningTxt.setText("Password is better 6 character!");
    		loginWarningTxt.setVisible(true);
    		return false;
    	}
    	
    	return true;
    }

}