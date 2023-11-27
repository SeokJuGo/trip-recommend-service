package com.ssafy.enjoytrip.fileinfo.service;

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

import com.ssafy.enjoytrip.fileinfo.mapper.FileInfoMapper;
import com.ssafy.enjoytrip.fileinfo.model.FileInfo;
import com.ssafy.enjoytrip.fileinfo.model.FileInfoRequestDto;
import com.ssafy.enjoytrip.fileinfo.model.FileInfoResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileInfoServiceImpl implements FileInfoService {
	
	private final FileInfoMapper fileInfoMapper;
	
	@Override
	public Integer count() throws Exception {
		return fileInfoMapper.count();
	}

	@Override
	public Integer countByBoardId(Long boardId) throws Exception {
		return fileInfoMapper.countByBoardId(boardId);
	}

	@Override
	public List<FileInfoResponseDto> findAll() throws Exception {
		return fileInfoMapper.findAll().stream()
				.map(FileInfoResponseDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public List<FileInfoResponseDto> findAllByBoardId(Long boardId) throws Exception {
		return fileInfoMapper.findAllByBoardId(boardId).stream()
				.map(FileInfoResponseDto::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public FileInfoResponseDto findById(Long id) throws Exception {
		return new FileInfoResponseDto(fileInfoMapper.findById(id));
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	@Transactional
	public Long insert(Long boardId, MultipartFile file) throws Exception {
		if (!file.isEmpty()) {
			FileInfoRequestDto requestDto = uploadFile(boardId, file);
			fileInfoMapper.insert(requestDto.toEntity());
		}
		return boardId;
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
		deleteFile(fileInfoMapper.findById(id));
		fileInfoMapper.delete(id);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** 실제파일 다운로드 */
	public Resource downloadFile(Long id) throws Exception {
		FileInfo entity = fileInfoMapper.findById(id);
		return new UrlResource("file:" + entity.getFilepath());
	}

	/** 실제파일 업로드 */
	private FileInfoRequestDto uploadFile(Long boardId, MultipartFile file) throws IOException {
		// 저장경로 설정 (Windows: "/", Linux: "\\", Default: "\\")
		Path basePath = Paths.get(System.getProperty("user.dir"));
		Path savePath = basePath.getParent().resolve("attachments");
		
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
		
		return FileInfoRequestDto.builder()
				.filename(filename)
				.filepath(filepath)
				.filesize(filesize)
				.boardId(boardId)
				.build();
	}
	
	/** 실제파일 삭제 */
	@Transactional
	private void deleteFile(FileInfo entity) {
		File file = new File(entity.getFilepath());
		if (file.exists() && file.delete())
			log.debug("[FileInfoService] deleteFile() function called, "
					+ "filepath = {}", entity.getFilepath());
	}

}
