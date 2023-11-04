package com.ssafy.enjoytrip.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.member.model.UserDto;

@Mapper
public interface UserMapper {

	int idCheck(String id) throws Exception;
	void joinUser (UserDto userDto) throws Exception;
	UserDto loginUser(UserDto userDto) throws Exception;
	
	/* Admin */
	List<UserDto> listUser() throws Exception;
	UserDto getUser(String id) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	void deleteUser(String id) throws Exception;
	
}
