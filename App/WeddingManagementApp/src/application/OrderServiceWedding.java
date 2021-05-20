package application;

public class OrderServiceWedding {
	public OrderServiceWedding(String idService, String idWedding) {
		super();
		this.idService = idService;
		this.idWedding = idWedding;
	}
	private String idService;
	private String idWedding;
	public String getIdService() {
		return idService;
	}
	public void setIdService(String idService) {
		this.idService = idService;
	}
	public String getIdWedding() {
		return idWedding;
	}
	public void setIdWedding(String idWedding) {
		this.idWedding = idWedding;
	}
}
