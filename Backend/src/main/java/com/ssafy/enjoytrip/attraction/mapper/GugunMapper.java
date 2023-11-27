package com.ssafy.enjoytrip.attraction.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.GugunDto;

@Mapper
public interface GugunMapper {
	List<GugunDto> findAll() throws SQLException;

	List<GugunDto> findAllBySidoCode(int sidoCode) throws SQLException;

	GugunDto findBySidoCodeAndGugunCode(int sidoCode, int gugunCode) throws SQLException;
}
