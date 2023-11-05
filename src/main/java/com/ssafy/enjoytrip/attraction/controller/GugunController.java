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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/gugun")
@Api(tags = { "구군 컨트롤러 API" })
public class GugunController {
	private GugunService gugunService;

	public GugunController(GugunService gugunService) {
		super();
		this.gugunService = gugunService;
	}

	@ApiOperation(value = "구군 목록 조회", notes = "<h2><b>구군 목록을 조회한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sidoCode", value = "시도 코드", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "gugunCode", value = "구군 코드", required = false, dataType = "Integer", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("")
	public ResponseEntity<?> findBySidoCode(
			@RequestParam(value = "sidoCode", required = false) Integer sidoCode,
			@RequestParam(value = "gugunCode", required = false) Integer gugunCode) {
		log.debug("GugunController findBySidoCode() called!!!");
		log.debug("sidoCode = {}", sidoCode);
		log.debug("gugunCode = {}", gugunCode);
		try {
			if (sidoCode == null && gugunCode != null)
				return new ResponseEntity<>("sidoCode should be included", HttpStatus.BAD_REQUEST);
			else if (sidoCode != null && gugunCode == null)
				return new ResponseEntity<>(gugunService.findAllBySidoCode(sidoCode), HttpStatus.OK);
			else if (sidoCode != null && gugunCode != null)
				return new ResponseEntity<>(gugunService.findBySidoCodeAndGugunCode(sidoCode, gugunCode), HttpStatus.OK);

			List<GugunDto> list = gugunService.findAll();
			if (list != null && !list.isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			return new ResponseEntity<>("List is Empty, findAll()", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!, findAll()", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
