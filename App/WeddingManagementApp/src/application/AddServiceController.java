package application;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddServiceController {

    /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) tfNameService.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) tfNameService.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) tfNameService.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) tfNameService.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/

    @FXML
    private TextField tfNameService;

    @FXML
    private TextField tfPriceService;
    @FXML
    private Label warningLb;
    @FXML
    void onAddService(ActionEvent event) throws SQLException {
    	String message = Validator();
    	HolderManager holderManager = HolderManager.getInstance();
    	warningLb.setVisible(false);
    	if (message.equals("success")) {
    		String messageAddService = ServiceModel.addService(tfNameService.getText(), Integer.parseInt(tfPriceService.getText()));

			if (messageAddService.equals("true")) {
				
				holderManager.getIndexController().ViewServiceTbView();
				holderManager.AlertNotification(" ","Thêm dịch vụ thành công",1);
				Stage currentScene = (Stage) tfNameService.getScene().getWindow();
				currentScene.close();
			}
			else {
				holderManager.AlertNotification(" ","Thêm dịch vụ thất bại, vui lòng thử lại",1);
			}
		
		} else {
			warningLb.setVisible(true);
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
