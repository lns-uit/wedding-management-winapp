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
		
		// Lấy dữ liệu từ holder sau khi call
		System.out.println(holderManager.getDetailOrderWedding());
		OrderWedding detailOrderWedding = holderManager.getDetailOrderWedding();
		idWedding.setText(detailOrderWedding.getIdWedding());
		arrFoodOrder = holderManager.getArrFoodOrder();
		arrServiceOrder = holderManager.getArrServiceOrder();
		InfoWedding infoOrder = holderManager.getInfoOrder();
		Customer cusOrder = holderManager.getCusOrder();
		// VNĐ FORMAT
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		// set Default value
		//SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		//Date dateStart = (Date) formatterDate.parse(detailOrderWedding.getDateStart());

		dayStart.setText("Ngày bắt đầu: "+ detailOrderWedding.getDateStart());
		dayOrder.setText("Ngày đặt: "+detailOrderWedding.getDateOrder());
		money.setText("Tổng tiền: "+ formatter.format(detailOrderWedding.getMoney().longValue())+ " VNĐ");
		deposit.setText("Tiền đã trả: "+ formatter.format(detailOrderWedding.getDeposit().longValue())+ " VNĐ");
		numberTable.setText("Số bàn đã đặt: "+ detailOrderWedding.getNumberOfTable().toString());
		long a = detailOrderWedding.getMoney().longValue(); 
		long b = detailOrderWedding.getDeposit().longValue();
		if (a-b==0) {
			btnPay.setVisible(false);
		}
		
		remain.setText("Số tiền còn lại phải trả: "+ formatter.format(a-b)+ " VNĐ");
		//nameCus
		nameBride.setText("Tên cô dâu: "+ infoOrder.getNameBride());
		nameGroom.setText("Tên chú rể: "+infoOrder.getNameGroom());
		phoneCus.setText("Số điện thoại: "+cusOrder.getPhone());
		nameCus.setText("Tên người đặt: "+ cusOrder.getName());
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
    private	void OnPressPay(ActionEvent event) throws SQLException {
		HolderManager holderManager = HolderManager.getInstance();
		OrderWeddingModel.getDetailWedding(holderManager.getOrderWeddingCurrent().getIdWedding());
		OrderWedding detailOrderWedding = holderManager.getDetailOrderWedding();
		holderManager.setStageNeedClose((Stage) orderDetail.getScene().getWindow());
		holderManager.AlertNotification("payAndBill", "Xác nhận thanh toán và lập hóa đơn ?", 0);
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
