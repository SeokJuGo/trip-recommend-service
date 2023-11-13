package com.ssafy.enjoytrip.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.UserResponseDto;
import com.ssafy.enjoytrip.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@RequiredArgsConstructor
@Api(tags = {"관리자 컨트롤러 API"})
public class AdminController {
	private final UserService userService;
	
	@GetMapping("/user-list")
	@ApiOperation(value = "회원목록 조회", notes = "회원목록을 조회한다.")
	public ResponseEntity<?> findAll() {
		log.debug("[AdminController] findAll() function called");
		try {
			List<UserResponseDto> responseDtos = userService.findAll();
			if (responseDtos != null && !responseDtos.isEmpty())
				return new ResponseEntity<>(responseDtos, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user")
	@ApiOperation(value = "회원정보 조회", notes = "회원정보를 조회한다.")
	public ResponseEntity<?> findByUsername(String username) {
		log.debug("[AdminController] findByUsername() function called, username = {}", username);
		try {
			UserResponseDto responseDto = userService.findByUsername(username);
			if (responseDto != null)
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
