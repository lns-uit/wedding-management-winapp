package application;

import java.text.DecimalFormat;

import javafx.scene.control.CheckBox;

public class Food {
	public Food(String id, String name, Number price, String type) {

		super();
		this.id = id;
		this.name = name;
		this.price = price.toString();
		this.type = type;
		this.checkBox = new CheckBox();
	}

	public Food() {};

	private String id;
	private String name;
	private String price;
	private String type;
	private CheckBox checkBox;
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Number price) {
		this.price = price.toString();
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
	
	public String getId() {return id;}
	public String getName() {return name;}
	public String getPrice() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		String tmpString = formatter.format(Long.parseLong(price))+" VNĐ";
		return tmpString;
	}
	public String getType() {return type;}
	public CheckBox getCheckBox() {return checkBox;}

}
