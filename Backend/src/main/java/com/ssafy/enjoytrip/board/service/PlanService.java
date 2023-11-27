package com.ssafy.enjoytrip.board.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.board.model.PlanRequestDto;
import com.ssafy.enjoytrip.board.model.PlanResponseDto;

public interface PlanService {
	
	Integer count(Long boardId) throws SQLException;
	
	List<PlanResponseDto> findAllByBoardId(Long boardId) throws SQLException;
	
	Long insert(PlanRequestDto requestDto) throws SQLException;
	
	Long update(Long id, PlanRequestDto requestDto) throws SQLException;
	
	Integer delete(Long id) throws SQLException;
	
	Integer deleteAllByBoardId(Long boardId) throws SQLException;
	
}
