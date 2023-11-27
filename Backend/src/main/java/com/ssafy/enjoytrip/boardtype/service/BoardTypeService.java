package com.ssafy.enjoytrip.boardtype.service;

import java.util.List;

import com.ssafy.enjoytrip.boardtype.model.BoardTypeRequestDto;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeResponseDto;

public interface BoardTypeService {

	Integer count() throws Exception;
	
	List<BoardTypeResponseDto> findAll() throws Exception;
	
	void insert(BoardTypeRequestDto requestDto) throws Exception;
	
	void update(Integer id, BoardTypeRequestDto requestDto) throws Exception;
	
	void delete(Integer id) throws Exception;
	
}
