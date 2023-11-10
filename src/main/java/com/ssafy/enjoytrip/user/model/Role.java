package com.ssafy.enjoytrip.user.model;

public enum Role {
	ADMIN("관리자"), USER("사용자"), GUEST("비회원");

	private final String name;

	Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
