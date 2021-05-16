package application;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private Button btnLogin;

    @FXML
    void LoginToApp(ActionEvent event) throws SQLException {
    	String usernameString = tfUsername.getText() ;
    	String passwordString	 = tfPassword.getText() ;
    	
    	if (ValiDateForm(usernameString, passwordString)) {
    		String type = AccountModel.Login(usernameString, passwordString);
    		if ( type!="") {
    			System.out.println("Login success with type " + type);
    			indexScene mainScene = new indexScene();
    			Stage stage = new Stage();
    			mainScene.start(stage);
    			Stage currentScene = (Stage) btnLogin.getScene().getWindow();
    			currentScene.close();
    		} else {
    			System.out.println("Wrong email and password");
    		}
    	} else {
    		System.out.println("please retype");
    	}
    	
    }
    
    boolean ValiDateForm(String username,String password) {
    	if (username.length()== 0 ) {
    		System.out.println("Email is required");
    		return false;
    	} 
    	
    	if (password.length()== 0) {
    		System.out.println("Password is required");
    		return false;
    	}else if (password.length()<1) {
    		System.out.println("Password is better 6 character");
    		return false;
    	}
    	
    	return true;
    }

}