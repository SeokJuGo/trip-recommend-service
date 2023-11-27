package com.ssafy.enjoytrip.boardtype.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.boardtype.model.BoardType;

@Mapper
public interface BoardTypeMapper {
	
	Integer count() throws SQLException;
	
	List<BoardType> findAll() throws SQLException;
	
	BoardType findById(Integer id) throws SQLException;
	
	void insert(BoardType entity) throws SQLException;
	
	void update(BoardType entity) throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
}
