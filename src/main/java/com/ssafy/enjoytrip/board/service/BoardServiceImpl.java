package com.ssafy.enjoytrip.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.mapper.BoardMapper;
import com.ssafy.enjoytrip.board.model.Board;
import com.ssafy.enjoytrip.board.model.BoardRequestDto;
import com.ssafy.enjoytrip.board.model.BoardResponseDto;
import com.ssafy.enjoytrip.board.model.BoardsResponseDto;
import com.ssafy.enjoytrip.boardtype.mapper.BoardTypeMapper;
import com.ssafy.enjoytrip.boardtype.model.BoardType;
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
		Map<String, Object> params = new HashMap<String, Object>();
		
		/* 게시판 타입 파라미터 처리 */
		Long boardTypeId = (Long) requestParams.get("boardTypeId");
		if (boardTypeId != null)
			params.put("boardTypeId", boardTypeId);
//		String boardType = (String) requestParams.get("boardType"); 
//		if (boardType != null && !boardType.equals("")) {
//			Long boardTypeId = boardTypeMapper.findByName(boardType).getId();
//			params.put("boardTypeId", boardTypeId);
//		}
		
		/* 검색어 쿼리 객체 생성 */
		String keyword = (String) requestParams.get("keyword");
		String searchType = (String) requestParams.get("searchType");		
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
		List<BoardResponseDto> responseDtos = new ArrayList<>();
		List<Board> boards = boardMapper.findAll(params);
		for (Board board : boards) {
			UserEntity user = userMapper.findById(board.getUserId().intValue());
			BoardType boardType = boardTypeMapper.findById(board.getBoardTypeId().intValue());
			responseDtos.add(new BoardResponseDto(board, user, boardType));
		}
//		List<BoardResponseDto> boards = new ArrayList<>();
//		List<Board> boardEntities = boardMapper.findAll(params);
//		for (Board boardEntity : boardEntities) {
//			Integer userId = boardEntity.getUserId().intValue();
//			Integer boardTypeId = boardEntity.getBoardTypeId().intValue();
//			UserEntity userEntity = userMapper.findById(userId);
//			BoardType boardTypeEntity = boardTypeMapper.findById(boardTypeId);
//			boards.add(new BoardResponseDto(boardEntity, userEntity, boardTypeEntity));
//		}
				
		return BoardsResponseDto.builder()
				.offset(offset)
				.pageNum(pageNum)
				.pageSize(pageSize)
				.totalPages(cntTotalPages)
				.totalElements(cntTotalElements)
				.first(pageNum == 1 ? true : false)
				.last(pageNum == cntTotalPages ? true : false)
				.boards(responseDtos)
				.build();
	}

	@Override
	public BoardResponseDto findById(Integer id) throws Exception {
		Board board = boardMapper.findById(id);
		UserEntity user = userMapper.findById(board.getUserId().intValue());
		BoardType boardType = boardTypeMapper.findById(board.getBoardTypeId().intValue());
		return new BoardResponseDto(board, user, boardType);
		
//		Board boardEntity = boardMapper.findById(id);
//		Integer userId = boardEntity.getUserId().intValue();
//		Integer boardTypeId = boardEntity.getBoardTypeId().intValue();
//		
//		UserEntity userEntity = userMapper.findById(userId);
//		BoardType boardTypeEntity = boardTypeMapper.findById(boardTypeId);
//		return new BoardResponseDto(boardEntity, userEntity, boardTypeEntity);
	}

	@Override
	public Integer insert(BoardRequestDto requestDto) throws Exception {
		Board entity = requestDto.toEntity();
		boardMapper.insert(entity);
		return entity.getId().intValue();
		
//		String username = boardRequestDto.getUsername();
//		String boardType = boardRequestDto.getBoardType();
//		
//		UserEntity userEntity = userMapper.findByUsername(username);
//		BoardType boardTypeEntity = boardTypeMapper.findByName(boardType);
//		Board boardEntity = Board.builder()
//				.title(boardRequestDto.getTitle())
//				.content(boardRequestDto.getContent())
//				.userId(userEntity.getId())
//				.boardTypeId(boardTypeEntity.getId())
//				.build();
//		
//		boardMapper.insert(boardEntity);
//		return boardEntity.getId().intValue();
	}

	@Override
	public Integer update(Integer id, BoardRequestDto requestDto) throws Exception {
		Board entity = requestDto.toEntity(id);
		boardMapper.update(entity);
		return entity.getId().intValue();
		
//		String username = boardRequestDto.getUsername();
//		String boardType = boardRequestDto.getBoardType();
//		
//		UserEntity userEntity = userMapper.findByUsername(username);
//		BoardType boardTypeEntity = boardTypeMapper.findByName(boardType);
//		Board boardEntity = Board.builder()
//				.id(boardRequestDto.getId())
//				.title(boardRequestDto.getTitle())
//				.content(boardRequestDto.getContent())
//				.userId(userEntity.getId())
//				.boardTypeId(boardTypeEntity.getId())
//				.build();
//		
//		boardMapper.update(boardEntity);
//		return boardEntity.getId().intValue();
	}

	@Override
	public void delete(Integer id) throws Exception {
		boardMapper.delete(id);
	}

}
