package com.ssafy.enjoytrip.user.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

@Mapper
public interface AuthMapper {

	UserResponseDto login(AuthRequestDto authRequestDto) throws SQLException;

	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String username) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
