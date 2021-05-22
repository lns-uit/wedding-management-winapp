package application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.media.jfxmedia.control.VideoDataBuffer;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class indexController {
    @FXML
    private Button btnAddLobby;
    @FXML
    private Button btnDeleteLobby;
    @FXML
    private Button btnUpdateLobby;
    @FXML
    private Button btnWeddingOrderManagement;
    @FXML
    private Button btnStaffManagement;
    @FXML
    private Button btnWeddingInfoManagement;
    @FXML
    private Button btnCustomerManagement;
    @FXML
    private Button btnBill;
    @FXML
    private Button btnReport;
    @FXML
    private Button btnInfoPersonal;
    @FXML
    private Button btnLogout;
    @FXML
    private AnchorPane weddingOrderPanel;
    @FXML
    private AnchorPane staffManagerPanel;
    @FXML
    private AnchorPane weddingOrderInfoPanel;
    @FXML
    private Button btnLobbyManager;
    @FXML
    private Button btnMenuManager;
    @FXML
    private Button btnTimeManager;
    @FXML
    private AnchorPane stackLobbyManager;
    @FXML
    private AnchorPane stackMenuManager;
    @FXML
    private AnchorPane stackTimeManager;
    @FXML
    private AnchorPane customerPanel;
    @FXML 
    private AnchorPane billPanel;
    @FXML
    private AnchorPane reportPanel;
    @FXML
    private AnchorPane infoPersonalPanel;
    @FXML
    private Button btnName;
    private AnchorPane currentPane;
    private Button currentButton;
    
    /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) btnName.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) btnName.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) btnName.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) btnName.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
    @FXML
    void initialize() throws SQLException {
    	
    	// TODO Auto-generated method stub
    	//gán user vào info
    	StaffHolder holder = StaffHolder.getInstance();
    	Staff staff = holder.getStaff();
    	btnName.setText(staff.getName());
    	nameStaff.setText(staff.getName());
    	phoneNumberStaff.setText(staff.getPhoneNumber());
    	addressStaff.setText(staff.getAddress());
    	identityCardStaff.setText(staff.getIdentityCard());
    	typeStaff.setText(staff.getType());
    	startWorkStaff.setText(staff.getStartWork());
    	// lấy tất cả dữ liệu từ data
    	allStaff = StaffModel.getAllStaff();
    	// xử lí tất cả các view
		viewStaff();
		ViewLobbyColumn();
		ViewFoodColumn();
		ViewServiceColumn();
    	IndexInit(staff.getType());
    	// tìm kiếm nhân viên
    	tfSearchStaff.textProperty().addListener((observable, oldValue, newValue) -> {
    		arrStaff = FXCollections.observableArrayList(
    				filterStaff(observable.getValue())
    		);
    		staffTbView.setItems(arrStaff);
    	});
    	if (currentPane==null) currentPane = infoPersonalPanel;
    	if (currentButton==null) currentButton = btnInfoPersonal;
    	currentButton.setStyle("-fx-background-color: #cf4848");
    	currentPane.setVisible(true);
	}
    @FXML
    private Label LbNameIndex;
    @FXML
    public void PressIndex(ActionEvent event) throws SQLException {
    	
    	if (currentPane==null) currentPane = infoPersonalPanel;
    	currentPane.setVisible(false);
    	if (currentButton==null) currentButton = btnInfoPersonal;
    	currentButton.setStyle("-fx-background-color: #b83737");
    	if (event.getSource()==btnWeddingOrderManagement) { 
    		LbNameIndex.setText("QUẢN LÝ THÔNG TIN ĐẶT TIỆC");
    		currentPane = weddingOrderPanel; 
    		currentButton = btnWeddingOrderManagement;
    
    	}
    	else if (event.getSource()==btnStaffManagement) { 
    		LbNameIndex.setText("QUẢN LÝ THÔNG TIN NHÂN VIÊN");
    		currentPane = staffManagerPanel; 
    		currentButton = btnStaffManagement;
    		updateStaffTView();
    		
    	}
    	else if (event.getSource()==btnWeddingInfoManagement) { 
    		LbNameIndex.setText("QUẢN LÝ THÔNG TIN TIỆC CƯỚI");
    		currentPane = weddingOrderInfoPanel; 
    		currentButton = btnWeddingInfoManagement;
    		ViewFoodTbView();
        	ViewLobbyTbView();
        	ViewServiceTbView();
    	}
    	else if (event.getSource()==btnReport) { 
    		LbNameIndex.setText("BÁO CÁO");
    		currentPane = reportPanel; currentButton = btnReport;
    		ReportChartShow();    		
    		ReportTbViewShow();
    	}
    	else if (event.getSource()==btnInfoPersonal) { 
    		LbNameIndex.setText("THÔNG TIN CÁ NHÂN");
    		currentPane = infoPersonalPanel; 
    		currentButton = btnInfoPersonal;
    		}
    	else if (event.getSource()==btnBill) {
    		LbNameIndex.setText("QUẢN LÝ HÓA ĐƠN");
    		currentPane = billPanel;
    		currentButton = btnBill;
    	} 
    	else if (event.getSource()==btnCustomerManagement) {
    		LbNameIndex.setText("QUẢN LÝ THÔNG TIN KHÁCH HÀNG");
    		currentPane = customerPanel;
    		currentButton = btnCustomerManagement;
    	}
    	currentButton.setStyle("-fx-background-color:#cf4848");
    	currentPane.setVisible(true);
    }
    private Button currentButtonOptionWeddingInfoManager;
    private AnchorPane currentPanelOptionWeddingInfoManager;
    @FXML 
    public void PressInfoWeddingManagerOption(ActionEvent event) {
    	if (currentButtonOptionWeddingInfoManager==null) currentButtonOptionWeddingInfoManager = btnLobbyManager;
    	if (currentPanelOptionWeddingInfoManager==null) currentPanelOptionWeddingInfoManager = stackLobbyManager;
    	currentButtonOptionWeddingInfoManager.setDisable(false);
    	currentPanelOptionWeddingInfoManager.setVisible(false);
    	
    	if (event.getSource()== btnLobbyManager) { 
    		currentButtonOptionWeddingInfoManager = btnLobbyManager; 
    		currentPanelOptionWeddingInfoManager = stackLobbyManager; 
    	}
    	else if (event.getSource()== btnMenuManager) { 
    		currentButtonOptionWeddingInfoManager = btnMenuManager; 
    		currentPanelOptionWeddingInfoManager = stackMenuManager;
    	} else if (event.getSource()==btnServiceManger) {
    		currentButtonOptionWeddingInfoManager = btnServiceManger;
    		currentPanelOptionWeddingInfoManager = stackServiceManger;
    	}

    	currentButtonOptionWeddingInfoManager.setDisable(true);
    	currentPanelOptionWeddingInfoManager.setVisible(true);
    }
    @FXML
    public void AddStaff(ActionEvent event) {
    	AddStaffScene addStaffScene = new AddStaffScene();
		Stage stage = new Stage();
		addStaffScene.start(stage);
    }
    @FXML
    public void LogOut(ActionEvent event) {
		LoginScene mainScene = new LoginScene();
		Stage stage = new Stage();
		mainScene.start(stage);
		Stage currentScene = (Stage) btnInfoPersonal.getScene().getWindow();
		currentScene.close();
    }
    private void IndexInit(String type) {
    	System.out.print(type);
    	if (type.equals("nhân viên lễ tân")) { // Nhân viên lễ tân
    		btnStaffManagement.setDisable(true);
    		btnAddLobby.setDisable(true);
    		btnDeleteLobby.setDisable(true);
    		btnUpdateLobby.setDisable(true);	
    		btnAddService.setDisable(true);
    		btnDeleteService.setDisable(true);
    		btnUpdateService.setDisable(true);
    		btnAddFood.setDisable(true);
    		btnDeleteFood.setDisable(true);
    		btnUpdateFood.setDisable(true);
    	} else if (type.equals("nhân viên lao công")|| type.equals("nhân viên phục vụ")) { // Nhân viên phục vụ - Lao công 
    		btnWeddingInfoManagement.setDisable(true);
    		btnStaffManagement.setDisable(true);
    		btnLobbyManager.setDisable(true);
    		btnWeddingOrderManagement.setDisable(true);
    		btnReport.setDisable(true);	
    	}
    }
    /*********** ORDER LOBBY MANAGER CONTROLLER********/
    
    @FXML 
    private Button btnAddOrderWedding;
    
    @FXML
    public void OnActionOrderWedding(ActionEvent event) {
    	if (event.getSource()==btnAddOrderWedding) {
    		AddWeddingOrderScene addWeddingOrderScene = new AddWeddingOrderScene();
    		Stage stage = new Stage();
    		addWeddingOrderScene.start(stage);	
    	}
    }
    
    /*********** LOBBY MANAGER CONTROLLER ********/
    @FXML
    private TableView<Lobby> tbViewLobbyManager;
    @FXML
    private TableColumn<Lobby,String> lobbyIdColumn;
    @FXML
    private TableColumn<Lobby, String> lobbyNameColumn;
    @FXML
    private TableColumn<Lobby,String> lobbyTypeColumn;
    @FXML
    private TableColumn<Lobby,Number> lobbyTableColumn;
    @FXML
    private TableColumn<Lobby,Number> lobbyTablePriceColumn;
    @FXML
    private TableColumn<Lobby,Number> lobbyPriceColumn;
    @FXML
    private TableColumn<Lobby,String> lobbyNote;
    
    public void ViewLobbyColumn() throws SQLException {
    	lobbyIdColumn.setCellValueFactory(new PropertyValueFactory<Lobby,String>("id"));
    	lobbyNameColumn.setCellValueFactory(new PropertyValueFactory<Lobby,String>("name"));
     	lobbyTypeColumn.setCellValueFactory(new PropertyValueFactory<Lobby,String>("type"));
     	lobbyTableColumn.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("tableNumber"));
     	lobbyTablePriceColumn.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("priceTable"));
     	lobbyPriceColumn.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("priceLobby"));
     	lobbyNote.setCellValueFactory(new PropertyValueFactory<Lobby,String>("note"));
    }
    
    public void ViewLobbyTbView() throws SQLException {
    	
    	ArrayList<Lobby> arr = LobbyModel.getAllLobby();
    	
    	ObservableList<Lobby> arrLobby;
    	arrLobby = FXCollections.observableArrayList(arr);
    	tbViewLobbyManager.setItems(arrLobby);
    }
    
    @FXML
    public void PressAddLobby(ActionEvent event) {
    	AddLobbyUI addLobbySceneAddLobbyUI = new AddLobbyUI();
    	Stage stage = new Stage();
    	addLobbySceneAddLobbyUI.start(stage);
    }
    
    /***********End LOBBY MANAGER CONTROLLER*********/
    
    /***********FOOD MANAGER CONTROLLER*********/
    @FXML
    private TableView<Food> tbViewFood;
    @FXML
    private TableColumn<Food,String> foodIdColumn;

    @FXML
    private TableColumn<Food,String> foodNameColumn;
    @FXML
    private TableColumn<Food,Number> foodPriceColumn;
    @FXML
    private TableColumn<Food,String> foodTypeColumn;
    @FXML
    private Button btnAddFood;
    @FXML
    private Button btnDeleteFood;
    @FXML    
    private Button btnUpdateFood;
    public void ViewFoodColumn() {
    	foodIdColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	foodNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	foodPriceColumn.setCellValueFactory(new PropertyValueFactory<Food,Number>("price"));
    	foodTypeColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("type"));
    }
    public void ViewFoodTbView() throws SQLException {

    	ArrayList<Food> arr = FoodModel.getAllFood();
    	
    	ObservableList<Food> arrFood;
    	arrFood = FXCollections.observableArrayList(arr);
    	tbViewFood.setItems(arrFood);
    }
    
    public void OnActionButtonFood(ActionEvent event) throws SQLException {
    	Food selectedFood  = tbViewFood.getSelectionModel().getSelectedItem();
    	if (selectedFood==null) {
    		System.out.println("is empty");
    	} else {
    		if (event.getSource()==btnDeleteFood) {
        		String messageDelete = FoodModel.deleteFood(selectedFood.getId()); 
        		System.out.print(messageDelete);
        		if (messageDelete.equals("true")) {
        			System.out.println("Success");
        			ViewFoodTbView();
        		}
        	}
        	if (event.getSource()==btnUpdateFood) {
        		HolderManager foodHolder = HolderManager.getInstance();
        		foodHolder.setFood(selectedFood);
        		PressUpdateFood();
        	}
        	
    	}
    }
    @FXML
    public void PressAddFood(ActionEvent event) {
    	AddFoodScene addFoodScene = new AddFoodScene();
    	Stage stage = new Stage();
    	addFoodScene.start(stage);
    }
    public void PressUpdateFood() {
    	UpdateFoodScene updateFoodScene = new UpdateFoodScene();
    	Stage stage = new Stage();
    	updateFoodScene.start(stage);
    }
    
    /***********END FOOD MANAGER CONTROLLER*********/
    
    /*********** SERVICE MANAGER CONTROLLER **********/
    @FXML
    private Button btnServiceManger;
    @FXML
    private AnchorPane stackServiceManger;
    @FXML
    private TableView<ServiceWedding> tbViewService;
    @FXML
    private TableColumn<ServiceWedding,String> serviceIdColumn;
    @FXML
    private TableColumn<ServiceWedding,String> serviceNameColumn;
    @FXML
    private TableColumn<ServiceWedding,Number> servicePriceColumn;
    @FXML
    private Button btnAddService;
    @FXML
    private Button btnDeleteService;
    @FXML
    private Button btnUpdateService;
    
    private void ViewServiceColumn() {
    	serviceIdColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("id"));
    	serviceNameColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("name"));
    	servicePriceColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,Number>("price"));
    }
    private void ViewServiceTbView() {
    //	ArrayList<ServiceWedding> arr = FoodModel.getAllFood();
    	
    //	ObservableList<ServiceWedding> arrService;
    //	arrService = FXCollections.observableArrayList(arr);
    //	tbViewService.setItems(arrService);
    }
    @FXML
    public void OnPressServiceBtn(ActionEvent event) {
    	if (event.getSource()==btnAddService) {
    		AddServiceScene addServiceScene = new AddServiceScene();
        	Stage stage = new Stage();
        	addServiceScene.start(stage);
    	}
    	else if (event.getSource()==btnUpdateService) {
    		UpdateServiceScene updateServiceScene = new UpdateServiceScene();
        	Stage stage = new Stage();
        	updateServiceScene.start(stage);
    	}
    	else if (event.getSource()==btnDeleteService) {
    		
    	}
    }
    
    /*********** END SERVICE MANAGER CONTROLLER *********/
    
    /***********CUSTOMER CONTROLLER*******/
    @FXML
    private TableView<?> tbViewCustomer;
    @FXML
    private TableColumn<?, ?> cusIDColumn;
    @FXML
    private TableColumn<?, ?> cusNameColumn;
    @FXML
    private TableColumn<?, ?> cusPhoneNumberColumn;
    @FXML
    private TableColumn<?, ?> cusMoneyColumn;
    @FXML
    private TableColumn<?, ?> cusDiscountColumn;
    
    /***********BILL CONTROLLER**********/

    @FXML
    private TextField tfSearchBill;

    @FXML
    private TableView<?> tbViewBill;

    @FXML
    private TableColumn<?, ?> billIDColumn;

    @FXML
    private TableColumn<?, ?> billIDStaffColumn;

    @FXML
    private TableColumn<?, ?> billIDCustomerColumn;

    @FXML
    private TableColumn<?, ?> billIDWeddingColumn;

    @FXML
    private TableColumn<?, ?> billMoneyColumn;

    @FXML
    private TableColumn<?, ?> dateOfPayColumn;
    
    /***********Staff controller *************/
    @FXML
    private TableColumn<Staff,String> staffTypeColumn;
    private ObservableList<Staff> arrStaff;
    private ArrayList<Staff> allStaff;
    @FXML
    private TextField tfSearchStaff;
    @FXML
    private TableView<Staff> staffTbView;
    @FXML
    private TableColumn<Staff,String> staffIdColumn;
    @FXML
    private TableColumn<Staff,String> staffNameColumn;
    @FXML
    private TableColumn<Staff,String> staffPhoneColumn;
    @FXML
    private TableColumn<Staff,String> staffAdressColumn;
    @FXML
    private TableColumn<Staff,String> staffCMNDColumn;
    @FXML
    private TableColumn<Staff,String> staffStartWorkDateColumn;
    @FXML
    private Button btnStaffUpdate;
    @FXML
    private Button btnStaffDelete;

    public void OnActionButtonStaff(ActionEvent event) throws SQLException {
  		Staff selectStaff = staffTbView.getSelectionModel().getSelectedItem();
    	if (event.getSource()==btnStaffDelete) {
    		if (selectStaff==null) {
    			System.out.println("is empty");
    		} else {
    			String messageDelete = StaffModel.deleteStaff(selectStaff.getId());
    			System.out.println(messageDelete);
    			if (messageDelete.equals("true")) {
    				updateStaffTView();
    				tfSearchStaff.setText("");
    			}
    		}
    	}
    	if (event.getSource()==btnStaffUpdate) {
    		if (selectStaff==null) {
    			System.out.println("is empty");
    		} else {
    			StaffHolder holder = StaffHolder.getInstance();
        		holder.setStaffSelect(selectStaff);
        		UpdateStaffScene mainScene = new UpdateStaffScene();
        		Stage stage = new Stage();
        		mainScene.start(stage);	
    		}
    		
    	}
    }
    
    public void viewStaff() throws SQLException {
    		
		staffIdColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("id"));
		staffNameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
		staffPhoneColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNumber"));
		staffAdressColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("address"));
		staffCMNDColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("identityCard"));
		staffStartWorkDateColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("startWork"));
		staffTypeColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("type"));

	}
    
    public void updateStaffTView() throws SQLException {
    	ArrayList<Staff> arr = StaffModel.getAllStaff();
    	allStaff = arr;
    	staffTbView.getItems().clear();
    	setTbView(arr);	
    }
    
    
    public ArrayList<Staff> filterStaff (String inputName) {
    	ArrayList<Staff> resultStaffs = new ArrayList<Staff>();
    	
    	allStaff.forEach(staff -> {
    		if (staff.getName().toUpperCase().indexOf(inputName.toUpperCase())>-1) {
    			resultStaffs.add(staff);
    		}
    	});
    	
    	return resultStaffs;
    }
    
    public void setTbView (ArrayList<Staff> arrayStaff) {
    	arrStaff = FXCollections.observableArrayList(arrayStaff);
    	staffTbView.setItems(arrStaff);
    }
    
    /***********End Staff controller *************/

    /***********Report controller *************/
    @FXML
    private CategoryAxis xRevenue;
    @FXML
    private NumberAxis yRevenue;
    @FXML
    private LineChart< ? ,? > revenue;
    @FXML
    private LineChart<?, ?> countWedding;
    @FXML
    private CategoryAxis xCountWedding;
    @FXML
    private NumberAxis yCountWedding;

   
    void ReportChartShow() {
    	// Doanh số theo tháng
    	revenue.setTitle("Doanh thu theo tháng");
    	
    	XYChart.Series revenueSeries = new XYChart.Series();
    	revenueSeries.getData().add(new XYChart.Data("3/2020",20000000));
    	revenueSeries.getData().add(new XYChart.Data("4/2020",110000011));
    	revenueSeries.getData().add(new XYChart.Data("5/2020",12310002));
    	revenueSeries.getData().add(new XYChart.Data("6/2020",92310002));
    	xRevenue.setLabel("Tháng");
    	yRevenue.setLabel("VNĐ");
    	revenue.getData().addAll(revenueSeries);
    	
    	// Só tiệc theo tháng
    	countWedding.setTitle("Số tiệc theo tháng");
    	
    	XYChart.Series countWeddingSeries = new XYChart.Series();
    	countWeddingSeries.getData().add(new XYChart.Data("3/2020",20));
      	countWeddingSeries.getData().add(new XYChart.Data("4/2020",32));
      	countWeddingSeries.getData().add(new XYChart.Data("5/2020",15));
      	countWeddingSeries.getData().add(new XYChart.Data("6/2020",23));
      	xCountWedding.setLabel("Tháng");
      	yCountWedding.setLabel("VNĐ");
      	countWedding.getData().addAll(countWeddingSeries);
    }
    private ObservableList<ReportRevenue> arrReport;
    @FXML
    private TableView<ReportRevenue> tbViewReport;
    @FXML
    private TableColumn<ReportRevenue, Number> reportIDColumn;
    @FXML
    private TableColumn<ReportRevenue, String> reportMonthColumn;
    @FXML
    private TableColumn<ReportRevenue, Number> reportCountWeddingColumn;
    @FXML
    private TableColumn<ReportRevenue, Number> reportRevenueColumn;
    public void ReportTbViewShow() {

    	arrReport = FXCollections.observableArrayList(
    			new ReportRevenue(1,"3/2020",20,20000000),
    			new ReportRevenue(2,"4/2020",32,110000011),
    			new ReportRevenue(3,"5/2020",15,12310002),
    			new ReportRevenue(4,"6/2020",23,92310002)
    	);

    	reportIDColumn.setCellValueFactory(new PropertyValueFactory<ReportRevenue,Number>("stt"));
    	reportMonthColumn.setCellValueFactory(new PropertyValueFactory<ReportRevenue, String>("month"));
    	reportCountWeddingColumn.setCellValueFactory(new PropertyValueFactory<ReportRevenue, Number>("countWedding"));
    	reportRevenueColumn.setCellValueFactory(new PropertyValueFactory<ReportRevenue, Number>("revenue"));

    	tbViewReport.setItems(arrReport);
    }
    
    /***********Info controller *************/
    
    @FXML
    private Label nameStaff;
    @FXML
    private Label phoneNumberStaff;
    @FXML
    private Label addressStaff;
    @FXML
    private Label identityCardStaff;
    @FXML
    private Label typeStaff;
    @FXML
    private Label startWorkStaff;
    /***********End Info controller *************/
}




















