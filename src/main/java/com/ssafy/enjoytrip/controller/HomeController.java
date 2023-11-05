package com.ssafy.enjoytrip.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.admin.controller.AdminUserController;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Api(tags = {"홈 컨트롤러"})
@Slf4j
public class HomeController {
	private AttractionService attractionService ;
	
	public HomeController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}

	@GetMapping("/")
	public ResponseEntity<?> home() {
		try {
			List<AttractionDto> list = attractionService.home();
			return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/index")
	public ResponseEntity<?> index() {
		return new ResponseEntity<String>("Welcome Index!!!", HttpStatus.OK);
	}
}
