package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.mapper.BoardTypeMapper;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeEntity;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeRequestDto;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardTypeServiceImpl implements BoardTypeService {
	private final BoardTypeMapper boardTypeMapper;
	
	@Override
	public List<BoardTypeResponseDto> findAll(Map<String, Object> params) throws Exception {
		List<BoardTypeEntity> list = boardTypeMapper.findAll(params);
		return list.stream().map(BoardTypeResponseDto::new).collect(Collectors.toList());
	}

	@Override
	public BoardTypeResponseDto findById(Integer id) throws Exception {
		BoardTypeEntity boardTypeEntity = boardTypeMapper.findById(id);
		return new BoardTypeResponseDto(boardTypeEntity);
	}

	@Override
	public BoardTypeResponseDto findByName(String name) throws Exception {
		BoardTypeEntity boardTypeEntity = boardTypeMapper.findByName(name);
		return new BoardTypeResponseDto(boardTypeEntity);
	}

	@Override
	public void insert(BoardTypeRequestDto boardTypeRequestDto) throws Exception {
		BoardTypeEntity boardTypeEntity = boardTypeRequestDto.toEntity();
		boardTypeMapper.insert(boardTypeEntity);
	}

	@Override
	public void update(BoardTypeRequestDto boardTypeRequestDto) throws Exception {
		BoardTypeEntity boardTypeEntity = boardTypeRequestDto.toEntity();
		boardTypeMapper.update(boardTypeEntity);
	}

	@Override
	public void delete(Integer id) throws Exception {
		boardTypeMapper.delete(id);
	}

}
