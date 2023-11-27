package com.ssafy.enjoytrip.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.BoardRequestDto;
import com.ssafy.enjoytrip.board.model.BoardResponseDto;
import com.ssafy.enjoytrip.board.model.BoardsResponseDto;
import com.ssafy.enjoytrip.board.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/share")
@RequiredArgsConstructor
@Api(tags = { "정보공유 게시판 컨트롤러 API" })
@ApiResponses({
	@ApiResponse(code = 200, message = "OK"),
	@ApiResponse(code = 201, message = "CREATED"),
	@ApiResponse(code = 204, message = "NO CONTENT"),
	@ApiResponse(code = 400, message = "BAD REQUEST"),
	@ApiResponse(code = 401, message = "UNAUTHORIZED"),
	@ApiResponse(code = 403, message = "FORBIDDEN"),
	@ApiResponse(code = 404, message = "NOT FOUND"),
	@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
public class ShareController {
	
	private final BoardService boardService;
	
	@GetMapping("")
	@ApiOperation(value = "정보공유 게시판 목록 조회", notes = "<h2><b>정보공유 게시판 목록을 조회한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", required = false, dataType = "String", name = "sortBy", value = "정렬 기준"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "String", name = "orderBy", value = "정렬 쿼리"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "Integer", name = "pageNum", value = "페이지 번호"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "Integer", name = "pageSize", value = "페이지 크기"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "String", name = "searchType", value = "검색 기준"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "String", name = "searchQuery", value = "검색 쿼리")})
	public ResponseEntity<?> fetchShares(
			@RequestParam(required = false, value = "sortBy") String sortBy,
			@RequestParam(required = false, value = "orderBy") String orderBy,
			@RequestParam(required = false, value = "pageNum") Integer pageNum, 
			@RequestParam(required = false, value = "pageSize") Integer pageSize,
			@RequestParam(required = false, value = "searchType") String searchType,
			@RequestParam(required = false, value = "searchQuery") String searchQuery) {
		try {
			log.debug("[ShareController] fetchShares() called,");
			log.debug("sortBy = {}, orderBy = {}", sortBy, orderBy);
			log.debug("pageNum = {}, pageSize = {}", pageNum, pageSize);
			log.debug("searchType = {}, searchQuery = {}", searchType, searchQuery);
			
			Map<String, Object> params = new HashMap<>();			
			params.put("boardTypeId", 4);
			
			if (sortBy != null) params.put("sortBy", sortBy);
			if (orderBy != null) params.put("orderBy", orderBy);
			if (pageNum != null) params.put("pageNum", pageNum);
			if (pageSize != null) params.put("pageSize", pageSize);
			if (searchType != null) params.put("searchType", searchType);
			if (searchQuery != null) params.put("searchQuery", searchQuery);
			
			BoardsResponseDto responseDtos = boardService.findAll(params);
			if (responseDtos != null && !responseDtos.getBoards().isEmpty())
				return new ResponseEntity<>(responseDtos, HttpStatus.OK);				
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "정보공유 게시글 조회", notes = "<h2><b>정보공유 게시글 상세를 조회한다.</b></h2>")
	@ApiImplicitParam(required = true, paramType = "path", dataType = "Integer", name = "id", value = "게시글 아이디")
	public ResponseEntity<?> fetchShare(@PathVariable Long id) {
		try {
			log.debug("[ShareController] fetchShare() called, id = {}", id);
			BoardResponseDto responseDto = boardService.findById(id);
			if (responseDto != null)
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("")
	@ApiOperation(value = "정보공유 게시글 작성", notes = "<h2><b>정보공유 게시글을 작성한다.</b></h2>")
	public ResponseEntity<?> insertShare(@RequestBody BoardRequestDto requestDto) {
		try {
			log.debug("[ShareController] insertShare() called, requestDto = {}", requestDto);
			Long id = boardService.insert(requestDto);
			if (id != null) return new ResponseEntity<>(id, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "정보공유 게시글 수정", notes = "<h2><b>정보공유 게시글을 수정한다.</b></h2>")
	@ApiImplicitParam(required = true, paramType = "path", dataType = "Integer", name = "id", value = "게시글 아이디")
	public ResponseEntity<?> updateShare(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
		try {
			log.debug("[ShareController] updateShare() called, id = {}, requestDto = {}", id, requestDto);
			Long rid = boardService.update(id, requestDto);
			if (rid != null) return new ResponseEntity<>(rid, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "정보공유 게시글 삭제", notes = "<h2><b>정보공유 게시글을 삭제한다.</b></h2>")
	@ApiImplicitParam(required = true, paramType = "path", dataType = "Integer", name = "id", value = "게시글 아이디")
	public ResponseEntity<?> deleteShare(@PathVariable Long id) {
		try {
			log.debug("[ShareController] deleteShare() called");
			Integer result = boardService.delete(id);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
