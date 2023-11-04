package com.ssafy.enjoytrip.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/")
	public ResponseEntity<?> home() {
		return new ResponseEntity<String>("Welcome Home!!!", HttpStatus.OK);
	}

	@GetMapping("/index")
	public ResponseEntity<?> index() {
		return new ResponseEntity<String>("Welcome Index!!!", HttpStatus.OK);
	}
}
