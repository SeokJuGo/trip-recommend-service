package com.ssafy.enjoytrip.board.model;

public class BoardDto {
	private int id;
	private String title;
	private String content;
	private String userId;
	private String userName;
	private String userEmail;
	private String createdDate;
	private String updatedDate;

	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int id, String title, String content, String userId, String userName, String userEmail,
			String createdDate, String updatedDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + ", userName="
				+ userName + ", userEmail=" + userEmail + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
