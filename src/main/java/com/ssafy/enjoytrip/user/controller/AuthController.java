package com.ssafy.enjoytrip.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoytrip.ssafy.jwt.model.service.JwtService;
import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.AuthResponseDto;
import com.ssafy.enjoytrip.user.model.UserEntity;
import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;
import com.ssafy.enjoytrip.user.service.AuthService;
import com.ssafy.enjoytrip.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@Api(tags = { "권한정보 컨트롤러 API" })
public class AuthController {
	private AuthService authService;
	private JwtService jwtService;

	public AuthController(AuthService authService, JwtService jwtService) {
		super();
		this.authService = authService;
		this.jwtService = jwtService;
	}

//	@PostMapping("/login")
//	@ApiOperation(value = "로그인", notes = "로그인")
//	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
//			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
//			@ApiResponse(code = 500, message = "Internal Server Error") })
//	public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto, HttpSession httpSession) {
//		log.debug("[UserController] login() function called, authRequestDto = {}", authRequestDto);
//		Map<String, Object> resultMap = new HashMap<>();
//		try {
//			UserEntity loginUser = auth.loginUser(userDto);
//			if (loginUser != null) {
//				logger.debug("token : {}, memberDto : {}", loginUser);
//				String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
//				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());// key, data
//				uservice.deleRefreshToken(userDto.getId()); // 토큰이 중복되지 않도록 삭제 후 삽입
//				uservice.saveRefreshToken(userDto.getId(), refreshToken);
//				logger.debug("로그인 accessToken 정보 : {}", accessToken);
//				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
//				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
		// logger.info("Welcome login!  {}.", userDto);
		// UserDto loginUser = uservice.loginUser(userDto);
		// if(loginUser != null) {
		// 	session.setAttribute("loginUser", loginUser);
		// 	return new ResponseEntity<UserDto>(uservice.getUserInfo(userDto.getId()), HttpStatus.OK);
		// }
		// return new ResponseEntity<UserDto>(uservice.getUserInfo(userDto.getId()), HttpStatus.BAD_REQUEST);
//	}

	@GetMapping("/logout")
	@ApiOperation(value = "로그아웃", notes = "로그아웃")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("")
	@ApiOperation(value = "회원정보 삭제", notes = "회원정보를 삭제한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ResponseEntity<?> delete(@RequestBody AuthRequestDto authRequestDto) {
		log.debug("[UserController] delete() function called, authRequestDto = {}", authRequestDto);
		try {
			authService.delete(authRequestDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
