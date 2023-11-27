package com.ssafy.enjoytrip.attraction.model;

public class GugunDto {
	private int sidoCode;
	private int gugunCode;
	private String gugunName;

	public GugunDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GugunDto(int sidoCode, int gugunCode, String gugunName) {
		super();
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
	}

	@Override
	public String toString() {
		return "GugunDto [sidoCode=" + sidoCode + ", gugunCode=" + gugunCode + ", gugunName=" + gugunName + "]";
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

}
