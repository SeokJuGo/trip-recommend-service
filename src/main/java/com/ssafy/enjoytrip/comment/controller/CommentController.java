package com.ssafy.enjoytrip.comment.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.comment.model.CommentResponseDto;
import com.ssafy.enjoytrip.comment.model.CommentSaveRequestDto;
import com.ssafy.enjoytrip.comment.model.CommentUpdateRequestDto;
import com.ssafy.enjoytrip.comment.service.CommentService;

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
@RequestMapping("/comment")
@RequiredArgsConstructor
@Api(tags = { "댓글 컨트롤러 API" })
@ApiResponses({
	@ApiResponse(code = 200, message = "OK"),
	@ApiResponse(code = 201, message = "CREATED"),
	@ApiResponse(code = 204, message = "NO CONTENT"),
	@ApiResponse(code = 400, message = "BAD REQUEST"),
	@ApiResponse(code = 401, message = "UNAUTHORIZED"),
	@ApiResponse(code = 403, message = "FORBIDDEN"),
	@ApiResponse(code = 404, message = "NOT FOUND"),
	@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
public class CommentController {
	private final CommentService commentService;
	
	@GetMapping("")
	@ApiOperation(value = "댓글 목록 조회", notes = "<h2><b>댓글 목록을 조회한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", required = false, dataType = "Integer", name = "boardId", value = "게시글 PK")})
	public ResponseEntity<?> findAll(@RequestParam(required = false) Integer boardId) {
		try {
			log.debug("[CommentController] findAll() function called, boardId = {}", boardId);
			List<CommentResponseDto> responseDtos = boardId == null
					? commentService.findAll()
							: commentService.findAllByBoardId(boardId);
			if (responseDtos != null && !responseDtos.isEmpty())
				return new ResponseEntity<>(responseDtos, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	@ApiOperation(value = "댓글 삽입", notes = "<h2><b>댓글을 삽입한다.</b></h2>")
	public ResponseEntity<?> insert(@RequestBody CommentSaveRequestDto requestDto) {
		try {
			log.debug("[CommentController] insert() function called, requestDto = {}", requestDto);
			Integer id = commentService.insert(requestDto);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "댓글 수정", notes = "<h2><b>댓글을 수정한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", required = true, dataType = "Integer", name = "id", value = "댓글 PK")})
	public ResponseEntity<?> update(@PathVariable Integer id,
			@RequestBody CommentUpdateRequestDto requestDto) {
		try {
			log.debug("[CommentController] update() function called, id = {}, requestDto = {}", id, requestDto);
			commentService.update(requestDto);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "댓글 삭제", notes = "<h2><b>댓글을 삭제한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", required = true, dataType = "Integer", name = "id", value = "댓글 PK")})
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			log.debug("[CommentController] delete() function called, id = {}", id);
			commentService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
