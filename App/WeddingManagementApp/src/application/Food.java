package application;

import java.text.DecimalFormat;

import javafx.scene.control.CheckBox;

public class Food {
	public Food(String id, String name, Number price, String type) {

		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.checkBox = new CheckBox();
		setPriceShow(price);
	}

	public Food() {};

	private String id;
	private String name;
	private Number price;
	private String type;
	private CheckBox checkBox;
	private String priceShow;
	public String getPriceShow() {
		return priceShow;
	}

	public void setPriceShow(Number priceShow) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		String tmpString = formatter.format((priceShow).longValue())+" VNĐ";
		this.priceShow = tmpString;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Number price) {
		setPriceShow(price);
		this.price = price;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
	
	public String getId() {return id;}
	public String getName() {return name;}
	public Number getPrice() {
		return this.price;
	}
	public String getType() {return type;}
	public CheckBox getCheckBox() {return checkBox;}

}
