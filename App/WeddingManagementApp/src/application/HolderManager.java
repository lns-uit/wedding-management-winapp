package application;

public final class HolderManager {
	private final static HolderManager INSTANCE = new HolderManager();
	private HolderManager() {}
	public static HolderManager getInstance() {
	    return INSTANCE;
	}
	
	private OrderWedding orderWeddingCurrent;
	public void setOrderWedding(OrderWedding u) {
		this.orderWeddingCurrent = u;
	}
	public OrderWedding getOrderWeddingCurrent() {return this.orderWeddingCurrent;}
	
	private Food foodCurrent;
	public void setFood (Food food ) {
		this.foodCurrent = food;
	}
	public Food getFood() {
		return this.foodCurrent;
	}
}
