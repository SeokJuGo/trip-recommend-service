package com.ssafy.enjoytrip.user.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.AuthResponseDto;
import com.ssafy.enjoytrip.user.model.UserEntity;

@Mapper
public interface AuthMapper {

	UserEntity login(AuthRequestDto authRequestDto) throws SQLException;

	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String username) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
