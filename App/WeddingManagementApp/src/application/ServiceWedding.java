package application;

import java.text.DecimalFormat;

import javafx.scene.control.CheckBox;

public class ServiceWedding {
	public ServiceWedding(String id, String name, Number price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.checkBox = new CheckBox();
		setPriceShow(price);
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
	private Number price;
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
	public Number getPrice() {
		return price;
	}
	public void setPrice(Number price) {
		this.price = price;
	}
	private String priceShow;
	public String getPriceShow() {
		return priceShow;
	}

	public void setPriceShow(Number priceShow) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		String tmpString = formatter.format((priceShow).longValue())+" VNĐ";
		this.priceShow = tmpString;
	}
}
