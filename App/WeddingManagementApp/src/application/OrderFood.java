package application;

public class OrderFood {
	public OrderFood(String idFood, String idWedding) {
		super();
		this.idFood = idFood;
		this.idWedding = idWedding;
	}
	private String idFood;
	private String idWedding;
	public void setIdFood(String idFood) {
		this.idFood= idFood;
	}
	public void setIdWedding(String idWedding) {
		this.idWedding = idWedding;
	}
	public String getIdFood() {return idFood;}
	public String getIdWedding() {return idWedding;}
	
}
