package application;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.print.*;

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
    private TableView<Lobby> tbViewOrderLobby;
    @FXML
    private TableColumn<Lobby,String> orderLobbyID;
    @FXML
    private TableColumn<Lobby,String> orderLobbyName;
    @FXML
    private TableColumn<Lobby,String> orderLobbyType;
    @FXML
    private TableColumn<Lobby,Number> orderLobbyTable;
    @FXML
    private TableColumn<Lobby,String> orderLobbyPriceTable;
    @FXML
    private TableColumn<Lobby,String> orderLobbyPrice;
    @FXML
    private TableView<Food> foodFinal;
    @FXML
    private TableColumn<Food,String> idFoodFinal;
    @FXML
    private TableColumn<Food,String> nameFoodFinal;
    @FXML
    private TableColumn<Food,String> priceFoodFinal;
    @FXML
    private TableColumn<Food,String> typeFoodFinal;
    @FXML
    private TableView<ServiceWedding> serviceFinal;
    @FXML
    private TableColumn<ServiceWedding,String> idServiceFinal;
    @FXML
    private TableColumn<ServiceWedding,String> nameServiceFinal;
    @FXML
    private TableColumn<ServiceWedding,String> priceServiceFinal;
    @FXML
    private Button btnPay;

    ArrayList<Food> arrFoodOrder;
	ArrayList<ServiceWedding> arrServiceOrder;
    @FXML
    private Label idWedding;
	@FXML
	void initialize() throws SQLException, ParseException {
		HolderManager holderManager = HolderManager.getInstance();
		
		OrderWeddingModel.getDetailWedding(holderManager.getOrderWeddingCurrent().getIdWedding());
		
		// L???y d??? li???u t??? holder sau khi call
		System.out.println(holderManager.getDetailOrderWedding());
		OrderWedding detailOrderWedding = holderManager.getDetailOrderWedding();
		idWedding.setText(detailOrderWedding.getIdWedding());
		arrFoodOrder = holderManager.getArrFoodOrder();
		arrServiceOrder = holderManager.getArrServiceOrder();
		InfoWedding infoOrder = holderManager.getInfoOrder();
		Customer cusOrder = holderManager.getCusOrder();
		// VN?? FORMAT
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		// set Default value
		//SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		//Date dateStart = (Date) formatterDate.parse(detailOrderWedding.getDateStart());

		dayStart.setText("Ng??y b???t ?????u: "+ detailOrderWedding.getDateStart());
		dayOrder.setText("Ng??y ?????t: "+detailOrderWedding.getDateOrder());
		money.setText("T???ng ti???n: "+ formatter.format(detailOrderWedding.getMoney().longValue())+ " VN??");
		deposit.setText("Ti???n ???? tr???: "+ formatter.format(detailOrderWedding.getDeposit().longValue())+ " VN??");
		numberTable.setText("S??? b??n ???? ?????t: "+ detailOrderWedding.getNumberOfTable().toString());
		idStaff.setText("M?? nh??n vi??n l???p ti???c: "+ detailOrderWedding.getIdStaff().toString());
		long a = detailOrderWedding.getMoney().longValue(); 
		long b = detailOrderWedding.getDeposit().longValue();
		if (a-b==0) {
			btnPay.setVisible(false);
		}
		
		remain.setText("S??? ti???n c??n l???i ph???i tr???: "+ formatter.format(a-b)+ " VN??");
		//nameCus
		nameBride.setText("T??n c?? d??u: "+ infoOrder.getNameBride());
		nameGroom.setText("T??n ch?? r???: "+infoOrder.getNameGroom());
		phoneCus.setText("S??? ??i???n tho???i: "+cusOrder.getPhone());
		nameCus.setText("T??n ng?????i ?????t: "+ cusOrder.getName());
		ViewTbColumnAll();
		ViewTbViewAll();
	}
	void ViewTbViewAll() {
		ObservableList<Food> aFoods = FXCollections.observableArrayList(arrFoodOrder);
		foodFinal.setItems(aFoods);
		ObservableList<ServiceWedding> serviceWeddings = FXCollections.observableArrayList(arrServiceOrder);
		serviceFinal.setItems(serviceWeddings);
		HolderManager holderManager = HolderManager.getInstance();
		ObservableList<Lobby> lobbyList = FXCollections.observableArrayList(
					holderManager.getLobbyOrder()
				); 
		tbViewOrderLobby.setItems(lobbyList);
	}
	void ViewTbColumnAll() {
		orderLobbyID.setCellValueFactory(new PropertyValueFactory<Lobby,String>("id"));
		orderLobbyName.setCellValueFactory(new PropertyValueFactory<Lobby,String>("name"));
		orderLobbyType.setCellValueFactory(new PropertyValueFactory<Lobby,String>("type"));
		orderLobbyTable.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("tableNumber"));
		orderLobbyPriceTable.setCellValueFactory(new PropertyValueFactory<Lobby,String>("priceTable"));
		orderLobbyPrice.setCellValueFactory(new PropertyValueFactory<Lobby,String>("priceLobby"));
		
		idFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
		nameFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
		priceFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("price"));
		typeFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("type"));
		
		idServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("id"));
		nameServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("name"));
		priceServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("price"));
	}

	@FXML
	AnchorPane orderDetail;
    @FXML
    private Label numberTable;
    @FXML
    private Label idStaff;
	@FXML
    private	void OnPressPay(ActionEvent event) throws SQLException {
		HolderManager holderManager = HolderManager.getInstance();
		OrderWeddingModel.getDetailWedding(holderManager.getOrderWeddingCurrent().getIdWedding());
		OrderWedding detailOrderWedding = holderManager.getDetailOrderWedding();
		holderManager.setStageNeedClose((Stage) orderDetail.getScene().getWindow());
		holderManager.AlertNotification("payAndBill", "X??c nh???n thanh to??n v?? l???p h??a ????n ?", 0);
	}

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
