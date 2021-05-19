package application;

public class Menu {
	public Menu(String id, String name, Number price, String type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}
	private String id;
	private String name;
	private Number price;
	private String type;
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Number price) {
		this.price = price;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getId() {return id;}
	public String getName() {return name;}
	public Number getPrice() {return price;}
	public String getType() {return type;}
}
