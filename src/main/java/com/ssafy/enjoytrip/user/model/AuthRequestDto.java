package com.ssafy.enjoytrip.user.model;

public class AuthRequestDto {
	private String username;
	private String password;

	public AuthRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthRequestDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthRequestDto [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
