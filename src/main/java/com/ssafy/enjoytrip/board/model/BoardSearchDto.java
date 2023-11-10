package com.ssafy.enjoytrip.board.model;

public class BoardSearchDto {
	private int pageNum;
	private int pageSize;
	private String keyword;
	private String searchType;

	public BoardSearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardSearchDto(int pageNum, int pageSize, String keyword, String searchType) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.searchType = searchType;
	}

	@Override
	public String toString() {
		return "BoardSearchDto [pageNum=" + pageNum + ", pageSize=" + pageSize + ", keyword=" + keyword
				+ ", searchType=" + searchType + "]";
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

}
