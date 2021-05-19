package application;

import javafx.scene.control.CheckBox;

public class Lobby {
	public Lobby(Number id, String name, String type, Number tableNumber, Number priceTable, Number priceLobby,
			CheckBox checkBox) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.tableNumber = tableNumber;
		this.priceTable = priceTable;
		this.priceLobby = priceLobby;
		this.checkBox = checkBox;
	}
	public Lobby(Number id, String name, String type, Number tableNumber, Number priceTable, Number priceLobby,
			String note) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.tableNumber = tableNumber;
		this.priceTable = priceTable;
		this.priceLobby = priceLobby;
		this.note = note;
	}
	private Number id;
	private String name;
	private String type;
	private Number tableNumber;
	private Number priceTable;
	private Number priceLobby;
	private String note;
	private CheckBox checkBox; 
	public Lobby() {}
	
	public void setId(Number id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTable(Number tableNumber) {
		this.tableNumber = tableNumber;
	}
	public void setPriceTable(Number priceTable) {
		this.priceTable = priceTable;
	}
	public void setPriceLobby(Number priceLobby) {
		this.priceLobby = priceLobby;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Number getId() {return id;}
	public String getName() {return name;}
	public String getType() {return type;}
	public Number getTableNumber() {return tableNumber;}
	public Number getPriceTable() {return priceTable;}
	public Number getPriceLobby() {return priceLobby;}
	public String getNote() {return note;}
	public void setCheckBox(CheckBox cb) {
		checkBox = cb;
	}
}
