package application;

public class Staff {
	public Staff(String id, String name, String address, String phoneNumber, String identityCard, String startWork,
			String type) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.identityCard = identityCard;
		this.startWork = startWork;
		this.type = type;
	}
	public Staff() {}
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	
	private String identityCard;
	private String startWork;
	
	private String type;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getStartWork() {
		return startWork;
	}

	public void setStartWork(String startWork) {
		this.startWork = startWork;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
