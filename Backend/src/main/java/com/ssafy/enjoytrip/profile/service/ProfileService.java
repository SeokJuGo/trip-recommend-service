package com.ssafy.enjoytrip.profile.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.profile.model.ProfileResponseDto;

public interface ProfileService {
	
	Integer count() throws Exception;

	Integer countByBoardId(Long userId) throws Exception;
	
	List<ProfileResponseDto> findAll() throws Exception;
	
	List<ProfileResponseDto> findAllByUserId(Long userId) throws Exception;
	
	ProfileResponseDto findById(Long id) throws Exception;
	
	Long insert(Long userId, MultipartFile file) throws Exception;
	
	Long update(Long userId, MultipartFile file) throws Exception;
	
	void delete(Long id) throws Exception;
	
	Resource downloadFile(Long id) throws Exception;
	
}
