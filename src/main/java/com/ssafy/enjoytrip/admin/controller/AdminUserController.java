package com.ssafy.enjoytrip.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.member.model.UserDto;
import com.ssafy.enjoytrip.member.model.service.UserService2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
//@Controller
@RequestMapping("/admin")
@CrossOrigin("*")
@Api(tags = {"어드민 컨트롤러"})
@Slf4j
public class AdminUserController {

//	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);

	private UserService2 userService;

	@Autowired
	public AdminUserController(UserService2 userService) {
		this.userService = userService;
	}


	@ApiOperation(value = "회원목록", notes = "회원의 <big>전체 목록</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/user")
	public ResponseEntity<?> userList() {
		log.debug("userList call");
		try {
			List<UserDto> list = userService.listUser();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@ApiOperation(value = "회원등록", notes = "회원의 정보를 받아 처리.")
	@PostMapping(value = "/user")
	public ResponseEntity<?> userRegister(@RequestBody UserDto userDto) {
		log.debug("userRegister memberDto : {}", userDto);
		try {
			userService.joinUser(userDto);
			List<UserDto> list = userService.listUser();
			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@ApiOperation(value = "회원정보", notes = "회원한명에 대한 정보.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userid", value = "아이디", required = true, dataType = "String", paramType = "path")
//			@ApiImplicitParam(name = "param1", value = "파라미터1", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "param2", value = "파마미터2", required = false, dataType = "int", paramType = "query") 
	})
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<?> userInfo(@PathVariable("id") String id) {
		log.debug("userInfo userid : {}", id);
		try {
			UserDto userDto = userService.getUser(id);
			if(userDto != null)
				return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "회원정보수정", notes = "회원정보를 수정합니다.")
	@PutMapping(value = "/user")
	public ResponseEntity<?> userModify(@RequestBody UserDto userDto) {
		log.debug("userModify userDto : {}", userDto);
		try {
			userService.updateUser(userDto);
			List<UserDto> list = userService.listUser();
			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "회원정보삭제", notes = "회원정보를 삭제합니다.")
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<?> userDelete(@PathVariable("id") String id) {
		log.debug("userDelete userid : {}", id);
		try {
			userService.deleteUser(id);
			List<UserDto> list = userService.listUser();
			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
