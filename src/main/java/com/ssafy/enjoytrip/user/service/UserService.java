package com.ssafy.enjoytrip.user.service;

import java.util.List;

import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

public interface UserService {
	
	List<UserResponseDto> findAll() throws Exception;

	UserResponseDto findByUsername(String username) throws Exception;
	
	UserResponseDto login (AuthRequestDto authRequestDto) throws Exception;

	void regist(UserRequestDto userRequestDto) throws Exception;

	void update(UserRequestDto userRequestDto) throws Exception;

	void delete(Integer id) throws Exception;
	
}
