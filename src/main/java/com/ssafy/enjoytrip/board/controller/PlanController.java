package com.ssafy.enjoytrip.board.controller;

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

import com.ssafy.enjoytrip.board.model.PlanRequestDto;
import com.ssafy.enjoytrip.board.model.PlanResponseDto;
import com.ssafy.enjoytrip.board.service.PlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/plan")
@RequiredArgsConstructor
@Api(tags = { "여행계획 게시판 컨트롤러 API" })
@ApiResponses({
	@ApiResponse(code = 200, message = "OK"),
	@ApiResponse(code = 201, message = "CREATED"),
	@ApiResponse(code = 204, message = "NO CONTENT"),
	@ApiResponse(code = 400, message = "BAD REQUEST"),
	@ApiResponse(code = 401, message = "UNAUTHORIZED"),
	@ApiResponse(code = 403, message = "FORBIDDEN"),
	@ApiResponse(code = 404, message = "NOT FOUND"),
	@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
public class PlanController {
	
	private final PlanService planService;
	
	@GetMapping("/{boardId}")
	public ResponseEntity<?> fetchPlans(@PathVariable Long boardId) {
		try {
			log.debug("[PlanController] fetchPlans() called, boardId = {}", boardId);
			List<PlanResponseDto> responseDtos = planService.findAllByBoardId(boardId);
			if (responseDtos != null && !responseDtos.isEmpty())
				return new ResponseEntity<>(responseDtos, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> insertPlans(@RequestBody(required = false) List<PlanRequestDto> requestDtos) {
		try {
			log.debug("[PlanController] insertPlans() called, requestDtos = {}", requestDtos);
			if (requestDtos != null && !requestDtos.isEmpty())
				for (PlanRequestDto requestDto : requestDtos)
					planService.insert(requestDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{boardId}")
	public ResponseEntity<?> updatePlans(@PathVariable(required = true) Long boardId,
			@RequestBody(required = false) List<PlanRequestDto> requestDtos) {
		try {
			log.debug("[PlanController] updatePlans() called, boardId = {}", boardId);
			planService.deleteAllByBoardId(boardId);
			if (requestDtos != null && !requestDtos.isEmpty())
				for (PlanRequestDto requestDto : requestDtos)	
					planService.insert(requestDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> deletePlans(@PathVariable(required = true) Long boardId) {
		try {
			log.debug("[PlanController] deletePlans() called, boardId = {}", boardId);
			Integer result = planService.deleteAllByBoardId(boardId);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
