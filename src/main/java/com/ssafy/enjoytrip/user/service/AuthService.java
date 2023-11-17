package com.ssafy.enjoytrip.user.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

public interface AuthService {

	UserResponseDto login(AuthRequestDto authRequestDto) throws SQLException;


	
	
	void saveRefreshToken(String username, String refreshToken) throws Exception;

	Object getRefreshToken(String username) throws Exception;

	void deleRefreshToken(String username) throws Exception;
	
	
	//JWT
	<T> String createAccessToken(String key, T data);

	<T> String createRefreshToken(String key, T data);

	<T> String create(String key, T data, String subject, long expir);

	Map<String, Object> get(String key);

	String getUserId();

	boolean checkToken(String jwt);
}
