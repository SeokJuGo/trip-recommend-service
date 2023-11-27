package com.ssafy.enjoytrip.user.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;
import com.ssafy.enjoytrip.user.service.AuthService;
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
@Api(tags = { "유저 컨트롤러 API" })
public class UserController {
	private final UserService userService;
	private final AuthService authService;
	
	@GetMapping(value = "/{username}")
	@ApiOperation(value = "회원정보 조회", notes = "회원정보를 조회한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<?> findByUsername(@PathVariable("username") String username, Locale locale,
			HttpServletRequest request) {
		log.debug("[UserController] findByName() function called, useremail = {}", username);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (authService.checkToken(request.getHeader("access-token"))) {
			log.info("사용 가능한 토큰");
			try {
				UserResponseDto responseDto = userService.findByUsername(username);
				resultMap.put("message", "success");
				resultMap.put("userInfo", responseDto);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				log.error("유저 정보 가져오기 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰");
			resultMap.put("message", "fail");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<>(resultMap, status);
	}
	@GetMapping(value = "/idcheck/{username}")
	@ApiOperation(value = "username Check", notes = "중복검사를 한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<?> idCheck(@PathVariable("username") String username) {
		log.debug("[UserController] idCheck() function called, username = {}", username);
		try {
			int result = userService.idCheck(username);
			return new ResponseEntity<>(result, HttpStatus.OK);
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
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
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
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<?> delete(@RequestBody UserRequestDto userRequestDto) {
		log.debug("[UserController] delete() function called, authRequestDto = {}", userRequestDto);
		try {
			userService.delete(userRequestDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/email/{email}")
	@ApiOperation(value = "회원정보 조회", notes = "회원정보를 조회한다.")
	public ResponseEntity<?> findByUsername(String email) {
		log.debug("[UserController] findByEmail() function called,email= {}", email);
		try {
			UserResponseDto responseDto = userService.findByEmail(email);
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
