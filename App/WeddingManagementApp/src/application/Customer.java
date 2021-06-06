package application;

import java.text.DecimalFormat;

public class Customer {
	private String id;
	private String name;
	private String phone;
	private String money;
	private String discount;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMoney() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		String tmpString = formatter.format(Long.parseLong(money))+" VNĐ";
		return tmpString;
	}
	public void setMoney(Number money) {
		this.money = money.toString();
		
	}
	public String getDiscount() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		String tmpString = formatter.format(Long.parseLong(discount))+" %";
		return tmpString;
	}
	public void setDiscount(Number discount) {
		this.discount = discount.toString();
	}
	public Customer(String id, String name, String phone, Number money, Number discount) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.money = money.toString();
		this.discount = discount.toString();
	}
	public Customer() {};
	
}
