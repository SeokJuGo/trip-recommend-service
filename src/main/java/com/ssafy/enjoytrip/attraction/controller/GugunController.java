package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.GugunDto;
import com.ssafy.enjoytrip.attraction.service.GugunService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/gugun")
public class GugunController {
	private GugunService gugunService;

	public GugunController(GugunService gugunService) {
		super();
		this.gugunService = gugunService;
	}

	@GetMapping("")
	public ResponseEntity<?> findBySidoCode(@RequestParam(value = "sidoCode", required = false) Integer sidoCode,
			@RequestParam(value = "gugunCode", required = false) Integer gugunCode) {
		log.debug("GugunController findBySidoCode() called!!!");
		log.debug("sidoCode = {}", sidoCode);
		log.debug("gugunCode = {}", gugunCode);
		try {
			if (gugunCode != null && sidoCode != null)
				return new ResponseEntity<>(gugunService.findBySidoCodeAndGugunCode(sidoCode, gugunCode), HttpStatus.OK);
			else if (sidoCode != null)
				return new ResponseEntity<>(gugunService.findAllBySidoCode(sidoCode), HttpStatus.OK);

			List<GugunDto> list = gugunService.findAll();
			if (list != null && !list.isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			return new ResponseEntity<>("List is Empty, findAll()", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error Occurred!!!, findAll()", HttpStatus.OK);
		}
	}

}
