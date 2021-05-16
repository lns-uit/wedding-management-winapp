package application;

import java.sql.SQLException;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class indexController {

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
    private Label emailStaff;
    @FXML
    private Label typeStaff;
    @FXML
    private Label dateOfBirthStaff;
    //    Table 
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
    	btnName.setText(holder.getStaff().getName());
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
    
    void runFirst() throws SQLException {

		
    }
    public void ShowStaffList() {
   
    }

    private AnchorPane currentPane;
    private Button currentButton;
    
    @FXML
    public void PressIndex(ActionEvent event) throws SQLException {
    	
    	if (currentPane==null) currentPane = weddingOrderPanel;
    	currentPane.setVisible(false);
    	if (currentButton==null) currentButton = btnWeddingOrderManagement;
    	currentButton.setStyle("-fx-background-color: rgb(184, 55, 55)");
    	if (event.getSource()==btnWeddingOrderManagement) { currentPane = weddingOrderPanel; currentButton = btnWeddingOrderManagement;}
    	else if (event.getSource()==btnStaffManagement) { 
    		currentPane = staffManagerPanel; 
    		currentButton = btnStaffManagement;

//    		runFirst();
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
}




















