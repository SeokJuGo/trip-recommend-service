package com.ssafy.enjoytrip.user.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserEntity;
import com.ssafy.enjoytrip.user.model.UserRequestDto;

@Mapper
public interface UserMapper {
	
	List<UserEntity> findAll() throws SQLException;
	
	UserEntity findById(Integer id) throws SQLException;
	
	UserEntity findByEmail(String email) throws SQLException;
	
	UserEntity findByUsername(String username) throws SQLException;
	
	void regist(UserRequestDto userRequestDto) throws SQLException;
	
	void update(UserEntity userEntity) throws SQLException;

	void delete(UserRequestDto userRequestDto) throws Exception;
}
