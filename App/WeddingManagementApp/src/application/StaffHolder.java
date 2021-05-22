package application;

public final class StaffHolder {
	private Staff staff;
	private final static StaffHolder INSTANCE = new StaffHolder();
	private StaffHolder() {}
	public static StaffHolder getInstance() {
	    return INSTANCE;
	}
	public void setStaff(Staff u) {
	    this.staff = u;
	}
	public Staff getStaff() {
	    return this.staff;
	}
	
	private Staff selectStaff;
	public void setStaffSelect(Staff u) {
		this.selectStaff = u;
	}
	public Staff getSelectStaff() {
		return this.selectStaff;
	}
	
}
