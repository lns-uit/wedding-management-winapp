package application;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import oracle.net.aso.c;


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
    private TableColumn<Lobby,String> orderLobbyPriceTable;
    @FXML
    private TableColumn<Lobby,String> orderLobbyPrice;
    @FXML
    private TableColumn<Lobby,CheckBox> orderLobbySelect;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @FXML
    void initialize() throws SQLException {
		ViewLobbyColumn();
		ViewLobbyTbView();
		datePkStart.setValue(LocalDate.now().plusDays(2));
		InitDatePicker();
		ViewFoodTbView();
		UpdateFoodTbView();
		ViewServiceTbView();
		UpdateServiceTbView();
		ViewFinalTbViewAll();
		NumberTableListener();
	}
    private long NumberTableMax = 1000000;
    void NumberTableListener() {
    	numberTable.textProperty().addListener((observable, oldValue, newValue) -> {
    		long value;
    		try {
				value = Long.parseLong(newValue);
				if (NumberTableMax<value) {
					numberTable.setText(oldValue);
				}
				
			} catch (Exception e) {
				numberTable.setText(oldValue);
			}
    	});
    }
    
    ArrayList<OrderWedding> arrOrderFilter;

    void InitDatePicker() throws SQLException {
    	arrOrderFilter = OrderWeddingModel.getAllOrderWedding();
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
        datePkStart.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            for (int i=0; i<arrLobby.size(); i++) {
            	tbViewOrderLobby.getItems().get(i).getCheckBox().setDisable(false);
            	for (OrderWedding item : arrOrderFilter) {
            		if (arrLobby.get(i).getId().equals(item.getIdLobby()) && item.getDateStart().substring(0,10).equals(datePkStart.getValue().toString())){
            			tbViewOrderLobby.getItems().get(i).getCheckBox().setDisable(true);
            			break;
            		}
        		}
             }
        });
        datePkStart.setDayCellFactory(dayCellFactory);
        datePkStart.setConverter(converter);
        datePkStart.setPromptText("dd/MM/yyyy");
    }
    public void ViewLobbyColumn() {
    	orderLobbyID.setCellValueFactory(new PropertyValueFactory<Lobby,String>("id"));
    	orderLobbyName.setCellValueFactory(new PropertyValueFactory<Lobby,String>("name"));
    	orderLobbyType.setCellValueFactory(new PropertyValueFactory<Lobby,String>("type"));
    	orderLobbyTable.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("tableNumber"));
    	orderLobbyPriceTable.setCellValueFactory(new PropertyValueFactory<Lobby,String>("priceTable"));
    	orderLobbyPrice.setCellValueFactory(new PropertyValueFactory<Lobby,String>("priceLobby"));
    	orderLobbySelect.setCellValueFactory(new PropertyValueFactory<Lobby,CheckBox>("checkBox"));
    }
    private ObservableList<Lobby> arrLobby;

    public void ViewLobbyTbView() throws SQLException {    	
    	arrLobby = FXCollections.observableArrayList(
    			LobbyModel.getAllLobby()
    	);
    	for (Lobby lobby : arrLobby) {
    		lobby.getCheckBox().selectedProperty().addListener(
    	  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
    	  		    	  if (!old_val) {
	       	  		    	  for (Lobby item : arrLobby) {
	       	  		        	  if (item==lobby) {
	       	  		        		  item.getCheckBox().setSelected(true); 
	       	  		        		  NumberTableMax = item.getTableNumber().longValue();
	       	  		        		  numberTable.setText(item.getTableNumber().toString());
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
    private TableColumn<Food, String> aptMoneyColumn;
    @FXML
    private TableView<Food> tbViewMainFood;
    @FXML
    private TableColumn<Food, String> mFoodIDColumn;
    @FXML
    private TableColumn<Food, String> mFoodNameColumn;
    @FXML
    private TableColumn<Food, String> mFoodMoney;
    @FXML
    private TableView<Food> tbViewDrink;
    @FXML
    private TableColumn<Food, String> drinkIDColumn;
    @FXML
    private TableColumn<Food, String> drinkNameColumn;
    @FXML
    private TableColumn<Food, String> drinkMoneyColumn;
    @FXML
    private TableColumn<Food, CheckBox> aptCheckBox;
    @FXML
    private TableColumn<Food, CheckBox> mFoodCheckBox;
    @FXML
    private TableColumn<Food, CheckBox> drinkCheckBox;

    public void ViewFoodTbView() {
    	aptIDColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	aptNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	aptMoneyColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("price"));
    	aptCheckBox.setCellValueFactory(new PropertyValueFactory<Food,CheckBox>("checkBox"));
    	
    	
    	mFoodIDColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	mFoodNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	mFoodMoney.setCellValueFactory(new PropertyValueFactory<Food,String>("price"));
    	mFoodCheckBox.setCellValueFactory(new PropertyValueFactory<Food,CheckBox>("checkBox"));
    	
    	drinkIDColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	drinkNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	drinkMoneyColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("price"));
    	drinkCheckBox.setCellValueFactory(new PropertyValueFactory<Food,CheckBox>("checkBox"));
    }
    private ObservableList<Food> arrAptFood;
    private ObservableList<Food> arrMainFood;
    private ObservableList<Food> arrDrink;

    private ArrayList<Food> arrFoods = new ArrayList<Food>();
    public void UpdateFoodTbView() throws SQLException {
    	// Mon Khai Vi
    	arrAptFood = FXCollections.observableArrayList(
       			FoodModel.getTypeFood("khai vị")
     	);
    	tbViewAppetizer.setItems(arrAptFood);
    	// Mon Chinh
    	arrMainFood = FXCollections.observableArrayList(
    			FoodModel.getTypeFood("món chính")   			
    	);
    	tbViewMainFood.setItems(arrMainFood);
    	// Trang mieng
    	arrDrink = FXCollections.observableArrayList(
    			FoodModel.getTypeFood("tráng miệng")    			
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
    public void UpdateServiceTbView() throws SQLException {
    	arrService = FXCollections.observableArrayList(
    				ServiceModel.getAllService()
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
    private AnchorPane step4;
    @FXML
    private Label moneySum;
    @FXML
    private Label moneyRest;
    @FXML
    private Label deposit;
    @FXML
    private Button btnCommitFinal;

    @FXML
    private Button btnExitOrder;
    @FXML
    private DatePicker datePkStart;
    private AnchorPane currentPane;
    private OrderWedding currentOrderWedding = new OrderWedding();
    private Lobby currentOrderLobby = new Lobby();
    private ArrayList<OrderFood> currentOrderFood = new ArrayList<OrderFood>();
    private ArrayList<OrderServiceWedding> currentOrderService = new ArrayList<OrderServiceWedding>();
    @FXML
    public void CommitStep(ActionEvent event) throws SQLException {
    	if (currentPane==null) currentPane = step1;
    	HolderManager holderManager = HolderManager.getInstance();
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
    			holderManager.AlertNotification(" ", "Vui lòng nhập đầy đủ thông tin",1);
    		} else {

    			currentOrderWedding.setIdLobby(currentOrderLobby.getId());
//    			currentOrderWedding.setDateStart(java.sql.Date.valueOf(datePkStart.getValue()));
//    			currentOrderWedding.setDateOrder(java.sql.Date.valueOf(LocalDate.now()));
    			currentPane.setVisible(false);
    			currentPane = step2;
    			currentPane.setVisible(true);
    		}
    		
    		
    	} else
    	if (event.getSource()==btnCommit2) {
    		GetStep2();
    
		
    	} else
    	if (event.getSource()==btnCommit3) {
    		if (nameCustomer.getText().isEmpty() || phoneNumberCus.getText().isEmpty() || nameGroom.getText().isEmpty() ||nameBride.getText().isEmpty()){
    			holderManager.AlertNotification(" ", "Vui lòng nhập đầy đủ thông tin", 1);
    		} else {
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
    	} else 
    	if (event.getSource()==btnCommitFinal) {
    		
    		holderManager.AlertNotification("payOrderWedding","Xác nhận thanh toán tiền cọc ?", 0);
    	} else 
    	if (event.getSource()==btnExitOrder){
			holderManager.AlertNotification("exitOrderWedding", "Xác nhận hủy đặt tiệc này ?", 0);
		}
    	
    }
    @FXML
    private Label numberTableFinal;
    public void GetStep2() {
    	
    	
    	numberTableFinal.setText("Số bàn đã đặt: "+numberTable.getText());

    	// DATE FORMATTER
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
    	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
    	
    	dateFinal.setText("Ngày diễn ra: "+ formatter1.format(java.sql.Date.valueOf(datePkStart.getValue())));
    	
		String dateStartConvert = formatter.format(java.sql.Date.valueOf(datePkStart.getValue()));
        currentOrderWedding.setDateStart(dateStartConvert);
        
        // FOOD AND SERVICE SUMMARY
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
    	HolderManager holderManager = HolderManager.getInstance();
		
    	if (arrFoods.size()==0) {
    		holderManager.AlertNotification(" ", "Vui lòng chọn ít nhất 1 món ăn ", 1);
    	} else if (arrServices.size()==0) {
    		holderManager.AlertNotification(" ", "Vui lòng chọn ít nhất 1 dịch vụ ", 1);
    	}
    	else {
    		currentPane.setVisible(false);
    		currentPane = step3;
    		currentPane.setVisible(true);
    		UpdateFinalTbView();
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
    private TableColumn<Food, String> priceFoodFinal;
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
    private Label dateFinal;
    
    public void ViewFinalTbViewAll() {
    	// Lobby
    	
    	idLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,String>("id"));
    	nameLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,String>("name"));
    	typeLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,String>("type"));
    	numberTableLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("tableNumber"));
    	priceLobbyTableFinal.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("priceTable"));
    	priceLobbyFinal.setCellValueFactory(new PropertyValueFactory<Lobby,Number>("priceLobby"));

    	// Food
    	idFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("id"));
    	nameFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
    	priceFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("price"));
    	typeFoodFinal.setCellValueFactory(new PropertyValueFactory<Food,String>("type"));
    	
    	// Service
    	
    	idServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("id"));
    	nameServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,String>("name"));
    	priceServiceFinal.setCellValueFactory(new PropertyValueFactory<ServiceWedding,Number>("price"));
    	

    	
    	
    }
    public void UpdateFinalTbView() {
    	ObservableList<Lobby> lbList = FXCollections.observableArrayList(currentOrderLobby);
    	lobbyFinal.setItems(lbList);
    	
    	ObservableList<Food> fList = FXCollections.observableArrayList(arrFoods);
    	foodFinal.setItems(fList);
    	
    	ObservableList<ServiceWedding> svList = FXCollections.observableArrayList(arrServices);
    	serviceFinal.setItems(svList);
    }

    /************* CALCULATE MONEY *************************/
	@FXML
	private Label moneySumLb;
	@FXML
	private TextField depositTF;
	@FXML
	private Label moneyRestLb;
	@FXML
	private TextField numberTable;
	private long priceSum, depositOrder, restMoney;
	
    public void CalculateMoney() {
    	priceSum =  Integer.parseInt(moneySumLb.getText());
    	depositOrder = Integer.parseInt(depositTF.getText());
    	restMoney = Integer.parseInt(moneyRestLb.getText());
    	
    	long tmp = priceSum-depositOrder;
    	moneyRestLb.setText(Long.toString(tmp));
    }
    
    /************* FINAL COMMIT 
     * @throws SQLException ******************/
    public void CommitFinal() throws SQLException {
    	HolderManager holderManager = HolderManager.getInstance();
    	StaffHolder holder = StaffHolder.getInstance();
		currentOrderWedding.setIdStaff(holder.getStaff().getId());
		currentOrderWedding.setNumberFood(arrFoods.size());
		currentOrderWedding.setNumberOfTable(Integer.parseInt(numberTable.getText()));
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
		
	
		try {
			//String statusOrder = "true";
			String idInfoWedding = OrderWeddingModel.CreateInfoWedding(nameBride.getText(), nameGroom.getText());
			if (idInfoWedding != null) {
				currentOrderFood.forEach((itemOrderFood) -> {
					try {
						String message = OrderWeddingModel.CreateFoodOrder(idInfoWedding, itemOrderFood.getIdFood());
						if (message.equals("false")) {
							holderManager.AlertNotification("", "Đã có lỗi xảy ra, Vui lòng thử lại sau", 1);
							//statusOrder = "false";
							return;
						}
					} catch (SQLException e) {
						holderManager.AlertNotification("", "Đã có lỗi xảy ra, Vui lòng thử lại sau !", 1);
					}
				});
						
				
				currentOrderService.forEach((itemOrderService) -> {
					try {
						String message = OrderWeddingModel.CreateServiceOrder(idInfoWedding, itemOrderService.getIdService());
						if (message.equals("false")) {
							holderManager.AlertNotification("", "Đã có lỗi xảy ra, Vui lòng thử lại sau", 1);
							//statusOrder = "false";
							return;
						}
						
					} catch (Exception e2) {
						holderManager.AlertNotification("", "Đã có lỗi xảy ra, Vui lòng thử lại sau !", 1);
					}
				});
				
				try {
					OrderWedding resultOrder = OrderWeddingModel.callOrderWedding(
							idInfoWedding, 
							nameCustomer.getText(),
							phoneNumberCus.getText(), 
							currentOrderWedding.getIdLobby(), 
							currentOrderWedding.getIdStaff(), 
							currentOrderWedding.getNumberOfTable(), 
							currentOrderWedding.getDateStart()
							
					);
					
					DecimalFormat formatter = new DecimalFormat("###,###,###");
					moneySum.setText ("Tổng tiền: "+ formatter.format(resultOrder.getMoney())+ " VNĐ");
					deposit.setText  ("Tiền cọc: "+ formatter.format(resultOrder.getDeposit())+ " VNĐ");
					moneyRest.setText("Còn lại: "+ formatter.format(Long.parseLong(resultOrder.getMoney().toString())-Long.parseLong(resultOrder.getDeposit().toString()))+ " VNĐ");
					holderManager.setStageNeedClose((Stage)step1.getScene().getWindow());
					holderManager.setIdWeddingCommitPayment(idInfoWedding);
					currentPane = step4;
					currentPane.setVisible(true);
				} catch (Exception e3) {
					holderManager.AlertNotification("", "Đã có lỗi xảy ra, Vui lòng thử lại sau !", 1);
				}
				
			}
		} catch (Exception e) {
			holderManager.AlertNotification("", "Đã có lỗi xảy ra, Vui lòng thử lại sau !", 1);
		}

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
    	if (currentPane==step4) {
    		HolderManager holderManager = HolderManager.getInstance();
    		holderManager.AlertNotification("exitOrderWedding","Bạn đang hủy đặt tiệc !", 0);
    	} else 	primaryStage.close();
    }
    @FXML
    private void onPressMinimizeWindow(ActionEvent event) {
    	primaryStage = (Stage) step1.getScene().getWindow();
    	primaryStage.setIconified(true);
    }
    /*************** END WINDOW CONTROLLER *************/
}










