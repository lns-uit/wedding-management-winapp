package application;

public class Bill {
	public String getIdBill() {
		return idBill;
	}
	public void setIdBill(String idBill) {
		this.idBill = idBill;
	}
	public String getIdStaff() {
		return idStaff;
	}
	public void setIdStaff(String idStaff) {
		this.idStaff = idStaff;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getIdWedding() {
		return idWedding;
	}
	public void setIdWedding(String idWedding) {
		this.idWedding = idWedding;
	}
	public Number getMoney() {
		return money;
	}
	public void setMoney(Number money) {
		this.money = money;
	}
	public String getDateOfPay() {
		return dateOfPay;
	}
	public void setDateOfPay(String dateOfPay) {
		this.dateOfPay = dateOfPay;
	}
	private String idBill;
	private String idStaff;
	private String idCustomer;
	private String idWedding;
	private Number money;
	private String dateOfPay;
	public Bill(String idBill, String idStaff, String idCustomer, String idWedding, Number money, String dateOfPay) {
		super();
		this.idBill = idBill;
		this.idStaff = idStaff;
		this.idCustomer = idCustomer;
		this.idWedding = idWedding;
		this.money = money;
		this.dateOfPay = dateOfPay;
	}
	
}
