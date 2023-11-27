package com.ssafy.enjoytrip.profile.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.ssafy.enjoytrip.profile.model.ProfileResponseDto;
import com.ssafy.enjoytrip.profile.service.ProfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@Api(tags = { "프로필 컨트롤러 API" })
@ApiResponses({
	@ApiResponse(code = 200, message = "OK"),
	@ApiResponse(code = 201, message = "CREATED"),
	@ApiResponse(code = 204, message = "NO CONTENT"),
	@ApiResponse(code = 400, message = "BAD REQUEST"),
	@ApiResponse(code = 401, message = "UNAUTHORIZED"),
	@ApiResponse(code = 403, message = "FORBIDDEN"),
	@ApiResponse(code = 404, message = "NOT FOUND"),
	@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
	
	private final ProfileService profileService;
	
	@GetMapping("")
	@ApiOperation(value = "전체 첨부파일 목록 조회", notes = "<h2><b>첨부파일 목록을 조회한다.</b></h2>")
	public ResponseEntity<?> findAll() {
		try {
			log.debug("[ProfileController] findAll() function called");
			List<ProfileResponseDto> responseDtos = profileService.findAll();
			if (responseDtos != null && !responseDtos.isEmpty())
				return new ResponseEntity<>(responseDtos, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "게시글 첨부파일 목록 조회", notes = "<h2><b>게시글 첨부파일 목록을 조회한다.</b></h2>")
	public ResponseEntity<?> findAllByUserId(@PathVariable("userId") Long userId) {
		try {
			log.debug("[ProfileController] findAllByUserId() function called, userId = {}", userId);
			List<ProfileResponseDto> responseDtos = profileService.findAllByUserId(userId);
			if (responseDtos != null && !responseDtos.isEmpty())
				return new ResponseEntity<>(responseDtos, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/image/{id}")
	@ApiOperation(value = "이미지 다운로드", notes = "<h2><b>이미지 데이터를 다운로드한다.</b></h2>")
	public ResponseEntity<?> downloadImage(@PathVariable("id") Long id) {
		try {
			log.debug("[ProfileController] downloadImage() function called, id = {}", id);
			Resource resource = profileService.downloadFile(id);
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/download/{id}")
	@ApiOperation(value = "첨부파일 다운로드", notes = "<h2><b>첨부파일을 다운로드한다.</b></h2>")
	public ResponseEntity<?> downloadFile(@PathVariable("id") Long id) {
		try {
			log.debug("[ProfileController] downloadFile() function called, id = {}", id);
			ProfileResponseDto responseDto = profileService.findById(id);
			Resource resource = profileService.downloadFile(id);
			
			String filename = responseDto.getFilename();
			String encodedFilename = UriUtils.encode(filename, StandardCharsets.UTF_8);
	        String contentDisposition = "attachment; filename=\"" + encodedFilename + "\"";
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/upload/{userId}")
	@ApiOperation(value = "첨부파일 업로드", notes = "<h2><b>첨부파일을 업로드한다.</b></h2>")
	public ResponseEntity<?> uploadFile(
			@PathVariable(required = true, value = "userId") Long userId,
			@RequestPart(required = false, value = "file") MultipartFile file,
			@RequestPart(required = false, value = "files") List<MultipartFile> files) {
		try {
			log.debug("[ProfileController] uploadFile() function called");
			if (file != null && !file.isEmpty())
				profileService.insert(userId, file);
			if (files != null && !files.isEmpty())
				for (MultipartFile multipartFile : files)
					profileService.insert(userId, multipartFile);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateFile(
			@PathVariable(required = true, value = "userId") Long userId,
			@RequestPart(required = false, value = "dFileIds") List<Long> dFileIds,
			@RequestPart(required = false, value = "file") MultipartFile file,
			@RequestPart(required = false, value = "files") List<MultipartFile> files
			) {
		try {
			log.debug("[ProfileController] updateFile() function called");
			if (dFileIds != null && !dFileIds.isEmpty())
				for (Long id : dFileIds)
					profileService.delete(id);
			if (file != null && !file.isEmpty())
				profileService.insert(userId, file);
			if (files != null && !files.isEmpty())
				for (MultipartFile multipartFile : files)
					profileService.insert(userId, multipartFile);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFile(@PathVariable("id") Long id) {
		try {
			log.debug("[ProfileController] deleteFile() function called");
			profileService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
