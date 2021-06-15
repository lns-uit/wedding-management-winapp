package application;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class indexController {

    @FXML
    private ProgressIndicator processTbView;
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
    /*************** END WINDOW CONTROLLER 
     * @throws JRException 
     * @throws ClassNotFoundException *************/
    @FXML
    void initialize() throws SQLException, ClassNotFoundException, JRException {
    	processTbView.setVisible(false);
    	// TODO Auto-generated method stub
    	//gán user vào info
    	StaffHolder holder = StaffHolder.getInstance();
    	Staff staff = holder.getStaff();
    	HolderManager holderCurrentStaff = HolderManager.getInstance();
    	holderCurrentStaff.setCurrentStaff(staff);
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
		ViewBillColumn();
		ViewCustomerColumn();
		ViewOrderSummaryColumn();
    	IndexInit(staff.getType());
    	ArrayList<OrderWedding> getAllOrderWedding = OrderWeddingModel.getAllOrderWedding();
    	
    	if (currentPane==null) currentPane = infoPersonalPanel;
    	if (currentButton==null) currentButton = btnInfoPersonal;
    	currentButton.setStyle("-fx-background-color: #cf4848");
    	currentPane.setVisible(true);
	}
    
    private boolean isFist = false;
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
    		tfSearchOrderWedding.setText("");
    		ViewOrderSummanryTbView();
    	}
    	else if (event.getSource()==btnStaffManagement) { 
    		LbNameIndex.setText("QUẢN LÝ THÔNG TIN NHÂN VIÊN");
    		currentPane = staffManagerPanel; 
    		currentButton = btnStaffManagement;
    		tfSearchStaff.setText("");
    		updateStaffTView();
    		
    	}
    	else if (event.getSource()==btnWeddingInfoManagement) { 
    		LbNameIndex.setText("QUẢN LÝ THÔNG TIN TIỆC CƯỚI");
    		currentPane = weddingOrderInfoPanel; 
    		currentButton = btnWeddingInfoManagement;
    		tfSearchLobby.setText("");
    		tfSearchFood.setText("");
    		tfSearchService.setText("");
    		ViewLobbyTbView();
    		ViewFoodTbView();
    		ViewServiceTbView();
    	}
    	else if (event.getSource()==btnReport) { 
    		LbNameIndex.setText("THỐNG KÊ - BÁO CÁO");
    		currentPane = reportPanel; currentButton = btnReport; 		
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
    		ViewBillTbView() ;
    	} 
    	else if (event.getSource()==btnCustomerManagement) {
    		LbNameIndex.setText("QUẢN LÝ THÔNG TIN KHÁCH HÀNG");
    		currentPane = customerPanel;
    		currentButton = btnCustomerManagement;
    		tfSearchCustomer.setText("");
    		ViewCustomerTbView();
    
    	}
    	currentButton.setStyle("-fx-background-color:#cf4848");
    	currentPane.setVisible(true);
    	TransitionAnimate(currentPane);
    	isFist = true;
    }
    
    void TransitionAnimate(AnchorPane x) {
		FadeTransition transfade = new FadeTransition(Duration.seconds(0.2), x);
	
		transfade.setFromValue(.5);
        transfade.setToValue(1.0);
        transfade.setCycleCount(1);
        transfade.setAutoReverse(false);
        transfade.play();
    }

    
    private Button currentButtonOptionWeddingInfoManager;
    private AnchorPane currentPanelOptionWeddingInfoManager;
    @FXML 
    public void PressInfoWeddingManagerOption(ActionEvent event) throws SQLException {
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
    private boolean pow = true;
    private void IndexInit(String type) {
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
    		pow = false;
    	} else if (type.equals("nhân viên lao công")|| type.equals("nhân viên phục vụ")) { // Nhân viên phục vụ - Lao công 
    		btnWeddingInfoManagement.setDisable(true);
    		btnStaffManagement.setDisable(true);
    		btnLobbyManager.setDisable(true);
    		btnWeddingOrderManagement.setDisable(true);
    		btnReport.setDisable(true);	
    		btnCustomerManagement.setDisable(true);
    		btnBill.setDisable(true);
    		pow = false;
    	}
    }
    /*********** ORDER WEDDING MANAGER CONTROLLER********/
    @FXML
    private TableView<OrderWedding> tbViewOrderSummary;
    @FXML
    private TableColumn<OrderWedding,String> idOrderSummary;
    @FXML
    private TableColumn<OrderWedding,String> nameOrderSummary;
    @FXML
    private TableColumn<OrderWedding,String> phoneOrderSummary;
    @FXML
    private TableColumn<OrderWedding,String> dateOrderSummary;
    @FXML
    private TableColumn<OrderWedding,String> dateStartSummary;
    @FXML
    private TableColumn<OrderWedding,String> statusOrderSummary;
    @FXML 
    private Button btnAddOrderWedding;
    @FXML
    private ProgressIndicator LoadingAddOrder;
    @FXML
    public void OnActionOrderWedding(ActionEvent event) {
    	
		Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	LoadingOrderWedding(true);
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			AddWeddingOrderScene addWeddingOrderScene = new AddWeddingOrderScene();
    		Stage stage = new Stage();
    		addWeddingOrderScene.start(stage);	
		});
		new Thread(task).start();

    }
    
    public void LoadingOrderWedding(boolean x) {
    	btnAddOrderWedding.setDisable(x);
    	LoadingAddOrder.setVisible(x);
    }
    
    void ViewOrderSummaryColumn() {
    	idOrderSummary.setCellValueFactory(new PropertyValueFactory<OrderWedding,String>("idWedding"));
    	nameOrderSummary.setCellValueFactory(new PropertyValueFactory<OrderWedding,String>("nameCus"));
    	phoneOrderSummary.setCellValueFactory(new PropertyValueFactory<OrderWedding,String>("phoneCus"));
    	dateOrderSummary.setCellValueFactory(new PropertyValueFactory<OrderWedding,String>("dateOrder"));
    	dateStartSummary.setCellValueFactory(new PropertyValueFactory<OrderWedding,String>("dateStart"));
    	statusOrderSummary.setCellValueFactory(new PropertyValueFactory<OrderWedding,String>("statusPay"));
    	InitSearchOrderWedding();
    	tbViewOrderSummary.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    		btnDetailOrder.setDisable(false);
    	    if (newSelection.getStatusPay().equals("Đã thanh toán")) {
    	    	btnDetailOrder.setDisable(true);
    	    } 
    	});

    }
    @FXML
    private Button btnDetailOrder;
    private ObservableList<OrderWedding> arrOrder;
    void ViewOrderSummanryTbView() {
    	tbViewOrderSummary.getSelectionModel().clearSelection();
    	btnDetailOrder.setDisable(true);
    	FadeTransition transfade = new FadeTransition(Duration.seconds(1), tbViewLobbyManager);
    	if (tbViewOrderSummary.getSelectionModel().isEmpty()) {
    		transfade.setFromValue(.5);
            transfade.setToValue(.9);
            transfade.setCycleCount(Animation.INDEFINITE);
            transfade.setAutoReverse(true);
            transfade.play();
    	}
    
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	ArrayList<OrderWedding> arr = OrderWeddingModel.getAllOrderWedding();
		    	arrOrder = FXCollections.observableArrayList(arr);
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			tbViewOrderSummary.setItems(arrOrder);
		  	transfade.stop();
		  	tbViewOrderSummary.setOpacity(1);
		  
		});
		new Thread(task).start();
    }
    
    @FXML
    void ShowDetailOrder(ActionEvent event) {
    	HolderManager holderManager = HolderManager.getInstance();
    	if (tbViewOrderSummary.getSelectionModel().getSelectedItem()!=null) {
    		holderManager.setOrderWedding(tbViewOrderSummary.getSelectionModel().getSelectedItem());
    		DetailOrderWeddingScene detailOrderWeddingScene = new DetailOrderWeddingScene();
    		Stage stage = new Stage();
    		detailOrderWeddingScene.start(stage);
    	} else {
    		holderManager.AlertNotification("", "Vui lòng chọn dòng muốn xem thông tin !", 1);
    	}
    }
    private ObservableList<OrderWedding> arrOrderFilter;
    public void InitSearchOrderWedding() {
    	tfSearchOrderWedding.textProperty().addListener((observable, oldValue, newValue) -> {
    		arrOrderFilter = FXCollections.observableArrayList(
    				filterNameCus(observable.getValue())
    				
    		);
    		if (!observable.getValue().equals("")) {
    			arrOrderFilter.addAll(filterPhoneCus(observable.getValue()));
    			arrOrderFilter.addAll(filterIdWedding(observable.getValue()));
    		}

    		tbViewOrderSummary.setItems(arrOrderFilter);
    	});
    }
    @FXML TextField tfSearchOrderWedding;
    public ArrayList<OrderWedding> filterNameCus (String inputName) {
    	ArrayList<OrderWedding> result = new ArrayList<OrderWedding>();
    	
    	arrOrder.forEach(item -> {
    		if (item.getNameCus().toUpperCase().indexOf(inputName.toUpperCase())>-1) {
    			result.add(item);
    		}
    	});
    	
    	return result;
    }
    
    public ArrayList<OrderWedding> filterPhoneCus (String inputID) {
    	ArrayList<OrderWedding> result = new ArrayList<OrderWedding>();
    	
    	arrOrder.forEach(item -> {
    		if (item.getPhoneCus().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (OrderWedding item1 : arrOrderFilter) {
					if (item1.getIdWedding().equals(item.getIdWedding())) {
						kt=false;
						break;
					}
				}
    			if (kt) result.add(item);
    		}
    	});
    	
    	return result;
    }
    public ArrayList<OrderWedding> filterIdWedding (String inputID) {
    	ArrayList<OrderWedding> result = new ArrayList<OrderWedding>();
    	
    	arrOrder.forEach(item -> {
    		if (item.getIdWedding().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (OrderWedding item1 : arrOrderFilter) {
					if (item1.getIdWedding().equals(item.getIdWedding())) {
						kt=false;
						break;
					}
				}
    			if (kt) result.add(item);
    		}
    	});
    	
    	return result;
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
    private TableColumn<Lobby,String> lobbyTablePriceColumn;
    @FXML
    private TableColumn<Lobby,String> lobbyPriceColumn;
    
    public void ViewLobbyColumn() throws SQLException {
    	lobbyIdColumn.setCellValueFactory(new PropertyValueFactory<Lobby,String>("id"));
    	lobbyNameColumn.setCellValueFactory(new PropertyValueFactory<Lobby,String>("name"));
     	lobbyTypeColumn.setCellValueFactory(new PropertyValueFactory<Lobby,String>("type"));
     	lobbyTableColumn.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("tableNumber"));
     	lobbyTablePriceColumn.setCellValueFactory(new PropertyValueFactory<Lobby,String>("priceShowTable"));
     	lobbyPriceColumn.setCellValueFactory(new PropertyValueFactory<Lobby,String>("priceShowLobby"));
     	InitSearchLobby() ;
     	if (pow)
     	tbViewLobbyManager.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
     		btnUpdateLobby.setDisable(false);
     		btnDeleteLobby.setDisable(false);
    	});
    }
    private ObservableList<Lobby> arrLobby;
    public void ViewLobbyTbView() throws SQLException {
    	tbViewLobbyManager.getSelectionModel().clearSelection();
    	btnUpdateLobby.setDisable(true);
 		btnDeleteLobby.setDisable(true);
    	FadeTransition transfade = new FadeTransition(Duration.seconds(1), tbViewLobbyManager);
    	if (tbViewLobbyManager.getSelectionModel().isEmpty()) {
    		transfade.setFromValue(.5);
            transfade.setToValue(.9);
            transfade.setCycleCount(Animation.INDEFINITE);
            transfade.setAutoReverse(true);
            transfade.play();
    	}
    
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	ArrayList<Lobby> arr = LobbyModel.getAllLobby();
		    	arrLobby = FXCollections.observableArrayList(arr);
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
		  	tbViewLobbyManager.setItems(arrLobby);
		  	transfade.stop();
		  	tbViewLobbyManager.setOpacity(1);
		});
		new Thread(task).start();

	
    }
    
    @FXML
    public void PressAddLobby(ActionEvent event) {
    	AddLobbyUI addLobbySceneAddLobbyUI = new AddLobbyUI();
    	Stage stage = new Stage();
    	addLobbySceneAddLobbyUI.start(stage);
    }
    
    @FXML
    void onActionButtonLobby(ActionEvent event) throws SQLException {
    	
    	Lobby selectedLobby  = tbViewLobbyManager.getSelectionModel().getSelectedItem();
    	HolderManager holderManager = HolderManager.getInstance();
    	holderManager.setLobby(selectedLobby);
    	if (selectedLobby==null) {
    		holderManager.AlertNotification(" ", "Chọn một sảnh để thực hiện hành động ", 1);
    	} else {
    		if (event.getSource()==btnDeleteLobby) {
    			holderManager.AlertNotification("deleteLobby","Bạn chắc chắn muốn xóa sảnh này ?", 0);
        	} else 
        	if (event.getSource()==btnUpdateLobby) {
        		HolderManager lobbyHolder = HolderManager.getInstance();
        		lobbyHolder.setLobby(selectedLobby);
        		
        		UpdateLobbyScene updateLobbyScene = new UpdateLobbyScene();
        		Stage stage = new Stage();
        		updateLobbyScene.start(stage);
        	}
        	
    	}
    }
    private ObservableList<Lobby> arrLobbyFilter;
    public void InitSearchLobby() {
    	tfSearchLobby.textProperty().addListener((observable, oldValue, newValue) -> {
    		arrLobbyFilter = FXCollections.observableArrayList(
    				filterNameLobby(observable.getValue())
    				
    		);
    		if (!observable.getValue().equals("")) {
    			arrLobbyFilter.addAll(filterIDLobby(observable.getValue()));
    			arrLobbyFilter.addAll(filterTypeLobby(observable.getValue()));
    		}

    		tbViewLobbyManager.setItems(arrLobbyFilter);
    	});
    }
    @FXML TextField tfSearchLobby;
    public ArrayList<Lobby> filterNameLobby (String inputName) {
    	ArrayList<Lobby> result = new ArrayList<Lobby>();
    	
    	arrLobby.forEach(item -> {
    		if (item.getName().toUpperCase().indexOf(inputName.toUpperCase())>-1) {
    			result.add(item);
    		}
    	});
    	
    	return result;
    }
    public ArrayList<Lobby> filterIDLobby (String inputID) {
    	ArrayList<Lobby> result = new ArrayList<Lobby>();
    	
    	arrLobby.forEach(item -> {
    		if (item.getId().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Lobby item1 : arrLobbyFilter) {
					if (item1.getId().equals(item.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) result.add(item);
    		}
    	});
    	
    	return result;
    }
    public ArrayList<Lobby> filterTypeLobby (String inputID) {
    	ArrayList<Lobby> result = new ArrayList<Lobby>();
    	
    	arrLobby.forEach(item -> {
    		if (item.getType().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Lobby item1 : arrLobbyFilter) {
					if (item1.getId().equals(item.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) result.add(item);
    		}
    	});
    	
    	return result;
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
    private TableColumn<Food,String> foodPriceColumn;
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
    	foodPriceColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("priceShow"));
    	foodTypeColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("type"));
    	InitSearchFood() ;
    	if (pow)
    	tbViewFood.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
     		btnUpdateFood.setDisable(false);
     		btnDeleteFood.setDisable(false);
    	});
    }
    private ObservableList<Food> arrFood;
    public void ViewFoodTbView() throws SQLException {
    	tbViewFood.getSelectionModel().clearSelection();
    	btnUpdateFood.setDisable(true);
    	btnDeleteFood.setDisable(true);
    	
    	FadeTransition transfade = new FadeTransition(Duration.seconds(1), tbViewFood);
    	if (tbViewFood.getSelectionModel().isEmpty()) {
    		transfade.setFromValue(.5);
            transfade.setToValue(.9);
            transfade.setCycleCount(Animation.INDEFINITE);
            transfade.setAutoReverse(true);
            transfade.play();
    	}
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	ArrayList<Food> arr = FoodModel.getAllFood();	    	
		    	arrFood = FXCollections.observableArrayList(arr);
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
    	 	tbViewFood.setItems(arrFood);
    		transfade.stop();
        	tbViewFood.setOpacity(1);
        	
		});
		new Thread(task).start();

    	
    }
    
    public void OnActionButtonFood(ActionEvent event) throws SQLException {
    	Food selectedFood  = tbViewFood.getSelectionModel().getSelectedItem();
		HolderManager holderManager = HolderManager.getInstance();
		holderManager.setFood(selectedFood);
    	if (selectedFood==null) {
    		holderManager.AlertNotification(" ", "Chọn một món ăn để thực hiện hành động ", 1);
    	} else {
    		if (event.getSource()==btnDeleteFood) {
    			holderManager.AlertNotification("deleteFood","Bạn có chắc chắn xóa món ăn này ?", 0);
        	}
        	if (event.getSource()==btnUpdateFood) {
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
    private ObservableList<Food> arrFoodFilter;
    public void InitSearchFood() {
    	tfSearchFood.textProperty().addListener((observable, oldValue, newValue) -> {
    		arrFoodFilter = FXCollections.observableArrayList(
    				filterNameFood(observable.getValue())
    				
    		);
    		if (!observable.getValue().equals("")) {
    			arrFoodFilter.addAll(filterIDFood(observable.getValue()));
    			arrFoodFilter.addAll(filterType(observable.getValue()));
    		}

    		tbViewFood.setItems(arrFoodFilter);
    	});
    }
    @FXML TextField tfSearchFood;
    public ArrayList<Food> filterNameFood (String inputName) {
    	ArrayList<Food> resultFoods = new ArrayList<Food>();
    	
    	arrFood.forEach(food -> {
    		if (food.getName().toUpperCase().indexOf(inputName.toUpperCase())>-1) {
    			resultFoods.add(food);
    		}
    	});
    	
    	return resultFoods;
    }
    
    public ArrayList<Food> filterIDFood (String inputID) {
    	ArrayList<Food> resultFoods = new ArrayList<Food>();
    	
    	arrFood.forEach(food -> {
    		if (food.getId().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Food item : arrFoodFilter) {
					if (food.getId().equals(item.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultFoods.add(food);
    		}
    	});
    	
    	return resultFoods;
    }
    public ArrayList<Food> filterType (String input) {
    	ArrayList<Food> resultFoods = new ArrayList<Food>();
    	
    	arrFood.forEach(food -> {
    		if (food.getType().toUpperCase().indexOf(input.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Food stff : arrFoodFilter) {
					if (food.getId().equals(stff.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultFoods.add(food);
    		}
    	});
    	
    	return resultFoods;
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
    private TableColumn<ServiceWedding,String> servicePriceColumn;
    @FXML
    private Button btnAddService;
    @FXML
    private Button btnDeleteService;
    @FXML
    private Button btnUpdateService;
    
    private void ViewServiceColumn() {
    	serviceIdColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("id"));
    	serviceNameColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("name"));
    	servicePriceColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("priceShow"));
    	InitSearchService();
    	if (pow)
    	tbViewService.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
     		btnUpdateService.setDisable(false);
     		btnDeleteService.setDisable(false);
    	});
    }
    private ObservableList<ServiceWedding> arrService;
    public void ViewServiceTbView() throws SQLException {
    	tbViewService.getSelectionModel().clearSelection();
    	btnUpdateService.setDisable(true);
    	btnDeleteService.setDisable(true);
    	FadeTransition transfade = new FadeTransition(Duration.seconds(1), tbViewService);
    	if (tbViewService.getSelectionModel().isEmpty()) {
    		transfade.setFromValue(.5);
            transfade.setToValue(.9);
            transfade.setCycleCount(Animation.INDEFINITE);
            transfade.setAutoReverse(true);
            transfade.play();
    	}
 
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	ArrayList<ServiceWedding> arr = ServiceModel.getAllService();
		    	arrService = FXCollections.observableArrayList(arr);
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
	    	tbViewService.setItems(arrService);
	    	tbViewService.setOpacity(1);
	    	transfade.stop();
		});
		new Thread(task).start();

    }
    @FXML
    public void OnPressServiceBtn(ActionEvent event) throws SQLException {
  		
  		HolderManager holderManager = HolderManager.getInstance();
    	if (event.getSource()==btnAddService) {
    		AddServiceScene addServiceScene = new AddServiceScene();
        	Stage stage = new Stage();
        	addServiceScene.start(stage);
    	}
    	else {
    		ServiceWedding selectService = tbViewService.getSelectionModel().getSelectedItem();
        	HolderManager serviceHolder = HolderManager.getInstance();
        	serviceHolder.setService(selectService);
        	if (selectService==null) {
        		holderManager.AlertNotification(" ", "Chọn một dịch vụ để thực hiện hành động ", 1);
        	} else {
        		if (event.getSource()==btnUpdateService) {
            		
            		UpdateServiceScene updateServiceScene = new UpdateServiceScene();
                	Stage stage = new Stage();
                	updateServiceScene.start(stage);
            	} 
            	if (event.getSource()==btnDeleteService) {
            		holderManager.AlertNotification("deleteService","Bạn có chắc chắn xóa dịch vụ này ?", 0);
            	}
        	}
    	} 
    }
    private ObservableList<ServiceWedding> arrServiceFilter;
    public void InitSearchService() {
    	tfSearchService.textProperty().addListener((observable, oldValue, newValue) -> {
    		arrServiceFilter = FXCollections.observableArrayList(
    				filterNameService(observable.getValue())
    				
    		);
    		if (!observable.getValue().equals("")) {
    			arrServiceFilter.addAll(filterIDService(observable.getValue()));
    		}

    		tbViewService.setItems(arrServiceFilter);
    	});
    }
    @FXML TextField tfSearchService;
    public ArrayList<ServiceWedding> filterNameService (String inputName) {
    	ArrayList<ServiceWedding> result = new ArrayList<ServiceWedding>();
    	
    	arrService.forEach(item -> {
    		if (item.getName().toUpperCase().indexOf(inputName.toUpperCase())>-1) {
    			result.add(item);
    		}
    	});
    	
    	return result;
    }
    
    public ArrayList<ServiceWedding> filterIDService (String inputID) {
    	ArrayList<ServiceWedding> result = new ArrayList<ServiceWedding>();
    	
    	arrService.forEach(item -> {
    		if (item.getId().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (ServiceWedding item1 : arrServiceFilter) {
					if (item1.getId().equals(item.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) result.add(item);
    		}
    	});
    	
    	return result;
    }
  
    /*********** END SERVICE MANAGER CONTROLLER *********/
    
    /***********CUSTOMER CONTROLLER*******/
    @FXML
    private TableView<Customer> tbViewCustomer;
    @FXML
    private TableColumn<Customer,String> cusIDColumn;
    @FXML
    private TableColumn<Customer,String> cusNameColumn;
    @FXML
    private TableColumn<Customer,String> cusPhoneNumberColumn;
    @FXML
    private TableColumn<Customer,String> cusMoneyColumn;
    @FXML
    private TableColumn<Customer,String> cusDiscountColumn;
    
    void ViewCustomerColumn() {
    	cusIDColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
    	cusNameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
    	cusPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("phone"));
    	cusMoneyColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("money"));
    	cusDiscountColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("discount"));
    	InitSearchCustomer();
    	tbViewCustomer.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
     	
    	});
    }
    private ObservableList<Customer> arrCustomer;
    public void ViewCustomerTbView() throws SQLException {
    	FadeTransition transfade = new FadeTransition(Duration.seconds(1), tbViewCustomer);
    	if (tbViewCustomer.getSelectionModel().isEmpty()) {
    		transfade.setFromValue(.5);
            transfade.setToValue(.9);
            transfade.setCycleCount(Animation.INDEFINITE);
            transfade.setAutoReverse(true);
            transfade.play();
    	}
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	arrCustomer = FXCollections.observableArrayList(CustomerModel.getAllCus()) ;
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			transfade.stop();
			tbViewCustomer.setOpacity(1);
	    	tbViewCustomer.setItems(arrCustomer);
		});
		new Thread(task).start();


    }
    
    private ObservableList<Customer> arrCusFilter;
    public void InitSearchCustomer() {
    	tfSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
    		arrCusFilter = FXCollections.observableArrayList(
    				filterNameCustomer(observable.getValue())
    				
    		);
    		if (!observable.getValue().equals("")) {
    			arrCusFilter.addAll(filterPhoneCustomer(observable.getValue()));
    			arrCusFilter.addAll(filterIdCustomer(observable.getValue()));
    		}

    		tbViewCustomer.setItems(arrCusFilter);
    	});
    }
    @FXML TextField tfSearchCustomer;
    public ArrayList<Customer> filterNameCustomer (String inputName) {
    	ArrayList<Customer> result = new ArrayList<Customer>();
    	
    	arrCustomer.forEach(item -> {
    		if (item.getName().toUpperCase().indexOf(inputName.toUpperCase())>-1) {
    			result.add(item);
    		}
    	});
    	
    	return result;
    }
    
    public ArrayList<Customer> filterPhoneCustomer (String inputID) {
    	ArrayList<Customer> result = new ArrayList<Customer>();
    	
    	arrCustomer.forEach(item -> {
    		if (item.getPhone().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Customer item1 : arrCusFilter) {
					if (item1.getId().equals(item.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) result.add(item);
    		}
    	});
    	
    	return result;
    }
    public ArrayList<Customer> filterIdCustomer (String inputID) {
    	ArrayList<Customer> result = new ArrayList<Customer>();
    	
    	arrCustomer.forEach(item -> {
    		if (item.getId().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Customer item1 : arrCusFilter) {
					if (item1.getId().equals(item.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) result.add(item);
    		}
    	});
    	
    	return result;
    }
    /***********BILL CONTROLLER**********/

    @FXML
    private TextField tfSearchBill;
    @FXML
    private TableView<Bill> tbViewBill;
    @FXML
    private TableColumn<Bill,String> billIDColumn;
    @FXML
    private TableColumn<Bill,String> billIDStaffColumn;
    @FXML
    private TableColumn<Bill,String> billIDCustomerColumn;
    @FXML
    private TableColumn<Bill,String> billIDWeddingColumn;
    @FXML
    private TableColumn<Bill,Number> billMoneyColumn;
    @FXML
    private TableColumn<Bill,String> dateOfPayColumn;
    @FXML
    private Label numberOrderSum;
    @FXML
    private Label moneyOrderSum;
    @FXML
    private Button btnShowBill;
    ObservableList<Bill> arrBills;  
    ObservableList<Bill> arrBillFilter;
    void ViewBillColumn() {
    	billIDColumn.setCellValueFactory(new PropertyValueFactory<Bill,String>("idBill"));
    	billIDStaffColumn.setCellValueFactory(new PropertyValueFactory<Bill,String>("idStaff"));
    	billIDCustomerColumn.setCellValueFactory(new PropertyValueFactory<Bill,String>("idCustomer"));
    	billIDWeddingColumn.setCellValueFactory(new PropertyValueFactory<Bill,String>("idWedding"));
    	billMoneyColumn.setCellValueFactory(new PropertyValueFactory<Bill,Number>("money"));
    	dateOfPayColumn.setCellValueFactory(new PropertyValueFactory<Bill,String>("dateOfPay"));
    	InitSearchBill();
    	tbViewBill.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    		btnShowBill.setDisable(false);
    	});
    }

	public void ViewBillTbView() throws SQLException {
		tbViewBill.getSelectionModel().clearSelection();
		btnShowBill.setDisable(true);
    	FadeTransition transfade = new FadeTransition(Duration.seconds(1), tbViewBill);
    	if (tbViewBill.getSelectionModel().isEmpty()) {
    		transfade.setFromValue(.5);
            transfade.setToValue(.9);
            transfade.setCycleCount(Animation.INDEFINITE);
            transfade.setAutoReverse(true);
            transfade.play();
    	}
		Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	arrBills = FXCollections.observableArrayList(BillModel.getAllBill());

		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			transfade.stop();
			tbViewBill.setOpacity(1);
	    	BigDecimal sumMoneyBigDecimal = new BigDecimal("0");
	    	for (Bill bill : arrBills) {
	    		String tmpString = bill.getMoneyNum().toString();
	    		BigDecimal tmpBigDecimal = new BigDecimal(tmpString);
	    		sumMoneyBigDecimal= sumMoneyBigDecimal.add(tmpBigDecimal);	
			}
	    	DecimalFormat formatter = new DecimalFormat("###,###,###");
			String tmpString = formatter.format(sumMoneyBigDecimal)+" VNĐ";
	    	moneyOrderSum.setText(tmpString);
	    	numberOrderSum.setText(Long.toString(arrBills.size()));
	    	tbViewBill.setItems(arrBills);
		});
		new Thread(task).start();
		

    }
    @FXML
    private ProgressIndicator billProcess;
	@FXML
	void OnSelectBill(ActionEvent event) throws SQLException, ClassNotFoundException, JRException {
		String idString = tbViewBill.getSelectionModel().getSelectedItem().getIdBill();
    	CallBill(idString);
	}

    void CallBill(String idBill) throws JRException, ClassNotFoundException, SQLException {
    	btnShowBill.setDisable(true);
    	billProcess.setVisible(true);
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	JasperDesign jDesign = JRXmlLoader.load("D:\\CourseProjects-WeddingManagement\\App\\WeddingManagementApp\\src\\application\\Bill.jrxml");
	        	JRDesignQuery updateQuery = new JRDesignQuery();
	        	updateQuery.setText("select * from bill, customer where idbill like '" + idBill + "%' AND bill.idcustomer = customer.idcustomer");
	        	jDesign.setQuery(updateQuery);
	        	JasperReport jReport = JasperCompileManager.compileReport(jDesign);
	        	JasperPrint jPrint = JasperFillManager.fillReport(jReport, null,ConnectDB.getOracleConnection());
	        	JasperViewer.viewReport(jPrint,false);
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			btnShowBill.setDisable(false);
	    	billProcess.setVisible(false);
		});
		new Thread(task).start();
    }
    
    public void InitSearchBill() {
    	tfSearchBill.textProperty().addListener((observable, oldValue, newValue) -> {
    		arrBillFilter = FXCollections.observableArrayList(
    				filterBill(observable.getValue())
    		
    		);
    		if (!observable.getValue().equals("")) {
        		arrBillFilter.addAll(filterIDBill(observable.getValue()));
        		arrBillFilter.addAll(filterIDCusBill(observable.getValue()));
        		arrBillFilter.addAll(filterIDStaffBill(observable.getValue()));
    		}

    		tbViewBill.setItems(arrBillFilter);
    	});
    }
    
    public ArrayList<Bill> filterBill (String inputName) {
    	ArrayList<Bill> resultStaffs = new ArrayList<Bill>();
    	
    	arrBills.forEach(item -> {
    		if (item.getIdWedding().toUpperCase().indexOf(inputName.toUpperCase())>-1) {
    			resultStaffs.add(item);
    		}
    	});
    	
    	return resultStaffs;
    }
    
    public ArrayList<Bill> filterIDBill (String inputID) {
    	ArrayList<Bill> resultStaffs = new ArrayList<Bill>();
    	
    	arrBills.forEach(item -> {
    		if (item.getIdBill().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Bill stff : arrBillFilter) {
					if (item.getIdBill().equals(stff.getIdBill())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultStaffs.add(item);
    		}
    	});
    	
    	return resultStaffs;
    }
    public ArrayList<Bill> filterIDCusBill (String input) {
    	ArrayList<Bill> resultStaffs = new ArrayList<Bill>();
    	
    	arrBills.forEach(item -> {
    		if (item.getIdCustomer().toUpperCase().indexOf(input.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Bill stff : arrBillFilter) {
					if (item.getIdBill().equals(stff.getIdBill())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultStaffs.add(item);
    		}
    	});
    	
    	return resultStaffs;
    }
    public ArrayList<Bill> filterIDStaffBill (String input) {
    	ArrayList<Bill> resultStaffs = new ArrayList<Bill>();
    	
    	arrBills.forEach(item -> {
    		if (item.getIdStaff().toUpperCase().indexOf(input.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Bill stff : arrBillFilter) {
					if (item.getIdBill().equals(stff.getIdBill())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultStaffs.add(item);
    		}
    	});
    	
    	return resultStaffs;
    }
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
  		HolderManager holderManager = HolderManager.getInstance();
    	if (event.getSource()==btnStaffDelete) {
    		if (selectStaff==null) {
    			holderManager.AlertNotification(" ", "Chọn một nhân viên để thực hiện hành động ", 1);
    		} else {
    			StaffHolder holder = StaffHolder.getInstance();
    	   		holder.setStaffSelect(selectStaff);
    	   		holderManager.AlertNotification("deleteStaff","Bạn có chắc chắn với hành động này khong",0);    			
    		}
    	} else
    	if (event.getSource()==btnStaffUpdate) {
    		if (selectStaff==null) {
    			holderManager.AlertNotification(" ", "Chọn một nhân viên để thực hiện hành động ", 1);
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
    	InitSearchStaff();
    	StaffHolder holder = StaffHolder.getInstance();
		staffTbView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (obs.getValue().getType().equals("quản lý") && holder.getStaff().getType().equals("quản lý")) {
				btnStaffUpdate.setDisable(true);
				btnStaffDelete.setDisable(true);
				btnChangePass.setDisable(true);
			} else {
				btnStaffUpdate.setDisable(false);
				btnStaffDelete.setDisable(false);
				btnChangePass.setDisable(false);
			}

    	});

	}
    ArrayList<Staff> arr;
    private int countQL, countLT, countNVK;
    @FXML
    private Button btnChangePass;
    public void updateStaffTView() throws SQLException {
    	staffTbView.getSelectionModel().clearSelection();
    	btnStaffUpdate.setDisable(true);
		btnStaffDelete.setDisable(true);
		btnChangePass.setDisable(true);
    	if (staffTbView.getSelectionModel().isEmpty()) {
           	processTbView.setVisible(true);
           	staffTbView.setOpacity(.5);
    	}

    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	arr = StaffModel.getAllStaff();
		    	allStaff = arr;
		    	staffTbView.getItems().clear();
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			setTbView(arr);	
			for (Staff staff : arr) {
				if (staff.getType().equals("quản lý")) countQL++;
				else if (staff.getType().equals("nhân viên lễ tân")) countLT++;
				else countNVK++;
			}
			processTbView.setVisible(false);
           	staffTbView.setOpacity(1);
		});
		new Thread(task).start();
    	
    	
    }
    public void InitSearchStaff() {
    	tfSearchStaff.textProperty().addListener((observable, oldValue, newValue) -> {
    		arrStaff = FXCollections.observableArrayList(
    				filterStaff(observable.getValue())
    				
    		);
    		if (!observable.getValue().equals("")) {
        		arrStaff.addAll(filterIDStaff(observable.getValue()));
        		arrStaff.addAll(filterPhoneStaff(observable.getValue()));
        		arrStaff.addAll(filterIdentityCardStaff(observable.getValue()));
        		arrStaff.addAll(filterTypeStaff(observable.getValue()));
    		}

    		staffTbView.setItems(arrStaff);
    	});
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
    
    public ArrayList<Staff> filterIDStaff (String inputID) {
    	ArrayList<Staff> resultStaffs = new ArrayList<Staff>();
    	
    	allStaff.forEach(staff -> {
    		if (staff.getId().toUpperCase().indexOf(inputID.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Staff stff : arrStaff) {
					if (staff.getId().equals(stff.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultStaffs.add(staff);
    		}
    	});
    	
    	return resultStaffs;
    }
    public ArrayList<Staff> filterPhoneStaff (String input) {
    	ArrayList<Staff> resultStaffs = new ArrayList<Staff>();
    	
    	allStaff.forEach(staff -> {
    		if (staff.getPhoneNumber().toUpperCase().indexOf(input.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Staff stff : arrStaff) {
					if (staff.getId().equals(stff.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultStaffs.add(staff);
    		}
    	});
    	
    	return resultStaffs;
    }
    public ArrayList<Staff> filterIdentityCardStaff (String input) {
    	ArrayList<Staff> resultStaffs = new ArrayList<Staff>();
    	
    	allStaff.forEach(staff -> {
    		if (staff.getIdentityCard().toUpperCase().indexOf(input.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Staff stff : arrStaff) {
					if (staff.getId().equals(stff.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultStaffs.add(staff);
    		}
    	});
    	
    	return resultStaffs;
    }
    public ArrayList<Staff> filterTypeStaff (String input) {
    	ArrayList<Staff> resultStaffs = new ArrayList<Staff>();
    	
    	allStaff.forEach(staff -> {
    		if (staff.getType().toUpperCase().indexOf(input.toUpperCase())>-1) {
    			boolean kt = true;
    			for (Staff stff : arrStaff) {
					if (staff.getId().equals(stff.getId())) {
						kt=false;
						break;
					}
				}
    			if (kt) resultStaffs.add(staff);
    		}
    	});
    	
    	return resultStaffs;
    }
    public void setTbView (ArrayList<Staff> arrayStaff) {
    	arrStaff = FXCollections.observableArrayList(arrayStaff);
    	staffTbView.setItems(arrStaff);
    	
    }
    
    @FXML
    void resetPassword() throws SQLException {
    	Staff selectStaff = staffTbView.getSelectionModel().getSelectedItem();
    	HolderManager holderManager = HolderManager.getInstance();
    	if (selectStaff==null) {
    		holderManager.AlertNotification(" ", "Chọn một nhân viên để thực hiện hành động ", 1);
		} else {
			StaffHolder holder = StaffHolder.getInstance();
    		holder.setStaffSelect(selectStaff);
    		holderManager.AlertNotification("resetPassword", "Bạn có chắc chắn với hành động đặt lại mật khẩu cho nhân viên này không ?",0);
		}
    }
    
    /***********End Staff controller *************/

    /***********Report controller *************/
//    @FXML
//    private CategoryAxis xRevenue;
//    @FXML
//    private NumberAxis yRevenue;
//    @FXML
//    private LineChart< ? ,? > revenue;
//    @FXML
//    private LineChart<?, ?> countWedding;
//    @FXML
//    private CategoryAxis xCountWedding;
//    @FXML
//    private NumberAxis yCountWedding;
//
//   
//    void ReportChartShow() {
//    	// Doanh số theo tháng
//    	revenue.setTitle("Doanh thu theo tháng");
//    	
//    	XYChart.Series revenueSeries = new XYChart.Series();
//    	revenueSeries.getData().add(new XYChart.Data("3/2020",20000000));
//    	revenueSeries.getData().add(new XYChart.Data("4/2020",110000011));
//    	revenueSeries.getData().add(new XYChart.Data("5/2020",12310002));
//    	revenueSeries.getData().add(new XYChart.Data("6/2020",92310002));
//    	xRevenue.setLabel("Tháng");
//    	yRevenue.setLabel("VNĐ");
//    	revenue.getData().addAll(revenueSeries);
//    	
//    	// Só tiệc theo tháng
//    	countWedding.setTitle("Số tiệc theo tháng");
//    	
//    	XYChart.Series countWeddingSeries = new XYChart.Series();
//    	countWeddingSeries.getData().add(new XYChart.Data("3/2020",20));
//      	countWeddingSeries.getData().add(new XYChart.Data("4/2020",32));
//      	countWeddingSeries.getData().add(new XYChart.Data("5/2020",15));
//      	countWeddingSeries.getData().add(new XYChart.Data("6/2020",23));
//      	xCountWedding.setLabel("Tháng");
//      	yCountWedding.setLabel("VNĐ");
//      	countWedding.getData().addAll(countWeddingSeries);
//    }
//    private ObservableList<ReportRevenue> arrReport;
//    @FXML
//    private TableView<ReportRevenue> tbViewReport;
//    @FXML
//    private TableColumn<ReportRevenue, Number> reportIDColumn;
//    @FXML
//    private TableColumn<ReportRevenue, String> reportMonthColumn;
//    @FXML
//    private TableColumn<ReportRevenue, Number> reportCountWeddingColumn;
//    @FXML
//    private TableColumn<ReportRevenue, Number> reportRevenueColumn;
    ArrayList<String> reportTime;
    public void ReportTbViewShow() throws SQLException {
    	
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		   
	    	
		    	reportTime = ReportModel.getAllReport();

		    	
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			monthFrom.setItems(listMonthFrom);
	    	monthTo.setItems(listMonthFrom);
	    	monthFrom.getSelectionModel().select(0);
	    	monthTo.getSelectionModel().select(11);
	    	for (String item : reportTime) {
	    		boolean kt = true;
	    		for (String item1 : listYearFrom) {
					if (item1.equals(item.substring(0,4))) {
						kt = false;
						break;
					}
				}
	    		if (kt)	listYearFrom.add(item.substring(0,4));
			}
	    	yearFrom.setItems(listYearFrom);
	    	yearTo.setItems(listYearFrom);
	    	yearFrom.getSelectionModel().select(0);
	    	yearTo.getSelectionModel().select(listYearFrom.size()-1);
	     	
		});
		new Thread(task).start();
    }
    @FXML
    private Label timeWarning;
    @FXML
    private ComboBox<String> yearFrom;
    @FXML
    private ComboBox<String> monthFrom;
    @FXML
    private ComboBox<String> yearTo;
    @FXML
    private ComboBox<String> monthTo;
    @FXML
    private AnchorPane reportLoading;
    ObservableList<String> listMonthFrom = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String> listYearFrom = FXCollections.observableArrayList();
    @FXML
    void CallReport(ActionEvent event) throws JRException, ClassNotFoundException, SQLException {
    	if (ValidateTimeReport().equals("true")) {
    		timeWarning.setVisible(false);
    		reportLoading.setVisible(true);
        	Task<Void> task = new Task<Void>() {
    		    @Override
    		    public Void call() throws Exception {
    	    		JasperDesign jDesign = JRXmlLoader.load("D:\\CourseProjects-WeddingManagement\\App\\WeddingManagementApp\\src\\application\\Report.jrxml");
    	        	JRDesignQuery updateQuery = new JRDesignQuery();
    	        	
    	        	String tmpDate = "01"+ getMonthName(monthFrom.getValue()) + yearFrom.getValue();
    	        	String tmpDate1 = "28"+ getMonthName(monthTo.getValue())+ yearTo.getValue();
    	          	updateQuery.setText("SELECT * FROM report where closingdate >= to_date('"+tmpDate+"','DD-MON-YY') and  closingdate <= to_date('"+tmpDate1 +"','DD-MON-YY') order by closingdate asc");
    	        	jDesign.setQuery(updateQuery);
    	        	JasperReport jReport = JasperCompileManager.compileReport(jDesign);
    	        	JasperPrint jPrint = JasperFillManager.fillReport(jReport, null,ConnectDB.getOracleConnection());
    	        	JasperViewer.viewReport(jPrint,false);
    		        return null ;
    		    }
    		};
    		task.setOnSucceeded(e -> {
    			reportLoading.setVisible(false);
    		});
    		new Thread(task).start();
    	} else {
    		timeWarning.setVisible(true);
    	}

    }
    
    String ValidateTimeReport() {
    	if (Long.parseLong(yearFrom.getValue()) > Long.parseLong(yearTo.getValue())) return "false";
    	else if (Long.parseLong(yearFrom.getValue()) == Long.parseLong(yearTo.getValue())) 
    		if (Long.parseLong(monthFrom.getValue())>=Long.parseLong(monthTo.getValue())) return "false";
    	return "true";
    }
    
    private String getMonthName(String month) {

		String monthName = null;
		switch (month) {
		case "1":
			monthName = "JAN";
			break;
		case "2":
			monthName = "FEB";
			break;
		case "3":
			monthName = "MAR";
			break;
		case "4":
			monthName = "APR";
			break;
		case "5":
			monthName = "MAY";
			break;
		case "6":
			monthName = "JUN";
			break;
		case "7":
			monthName = "JUL";
			break;
		case "8":
			monthName = "AUG";
			break;
		case "9":
			monthName = "SEP";
			break;
		case "10":
			monthName = "OCT";
			break;
		case "11":
			monthName = "NOV";
			break;
		case "12":
			monthName = "DEC";
			break;
		}

		return monthName;
	}
    
    @FXML
    void Top5Customer(ActionEvent event) throws JRException, ClassNotFoundException, SQLException {
    	reportLoading.setVisible(true);
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	JasperDesign jDesign = JRXmlLoader.load("D:\\CourseProjects-WeddingManagement\\App\\WeddingManagementApp\\src\\application\\Top5Customer.jrxml");
	        	JRDesignQuery updateQuery = new JRDesignQuery();
	        	updateQuery.setText("SELECT * FROM customer where ROWNUM <= 5 order by Money DESC");
	        	jDesign.setQuery(updateQuery);
	        	JasperReport jReport = JasperCompileManager.compileReport(jDesign);
	        	JasperPrint jPrint = JasperFillManager.fillReport(jReport, null,ConnectDB.getOracleConnection());
	        	JasperViewer.viewReport(jPrint,false);
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			reportLoading.setVisible(false);
		});
		new Thread(task).start();
    }
    @FXML
    void TopLobby(ActionEvent event) throws JRException, ClassNotFoundException, SQLException {
    	reportLoading.setVisible(true);
    	Task<Void> task = new Task<Void>() {
		    @Override
		    public Void call() throws Exception {
		    	JasperDesign jDesign = JRXmlLoader.load("D:\\CourseProjects-WeddingManagement\\App\\WeddingManagementApp\\src\\application\\Lobby.jrxml");
	        	JRDesignQuery updateQuery = new JRDesignQuery();
	        	updateQuery.setText("select Lobb.idLobby, Lobb.nameLobby, count(*) as SO_LUONG_DA_TUNG_DAT from OrderWedding Ord, Lobby Lobb where Ord.idLobby = Lobb.idLobby group by Lobb.idLobby, Lobb.nameLobby order by SO_LUONG_DA_TUNG_DAT desc");
	        	jDesign.setQuery(updateQuery);
	        	JasperReport jReport = JasperCompileManager.compileReport(jDesign);
	        	JasperPrint jPrint = JasperFillManager.fillReport(jReport, null,ConnectDB.getOracleConnection());
	        	JasperViewer.viewReport(jPrint,false);
		        return null ;
		    }
		};
		task.setOnSucceeded(e -> {
			reportLoading.setVisible(false);
		});
		new Thread(task).start();
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
    
    @FXML
    void onChangePassword(ActionEvent event) {
    	ChangePassScene changePassScene = new ChangePassScene();
    	Stage stage = new Stage();
    	changePassScene.start(stage);
    }
    
    /***********End Info controller *************/
}




















