package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    			AlertNotification("Xóa nhân viên thành công !");
    			closeScene();
    		} else {
    			if (messageDelete.equals("false")) {
    				AlertNotification("Nhân viên không tồn tại !");
    			} else {
    				AlertNotification("Xóa nhân viên thất bại, Vui lòng thử lại sau !");
    			}

    			closeScene();
    		}
    	} else 
    	if (holderManager.getAction()=="resetPassword") {
    		StaffHolder staffSelected = StaffHolder.getInstance();
    		try {
    			String messageReset = AccountModel.resetPassword(staffSelected.getSelectStaff().getPhoneNumber());
    			if (messageReset.equals("true")) {
    				AlertNotification("Đặt lại mật khẩu thành công !");
    				closeScene();
    			} else {
    				if (messageReset.equals("false")) {
        				AlertNotification("Nhân viên không tồn tại !");
        			} else {
        				AlertNotification("Đã có lỗi xảy ra, Vui lòng thử lại sau !");
        			}
    				closeScene();
    			}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				AlertNotification("Đã có lỗi xảy ra, Vui lòng thử lại sau !");
			}
    					
    		
    	} else
    	if (holderManager.getAction()=="deleteService") {
    		String messageDelete = ServiceModel.deleteService(holderManager.getService().getId());
    		if (messageDelete.equals("true")) {
    			holderManager.getIndexController().ViewServiceTbView();
    			AlertNotification("Xóa dịch vụ thành công");
    			closeScene();
    		} else {
    			if (messageDelete.equals("false")) {
    				AlertNotification("Dịch vụ không tồn tại !");
    			} else {
    				AlertNotification("Đã có lỗi xảy ra, Vui lòng thử lại sau !");
    			}
    			closeScene();
    		}
    	} else 
    	if (holderManager.getAction()=="deleteFood") {
    		String messageDelete = FoodModel.deleteFood(holderManager.getFood().getId()); 
    		System.out.print(messageDelete);
    		if (messageDelete.equals("true")) {
    			holderManager.getIndexController().ViewFoodTbView();
    			AlertNotification("Xóa món ăn thành công !");
    			closeScene();
    		}else {
    			if (messageDelete.equals("false")) {
    				AlertNotification("Món ăn không tồn tại !");
    			} else {
    				AlertNotification("Đã có lỗi xảy ra, Vui lòng thử lại sau !");
    			}
    			closeScene();
    		}
    	}else 
    	if (holderManager.getAction()=="deleteLobby") {
    		String messageDelete = LobbyModel.deleteLobby(holderManager.getLobby().getId()); 
    		System.out.print(messageDelete);
    		if (messageDelete.equals("true")) {
    			holderManager.getIndexController().ViewLobbyTbView();
    			AlertNotification("Xóa sảnh thành công !");
    			closeScene();
    		}else {
    			if (messageDelete.equals("false")) {
    				AlertNotification("Sảnh không tồn tại hoặc đang được đặt !");
    			} else {
    				AlertNotification("Đã có lỗi xảy ra, Vui lòng thử lại sau !");
    			}
    			closeScene();
    		}
    	} else 
    	if (holderManager.getAction()=="payOrderWedding") {
    		holderManager.getStageNeedClose().close();
    		holderManager.getIndexController().ViewOrderSummanryTbView();
    		closeScene();
    	} else 
    	if (holderManager.getAction()=="exitOrderWedding") {
    		OrderWeddingModel.DeleteOrderWedding(holderManager.getIdWeddingCommitPayment());
    		holderManager.getStageNeedClose().close();
    		closeScene();
    	} else {
			if (holderManager.getAction()=="payAndBill") {
				// Hàm Call Update Deposit vs Lập Bill chỗ này
				try {
					String idCus = holderManager.getCusOrder().getId();
					String idStaff = StaffHolder.getInstance().getStaff().getId();
					String idWedding = holderManager.getDetailOrderWedding().getIdWedding();
					String message = BillModel.createBill(idStaff, idCus, idWedding);
<<<<<<< HEAD
					System.out.println("Message after payment" + message);
					if ((message.equals("")==false) || (message!=null)  ) {
=======
					if ((!message.equals("")) || (message!=null)  ) {
>>>>>>> f32664d1fd650b90258594b51f88f5a9e1951493
						holderManager.getStageNeedClose().close();
						closeScene();		
						AlertNotification("Thanh toán thành công !");
						holderManager.getIndexController().ViewOrderSummanryTbView();
					} else {
						AlertNotification("Thanh toán thất bại !");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
					AlertNotification("Đã có lỗi xảy ra, Vui lòng thử lại sau !");
					closeScene();
				}
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
