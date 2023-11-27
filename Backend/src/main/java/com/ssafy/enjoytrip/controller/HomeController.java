package com.ssafy.enjoytrip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Api(tags = {"홈 컨트롤러 API"})
public class HomeController {
	private final AttractionService attractionService ;
	
	@GetMapping("/")
	public ResponseEntity<?> home() {
		log.debug("[HomeController] home() function called");
		try {
			List<AttractionDto> list = attractionService.findAllHotAttraction();
			return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/index")
	public ResponseEntity<?> index() {
		log.debug("[HomeController] index() function called");
		return new ResponseEntity<String>("Welcome Index!!!", HttpStatus.OK);
	}
}
