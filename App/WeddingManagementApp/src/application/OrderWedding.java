package application;

import java.sql.Date;

public class OrderWedding {    
    public OrderWedding(String idWedding, String idLobby, String idStaff, String idCustomer, Number numberFood,
			Number numberService, Number deposit, Number money, Number numberOfTable, String dateOrder,
			String dateStart) {
		super();
		this.idWedding = idWedding;
		this.idLobby = idLobby;
		this.idStaff = idStaff;
		this.idCustomer = idCustomer;
		this.numberFood = numberFood;
		this.numberService = numberService;
		this.deposit = deposit;
		this.money = money;
		this.numberOfTable = numberOfTable;
		this.dateOrder = dateOrder;
		this.dateStart = dateStart;
	}
    public OrderWedding() {}
	private String idWedding;
	private String idLobby;
	private String idStaff;
	private String idCustomer;
	private Number numberFood;
	private Number numberService;
	private Number deposit;
	private Number money;
	private Number numberOfTable;
	private String dateOrder;
	private String dateStart;

	private String nameCus;
	private String phoneCus;
	private String statusPay;
	public String getStatusPay() {
		return statusPay;
	}
	public void setStatusPay(String statusPay) {
		this.statusPay = statusPay;
	}
	public String getNameCus() {
		return nameCus;
	}
	public void setNameCus(String nameCus) {
		this.nameCus = nameCus;
	}
	public String getPhoneCus() {
		return phoneCus;
	}
	public void setPhoneCus(String phoneCus) {
		this.phoneCus = phoneCus;
	}
	public String getIdWedding() {return idWedding;}
	public String getIdLobby() {return idLobby;}
	public String getIdStaff() {return idStaff;}
	public String getIdCustomer() {return idCustomer;}
	public Number getNumberFood() { return numberFood;}
	public Number getNumberService() {return numberService;}
	public Number getDeposit() {return deposit;}
	public Number getMoney() {return money;}
	public Number getNumberOfTable() {return numberOfTable;}
	public String getDateOrder() { return dateOrder;}
	public String getDateStart() {return dateStart;}
	
	public void setIdWedding(String idWedding) {
		this.idWedding = idWedding;
	}
	public void setIdLobby(String idLobby) {
		this.idLobby = idLobby;
	}
	public void setIdStaff(String idStaff) {
		this.idStaff = idStaff;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public void setNumberFood(Number numberFood) {
		this.numberFood = numberFood;
	}
	public void setNumberService(Number numberService) {
		this.numberService = numberService;
	}
	public void setDeposit(Number deposit) {
		this.deposit = deposit;
	}
	public void setMoney(Number money) {
		this.money = money;
	}
	public void setNumberOfTable(Number numberOfTable) {
		this.numberOfTable = numberOfTable;
	}
	public void setDateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
}
