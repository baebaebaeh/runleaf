package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PageResult {
	private int page;			// 현재 요청된 페이지 번호
	private int beginPage;		// 목록 하단의 페이지 시작번호
	private int endPage;		
	private int lastPage;		
	private boolean prev;		
	private boolean next;
	
	private static final int LIST_SIZE = 10;
	private static final int TAB_SIZE = 10;

	public PageResult(int page, int totalCount) {
		this(page, totalCount, LIST_SIZE, TAB_SIZE);
	}

	public PageResult(int page, int totalCount, int listSize) {
		this(page, totalCount, listSize, TAB_SIZE);
	}

	public PageResult(int page, int totalCount, int listSize, int tabSize) {
		this.page = page;		
		this.lastPage = (totalCount % listSize == 0) ? totalCount / listSize : totalCount / listSize + 1;
		int tab = (page - 1) / tabSize + 1;
		this.beginPage = (tab - 1) * tabSize + 1;
		this.endPage = (tab * tabSize < lastPage) ? tab * tabSize : lastPage;

		// 이전 번호가 있는지
		this.prev = beginPage != 1;
//				// 다음 번호가 있는지
		this.next = endPage != lastPage;
	}

	public int getPage() {
		return page;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}
	
	
	
}