package com.ssafy.enjoytrip.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("")
	public ResponseEntity<?> findAll(
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content) {
		log.debug("BoardController findAll() function called!!!");
		try {
			List<BoardDto> list = null;
			
			if (userId != null)
				list = boardService.findAllByUserId(userId);
			else if (title != null)
				list = boardService.findAllByTitleContains(title);
			else if (content != null)
				list = boardService.findAllByContentContains(content);
			else
				list = boardService.findAll();

			if (list != null && !list.isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			return new ResponseEntity<>("Data Empty", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		log.debug("BoardController findById() function called!!!");
		try {
			BoardDto dto = boardService.findById(id);
			if (dto != null)
				return new ResponseEntity<>(dto, HttpStatus.OK);
			return new ResponseEntity<>("Data Not Exists", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
