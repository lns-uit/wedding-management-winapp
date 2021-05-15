package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private Button btnLogin;

    @FXML
    void LoginToApp(ActionEvent event) {
    	String usernameString = tfUsername.getText() ;
    	String passwordString	 = tfPassword.getText() ;
    	
    	if (ValiDateForm(usernameString, passwordString)) {
    		System.out.println("validate ok");
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
    	}else if (password.length()<6) {
    		System.out.println("Password is better 6 character");
    		return false;
    	}
    	
    	return true;
    }

}