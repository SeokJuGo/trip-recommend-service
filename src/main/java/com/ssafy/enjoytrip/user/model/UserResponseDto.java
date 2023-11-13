package com.ssafy.enjoytrip.user.model;

public class UserResponseDto {
	private Long id;
	private String email;
	private String username;
	private String nickname;
	private String createdDate;
	private String updatedDate;

	public UserResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResponseDto(Long id, String email, String username, String nickname, String createdDate,
			String updatedDate) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.nickname = nickname;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "UserResponseDto [id=" + id + ", email=" + email + ", username=" + username + ", nickname=" + nickname
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
