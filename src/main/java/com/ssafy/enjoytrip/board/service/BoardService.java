package com.ssafy.enjoytrip.board.service;

import java.util.List;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;
import com.ssafy.enjoytrip.board.model.BoardSearchDto;

public interface BoardService {

	BoardListDto findAll(BoardSearchDto boardSearchDto) throws Exception;

	List<BoardDto> findAllByUserId(String userId) throws Exception;

	List<BoardDto> findAllByTitleContains(String title) throws Exception;

	List<BoardDto> findAllByContentContains(String content) throws Exception;

	BoardDto findById(int id) throws Exception;

	void writeBoard(BoardDto boardDto) throws Exception;
	
	void updateBoard(BoardDto boardDto) throws Exception;
	
	void deleteBoard(Integer id) throws Exception;
	
}
