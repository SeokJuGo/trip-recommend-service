package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.SidoDto;
import com.ssafy.enjoytrip.attraction.service.SidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/sido")
@Api(tags = { "시도 컨트롤러 API" })
public class SidoController {
	private SidoService sidoService;

	public SidoController(SidoService sidoService) {
		super();
		this.sidoService = sidoService;
	}

	@ApiOperation(value = "시도 목록 조회", notes = "<h2><b>시도 목록을 조회한다.</b></h2>")
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "404 Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping("")
	public ResponseEntity<?> listSido() {
		log.debug("SidoController listSido() function called!!!");
		try {
			List<SidoDto> list = sidoService.listSido();
			if (list != null && !list.isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
