package com.ssafy.enjoytrip.user.service;

import java.util.List;

import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

public interface UserService {

	UserResponseDto findByUsername(String username) throws Exception;

	List<UserResponseDto> findAll() throws Exception;

	void regist(UserRequestDto userRequestDto) throws Exception;

	void update(UserRequestDto userRequestDto) throws Exception;

	void delete(UserRequestDto userRequestDto) throws Exception;

}
