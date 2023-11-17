package com.ssafy.enjoytrip.boardtype.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.boardtype.mapper.BoardTypeMapper;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeRequestDto;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardTypeServiceImpl implements BoardTypeService {
	private final BoardTypeMapper boardTypeMapper;
	
	@Override
	public Integer count() throws Exception {
		return boardTypeMapper.count();
	}

	@Override
	public List<BoardTypeResponseDto> findAll() throws Exception {
		return boardTypeMapper.findAll().stream()
				.map(BoardTypeResponseDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public void insert(BoardTypeRequestDto requestDto) throws Exception {
		boardTypeMapper.insert(requestDto.toEntity());
	}

	@Override
	public void update(Integer id, BoardTypeRequestDto requestDto) throws Exception {
		boardTypeMapper.update(requestDto.toEntity(id));
	}

	@Override
	public void delete(Integer id) throws Exception {
		boardTypeMapper.delete(id);
	}

}
