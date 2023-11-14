package com.ssafy.enjoytrip.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.mapper.BoardMapper;
import com.ssafy.enjoytrip.board.mapper.BoardTypeMapper;
import com.ssafy.enjoytrip.board.model.BoardEntity;
import com.ssafy.enjoytrip.board.model.BoardRequestDto;
import com.ssafy.enjoytrip.board.model.BoardResponseDto;
import com.ssafy.enjoytrip.board.model.BoardTypeEntity;
import com.ssafy.enjoytrip.board.model.BoardsResponseDto;
import com.ssafy.enjoytrip.user.mapper.UserMapper;
import com.ssafy.enjoytrip.user.model.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final UserMapper userMapper;
	private final BoardMapper boardMapper;
	private final BoardTypeMapper boardTypeMapper;

	@Override
	public BoardsResponseDto findAll(Map<String, Object> requestParams) throws Exception {
		String keyword = (String) requestParams.get("keyword");
		String searchType = (String) requestParams.get("searchType");
		
		/* 검색어 쿼리 객체 생성 */
		Map<String, Object> params = new HashMap<String, Object>();
		if (searchType != null && keyword != null) {
			if (searchType.equals("title")) params.put("title", keyword);
			else if (searchType.equals("content")) params.put("content", keyword);
			else if (searchType.equals("nickname")) params.put("nickname", keyword);
		}
		
		/* 페이징 처리 */
		Integer pageNum = (Integer) requestParams.get("pageNum");
		Integer pageSize = (Integer) requestParams.get("pageSize");
		
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 20 : pageSize;
		Integer offset = pageNum * pageSize - pageSize; 		
		
		params.put("offset", offset);
		params.put("pageSize", pageSize);

		int cntTotalElements = boardMapper.count(params);
		int cntTotalPages = (cntTotalElements - 1) / pageSize + 1;

		/* 리턴 객체 생성 */
		List<BoardResponseDto> boards = new ArrayList<>();
		List<BoardEntity> boardEntities = boardMapper.findAll(params);
		for (BoardEntity boardEntity : boardEntities) {
			Integer userId = boardEntity.getUserId().intValue();
			Integer boardTypeId = boardEntity.getBoardTypeId().intValue();
			UserEntity userEntity = userMapper.findById(userId);
			BoardTypeEntity boardTypeEntity = boardTypeMapper.findById(boardTypeId);
			boards.add(new BoardResponseDto(boardEntity, userEntity, boardTypeEntity));
		}
				
		BoardsResponseDto responseDto = BoardsResponseDto.builder()
				.offset(offset)
				.pageNum(pageNum)
				.pageSize(pageSize)
				.totalPages(cntTotalPages)
				.totalElements(cntTotalElements)
				.first(pageNum == 1 ? true : false)
				.last(pageNum == cntTotalPages ? true : false)
				.boards(boards)
				.build();
		
		return responseDto;
	}

	@Override
	public BoardResponseDto findById(Integer id) throws Exception {
		BoardEntity boardEntity = boardMapper.findById(id);
		Integer userId = boardEntity.getUserId().intValue();
		Integer boardTypeId = boardEntity.getBoardTypeId().intValue();
		
		UserEntity userEntity = userMapper.findById(userId);
		BoardTypeEntity boardTypeEntity = boardTypeMapper.findById(boardTypeId);
		return new BoardResponseDto(boardEntity, userEntity, boardTypeEntity);
	}

	@Override
	public Integer insert(BoardRequestDto boardRequestDto) throws Exception {
		String username = boardRequestDto.getUsername();
		String boardType = boardRequestDto.getBoardType();
		
		UserEntity userEntity = userMapper.findByUsername(username);
		BoardTypeEntity boardTypeEntity = boardTypeMapper.findByName(boardType);
		BoardEntity boardEntity = BoardEntity.builder()
				.title(boardRequestDto.getTitle())
				.content(boardRequestDto.getContent())
				.userId(userEntity.getId())
				.boardTypeId(boardTypeEntity.getId())
				.build();
		
		boardMapper.insert(boardEntity);
		return boardEntity.getId().intValue();
	}

	@Override
	public Integer update(BoardRequestDto boardRequestDto) throws Exception {
		String username = boardRequestDto.getUsername();
		String boardType = boardRequestDto.getBoardType();
		
		UserEntity userEntity = userMapper.findByUsername(username);
		BoardTypeEntity boardTypeEntity = boardTypeMapper.findByName(boardType);
		BoardEntity boardEntity = BoardEntity.builder()
				.id(boardRequestDto.getId())
				.title(boardRequestDto.getTitle())
				.content(boardRequestDto.getContent())
				.userId(boardRequestDto.getId())
				.boardTypeId(boardTypeEntity.getId())
				.build();
		
		boardMapper.update(boardEntity);
		return boardEntity.getId().intValue();
	}

	@Override
	public Integer delete(Integer id) throws Exception {
		boardMapper.delete(id);
		return id;
	}

}
