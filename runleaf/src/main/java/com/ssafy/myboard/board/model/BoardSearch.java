package com.ssafy.myboard.board.model;

public class BoardSearch {
	private int page;
	private int listSize;
	
	public BoardSearch() {
		this(1, 10);
	}

	public BoardSearch(int page) {
		this(page, 10);
	}
	
	public BoardSearch(int page, int listSize) {
		this.page = page;
		this.listSize = listSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getOffset() {
		return (page - 1) * listSize;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
}




