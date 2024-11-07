package kr.kro.runleaf.board.domain;

public class BoardSearch {
	// page는 1부터 시작 
	private int page; // 한번 부르면 5개 content load
	private String orderString;
	
	public BoardSearch() {
		this(0, 1);
	}
	
	public BoardSearch(int page) {
		this(page, 1);
	}
	
	public BoardSearch(int page, int order) {
		this.page = page;
		if (order == 1) {
			this.orderString = "distance";
		}
		if (order == 2) {
			this.orderString = "recent";
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStartIdx() {
		return page * 5 - 4;
	}
	
	public int getEndIdx() {
		return page * 5;
	}
	
	public String getOrderString() {
		return orderString;
	}
	
	public void set
	
	
}
