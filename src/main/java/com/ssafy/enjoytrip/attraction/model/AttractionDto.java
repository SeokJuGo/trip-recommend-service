package com.ssafy.enjoytrip.attraction.model;

public class AttractionDto {
	private int contentId;
	private int contentTypeId;

	private String title;
	private String address;
	private String zipCode;
	private String tel;

	private String firstImage;
	private String secondImage;

	private int readCount;
	private int sidoCode;
	private int gugunCode;

	private double latitude;
	private double longitude;

	private String mlevel;
	private String overview;

	public AttractionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttractionDto(int contentId, int contentTypeId, String title, String address, String zipCode, String tel,
			String firstImage, String secondImage, int readCount, int sidoCode, int gugunCode, double latitude,
			double longitude, String mlevel, String overview) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.address = address;
		this.zipCode = zipCode;
		this.tel = tel;
		this.firstImage = firstImage;
		this.secondImage = secondImage;
		this.readCount = readCount;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mlevel = mlevel;
		this.overview = overview;
	}

	@Override
	public String toString() {
		return "AttractionDto [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", title=" + title
				+ ", address=" + address + ", zipCode=" + zipCode + ", tel=" + tel + ", firstImage=" + firstImage
				+ ", secondImage=" + secondImage + ", readCount=" + readCount + ", sidoCode=" + sidoCode
				+ ", gugunCode=" + gugunCode + ", latitude=" + latitude + ", longitude=" + longitude + ", mlevel="
				+ mlevel + ", overview=" + overview + "]";
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getSecondImage() {
		return secondImage;
	}

	public void setSecondImage(String secondImage) {
		this.secondImage = secondImage;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
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

	public String getMlevel() {
		return mlevel;
	}

	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

}
