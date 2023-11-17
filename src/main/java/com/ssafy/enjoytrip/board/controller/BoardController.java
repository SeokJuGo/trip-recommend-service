package com.ssafy.enjoytrip.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.board.model.BoardRequestDto;
import com.ssafy.enjoytrip.board.model.BoardResponseDto;
import com.ssafy.enjoytrip.board.model.BoardsResponseDto;
import com.ssafy.enjoytrip.board.service.BoardService;
import com.ssafy.enjoytrip.fileinfo.service.FileInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/board")
@RequiredArgsConstructor
@Api(tags = { "게시판 컨트롤러 API" })
@ApiResponses({
	@ApiResponse(code = 200, message = "OK"),
	@ApiResponse(code = 201, message = "CREATED"),
	@ApiResponse(code = 204, message = "NO CONTENT"),
	@ApiResponse(code = 400, message = "BAD REQUEST"),
	@ApiResponse(code = 401, message = "UNAUTHORIZED"),
	@ApiResponse(code = 403, message = "FORBIDDEN"),
	@ApiResponse(code = 404, message = "NOT FOUND"),
	@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
public class BoardController {
	private final BoardService boardService;
	private final FileInfoService fileInfoService;
	
	@GetMapping("")
	@ApiOperation(value = "게시판 목록 조회", notes = "<h2><b>게시판 목록을 조회한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", required = false, dataType = "Integer", name = "pageNum", value = "페이지 번호"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "Integer", name = "pageSize", value = "페이지 크기"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "String", name = "keyword", value = "검색 키워드"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "String", name = "searchType", value = "검색 타입"),
		@ApiImplicitParam(paramType = "query", required = false, dataType = "String", name = "boardType", value = "게시판 타입")})
	public ResponseEntity<?> findAll(
			@RequestParam(required = false, value = "pageNum") Integer pageNum,
			@RequestParam(required = false, value = "pageSize") Integer pageSize,
			@RequestParam(required = false, value = "keyword") String keyword,
			@RequestParam(required = false, value = "searchType") String searchType,
			@RequestParam(required = false, value = "boardType") String boardType) {
		try {
			log.debug("BoardController findAll() function called!!!");
			log.debug("boardType = {}, pageNum = {}, pageSize = {}, keyword = {}, searchType = {}", boardType, pageNum, pageSize, keyword, searchType);
			Map<String, Object> params = new HashMap<>();
			if (pageNum != null) params.put("pageNum", pageNum);
			if (pageSize != null) params.put("pageSize", pageSize);
			if (keyword != null) params.put("keyword", keyword);
			if (searchType != null) params.put("searchType", searchType);
			if (boardType != null) params.put("boardType", boardType);
			
			BoardsResponseDto responseDto = boardService.findAll(params);
			if (responseDto != null && !responseDto.getBoards().isEmpty())
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "게시글 상세 조회", notes = "<h2><b>게시글 상세를 조회한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "게시글 아이디", required = true, dataType = "Integer", paramType = "path"),
	})
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		try {
			log.debug("BoardController findById() function called!!!");
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
	
	@PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "게시글 작성", notes = "<h2><b>게시글을 작성한다.</b></h2>")
	public ResponseEntity<?> writeBoard(
			@Schema(name = "data", description = "게시글 데이터", required = true, example = "")
			@RequestPart(value = "data") BoardRequestDto requestDto,
			@Schema(name = "files", description = "첨부파일 데이터", required = false, example = "")
			@RequestPart(value = "files", required = false) List<MultipartFile> files) {
		try {
			log.debug("BoardController writeBoard() function called!!!");
			Integer id = boardService.insert(requestDto);
			fileInfoService.insert(id, files);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시글 수정", notes = "<h2><b>게시글을 수정한다.</b></h2>")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateBoard(
			@PathVariable Integer id,
			@RequestBody BoardRequestDto requestDto) {
		try {
			log.debug("BoardController updateBoard() function called!!!");
			boardService.update(id, requestDto);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "게시글 삭제", notes = "<h2><b>게시글을 삭제한다.</b></h2>")
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
