package application;

public class ReportRevenue {
	public ReportRevenue(Number stt,String month, Number countWedding, Number revenue) {
		super();
		this.stt = stt;
		this.month = month;
		this.countWedding = countWedding;
		this.revenue = revenue;
	}
	public ReportRevenue() {
		
	}
	private Number stt;
	private String month;
	private Number countWedding;
	private Number revenue;
	public Number getStt() {
		return stt;
	}
	public String getMonth() {
		return month;
	}
	public Number getCountWedding() {
		return countWedding;
	}
	public Number getRevenue() {
		return revenue;
	}
}
