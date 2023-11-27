package com.ssafy.enjoytrip.board.service;

import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardRequestDto;
import com.ssafy.enjoytrip.board.model.BoardResponseDto;
import com.ssafy.enjoytrip.board.model.BoardsResponseDto;

public interface ShareService {
	
	BoardsResponseDto findAll(Map<String, Object> requestParams) throws Exception;
	
	BoardResponseDto findById(Long id) throws Exception;
	
	Long insert(BoardRequestDto requestDto) throws Exception;
	
	Long update(Long id, BoardRequestDto requestDto) throws Exception;
	
	Integer delete(Long id) throws Exception;
	
}
