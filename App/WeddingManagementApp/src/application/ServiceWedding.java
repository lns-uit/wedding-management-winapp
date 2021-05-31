package application;

import java.text.DecimalFormat;

import javafx.scene.control.CheckBox;

public class ServiceWedding {
	public ServiceWedding(String id, String name, Number price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price.toString();
		this.checkBox = new CheckBox();
	}
	public ServiceWedding() {
		
	}
	public CheckBox getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
	private String id;
	private String name;
	private String price;
	private CheckBox checkBox;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		String tmpString = formatter.format(Long.parseLong(price));
		return tmpString;
	}
	public void setPrice(Number price) {
		this.price = price.toString();
	}
	
}
