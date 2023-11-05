package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/attraction")
@Api(tags = { "관광지정보 컨트롤러 API" })
public class AttractionController {

	private AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}

	@ApiOperation(value = "관광지정보 목록 조회", notes = "<h2><b>관광지정보 목록을 조회한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contentTypeId", value = "관광지 타입 코드", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "sidoCode", value = "시도 코드", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "gugunCode", value = "구군 코드", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "title", value = "관광지명", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "address", value = "관광지 주소", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "zipCode", value = "관광지 우편번호", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "overview", value = "관광지 소개", required = false, dataType = "String", paramType = "query"),
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("")
	public ResponseEntity<?> findAllByAttraction(
			@RequestParam(defaultValue = "0") Integer contentTypeId,
			@RequestParam(defaultValue = "0") Integer sidoCode,
			@RequestParam(defaultValue = "0") Integer gugunCode,
			@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") String address,
			@RequestParam(defaultValue = "") String zipCode,
			@RequestParam(defaultValue = "") String overview) {
		log.debug("AttractionController findAllByQueryString() function called!!!");
		try {
			AttractionDto attractionDto = new AttractionDto();
			attractionDto.setContentTypeId(contentTypeId);
			attractionDto.setSidoCode(sidoCode);
			attractionDto.setGugunCode(gugunCode);
			attractionDto.setTitle(title);
			attractionDto.setAddress(address);
			attractionDto.setZipCode(zipCode);
			attractionDto.setOverview(overview);

			List<AttractionDto> list = attractionService.findAllByAttraction(attractionDto);
			if (list != null && !list.isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "관광지정보  조회", notes = "<h2><b>관광지정보를 조회한다.</b></h2>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contentId", value = "관광지 코드", required = true, dataType = "Integer", paramType = "path"),
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "404 Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("/{contentId}")
	public ResponseEntity<?> findByContentId(@PathVariable("contentId") int contentId) {
		log.debug("AttractionController findByContentId() function called!!!");
		try {
			AttractionDto dto = attractionService.findByContentId(contentId);
			if (dto != null)
				return new ResponseEntity<>(dto, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
