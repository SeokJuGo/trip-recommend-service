package com.ssafy.enjoytrip.user.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

public interface UserService {
	
	List<UserResponseDto> findAll() throws Exception;

	UserResponseDto findById(Long id) throws Exception;

	UserResponseDto findByEmail(String email) throws Exception;
	
	UserResponseDto findByUsername(String username) throws Exception;

	void regist(UserRequestDto userRequestDto) throws SQLException;
	
	void update(UserRequestDto userRequestDto) throws Exception;
	
	void delete(UserRequestDto userRequestDto) throws Exception;
	
	int idCheck(String username) throws SQLException;
}

