package com.ssafy.enjoytrip.board.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.Board;

@Mapper
public interface BoardMapper {

	Integer count(Map<String, Object> params) throws SQLException;

	List<Board> findAll(Map<String, Object> params) throws SQLException;

	Board findById(Integer id) throws SQLException;

	void insert(Board entity) throws SQLException;
	
	void update(Board entity) throws SQLException;
	
	Integer delete(Integer id) throws SQLException;
	
}
