package com.ssafy.enjoytrip.board.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardEntity;

@Mapper
public interface BoardMapper {

	Integer count(Map<String, Object> map) throws SQLException;

	List<BoardEntity> findAll(Map<String, Object> map) throws SQLException;

	BoardEntity findById(Integer id) throws SQLException;

	void insert(BoardEntity boardEntity) throws SQLException;
	
	void update(BoardEntity boardEntity) throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
}
