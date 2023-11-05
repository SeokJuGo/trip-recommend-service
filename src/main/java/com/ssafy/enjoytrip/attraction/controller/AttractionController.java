package com.ssafy.enjoytrip.attraction.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import com.ssafy.enjoytrip.member.model.UserDto;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/attraction")
public class AttractionController {

	private AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}

	@GetMapping("")
	public ResponseEntity<?> findAllByQueryString(
			@RequestParam(value = "contentTypeId", required = false) Integer contentTypeId,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "zipCode", required = false) String zipCode,
			@RequestParam(value = "sidoCode", required = false) Integer sidoCode,
			@RequestParam(value = "gugunCode", required = false) Integer gugunCode,
			@RequestParam(value = "overview", required = false) String overview) {
		log.debug("AttractionController findAllByQueryString() function called!!!");
		try {
			List<AttractionDto> list = null;
			if (sidoCode != null && gugunCode != null)
				list = attractionService.findAllBySidoCodeAndGugunCode(sidoCode, gugunCode);
			else if (sidoCode != null)
				list = attractionService.findAllBySidoCode(sidoCode);
			else if (contentTypeId != null)
				list = attractionService.findAllByContentTypeId(contentTypeId);
			else if (title != null)
				list = attractionService.findAllByTitleContains(title);
			else if (address != null)
				list = attractionService.findAllByAddrContains(address);
			else if (zipCode != null)
				list = attractionService.findAllByZipcodeContains(zipCode);
			else if (overview != null)
				list = attractionService.findAllByOverviewContains(overview);
			else
				list = attractionService.findAll();

			if (list != null && !list.isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			return new ResponseEntity<>("Data Empty", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{contentId}")
	public ResponseEntity<?> findByContentId(@PathVariable("contentId") int contentId) {
		log.debug("AttractionController findByContentId() function called!!!");
		try {
			AttractionDto dto = attractionService.findByContentId(contentId);
			log.debug("{}",dto);
			if (dto != null)
				return new ResponseEntity<>(dto, HttpStatus.OK);
			return new ResponseEntity<>("Data Not Exists", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("findType/{contentTypeId}")
	public ResponseEntity<?> findByContentTypeId(@PathVariable("contentTypeId") int contentTypeId) {
		log.debug("AttractionController findByContentId() function called!!!");
		try {
			List<AttractionDto> lis = attractionService.findAllByContentTypeId(contentTypeId);
			log.debug("{}",lis);
			if (lis != null)
				return new ResponseEntity<>(lis, HttpStatus.OK);
			return new ResponseEntity<>("Data Not Exists", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("sido/{sidoCode}")
	public ResponseEntity<?> findBySidoCode(@PathVariable("sidoCode") int sidoCode) {
		log.debug("AttractionController findByContentId() function called!!!");
		try {
			List<AttractionDto> dto = attractionService.findAllBySidoCode(sidoCode);
			if (dto != null)
				return new ResponseEntity<>(dto, HttpStatus.OK);
			return new ResponseEntity<>("Data Not Exists", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>("Error Occurred!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
