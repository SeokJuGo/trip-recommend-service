package com.ssafy.enjoytrip.board.service;

import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardSaveRequestDto;
import com.ssafy.enjoytrip.board.model.BoardUpdateRequestDto;
import com.ssafy.enjoytrip.board.model.BoardResponseDto;
import com.ssafy.enjoytrip.board.model.BoardsResponseDto;

public interface BoardService {

	BoardsResponseDto findAll(Map<String, Object> params) throws Exception;

	BoardResponseDto findById(Integer id) throws Exception;

	Integer insert(BoardSaveRequestDto boardRequestDto) throws Exception;
	
	Integer update(BoardUpdateRequestDto boardRequestDto) throws Exception;
	
	Integer delete(Integer id) throws Exception;
	
}
