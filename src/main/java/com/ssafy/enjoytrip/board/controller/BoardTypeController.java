package com.ssafy.enjoytrip.board.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.service.BoardTypeService;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeRequestDto;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/board-type")
@RequiredArgsConstructor
@Api(tags = { "게시판 타입 컨트롤러 API" })
@ApiResponses({
	@ApiResponse(code = 200, message = "OK"),
	@ApiResponse(code = 201, message = "CREATED"),
	@ApiResponse(code = 204, message = "NO CONTENT"),
	@ApiResponse(code = 400, message = "BAD REQUEST"),
	@ApiResponse(code = 401, message = "UNAUTHORIZED"),
	@ApiResponse(code = 403, message = "FORBIDDEN"),
	@ApiResponse(code = 404, message = "NOT FOUND"),
	@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
public class BoardTypeController {
	private final BoardTypeService boardTypeService;
	
	@GetMapping("")
	@ApiOperation(value = "게시판 타입 목록 조회", notes = "<h2><b>게시판 타입 목록을 조회한다.</b></h2>")
	public ResponseEntity<?> findAll() {
		log.debug("[BoardTypeController] findAll() function called");
		try {
			List<BoardTypeResponseDto> list = boardTypeService.findAll(new HashMap<String, Object>());
			if (list != null && !list.isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("")
	@ApiOperation(value = "게시판 타입 삽입", notes = "<h2><b>게시판 타입을 삽입한다.</b></h2>")
	public ResponseEntity<?> insert(@RequestBody BoardTypeRequestDto requestDto) {
		log.debug("[BoardTypeController] insert() function called");
		try {
			boardTypeService.insert(requestDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "게시판 타입 수정", notes = "<h2><b>게시판 타입을 수정한다.</b></h2>")
	public ResponseEntity<?> update(@RequestBody BoardTypeRequestDto requestDto) {
		log.debug("[BoardTypeController] update() function called");
		try {
			boardTypeService.update(requestDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "게시판 타입 삭제", notes = "<h2><b>게시판 타입을 삭제한다.</b></h2>")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		log.debug("[BoardTypeController] findAll() function called");
		try {
			boardTypeService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
