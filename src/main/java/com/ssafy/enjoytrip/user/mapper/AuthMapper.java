package com.ssafy.enjoytrip.user.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserEntity;

@Repository
public interface AuthMapper {

	UserEntity login(AuthRequestDto authRequestDto) throws SQLException;

	void delete(AuthRequestDto authRequestDto) throws Exception;

	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String userid) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
