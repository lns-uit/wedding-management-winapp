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
	
	private Lobby lobbyCurrent;
	public void setLobby (Lobby lobby ) {
		this.lobbyCurrent = lobby;
	}
	public Lobby getLobby() {
		return this.lobbyCurrent;
	}
	
	private ServiceWedding serviceCurrent;
	public void setService (ServiceWedding service ) {
		this.serviceCurrent = service;
	}
	public ServiceWedding getService() {
		return this.serviceCurrent;
	}
}
