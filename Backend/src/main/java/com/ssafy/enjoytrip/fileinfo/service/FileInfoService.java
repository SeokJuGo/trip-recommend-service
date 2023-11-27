package com.ssafy.enjoytrip.fileinfo.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.fileinfo.model.FileInfoResponseDto;

public interface FileInfoService {
	
	Integer count() throws Exception;

	Integer countByBoardId(Long boardId) throws Exception;
	
	List<FileInfoResponseDto> findAll() throws Exception;
	
	List<FileInfoResponseDto> findAllByBoardId(Long boardId) throws Exception;
	
	FileInfoResponseDto findById(Long id) throws Exception;
	
	Long insert(Long boardId, MultipartFile file) throws Exception;
	
	Long update(Long boardId, MultipartFile file) throws Exception;
	
	void delete(Long id) throws Exception;
	
	Resource downloadFile(Long id) throws Exception;
	
}
