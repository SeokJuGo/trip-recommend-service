package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.SidoDto;
import com.ssafy.enjoytrip.attraction.service.SidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@Api(tags = {"시도 관리"})
public class SidoController {
	private SidoService sidoService;

	public SidoController(SidoService sidoService) {
		super();
		this.sidoService = sidoService;
	}

	@GetMapping("/sido")
	@ApiOperation(value = "시도 조회", notes = "시도 정보를 조회한다.")
	@ApiResponse(code = 200, message = "This is ok indeed")
	public ResponseEntity<?> listSido() {
		log.debug("SidoController listSido() called!!!"); 
		try {
			List<SidoDto> list = sidoService.listSido();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<>(list, HttpStatus.OK);
			}
			return new ResponseEntity<String>("List is Empty!!!, listSido()", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error Occurred!!!, listSido()", HttpStatus.OK);
		}
	}
}
