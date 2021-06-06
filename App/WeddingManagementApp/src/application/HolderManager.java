package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public final class HolderManager {
	private final static HolderManager INSTANCE = new HolderManager();
	private HolderManager() {}
	public static HolderManager getInstance() {
	    return INSTANCE;
	}
	
	private OrderWedding orderWeddingCurrent;
	public void setOrderWedding(OrderWedding u) {
		this.orderWeddingCurrent = u;
	}
	public OrderWedding getOrderWeddingCurrent() {return this.orderWeddingCurrent;}
	
	private Food foodCurrent;
	public void setFood (Food food ) {
		this.foodCurrent = food;
	}
	public Food getFood() {
		return this.foodCurrent;
	}
	
	private  Staff staffCurrent;
	public void setCurrentStaff (Staff staff ) {
		this.staffCurrent = staff;
	}
	public Staff getCurrentStaff() {
		return this.staffCurrent;
	}
	
	private Lobby lobbyCurrent;
	public void setLobby (Lobby lobby ) {
		this.lobbyCurrent = lobby;
	}
	public Lobby getLobby() {
		return this.lobbyCurrent;
	}
	
	private ServiceWedding serviceCurrent;
	public void setService (ServiceWedding service ) {
		this.serviceCurrent = service;
	}
	public ServiceWedding getService() {
		return this.serviceCurrent;
	}
	
	private int typeAlert;
	public int getTypeAlert() {
		return typeAlert;
	}
	public void setTypeAlert(int x) {
		this.typeAlert = x;
	}
	private String alertContent;
	public String getAlertContent() {
		return alertContent;
	}
	public void setAlertContent(String x) {
		this.alertContent = x;
	}
	
	private String action;
	public void setAction(String x) {
		this.action = x;
	}
	public String getAction() {
		return this.action;
	}
	
	private indexController indexCtrl;
	public void setIndexController(indexController x) {
		this.indexCtrl = x;
	}
	public indexController getIndexController()
	{
		return this.indexCtrl;
	}
	public void setAlertInit(String action, String content, int type) {
		this.action = action;
		this.alertContent = content;
		this.typeAlert = type;
	}
	
    public void AlertNotification(String action,String content,int type) {
  		HolderManager holderManager = HolderManager.getInstance();
  		holderManager.setAlertInit(action,content, type);
  		AlertScene alertScene = new AlertScene();
  		Stage stage = new Stage();
  		alertScene.start(stage);
  	}
    
    // Detail order Wedding
    
    private OrderWedding detailOrderWedding;
    private ArrayList<Food> arrFoodOrder;
    private ArrayList<ServiceWedding> arrSerivceOrder;
    private InfoWedding infoOrder;
    private Customer cusOrder;
    private Lobby lobbyOrder;
    
    public void setDetailOrderWedding (OrderWedding newVar) {
    	this.detailOrderWedding = newVar;
    }
    public void setArrFoodOrder (ArrayList<Food> newArr) {
    	this.arrFoodOrder = newArr;
    }
    public void setArrServiceOrder( ArrayList<ServiceWedding> newArr) {
		this.arrSerivceOrder = newArr;
	}
    public void setInfoOrder (InfoWedding newVar) {
    	this.infoOrder = newVar;
    }
    public void setCusOrder (Customer newVar) {
    	this.cusOrder = newVar;
    }
    public void setLobbyOrder (Lobby newVar) {
    	this.lobbyOrder = newVar;
    }
    public OrderWedding getDetailOrderWedding () {
    	return this.detailOrderWedding;
    }
    public ArrayList<Food> getArrFoodOrder () {
    	return this.arrFoodOrder;
    }
    public ArrayList<ServiceWedding> getArrServiceOrder () {
    	return this.arrSerivceOrder;
    }
    public InfoWedding getInfoOrder () {
    	return this.infoOrder;
    }
    public Customer getCusOrder() {
    	return this.cusOrder;
    }
    public Lobby getLobbyOrder() {
    	return this.lobbyOrder;
    }
    
    private Stage stageNeedClose;
	public Stage getStageNeedClose() {
		return stageNeedClose;
	}
	public void setStageNeedClose(Stage stageNeedClose) {
		this.stageNeedClose = stageNeedClose;
	}
    private String idWeddingCommitPayment;
	public String getIdWeddingCommitPayment() {
		return idWeddingCommitPayment;
	}
	public void setIdWeddingCommitPayment(String idWeddingCommitPayment) {
		this.idWeddingCommitPayment = idWeddingCommitPayment;
	}
    
}
