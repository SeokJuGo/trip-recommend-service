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

import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;
import com.ssafy.enjoytrip.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = {"유저 컨트롤러 API"})
public class UserController {
	private final UserService userService;
	
	@GetMapping("")
	@ApiOperation(value = "회원정보 조회", notes = "회원정보를 조회한다.")
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
	
	@DeleteMapping("")
	@ApiOperation(value = "회원정보 삭제", notes = "회원정보를 삭제한다.")
	public ResponseEntity<?> delete(HttpSession httpSession) {
		log.debug("[UserController] delete() function called, id = {}", httpSession.getAttribute("id"));
		try {
			userService.delete((Integer) httpSession.getAttribute("id"));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "로그인")
	public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto, HttpSession httpSession) {
		log.debug("[UserController] login() function called, authRequestDto = {}", authRequestDto);
		try {
			UserResponseDto responseDto = userService.login(authRequestDto);
			if (responseDto != null) {
				// 추후 AuthResponseDto(JWT accessToken 및 refreshToken)를 반환하는 것으로 수정 필요
				httpSession.setAttribute("user", responseDto);
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/logout")
	@ApiOperation(value = "로그아웃", notes = "로그아웃")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
