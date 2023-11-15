package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.boardtype.model.BoardTypeRequestDto;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeResponseDto;

public interface BoardTypeService {

	List<BoardTypeResponseDto> findAll(Map<String, Object> params) throws Exception;
	
	BoardTypeResponseDto findById(Integer id) throws Exception;
	
	BoardTypeResponseDto findByName(String name) throws Exception;
	
	void insert(BoardTypeRequestDto boardTypeRequestDto) throws Exception;
	
	void update(BoardTypeRequestDto boardTypeRequestDto) throws Exception;
	
	void delete(Integer id) throws Exception;
	
}
