package kr.kro.runleaf.domain;
/**
 * 여기서 order에관한 작업하면됨.
 */
public class BoardSearch {
	// page는 1부터 시작 
	private int page; // 한번 부르면 5개 content load
	private int userId;
	private int orderInt;
	private double latitude;
	private double longitude;
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BoardSearch(int page, int userId, int orderInt, double latitude, double longitude) {
		super();
		this.page = page;
		this.userId = userId;
		this.orderInt = orderInt;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public BoardSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStartIdx() {
		return page * 5 - 5;
	}
	
	public int getEndIdx() {
		return page * 5;
	}

	public int getOrderInt() {
		return orderInt;
	}

	public void setOrderInt(int orderInt) {
		this.orderInt = orderInt;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
