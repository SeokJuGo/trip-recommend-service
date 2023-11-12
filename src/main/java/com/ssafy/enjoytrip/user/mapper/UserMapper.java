package com.ssafy.enjoytrip.user.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

@Mapper
public interface UserMapper {

	UserResponseDto findByUsername(String username) throws SQLException;

	List<UserResponseDto> findAll() throws SQLException;
	
	void regist(UserRequestDto userRequestDto) throws SQLException;
	
	void update(UserRequestDto userRequestDto) throws SQLException;
	
	void delete(UserRequestDto userRequestDto) throws SQLException;

}
