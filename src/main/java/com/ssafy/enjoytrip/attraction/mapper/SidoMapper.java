package com.ssafy.enjoytrip.attraction.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.SidoDto;

@Mapper
public interface SidoMapper {
	SidoDto getSido(int sidoCode) throws SQLException;

	List<SidoDto> listSido() throws SQLException;
}
