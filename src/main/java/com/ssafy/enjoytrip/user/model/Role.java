package com.ssafy.enjoytrip.user.model;

public enum Role {
	ADMIN("관리자"), USER("사용자"), GUEST("비회원");

	private final String value;

	Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
