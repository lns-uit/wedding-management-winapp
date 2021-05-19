package application;

public class Menu {
	public Menu(Number id, String name, Number price, String type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}
	private Number id;
	private String name;
	private Number price;
	private String type;
	public void setId(Number id) {
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
	
	public Number getId() {return id;}
	public String getName() {return name;}
	public Number getPrice() {return price;}
	public String getType() {return type;}
}
