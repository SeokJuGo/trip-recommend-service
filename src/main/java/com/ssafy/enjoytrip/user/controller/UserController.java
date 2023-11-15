package com.ssafy.enjoytrip.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoytrip.ssafy.jwt.model.service.JwtServiceImpl;
import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;
import com.ssafy.enjoytrip.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = {"유저 컨트롤러 API"})
public class UserController {
	private final UserService userService;
	private JwtServiceImpl jwtService;
	public UserController(UserService userService, JwtServiceImpl jwtService) {
		super();
		this.userService = userService;
		this.jwtService = jwtService;
	}
	@GetMapping("")
	@ApiOperation(value = "회원정보 조회", notes = "회원정보를 조회한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ResponseEntity<?> findByUsername(String username) {
		log.debug("[UserController] findByUsername() function called, username = {}", username);
		try {
			UserResponseDto responseDto = userService.findByUsername(username);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("")
	@ApiOperation(value = "회원정보 등록", notes = "회원정보를 등록한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<?> regist(@RequestBody UserRequestDto userRequestDto) {
		log.debug("[UserController] regist() function called, userRequestDto = {}", userRequestDto);
		try {
			userService.regist(userRequestDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping("")
	@ApiOperation(value = "회원정보 수정", notes = "회원정보를 수정한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ResponseEntity<?> update(@RequestBody UserRequestDto userRequestDto) {
		log.debug("[UserController] update() function called, userRequestDto = {}", userRequestDto);
		try {
			userService.update(userRequestDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	


}
