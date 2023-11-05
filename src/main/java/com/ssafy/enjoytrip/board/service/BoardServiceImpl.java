package com.ssafy.enjoytrip.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.mapper.BoardMapper;
import com.ssafy.enjoytrip.board.model.BoardDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardMapper mapper;

	@Override
	public List<BoardDto> findAll() throws Exception {
		return mapper.findAll();
	}

	@Override
	public List<BoardDto> findAllByUserId(String userId) throws Exception {
		return mapper.findAllByUserId(userId);
	}

	@Override
	public List<BoardDto> findAllByTitleContains(String title) throws Exception {
		return mapper.findAllByTitleContains(title);
	}

	@Override
	public List<BoardDto> findAllByContentContains(String content) throws Exception {
		return mapper.findAllByContentContains(content);
	}

	@Override
	public BoardDto findById(int id) throws Exception {
		return mapper.findById(id);
	}

	@Override
	public void writeBoard(BoardDto boardDto) throws Exception {
		mapper.writeBoard(boardDto);
	}

	@Override
	public void updateBoard(BoardDto boardDto) throws Exception {
		mapper.updateBoard(boardDto);
	}

	@Override
	public void deleteBoard(Integer id) throws Exception {
		mapper.deleteBoard(id);
	}

}
