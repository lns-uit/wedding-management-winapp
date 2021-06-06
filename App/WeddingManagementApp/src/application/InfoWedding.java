package application;

public class InfoWedding {
	public InfoWedding(String idWedding, String nameGroom, String nameBride) {
		super();
		this.idWedding = idWedding;
		this.nameGroom = nameGroom;
		this.nameBride = nameBride;
	}
	public InfoWedding() {}
	private String idWedding;
	private String nameGroom;
	private String nameBride;
	public String getIdWedding() {
		return idWedding;
	}
	public void setIdWedding(String idWedding) {
		this.idWedding = idWedding;
	}
	public String getNameGroom() {
		return nameGroom;
	}
	public void setNameGroom(String nameGroom) {
		this.nameGroom = nameGroom;
	}
	public String getNameBride() {
		return nameBride;
	}
	public void setNameBride(String nameBride) {
		this.nameBride = nameBride;
	}
	
}
