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
import com.ssafy.enjoytrip.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final UserMapper userMapper;
	private final BoardMapper boardMapper;
	private final BoardTypeMapper boardTypeMapper;

	@Override
	public BoardsResponseDto findAll(Map<String, Object> requestParams) throws Exception {
		Map<String, Object> params = new HashMap<>();
		
		/* 게시판 타입 파라미터 설정 */
		Integer boardTypeId = (Integer) requestParams.get("boardTypeId");
		if (boardTypeId != null) params.put("boardTypeId", boardTypeId);
		
		/* 검색 쿼리 설정 */
		String searchType = (String) requestParams.get("searchType");
		String searchQuery = (String) requestParams.get("searchQuery");	
		if (searchType != null && searchQuery != null) {
			switch (searchType) {
		        case "title":
		            params.put("title", searchQuery);
		            break;
		        case "content":
		            params.put("content", searchQuery);
		            break;
		        case "nickname":
		        	User user = userMapper.findByUsername(searchQuery);
		        	if (user != null) params.put("userId", user.getId());
		            break;
			}
		}
		
		/* 정렬 쿼리 설정 */
		String sortBy = (String) requestParams.get("sortBy");
		String orderBy = (String) requestParams.get("orderBy");
		params.put("sortBy", sortBy != null ? sortBy : "ID");
		params.put("orderBy", orderBy != null ? orderBy : "DESC");
		
		/* 페이징 쿼리 설정 */
		Integer pageNum = (Integer) requestParams.get("pageNum");
		Integer pageSize = (Integer) requestParams.get("pageSize");
		pageNum = (pageNum == null || pageNum < 1) ? 1 : pageNum;
		pageSize = (pageSize == null || pageSize < 1) ? 20 : pageSize;
		params.put("pageSize", pageSize);
		
		int offset = (pageNum - 1) * pageSize;
		params.put("offset", offset);
		
		int totalElements = boardMapper.count(params);
		int totalPages = (totalElements - 1) / pageSize + 1;

		/* 리턴 객체 생성 */
		List<BoardResponseDto> responseDtos = new ArrayList<>();
		List<Board> boards = boardMapper.findAll(params);
		for (Board board : boards) {
			User user = userMapper.findById(board.getUserId().intValue());
			BoardType boardType = boardTypeMapper.findById(board.getBoardTypeId().intValue());
			responseDtos.add(new BoardResponseDto(board, user, boardType));
		}
				
		return BoardsResponseDto.builder()
				.offset(offset)
				.pageNum(pageNum)
				.pageSize(pageSize)
				.totalPages(totalPages)
				.totalElements(totalElements)
				.first(pageNum == 1 ? true : false)
				.last(pageNum == totalPages ? true : false)
				.boards(responseDtos)
				.build();
	}

	@Override
	public BoardResponseDto findById(Long id) throws Exception {
		Board board = boardMapper.findById(id.intValue());
		board.increateHit();
		boardMapper.update(board);
		
		User user = userMapper.findById(board.getUserId().intValue());
		BoardType boardType = boardTypeMapper.findById(board.getBoardTypeId().intValue());
		return new BoardResponseDto(board, user, boardType);
	}

	@Override
	public Long insert(BoardRequestDto requestDto) throws Exception {
		Board entity = requestDto.toEntity();
		boardMapper.insert(entity);
		return entity.getId();
	}

	@Override
	public Long update(Long id, BoardRequestDto requestDto) throws Exception {
		Board entity = requestDto.toEntity(id);
		boardMapper.update(entity);
		return entity.getId();
	}

	@Override
	public Integer delete(Long id) throws Exception {
		return boardMapper.delete(id.intValue());
	}

}
