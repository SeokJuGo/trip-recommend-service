package com.ssafy.enjoytrip.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.mapper.BoardMapper;
import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;
import com.ssafy.enjoytrip.board.model.BoardSearchDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardMapper mapper;

	@Override
	public BoardListDto findAll(BoardSearchDto boardSearchDto) throws Exception {
		String keyword = boardSearchDto.getKeyword();
		String searchType = boardSearchDto.getSearchType();
		
		/* 검색어 쿼리 객체 생성 */
		Map<String, Object> param = new HashMap<String, Object>();
		if (searchType != null && keyword != null) {
			param.put("title", searchType.equals("title") ? keyword : "");
			param.put("userId", searchType.equals("userId") ? keyword : "");
			param.put("content", searchType.equals("content") ? keyword : "");			
		}
		
		/* 페이징 처리 */
		Integer pageNum = boardSearchDto.getPageNum();
		Integer pageSize = boardSearchDto.getPageSize();
		
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 20 : pageSize;
		Integer offset = pageNum * pageSize - pageSize; 		
		
		param.put("offset", offset);
		param.put("pageSize", pageSize);

		int cntTotalElements = mapper.countTotalElements(param);
		int cntTotalPage = (cntTotalElements - 1) / pageSize + 1;

		/* 리턴 객체 생성 */
		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setBoards(mapper.findAll(param));
		boardListDto.setOffset(offset);
		boardListDto.setPageNum(pageNum);
		boardListDto.setPageSize(pageSize);
		boardListDto.setTotalPages(cntTotalPage);
		boardListDto.setTotalElements(cntTotalElements);
		boardListDto.setFirst(pageNum == 1 ? true : false);
		boardListDto.setLast(pageNum == cntTotalPage ? true : false);
		
		return boardListDto;
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
