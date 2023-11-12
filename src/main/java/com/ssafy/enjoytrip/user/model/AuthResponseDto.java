package com.ssafy.enjoytrip.user.model;

public class AuthResponseDto {
	private String tokenType;
	private String accessToken;
	private String refreshToken;

	public AuthResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponseDto(String tokenType, String accessToken, String refreshToken) {
		super();
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "AuthResponseDto [tokenType=" + tokenType + ", accessToken=" + accessToken + ", refreshToken="
				+ refreshToken + "]";
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
