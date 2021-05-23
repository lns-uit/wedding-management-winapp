package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateServiceController {
	
	@FXML
    private TextField tfNameService;

    @FXML
    private TextField tfPriceService;

    ServiceWedding serviceSelect;
    
    public void initialize() {
    	serviceSelect = HolderManager.getInstance().getService();
    	// đổ data cũ
    	tfNameService.setText(serviceSelect.getName());
    	tfPriceService.setText(serviceSelect.getPrice().toString());
    }
	
    @FXML
    void onUpdateService(ActionEvent event) throws NumberFormatException, SQLException {
    	String message = Validator();
    	
    	if (message.equals("success")) {
    		
    		String messageUpdateService = ServiceModel.updateStaff(serviceSelect.getId(), tfNameService.getText(), Integer.parseInt(tfPriceService.getText()));
    		
    		if (messageUpdateService.equals("true")) {
    			System.out.print("Commit Success");
    			Stage currentScene = (Stage) tfNameService.getScene().getWindow();
    			currentScene.close();	
    		} else {
    			System.out.println("update faild");
    		}
    		
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
    
	  /*************** WINDOW CONTROLLER ************/
//    private Stage primaryStage =  new Stage();
//    private double X,Y;
//    @FXML
//    protected void onRectanglePressed(MouseEvent event) {
//    	primaryStage = (Stage) name.getScene().getWindow();
//        X = primaryStage.getX() - event.getScreenX();
//        Y = primaryStage.getY() - event.getScreenY();
//    }
//
//    @FXML
//    protected void onRectangleDragged(MouseEvent event) {
//    	primaryStage = (Stage) name.getScene().getWindow();
//        primaryStage.setX(event.getScreenX() + X);
//        primaryStage.setY(event.getScreenY() + Y);
//    }
//    @FXML
//    private void onPressExitWindow(ActionEvent event) {
//    	primaryStage = (Stage) name.getScene().getWindow();
//    	primaryStage.close();
//    }
//    @FXML
//    private void onPressMinimizeWindow(ActionEvent event) {
//    	primaryStage = (Stage) name.getScene().getWindow();
//    	primaryStage.setIconified(true);
//    }
    /*************** END WINDOW CONTROLLER *************/
}
