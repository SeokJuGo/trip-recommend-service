package com.ssafy.enjoytrip.board.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardTypeEntity;

@Mapper
public interface BoardTypeMapper {

	Integer count(Map<String, Object> map) throws SQLException;

	List<BoardTypeEntity> findAll(Map<String, Object> map) throws SQLException;
	
	BoardTypeEntity findById(Integer id) throws SQLException;
	
	BoardTypeEntity findByName(String name) throws SQLException;
	
	void insert(BoardTypeEntity boardTypeEntity) throws SQLException;
	
	void update(BoardTypeEntity boardTypeEntity) throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
}
