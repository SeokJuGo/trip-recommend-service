package com.ssafy.enjoytrip.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.admin.controller.AdminUserController;
import com.ssafy.enjoytrip.member.model.UserDto;
import com.ssafy.enjoytrip.member.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api(tags = {"유저 컨트롤러 API"})
@Slf4j
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ApiOperation(value = "id체크", notes = "id체크하기.")
	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(String id) throws Exception {
		logger.debug("idCheck id : {}", id);
		int cnt = userService.idCheck(id);
		return cnt + "";
	}
	@ApiOperation(value = "회원등록", notes = "회원의 정보를 받아 처리.")
	@PostMapping(value = "/join")
	public ResponseEntity<?> join(@RequestBody UserDto userDto, Model model) {
		logger.debug("userDto info : {}", userDto);
		try {
			userService.joinUser(userDto);
//			return new ResponseEntity<Integer>(1, HttpStatus.OK);
			return new ResponseEntity<Integer>(1, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@ApiOperation(value = "로그인", notes = "아이디랑 패스워드만 작성하시오.")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto user) {
		System.out.println("login gogogogo");
		logger.debug("login map : {}", user);
		try {
			UserDto userDto = userService.loginUser(user);
			logger.debug("login map : {}", userDto);
			if(userDto != null) {
//				session.setAttribute("userinfo", memberDto);
//				
//				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
//				cookie.setPath("/board");
//				if("ok".equals(saveid)) {
//					cookie.setMaxAge(60*60*24*365*40);
//				} else {
//					cookie.setMaxAge(0);
//				}
//				response.addCookie(cookie);
				return new ResponseEntity<Integer>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "로그아웃하기", notes = "로그아웃.")
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Integer>(HttpStatus.OK);
	}
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
