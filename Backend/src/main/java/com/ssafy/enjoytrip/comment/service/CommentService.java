package com.ssafy.enjoytrip.comment.service;

import java.util.List;

import com.ssafy.enjoytrip.comment.model.CommentResponseDto;
import com.ssafy.enjoytrip.comment.model.CommentSaveRequestDto;
import com.ssafy.enjoytrip.comment.model.CommentUpdateRequestDto;

public interface CommentService {

	Integer countByBoardId(Integer boardId) throws Exception;
	
	List<CommentResponseDto> findAllByBoardId(Integer boardId) throws Exception;
	
	List<CommentResponseDto> findAll() throws Exception;
	
	Integer insert(CommentSaveRequestDto requestDto) throws Exception;
	
	Integer update(CommentUpdateRequestDto requestDto) throws Exception;
	
	void delete(Integer id) throws Exception;
	
}
