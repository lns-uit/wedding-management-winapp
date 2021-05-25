package application;

public class Customer {
	private String id;
	private String name;
	private String phone;
	private Number money;
	private Number discount;
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
	public Number getMoney() {
		return money;
	}
	public void setMoney(Number money) {
		this.money = money;
	}
	public Number getDiscount() {
		return discount;
	}
	public void setDiscount(Number discount) {
		this.discount = discount;
	}
	public Customer(String id, String name, String phone, Number money, Number discount) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.money = money;
		this.discount = discount;
	}
	
}
