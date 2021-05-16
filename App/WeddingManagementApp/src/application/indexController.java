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
    private static TableView<Staff> tbvStaff;
    @FXML
    private TableColumn<Staff, Number> tbcStaffNumber;
    @FXML
    private TableColumn<Staff, String> tbcStaffId;
    @FXML
    private TableColumn<Staff, String> tbcStaffName;
    @FXML
    private TableColumn<Staff, String> tbcStaffBornDate;
    @FXML
    private TableColumn<Staff, String> tbcStaffAddress;
    @FXML
    private TableColumn<Staff, String> tbcStaffPhoneNumber;
    @FXML
    private TableColumn<Staff, String> tbcStaffEmail;
    @FXML
    private TableColumn<Staff, String> tbcStaffType;
    
    static ObservableList<Staff> staffList;
    
    public void searchStaff() throws SQLException {
		ObservableList<Staff> arrStaff = FXCollections.observableArrayList(StaffModel.getAllStaff());
		tbvStaff.setItems(arrStaff);
	}
    
    void runFirst() throws SQLException {
    	System.out.print("run table view");
		tbcStaffId.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffID"));
		tbcStaffName.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffName"));
		tbcStaffBornDate.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffBornDate"));
		tbcStaffAddress.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffAddress"));
		tbcStaffPhoneNumber.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffPhone"));
		tbcStaffEmail.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffEmail"));
		tbcStaffType.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffType"));
		searchStaff();
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




















