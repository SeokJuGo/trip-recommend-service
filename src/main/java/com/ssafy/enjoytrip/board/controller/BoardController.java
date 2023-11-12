package com.ssafy.enjoytrip.board.controller;

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

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardListDto;
import com.ssafy.enjoytrip.board.model.BoardSearchDto;
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
	
	@ApiOperation(value = "게시판 목록 조회", notes = "<h2><b>게시판 목록을 조회한다.</b></h2>")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "pageNum", value = "페이지", required = false, dataType = "Integer", paramType = "query"),
//		@ApiImplicitParam(name = "pageSize", value = "페이지", required = false, dataType = "Integer", paramType = "query"),
//		@ApiImplicitParam(name = "keyword", value = "검색어", required = false, dataType = "String", paramType = "query"),
//		@ApiImplicitParam(name = "searchType", value = "검색타입", required = false, dataType = "String", paramType = "query"),
//	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("")
	public ResponseEntity<?> findAll(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "searchType", required = false) String searchType) {
		BoardSearchDto boardSearchDto = new BoardSearchDto();
		boardSearchDto.setPageNum(pageNum);
		boardSearchDto.setPageSize(pageSize);
		boardSearchDto.setKeyword(keyword);
		boardSearchDto.setSearchType(searchType);
		
		log.debug("BoardController findAll() function called!!!, {}", boardSearchDto);
		try {
			BoardListDto list = boardService.findAll(boardSearchDto);
			if (list != null && !list.getBoards().isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	@ApiOperation(value = "게시판 목록 조회", notes = "<h2><b>게시판 목록을 조회한다.</b></h2>")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "title", value = "제목", required = false, dataType = "String", paramType = "query"),
//		@ApiImplicitParam(name = "content", value = "내용", required = false, dataType = "String", paramType = "query"),
//		@ApiImplicitParam(name = "userId", value = "작성자", required = false, dataType = "String", paramType = "query"),
//	})
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "OK"),
//		@ApiResponse(code = 204, message = "No Content"),
//		@ApiResponse(code = 400, message = "Bad Request"),
//		@ApiResponse(code = 404, message = "404 Not Found"),
//		@ApiResponse(code = 500, message = "Internal Server Error")
//	})
//	@GetMapping("")
//	public ResponseEntity<?> findAll(
//			@RequestParam(value = "title", required = false) String title,
//			@RequestParam(value = "content", required = false) String content,
//			@RequestParam(value = "userId", required = false) String userId) {
//		log.debug("BoardController findAll() function called!!!");
//		try {
//			List<BoardDto> list = null;
//			
//			if (userId != null)
//				list = boardService.findAllByUserId(userId);
//			else if (title != null)
//				list = boardService.findAllByTitleContains(title);
//			else if (content != null)
//				list = boardService.findAllByContentContains(content);
//			else
//				list = boardService.findAll();
//			
//			if (list != null && !list.isEmpty())
//				return new ResponseEntity<>(list, HttpStatus.OK);
//			return new ResponseEntity<>("Data Empty", HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.debug(e.getMessage());
//			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@ApiOperation(value = "게시글 상세 조회", notes = "<h2><b>게시글 상세를 조회한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "게시글 아이디", required = true, dataType = "Integer", paramType = "path"),
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		log.debug("BoardController findById() function called!!!");
		try {
			BoardDto dto = boardService.findById(id);
			if (dto != null)
				return new ResponseEntity<>(dto, HttpStatus.OK);
			return new ResponseEntity<>("Data Not Exists", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시글 작성", notes = "<h2><b>게시글을 작성한다.</b></h2>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping("/write")
	public ResponseEntity<?> writeBoard(@RequestBody BoardDto boardDto) {
		log.debug("BoardController writeBoard() function called!!!");
		try {
			boardService.writeBoard(boardDto);
			return new ResponseEntity<>(boardDto.getId(), HttpStatus.OK);
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
	public ResponseEntity<?> updateBoard(@RequestBody BoardDto boardDto) {
		log.debug("BoardController updateBoard() function called!!!");
		try {
			boardService.updateBoard(boardDto);
			return new ResponseEntity<>(boardDto.getId(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시글 삭제", notes = "<h2><b>게시글을 삭제한다.</b></h2>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteBoard(@RequestBody BoardDto boardDto) {
		log.debug("BoardController deleteBoard() function called!!!");
		try {
			boardService.deleteBoard(boardDto.getId());
			return new ResponseEntity<>(boardDto.getId(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
