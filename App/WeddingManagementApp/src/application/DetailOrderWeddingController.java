package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DetailOrderWeddingController {
	@FXML
    private Label dayStart;

    @FXML
    private Label dayOrder;

    @FXML
    private Label money;

    @FXML
    private Label deposit;

    @FXML
    private Label remain;

    @FXML
    private Label nameCus;

    @FXML
    private Label nameBride;

    @FXML
    private Label nameGroom;

    @FXML
    private Label phoneCus;
	@FXML
	void initialize() throws SQLException {
		HolderManager holderManager = HolderManager.getInstance();
		
		OrderWeddingModel.getDetailWedding(holderManager.getOrderWeddingCurrent().getIdWedding());
		
		// Lấy dữ liệu từ holder sau khi call
		OrderWedding detailOrderWedding = holderManager.getDetailOrderWedding();
		ArrayList<Food> arrFoodOrder = holderManager.getArrFoodOrder();
		ArrayList<ServiceWedding> arrServiceOrder = holderManager.getArrServiceOrder();
		InfoWedding infoOrder = holderManager.getInfoOrder();
		Customer cusOrder = holderManager.getCusOrder();
		
		// set Default value
		dayStart.setText(detailOrderWedding.getDateStart());
		dayOrder.setText(detailOrderWedding.getDateOrder());
		money.setText(detailOrderWedding.getMoney().toString());
		deposit.setText(detailOrderWedding.getDeposit().toString());
		
		
		long a = detailOrderWedding.getMoney().longValue(); 
		long b = detailOrderWedding.getDeposit().longValue();
		System.out.println(a-b);
		//remain.setText(a-b);
		//nameCus
		nameBride.setText(infoOrder.getNameBride());
		nameGroom.setText(infoOrder.getNameGroom());
		phoneCus.setText(cusOrder.getPhone());
		nameCus.setText(cusOrder.getName());
		
	}
	@FXML
	AnchorPane orderDetail;
	  
    /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) orderDetail.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) orderDetail.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) orderDetail.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) orderDetail.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
}
