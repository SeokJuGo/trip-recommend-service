package com.ssafy.enjoytrip.user.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserEntity;

@Mapper
public interface UserMapper {
	
	List<UserEntity> findAll() throws SQLException;
	
	UserEntity findByUsername(String username) throws SQLException;
	
	void regist(UserEntity userEntity) throws SQLException;
	
	void update(UserEntity userEntity) throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
}
