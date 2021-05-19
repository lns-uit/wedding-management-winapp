package application;

import java.awt.print.Printable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatter;

import javax.xml.ws.Holder;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;


public class AddWeddingOrderController {

    @FXML
    private AnchorPane step1;
    @FXML
    private AnchorPane step2;
    @FXML
    private AnchorPane step3;
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
    @FXML
    public void CommitStep(ActionEvent event) {
    	if (currentPane==null) currentPane = step1;
    	if (event.getSource()==btnCommit1) {
    		
    		boolean isCheckBoxLobby=false;
    		String indexSelectLobby = "";
    		for (Lobby lobby : arrLobby) {
				if (lobby.getCheckBox().isSelected()) {
					isCheckBoxLobby = true;
					indexSelectLobby = lobby.getId();
					break;
				}
			}
    		if (!isCheckBoxLobby || datePkStart.getValue()==null) {
    			showAlertWithoutHeaderText("Vui lòng nhập đầy đủ thông tin");
    		} else {

    			currentOrderWedding.setIdLobby(indexSelectLobby);
    			currentOrderWedding.setDateStart(datePkStart.getValue().toString());
    			currentOrderWedding.setDateOrder(LocalDate.now().toString());
    			currentPane.setVisible(false);
    			currentPane = step2;
    			currentPane.setVisible(true);
    		}
    		
    		
    	} else
    	if (event.getSource()==btnCommit2) {
    		currentPane.setVisible(false);
			currentPane = step3;
			currentPane.setVisible(true);
    	} else
    	if (event.getSource()==btnCommit3) {
    		System.out.print("Commit");
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
    private void showAlertWithoutHeaderText(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(message);
 
        alert.showAndWait();
	}
}










