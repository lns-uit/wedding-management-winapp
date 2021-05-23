package application;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddServiceController {

    @FXML
    private TextField tfNameService;

    @FXML
    private TextField tfPriceService;

    @FXML
    void onAddService(ActionEvent event) throws SQLException {
    	String message = Validator();
    	
    	if (message.equals("success")) {
    		String messageAddService = ServiceModel.addService(tfNameService.getText(), Integer.parseInt(tfPriceService.getText()));
    		System.out.println(messageAddService);
			System.out.print("Commit Success");
			Stage currentScene = (Stage) tfNameService.getScene().getWindow();
			currentScene.close();
		} else {
			System.out.println(message);
		}

    }
    
	public String Validator() {
		String messageString="success";
	    
		if ((tfNameService.getText().length()==0)||(tfPriceService.getText().length()==0)) {
			return messageString="Field còn trống !";
		}
		
		return messageString;
	}

}
