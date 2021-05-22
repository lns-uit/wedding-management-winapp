package application;

import java.awt.print.Printable;
import java.net.SecureCacheResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.xml.ws.Holder;

import com.sun.corba.se.pept.transport.EventHandler;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import oracle.security.o3logon.a;


public class AddWeddingOrderController {

    @FXML
    private AnchorPane step1;
    @FXML
    private AnchorPane step2;
    @FXML
    private AnchorPane step3;
    /****************ORDER LOBBY**************/
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
    private TableColumn<Lobby,Number> orderLobbyPriceTable;
    @FXML
    private TableColumn<Lobby,Number> orderLobbyPrice;
    @FXML
    private TableColumn<Lobby,String> orderLobbyNote;
    @FXML
    private TableColumn<Lobby,CheckBox> orderLobbySelect;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @FXML
    void initialize() {
		ViewLobbyColumn();
		ViewLobbyTbView();
		datePkStart.setValue(LocalDate.now().plusDays(2));
		InitDatePicker();
		ViewFoodTbView();
		UpdateFoodTbView();
		ViewServiceTbView();
		UpdateServiceTbView();
		ViewFinalTbViewAll();
	}
    void InitDatePicker() {
    	Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);

                if(item.isBefore(LocalDate.now().plusDays(2)) || item.isAfter(LocalDate.now().plusYears(1)))
                {
                    setDisable(true);              
                }
            }
        };
        StringConverter converter = new StringConverter<LocalDate>()
        {
            @Override
            public String toString(LocalDate date)
            {
                if(date != null) return dateFormatter.format(date);
                else return "";
            }

            @Override
            public LocalDate fromString(String string)
            {
                if(string != null && !string.isEmpty())
                {
                    LocalDate date = LocalDate.parse(string, dateFormatter);

                    if(date.isBefore(LocalDate.now().plusDays(2)) || date.isAfter(LocalDate.now().plusYears(1)))
                    {
                        return datePkStart.getValue();
                    }
                    else return date;
                }

                return null;
            }
        };

        datePkStart.setDayCellFactory(dayCellFactory);
        datePkStart.setConverter(converter);
        datePkStart.setPromptText("dd/MM/yyyy");
    }
    public void ViewLobbyColumn() {
    	orderLobbyID.setCellValueFactory(new PropertyValueFactory<Lobby,String>("id"));
    	orderLobbyName.setCellValueFactory(new PropertyValueFactory<Lobby,String>("name"));
    	orderLobbyType.setCellValueFactory(new PropertyValueFactory<Lobby,String>("type"));
    	orderLobbyTable.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("tableNumber"));
    	orderLobbyPriceTable.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("priceTable"));
    	orderLobbyPrice.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("priceLobby"));
    	orderLobbyNote.setCellValueFactory(new PropertyValueFactory<Lobby,String>("note"));
    	orderLobbySelect.setCellValueFactory(new PropertyValueFactory<Lobby,CheckBox>("checkBox"));
    }
    private ObservableList<Lobby> arrLobby;
    
    public void ViewLobbyTbView() {    	
    	arrLobby = FXCollections.observableArrayList(
    			new Lobby("phuc","phuc","vip",12,123,123," "),
    			new Lobby("phuc","loi","vip",12,123,123," "),
    			new Lobby("phuc","khoi","vip",1112,123,123," "),
    			new Lobby("phuc","tam","vio",12,123,123," ")
    	);
    	for (Lobby lobby : arrLobby) {
    		lobby.getCheckBox().selectedProperty().addListener(
    	  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
    	  		    	  if (!old_val) {
	       	  		    	  for (Lobby item : arrLobby) {
	       	  		        	  if (item==lobby) {
	       	  		        		  item.getCheckBox().setSelected(true); 
	       	  		        	  }
	       	  		        	  else {
	       	  		        		  item.getCheckBox().setSelected(false);
	       	  		        	  }
	       	  		    		  tbViewOrderLobby.setItems(arrLobby);
	       	  		    	  }  
    	  		    	  }	
    	  		       });
		}
    	tbViewOrderLobby.setItems(arrLobby);
    }
    /****************ORDER FOOD**************/
    @FXML
    private TableView<Food> tbViewAppetizer;
    @FXML
    private TableColumn<Food, String> aptIDColumn;
    @FXML
    private TableColumn<Food, String> aptNameColumn;
    @FXML
    private TableColumn<Food, Number> aptMoneyColumn;
    @FXML
    private TableView<Food> tbViewMainFood;
    @FXML
    private TableColumn<Food, String> mFoodIDColumn;
    @FXML
    private TableColumn<Food, String> mFoodNameColumn;
    @FXML
    private TableColumn<Food, Number> mFoodMoney;
    @FXML
    private TableView<Food> tbViewDrink;
    @FXML
    private TableColumn<Food, String> drinkIDColumn;
    @FXML
    private TableColumn<Food, String> drinkNameColumn;
    @FXML
    private TableColumn<Food, Number> drinkMoneyColumn;
    @FXML
    private TableColumn<Food, CheckBox> aptCheckBox;
    @FXML
    private TableColumn<Food, CheckBox> mFoodCheckBox;
    @FXML
    private TableColumn<Food, CheckBox> drinkCheckBox;

    public void ViewFoodTbView() {
    	aptIDColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	aptNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	aptMoneyColumn.setCellValueFactory(new PropertyValueFactory<Food,Number>("price"));
    	aptCheckBox.setCellValueFactory(new PropertyValueFactory<Food,CheckBox>("checkBox"));
    	
    	
    	mFoodIDColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	mFoodNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	mFoodMoney.setCellValueFactory(new PropertyValueFactory<Food,Number>("price"));
    	mFoodCheckBox.setCellValueFactory(new PropertyValueFactory<Food,CheckBox>("checkBox"));
    	
    	drinkIDColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	drinkNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	drinkMoneyColumn.setCellValueFactory(new PropertyValueFactory<Food,Number>("price"));
    	drinkCheckBox.setCellValueFactory(new PropertyValueFactory<Food,CheckBox>("checkBox"));
    }
    private ObservableList<Food> arrAptFood;
    private ObservableList<Food> arrMainFood;
    private ObservableList<Food> arrDrink;

    private ArrayList<Food> arrFoods = new ArrayList<Food>();
    public void UpdateFoodTbView() {
    	// Mon Khai Vi
    	arrAptFood = FXCollections.observableArrayList(
       			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi")
 
    			
    	);
    	tbViewAppetizer.setItems(arrAptFood);
    	// Mon Chinh
    	arrMainFood = FXCollections.observableArrayList(
       			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi")
 
    			
    	);
    	tbViewMainFood.setItems(arrMainFood);
    	// Trang mieng
    	arrDrink = FXCollections.observableArrayList(
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi"),
    			new Food("phuc","phuc",12,"Khaivi")
 
    			
    	);
    	tbViewDrink.setItems(arrDrink);

    	//tbViewDrink.getSelectionModel().getSelectedItem().getCheckBox().isSelected()
    	// Dich vu
    	
    }
    /****************ORDER SERVICE**************/
    @FXML
    private TableView<ServiceWedding> tbViewService;
    @FXML
    private TableColumn<ServiceWedding, String> svIDColumn;
    @FXML
    private TableColumn<ServiceWedding, String> svNameColumn;
    @FXML
    private TableColumn<ServiceWedding, Number> svMoneyColumn;
    @FXML
    private TableColumn<ServiceWedding, CheckBox> svCheckBox;
    private ObservableList<ServiceWedding> arrService;
    private ArrayList<ServiceWedding> arrServices = new ArrayList<ServiceWedding>();
    public void ViewServiceTbView() {
    	svIDColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("id"));
    	svNameColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("name"));
    	svMoneyColumn.setCellValueFactory(new PropertyValueFactory<ServiceWedding,Number>("price"));
    	svCheckBox.setCellValueFactory(new PropertyValueFactory<ServiceWedding,CheckBox>("checkBox"));
    }
    public void UpdateServiceTbView() {
    	arrService = FXCollections.observableArrayList(
    				new ServiceWedding("234", "Hmakdf", 10000),
    				new ServiceWedding("34", "Hmakdf", 10000),
    				new ServiceWedding("56", "Hmakdf", 10000),
    				new ServiceWedding("64", "Hmakdf", 10000)
    			);
    	tbViewService.setItems(arrService);
    }
    
    
    /*****************COMMIT STEP*******************/
    @FXML
    private Button btnCommit1;
    @FXML
    private Button btnCommit2;
    @FXML
    private Button btnBack1;
    @FXML
    private Button btnCommit3;
    @FXML
    private Button btnBack2;
    @FXML
    private DatePicker datePkStart;
    private AnchorPane currentPane;
    private OrderWedding currentOrderWedding = new OrderWedding();
    private Lobby currentOrderLobby = new Lobby();
    private ArrayList<OrderFood> currentOrderFood = new ArrayList<OrderFood>();
    private ArrayList<OrderServiceWedding> currentOrderService = new ArrayList<OrderServiceWedding>();
    @FXML
    public void CommitStep(ActionEvent event) {
    	if (currentPane==null) currentPane = step1;
    	if (event.getSource()==btnCommit1) {
    		
    		boolean isCheckBoxLobby=false;
    		for (Lobby lobby : tbViewOrderLobby.getItems()) {
				if (lobby.getCheckBox().isSelected()) {
					isCheckBoxLobby = true;
					currentOrderLobby = lobby;
					break;
				}
			}
    		if (!isCheckBoxLobby || datePkStart.getValue()==null) {
    			showAlertWithoutHeaderText("Vui lòng nhập đầy đủ thông tin");
    		} else {

    			currentOrderWedding.setIdLobby(currentOrderLobby.getId());
    			currentOrderWedding.setNumberOfTable(currentOrderLobby.getTableNumber());
    			currentOrderWedding.setDateStart(datePkStart.getValue().toString());
    			currentOrderWedding.setDateOrder(LocalDate.now().toString());
    			currentPane.setVisible(false);
    			currentPane = step2;
    			currentPane.setVisible(true);
    		}
    		
    		
    	} else
    	if (event.getSource()==btnCommit2) {
    		GetStep2();
    		currentPane.setVisible(false);
			currentPane = step3;
			currentPane.setVisible(true);
			UpdateFinalTbView();
		
    	} else
    	if (event.getSource()==btnCommit3) {
    		if (nameCustomer.getText().isEmpty() || phoneNumberCus.getText().isEmpty() || nameGroom.getText().isEmpty() ||nameBride.getText().isEmpty() || depositTF.getText().isEmpty()){
    			showAlertWithoutHeaderText("Vui lòng điền đầy đủ thông tin");
    		} else {
    			System.out.print("Commit");
    
    			CommitFinal();
    		}
    	} else
    	if (event.getSource()==btnBack1) {
    		currentPane.setVisible(false);
			currentPane = step1;
			currentPane.setVisible(true);
    	} else
    	if (event.getSource()==btnBack2) {
    		currentPane.setVisible(false);
			currentPane = step2;
			currentPane.setVisible(true);
    	}
    }
    public void GetStep2() {
    	arrFoods.clear();
    	for (Food food : tbViewAppetizer.getItems()) {
    		if (food.getCheckBox().isSelected()) arrFoods.add(food);
		}
    	for (Food food : tbViewMainFood.getItems()) {
			if (food.getCheckBox().isSelected()) arrFoods.add(food);
		}
    	for (Food food : tbViewDrink.getItems()) {
    		if (food.getCheckBox().isSelected()) arrFoods.add(food);
		}
    	arrServices.clear();
    	for (ServiceWedding sv : tbViewService.getItems()) {
    		if (sv.getCheckBox().isSelected()) arrServices.add(sv);
			
		}
    }
    /************ Summary TableView ***********/
    @FXML
    private TextField nameCustomer;
    @FXML
    private TextField phoneNumberCus;
    @FXML
    private TextField nameGroom;
    @FXML
    private TextField nameBride;
    @FXML
    private TableView<Food> foodFinal;
    @FXML
    private TableColumn<Food, String> idFoodFinal;
    @FXML
    private TableColumn<Food, String> nameFoodFinal;
    @FXML
    private TableColumn<Food, Number> priceFoodFinal;
    @FXML
    private TableColumn<Food, String> typeFoodFinal;
    @FXML
    private TableView<Lobby> lobbyFinal;
    @FXML
    private TableColumn<Lobby,String> idLobbyFinal;
    @FXML
    private TableColumn<Lobby,String> nameLobbyFinal;
    @FXML
    private TableColumn<Lobby,String> typeLobbyFinal;
    @FXML
    private TableColumn<Lobby,Number> numberTableLobbyFinal;
    @FXML
    private TableColumn<Lobby,Number> priceLobbyTableFinal;
    @FXML
    private TableColumn<Lobby,Number> priceLobbyFinal;
    @FXML
    private TableColumn<Lobby,String> noteFinal;
    @FXML
    private TableView<ServiceWedding> serviceFinal;
    @FXML
    private TableColumn<ServiceWedding,String> idServiceFinal;
    @FXML
    private TableColumn<ServiceWedding,String> nameServiceFinal;
    @FXML
    private TableColumn<ServiceWedding,Number> priceServiceFinal;
    @FXML
    private DatePicker dateFinal;
    
    public void ViewFinalTbViewAll() {
    	// Lobby
    	
    	idLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,String>("id"));
    	nameLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,String>("name"));
    	typeLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,String>("type"));
    	numberTableLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("tableNumber"));
    	priceLobbyTableFinal.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("priceTable"));
    	priceLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("priceLobby"));
    	noteFinal.setCellValueFactory(new PropertyValueFactory<Lobby,String>("note"));

    	// Food
    	idFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	nameFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	priceFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,Number>("price"));
    	typeFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("price"));
    	
    	// Service
    	
    	idServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("id"));
    	nameServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("name"));
    	priceServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,Number>("price"));
    	
    	dateFinal.setValue(datePkStart.getValue());
    	
    	
    }
    public void UpdateFinalTbView() {
    	ObservableList<Lobby> lbList = FXCollections.observableArrayList(currentOrderLobby);
    	lobbyFinal.setItems(lbList);
    	
    	ObservableList<Food> fList = FXCollections.observableArrayList(arrFoods);
    	foodFinal.setItems(fList);
    	
    	ObservableList<ServiceWedding> svList = FXCollections.observableArrayList(arrServices);
    	serviceFinal.setItems(svList);
    }
    /*********** ALERT NOTIFICATION **************/
    private void showAlertWithoutHeaderText(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(message);
 
        alert.showAndWait();
	}
    /************* CALCULATE MONEY *************************/
	@FXML
	private Label moneySumLb;
	@FXML
	private TextField depositTF;
	@FXML
	private Label moneyRestLb;
	
	private long priceSum, depositOrder, restMoney;
	
    public void CalculateMoney() {
    	priceSum =  Integer.parseInt(moneySumLb.getText());
    	depositOrder = Integer.parseInt(depositTF.getText());
    	restMoney = Integer.parseInt(moneyRestLb.getText());
    	
    	long tmp = priceSum-depositOrder;
    	moneyRestLb.setText(Long.toString(tmp));
    }
    
    /************* FINAL COMMIT ******************/
    public void CommitFinal() {
    	StaffHolder holder = StaffHolder.getInstance();
		currentOrderWedding.setIdStaff(holder.getStaff().getId());
		currentOrderWedding.setNumberFood(arrFoods.size());
		// Cai duoi nay chua co thong tin
		currentOrderWedding.setIdCustomer(null);
		currentOrderWedding.setIdWedding(null);
		currentOrderWedding.setDeposit(null);
		currentOrderWedding.setNumberService(null);
		currentOrderWedding.setMoney(null);
		
		for (Food food : arrFoods) {
			currentOrderFood.add(new OrderFood(food.getId(),currentOrderWedding.getIdWedding()));
		}
		for (ServiceWedding sv : arrServices) {
			currentOrderService.add(new OrderServiceWedding(sv.getId(),currentOrderWedding.getIdWedding()));
		}
		
		System.out.print("Commit");
		// Dữ liệu commit gồm 3 arrayList: currentOrderWedding ( Kiểu OrderWedding ) - currentOrderFood ( kiểu OrderFood)  - currentOrderService ( Kiểu OrderServiceWedding ) 
		// nameCustomer; phoneNumberCus; nameGroom; nameBride; Kiểu textField
		
		System.out.println(currentOrderWedding.getNumberFood());
		System.out.print(currentOrderFood.get(0).getIdFood());

    }
    /*************** WINDOW CONTROLLER ************/
    private Stage primaryStage =  new Stage();
    private double X,Y;
    @FXML
    protected void onRectanglePressed(MouseEvent event) {
    	primaryStage = (Stage) step1.getScene().getWindow();
        X = primaryStage.getX() - event.getScreenX();
        Y = primaryStage.getY() - event.getScreenY();
    }

    @FXML
    protected void onRectangleDragged(MouseEvent event) {
    	primaryStage = (Stage) step1.getScene().getWindow();
        primaryStage.setX(event.getScreenX() + X);
        primaryStage.setY(event.getScreenY() + Y);
    }
    @FXML
    private void onPressExitWindow(ActionEvent event) {
    	primaryStage = (Stage) step1.getScene().getWindow();
    	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) step1.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
}










