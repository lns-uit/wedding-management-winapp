//package application;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//public class StaffController {
//	
//    @FXML
//    private static TableView<Staff> tbvStaff;
//    @FXML
//    private TableColumn<Staff, Number> tbcStaffNumber;
//
//    @FXML
//    private TableColumn<Staff, String> tbcStaffId;
//
//    @FXML
//    private TableColumn<Staff, String> tbcStaffName;
//
//    @FXML
//    private TableColumn<Staff, String> tbcStaffBornDate;
//
//    @FXML
//    private TableColumn<Staff, String> tbcStaffAddress;
//
//    @FXML
//    private TableColumn<Staff, String> tbcStaffPhoneNumber;
//
//    @FXML
//    private TableColumn<Staff, String> tbcStaffEmail;
//
//    @FXML
//    private TableColumn<Staff, String> tbcStaffType;
//    
//    static ObservableList<Staff> staffList;
//	
//	public void searchStaff() throws SQLException {
//		ArrayList<Staff> arrStaff = StaffModel.getAllStaff();
//		staffList = FXCollections.observableArrayList(arrStaff);
//		tbvStaff.setItems(staffList);
//	}
//    void runFirst() throws SQLException {
//    	
//		tbcStaffId.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffID"));
//		tbcStaffName.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffName"));
//		tbcStaffBornDate.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffBornDate"));
//		tbcStaffAddress.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffAddress"));
//		tbcStaffPhoneNumber.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffPhone"));
//		tbcStaffEmail.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffEmail"));
//		tbcStaffType.setCellValueFactory(new PropertyValueFactory<Staff, String>("StaffType"));
//		searchStaff();
//    }
//}
