package com.ssafy.enjoytrip.board.service;

import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardRequestDto;
import com.ssafy.enjoytrip.board.model.BoardResponseDto;
import com.ssafy.enjoytrip.board.model.BoardsResponseDto;

public interface BoardService {

	BoardsResponseDto findAll(Map<String, Object> params) throws Exception;

	BoardResponseDto findById(Integer id) throws Exception;

	Integer insert(BoardRequestDto boardRequestDto) throws Exception;
	
	Integer update(BoardRequestDto boardRequestDto) throws Exception;
	
	Integer delete(Integer id) throws Exception;
	
}
