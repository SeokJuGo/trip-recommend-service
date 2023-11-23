package com.ssafy.enjoytrip.fileinfo.controller;

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

import com.ssafy.enjoytrip.fileinfo.model.FileInfoResponseDto;
import com.ssafy.enjoytrip.fileinfo.service.FileInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@Api(tags = { "첨부파일 컨트롤러 API" })
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
@RequestMapping("/attach")
public class FileInfoController {
	
	private final FileInfoService fileInfoService;
	
	@GetMapping("")
	@ApiOperation(value = "전체 첨부파일 목록 조회", notes = "<h2><b>첨부파일 목록을 조회한다.</b></h2>")
	public ResponseEntity<?> findAll() {
		try {
			log.debug("[FileInfoController] findAll() function called");
			List<FileInfoResponseDto> responseDtos = fileInfoService.findAll();
			if (responseDtos != null && !responseDtos.isEmpty())
				return new ResponseEntity<>(responseDtos, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{boardId}")
	@ApiOperation(value = "게시글 첨부파일 목록 조회", notes = "<h2><b>게시글 첨부파일 목록을 조회한다.</b></h2>")
	public ResponseEntity<?> findAllByBoardId(@PathVariable("boardId") Long boardId) {
		try {
			log.debug("[FileInfoController] findAllByBoardId() function called, boardId = {}", boardId);
			List<FileInfoResponseDto> responseDtos = fileInfoService.findAllByBoardId(boardId);
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
			log.debug("[FileInfoController] downloadImage() function called, id = {}", id);
			Resource resource = fileInfoService.downloadFile(id);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
//			return new ResponseEntity<>(resource, HttpStatus.OK);
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
			log.debug("[FileInfoController] downloadFile() function called, id = {}", id);
			FileInfoResponseDto responseDto = fileInfoService.findById(id);
			Resource resource = fileInfoService.downloadFile(id);
			
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
	
	@PostMapping("/upload/{boardId}")
	@ApiOperation(value = "첨부파일 업로드", notes = "<h2><b>첨부파일을 업로드한다.</b></h2>")
	public ResponseEntity<?> uploadFile(
			@PathVariable(required = true, value = "boardId") Long boardId,
			@RequestPart(required = false, value = "file") MultipartFile file,
			@RequestPart(required = false, value = "files") List<MultipartFile> files) {
		try {
			log.debug("[FileInfoController] uploadFile() function called");
			if (file != null && !file.isEmpty())
				fileInfoService.insert(boardId, file);
			if (files != null && !files.isEmpty())
				for (MultipartFile multipartFile : files)
					fileInfoService.insert(boardId, multipartFile);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{boardId}")
	public ResponseEntity<?> updateFile(
			@PathVariable(required = true, value = "boardId") Long boardId,
			@RequestPart(required = false, value = "dFileIds") List<Long> dFileIds,
			@RequestPart(required = false, value = "file") MultipartFile file,
			@RequestPart(required = false, value = "files") List<MultipartFile> files
			) {
		try {
			log.debug("[FileInfoController] updateFile() function called");
			if (dFileIds != null && !dFileIds.isEmpty())
				for (Long id : dFileIds)
					fileInfoService.delete(id);
			if (file != null && !file.isEmpty())
				fileInfoService.insert(boardId, file);
			if (files != null && !files.isEmpty())
				for (MultipartFile multipartFile : files)
					fileInfoService.insert(boardId, multipartFile);
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
			log.debug("[FileInfoController] deleteFile() function called");
			fileInfoService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
