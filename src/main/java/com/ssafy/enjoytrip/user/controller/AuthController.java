package com.ssafy.enjoytrip.user.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;
import com.ssafy.enjoytrip.user.service.AuthService;

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
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "로그인")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), 
	@ApiResponse(code = 204, message = "No Content"),
	@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto, HttpSession httpSession) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserResponseDto loginUser = authService.login(authRequestDto);
			if (loginUser != null) {
				String accessToken = authService.createAccessToken("username", loginUser.getUsername());
				String refreshToken = authService.createRefreshToken("username", loginUser.getUsername());
				authService.deleRefreshToken(authRequestDto.getUsername());
				authService.saveRefreshToken(authRequestDto.getUsername(), refreshToken);
				log.debug("LOGIN : Token 정보  Access {} \n Refresh {}", accessToken,refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				log.error("LOGIN : 사용자가 없음");
				resultMap.put("message", "fail");
			}
		} catch (Exception e) {
			log.error("LOGIN 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);

	}

	@GetMapping(value = "/logout/{username}")
	public ResponseEntity<?> removeToken(@PathVariable("username") String username) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			authService.deleRefreshToken(username);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			log.error("LOGOUT : 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			resultMap.put("message", "fail");
		}
		return new ResponseEntity<>(resultMap,status);

	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody AuthRequestDto authRequestDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		log.debug("token : {}", token);
		if (authService.checkToken(token)) {
			if (token.equals(authService.getRefreshToken(authRequestDto.getUsername()))) {
				String accessToken = authService.createAccessToken("username", authRequestDto.getUsername());
				log.debug("token : {}", accessToken);
				log.debug("REFRESH : 정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			}
		} else {
			log.debug("REFRESH : 리프레쉬토큰 발급 불가");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<>(resultMap, status);
	}

}
