package com.ssafy.enjoytrip.profile.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.profile.mapper.ProfileMapper;
import com.ssafy.enjoytrip.profile.model.Profile;
import com.ssafy.enjoytrip.profile.model.ProfileRequestDto;
import com.ssafy.enjoytrip.profile.model.ProfileResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
	
	private final ProfileMapper profileMapper;
	
	@Override
	public Integer count() throws Exception {
		return profileMapper.count();
	}

	@Override
	public Integer countByBoardId(Long boardId) throws Exception {
		return profileMapper.countByBoardId(boardId);
	}

	@Override
	public List<ProfileResponseDto> findAll() throws Exception {
		return profileMapper.findAll().stream()
				.map(ProfileResponseDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProfileResponseDto> findAllByUserId(Long userId) throws Exception {
		return profileMapper.findAllByUserId(userId).stream()
				.map(ProfileResponseDto::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public ProfileResponseDto findById(Long id) throws Exception {
		return new ProfileResponseDto(profileMapper.findById(id));
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	@Transactional
	public Long insert(Long userId, MultipartFile file) throws Exception {
		if (!file.isEmpty()) {
			ProfileRequestDto requestDto = uploadFile(userId, file);
			profileMapper.insert(requestDto.toEntity());
		}
		return userId;
	}

	@Override
	@Transactional
	public Long update(Long boardId, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		deleteFile(profileMapper.findById(id));
		profileMapper.delete(id);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** 실제파일 다운로드 */
	public Resource downloadFile(Long id) throws Exception {
		Profile entity = profileMapper.findById(id);
		return new UrlResource("file:" + entity.getFilepath());
	}

	/** 실제파일 업로드 */
	private ProfileRequestDto uploadFile(Long boardId, MultipartFile file) throws IOException {
		// 저장경로 설정 (Windows: "/", Linux: "\\", Default: "\\")
		Path basePath = Paths.get(System.getProperty("user.dir"));
		Path savePath = basePath.getParent().resolve("profile");
		
		File directory = new File(savePath.toString());
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		String uuid = UUID.randomUUID().toString();
		String filename = file.getOriginalFilename();
		
		Path filePath = savePath.resolve(uuid + "_" + filename);
		file.transferTo(filePath);
		
		// Linux 파일 권한 적용
		// Runtime.getRuntime().exec("chmod -R 777 " + saveFile);
		
		String filepath = filePath.toString(); 
		Long filesize = file.getSize();
		
		return ProfileRequestDto.builder()
				.filename(filename)
				.filepath(filepath)
				.filesize(filesize)
				.userId(boardId)
				.build();
	}
	
	/** 실제파일 삭제 */
	@Transactional
	private void deleteFile(Profile entity) {
		File file = new File(entity.getFilepath());
		if (file.exists() && file.delete())
			log.debug("[ProfileService] deleteFile() function called, "
					+ "filepath = {}", entity.getFilepath());
	}

}
