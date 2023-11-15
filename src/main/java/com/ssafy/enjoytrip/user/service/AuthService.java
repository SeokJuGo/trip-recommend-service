package com.ssafy.enjoytrip.user.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.AuthResponseDto;
import com.ssafy.enjoytrip.user.model.UserEntity;

public interface AuthService {

	UserEntity login(AuthRequestDto authRequestDto) throws SQLException;

	void delete(AuthRequestDto authRequestDto) throws Exception;

	void saveRefreshToken(String id, String refreshToken) throws Exception;

	Object getRefreshToken(String id) throws Exception;

	void deleRefreshToken(String id) throws Exception;
	
	
	//JWT
	<T> String createAccessToken(String key, T data);

	<T> String createRefreshToken(String key, T data);

	<T> String create(String key, T data, String subject, long expir);

	Map<String, Object> get(String key);

	String getUserId();

	boolean checkToken(String jwt);
}
