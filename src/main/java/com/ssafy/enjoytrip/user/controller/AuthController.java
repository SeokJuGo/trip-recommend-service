package com.ssafy.enjoytrip.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "로그인")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "404 Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto, HttpSession httpSession) {
		log.debug("[UserController] login() function called, authRequestDto = {}", authRequestDto);
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		try {
			UserEntity loginUser = authService.login(authRequestDto);
			if (loginUser != null) {
				log.debug("token : {}, memberDto : {}", loginUser);
				String accessToken = authService.createAccessToken("userid", loginUser.getId());// key, data
				String refreshToken = authService.createRefreshToken("userid", loginUser.getId());// key, data
				authService.deleRefreshToken(authRequestDto.getId()); // 토큰이 중복되지 않도록 삭제 후 삽입
				authService.saveRefreshToken(authRequestDto.getId(), refreshToken);
				log.debug("로그인 accessToken 정보 : {}", accessToken);
				log.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			log.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}

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
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody AuthRequestDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (authService.checkToken(token)) {
			if (token.equals(authService.getRefreshToken(memberDto.getId()))) {
				String accessToken = authService.createAccessToken("userid", memberDto.getId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping(value = "/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			authService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
}
