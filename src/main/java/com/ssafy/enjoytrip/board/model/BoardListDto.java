package com.ssafy.enjoytrip.board.model;

import java.util.List;

public class BoardListDto {
	private int offset;
	private int pageNum;
	private int pageSize;
	private int totalPages;
	private int totalElements;
	private boolean first;
	private boolean last;
	List<BoardDto> boards;

	public BoardListDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardListDto(int offset, int pageNum, int pageSize, int totalPages, int totalElements, boolean first,
			boolean last, List<BoardDto> boards) {
		super();
		this.offset = offset;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.first = first;
		this.last = last;
		this.boards = boards;
	}

	@Override
	public String toString() {
		return "BoardListDto [offset=" + offset + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalPages="
				+ totalPages + ", totalElements=" + totalElements + ", first=" + first + ", last=" + last + ", boards="
				+ boards + "]";
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
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

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public List<BoardDto> getBoards() {
		return boards;
	}

	public void setBoards(List<BoardDto> boards) {
		this.boards = boards;
	}

}
