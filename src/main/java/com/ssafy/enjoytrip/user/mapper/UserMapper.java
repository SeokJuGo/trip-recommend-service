package com.ssafy.enjoytrip.user.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.User;
import com.ssafy.enjoytrip.user.model.UserRequestDto;

@Mapper
public interface UserMapper {
	
	List<User> findAll() throws SQLException;
	
	User findById(Integer id) throws SQLException;
	
	User findByEmail(String email) throws SQLException;
	
	User findByUsername(String username) throws SQLException;
	
	void regist(UserRequestDto userRequestDto) throws SQLException;
	
	void update(User userEntity) throws SQLException;

	void delete(UserRequestDto userRequestDto) throws Exception;

	int idCheck(String username) throws SQLException;
}

