package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AlertController {

    @FXML
    private AnchorPane window;
    @FXML
    private Label alertContent;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    @FXML
    private Button btnOK;
    
    @FXML
    void initialize() {
    	HolderManager holderManager = HolderManager.getInstance();
    	if (holderManager.getTypeAlert()==0) {
    		btnYes.setVisible(true);
    		btnNo.setVisible(true);
    		btnOK.setVisible(false);

    		
    	} else {
    		btnYes.setVisible(false);
    		btnNo.setVisible(false);
    		btnOK.setVisible(true);

    	}
		alertContent.setText(holderManager.getAlertContent());
    }
    @FXML
    private indexController indexCtrl ;
    @FXML
    public void onAcceptPress(ActionEvent event) throws SQLException {
    	HolderManager holderManager = HolderManager.getInstance();
    	if (holderManager.getAction()=="deleteStaff") {
    		StaffHolder holder = StaffHolder.getInstance();
        	String messageDelete = StaffModel.deleteStaff(holder.getSelectStaff().getId());
    		if (messageDelete.equals("true")) {
    			holderManager.getIndexController().updateStaffTView();
    			AlertNotification("Xóa nhân viên thành công");
    			closeScene();
    		} else {
    			AlertNotification("Xóa nhân viên thất bại, Vui lòng thử lại");
    		}
    	} else 
    	if (holderManager.getAction()=="resetPassword") {
    		
    	} else
    	if (holderManager.getAction()=="deleteService") {
    		String messageDelete = ServiceModel.deleteService(holderManager.getService().getId());
    		if (messageDelete.equals("true")) {
    			holderManager.getIndexController().ViewServiceTbView();
    			AlertNotification("Xóa dịch vụ thành công");
    			closeScene();
    		} else {
    			AlertNotification("Xóa dịch vụ thất bại, Vui lòng thử lại");
    		}
    	} else 
    	if (holderManager.getAction()=="deleteFood") {
    		String messageDelete = FoodModel.deleteFood(holderManager.getFood().getId()); 
    		System.out.print(messageDelete);
    		if (messageDelete.equals("true")) {
    			holderManager.getIndexController().ViewFoodTbView();
    			AlertNotification("Xóa món ăn thành công");
    			closeScene();
    		}else {
    			AlertNotification("Xóa dịch vụ thất bại, Vui lòng thử lại");
    		}
    	}else 
    	if (holderManager.getAction()=="deleteLobby") {
    		String messageDelete = LobbyModel.deleteLobby(holderManager.getLobby().getId()); 
    		System.out.print(messageDelete);
    		if (messageDelete.equals("true")) {
    			holderManager.getIndexController().ViewLobbyTbView();
    			AlertNotification("Xóa sảnh thành công");
    			closeScene();
    		}else {
    			AlertNotification("Xóa dịch vụ thất bại, Vui lòng thử lại");
    		}
    	}
    
    }
    private void closeScene() {
    	Stage thisStage = (Stage) window.getScene().getWindow();
		thisStage.close();
    }
	public void AlertNotification(String content) throws SQLException {
		HolderManager holderManager = HolderManager.getInstance();
		holderManager.setAlertInit("addStaff",content, 1);
		AlertScene alertScene = new AlertScene();
		Stage stage = new Stage();
		alertScene.start(stage);
	}
	/*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) window.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) window.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) window.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) window.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
}
