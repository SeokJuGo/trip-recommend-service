package com.ssafy.enjoytrip.board.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardDto;

@Mapper
public interface BoardMapper {

	Integer countTotalElements(Map<String, Object> map) throws SQLException;

	List<BoardDto> findAll(Map<String, Object> map) throws SQLException;

	List<BoardDto> findAllByUserId(String userId) throws SQLException;

	List<BoardDto> findAllByTitleContains(String title) throws SQLException;

	List<BoardDto> findAllByContentContains(String content) throws SQLException;

	BoardDto findById(int id) throws SQLException;

	void writeBoard(BoardDto boardDto) throws SQLException;
	
	void updateBoard(BoardDto boardDto) throws SQLException;
	
	void deleteBoard(Integer id) throws SQLException;
	
	
}
