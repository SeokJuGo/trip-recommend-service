package com.ssafy.enjoytrip.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.comment.mapper.CommentMapper;
import com.ssafy.enjoytrip.comment.model.Comment;
import com.ssafy.enjoytrip.comment.model.CommentResponseDto;
import com.ssafy.enjoytrip.comment.model.CommentSaveRequestDto;
import com.ssafy.enjoytrip.comment.model.CommentUpdateRequestDto;
import com.ssafy.enjoytrip.user.mapper.UserMapper;
import com.ssafy.enjoytrip.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final UserMapper userMapper;
	
	private final CommentMapper commentMapper;

	@Override
	public Integer countByBoardId(Integer boardId) throws Exception {
		return commentMapper.countByBoardId(boardId);
	}
	
	@Override
	public List<CommentResponseDto> findAllByBoardId(Integer boardId) throws Exception {
		List<Comment> comments = commentMapper.findAllByBoardId(boardId);
		List<CommentResponseDto> responseDtos = new ArrayList<>();
		for (Comment comment : comments) {
			User user = userMapper.findById(comment.getUserId().intValue());
			responseDtos.add(new CommentResponseDto(comment, user));
		}
		return responseDtos;
	}

	@Override
	public List<CommentResponseDto> findAll() throws Exception {
		List<Comment> comments = commentMapper.findAll();
		List<CommentResponseDto> responseDtos = new ArrayList<>();
		for (Comment comment : comments) {
			User user = userMapper.findById(comment.getUserId().intValue());
			responseDtos.add(new CommentResponseDto(comment, user));
		}
		return responseDtos;
	}

	@Override
	public Integer insert(CommentSaveRequestDto requestDto) throws Exception {
		Comment comment = requestDto.toEntity();
		commentMapper.insert(comment);
		return comment.getId().intValue();
	}

	@Override
	public Integer update(CommentUpdateRequestDto requestDto) throws Exception {
		Comment comment = requestDto.toEntity();
		commentMapper.update(comment);
		return comment.getId().intValue();
	}

	@Override
	public void delete(Integer id) throws Exception {
		commentMapper.delete(id);
	}



}
