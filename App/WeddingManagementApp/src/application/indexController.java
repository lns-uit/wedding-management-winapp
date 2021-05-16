package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class indexController {
	@FXML
    private Button btnAddTime;
    @FXML
    private Button btnDeleteTime;
    @FXML
    private Button btnUpdateTime;
    @FXML
    private Button btnAddMenu;
    @FXML
    private Button btnDeleteMenu;
    @FXML
    private Button btnUpdateMenu;
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
    private AnchorPane reportPanel;
    @FXML
    private AnchorPane infoPersonalPanel;
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
    private TableColumn<Staff,String> staffTypeColumn;
    
    @FXML
    private Button btnName;
    
    private ObservableList<Staff> arrStaff;
    
    @FXML
    void initialize() {
		// TODO Auto-generated method stub
    	StaffHolder holder = StaffHolder.getInstance();
    	Staff staff = holder.getStaff();
    	btnName.setText(staff.getName());
    	nameStaff.setText(staff.getName());
    	phoneNumberStaff.setText(staff.getPhoneNumber());
    	addressStaff.setText(staff.getAddress());
    	identityCardStaff.setText(staff.getIdentityCard());
    	typeStaff.setText(staff.getType());
    	startWorkStaff.setText(staff.getStartWork());
    	IndexInit(staff.getType());
    	
	}
    
    public void searchStaff() throws SQLException {
    	
		arrStaff = FXCollections.observableArrayList(
				StaffModel.getAllStaff()
		);
		staffIdColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("id"));
		staffNameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
		staffPhoneColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNumber"));
		staffAdressColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("address"));
		staffCMNDColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("identityCard"));
		staffStartWorkDateColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("startWork"));
		staffTypeColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("type"));

		staffTbView.setItems(arrStaff);
	}
    

    private AnchorPane currentPane;
    private Button currentButton;
    
    @FXML
    public void PressIndex(ActionEvent event) throws SQLException {
    	
    	if (currentPane==null) currentPane = infoPersonalPanel;
    	currentPane.setVisible(false);
    	if (currentButton==null) currentButton = btnInfoPersonal;
    	currentButton.setStyle("-fx-background-color: rgb(184, 55, 55)");
    	if (event.getSource()==btnWeddingOrderManagement) { currentPane = weddingOrderPanel; currentButton = btnWeddingOrderManagement;}
    	else if (event.getSource()==btnStaffManagement) { 
    		currentPane = staffManagerPanel; 
    		currentButton = btnStaffManagement;
    		searchStaff();
    	}
    	else if (event.getSource()==btnWeddingInfoManagement) { 
    		currentPane = weddingOrderInfoPanel; 
    		currentButton = btnWeddingInfoManagement;
    	}
    	else if (event.getSource()==btnReport) { currentPane = reportPanel; currentButton = btnReport;}
    	else if (event.getSource()==btnInfoPersonal) { currentPane = infoPersonalPanel; currentButton = btnInfoPersonal;}
    	currentButton.setStyle("-fx-background-color:#d64d4d");
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
    	
    	if (event.getSource()== btnLobbyManager) { currentButtonOptionWeddingInfoManager = btnLobbyManager; currentPanelOptionWeddingInfoManager = stackLobbyManager; }
    	else if (event.getSource()== btnMenuManager) { currentButtonOptionWeddingInfoManager = btnMenuManager; currentPanelOptionWeddingInfoManager = stackMenuManager;}
    	else if (event.getSource()== btnTimeManager) { currentButtonOptionWeddingInfoManager = btnTimeManager; currentPanelOptionWeddingInfoManager = stackTimeManager;}
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
    	if (type.equals("nhan vien")) { // Nhân viên lễ tân
    		
    		btnStaffManagement.setDisable(true);
    		btnAddLobby.setDisable(true);
    		btnDeleteLobby.setDisable(true);
    		btnUpdateLobby.setDisable(true);	
    		btnAddTime.setDisable(true);
    		btnDeleteTime.setDisable(true);
    		btnUpdateTime.setDisable(true);
    		btnAddMenu.setDisable(true);
    		btnDeleteMenu.setDisable(true);
    		btnUpdateMenu.setDisable(true);
    	} else if (type.equals("quan ly")) { // Nhân viên phục vụ - Lao công 
    		btnWeddingInfoManagement.setDisable(true);
    		btnStaffManagement.setDisable(true);
    		btnLobbyManager.setDisable(true);
    		btnWeddingOrderManagement.setDisable(true);
    		btnReport.setDisable(true);	
    	}
    }
}




















