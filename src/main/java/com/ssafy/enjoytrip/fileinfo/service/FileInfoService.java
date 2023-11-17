package com.ssafy.enjoytrip.fileinfo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.fileinfo.model.FileInfoRequestDto;
import com.ssafy.enjoytrip.fileinfo.model.FileInfoResponseDto;

public interface FileInfoService {

	Integer countByBoardId(Integer boardId) throws SQLException;
	
	List<FileInfoResponseDto> findAllByBoardId(Integer boardId) throws SQLException;
	
	List<FileInfoResponseDto> findAll() throws SQLException;
	
	Integer insert(Integer id, List<MultipartFile> files) throws SQLException, IOException;
	
	Integer update(FileInfoRequestDto requestDto) throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
}
