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
}
