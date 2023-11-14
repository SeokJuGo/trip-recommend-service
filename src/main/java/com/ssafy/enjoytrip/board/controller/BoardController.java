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
@RequestMapping("/board")
@RequiredArgsConstructor
@Api(tags = { "게시판 컨트롤러 API" })
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("")
	@ApiOperation(value = "게시판 목록 조회", notes = "<h2><b>게시판 목록을 조회한다.</b></h2>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "페이지", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "페이지", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "keyword", value = "검색어", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "searchType", value = "검색타입", required = false, dataType = "String", paramType = "query")})
	public ResponseEntity<?> findAll(
			@RequestParam(required = false, value = "pageNum") Integer pageNum,
			@RequestParam(required = false, value = "pageSize") Integer pageSize,
			@RequestParam(required = false, value = "keyword") String keyword,
			@RequestParam(required = false, value = "searchType") String searchType) {
		log.debug("BoardController findAll() function called!!!");
		log.debug("pageNum = {}, pageSize = {}, keyword = {}, searchType = {}", pageNum, pageSize, keyword, searchType);
		try {
			Map<String, Object> params = new HashMap<>();
			if (pageNum != null) params.put("pageNum", pageNum);
			if (pageSize != null) params.put("pageSize", pageSize);
			if (keyword != null) params.put("keyword", keyword);
			if (searchType != null) params.put("searchType", searchType);
			
			BoardsResponseDto responseDto = boardService.findAll(params);
			if (responseDto != null && !responseDto.getBoards().isEmpty())
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/detail/{id}")
	@ApiOperation(value = "게시글 상세 조회", notes = "<h2><b>게시글 상세를 조회한다.</b></h2>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error"),
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "게시글 아이디", required = true, dataType = "Integer", paramType = "path"),
	})
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		log.debug("BoardController findById() function called!!!");
		try {
			BoardResponseDto responseDto = boardService.findById(id);
			if (responseDto != null)
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			return new ResponseEntity<>("Data Not Exists", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/write")
	@ApiOperation(value = "게시글 작성", notes = "<h2><b>게시글을 작성한다.</b></h2>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ResponseEntity<?> writeBoard(@RequestBody BoardRequestDto requestDto) {
		log.debug("BoardController writeBoard() function called!!!");
		try {
			Integer id = boardService.insert(requestDto);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시글 수정", notes = "<h2><b>게시글을 수정한다.</b></h2>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PutMapping("/update")
	public ResponseEntity<?> updateBoard(@RequestBody BoardRequestDto requestDto) {
		log.debug("BoardController updateBoard() function called!!!");
		try {
			Integer id = boardService.update(requestDto);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "게시글 삭제", notes = "<h2><b>게시글을 삭제한다.</b></h2>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ResponseEntity<?> deleteBoard(@PathVariable("id") Integer id) {
		log.debug("BoardController deleteBoard() function called!!!");
		try {
			boardService.delete(id);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
