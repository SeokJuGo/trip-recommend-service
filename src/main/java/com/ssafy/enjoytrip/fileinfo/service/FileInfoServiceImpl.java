package com.ssafy.enjoytrip.fileinfo.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.fileinfo.mapper.FileInfoMapper;
import com.ssafy.enjoytrip.fileinfo.model.FileInfo;
import com.ssafy.enjoytrip.fileinfo.model.FileInfoRequestDto;
import com.ssafy.enjoytrip.fileinfo.model.FileInfoResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileInfoServiceImpl implements FileInfoService {
	private final FileInfoMapper fileInfoMapper;
	
	/** 실제파일 저장 */
    private FileInfoRequestDto uploadFile(Long boardId, MultipartFile file) throws IOException {
        // 저장경로 설정 (Windows: "/", Linux: "\\", Default: "\\")
        Path basePath = Paths.get(System.getProperty("user.dir"));
        Path savePath = basePath.getParent().resolve("attachments");

        // 파일이름, 파일경로 설정 및 파일 저장
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = savePath.resolve(fileName);
        file.transferTo(filePath);

        // Linux 파일 권한 적용
        // Runtime.getRuntime().exec("chmod -R 777 " + saveFile);

        // FileInfoRequestDto 생성 및 반환
        return FileInfoRequestDto.builder()
                .filename(file.getOriginalFilename())
                .filepath(filePath.toString())
                .filesize(file.getSize())
                .boardId(boardId)
                .build();
    }

	@Override
	public Integer countByBoardId(Integer boardId) throws SQLException {
		return fileInfoMapper.countByBoardId(boardId);
	}

	@Override
	public List<FileInfoResponseDto> findAllByBoardId(Integer boardId) throws SQLException {
		return fileInfoMapper.findAllByBoardId(boardId).stream()
				.map(FileInfoResponseDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public List<FileInfoResponseDto> findAll() throws SQLException {
		return fileInfoMapper.findAll().stream()
				.map(FileInfoResponseDto::new)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Integer insert(Integer boardId, List<MultipartFile> files) throws SQLException, IOException {
		if (files != null) {
            for (MultipartFile file : files) {
            	FileInfoRequestDto requestDto = uploadFile(Long.valueOf(boardId), file);
            	fileInfoMapper.insert(requestDto.toEntity());
            }
        }
        return boardId;
	}

	@Override
	public Integer update(FileInfoRequestDto requestDto) throws SQLException {
		FileInfo entity = requestDto.toEntity();
		fileInfoMapper.update(entity);
		return entity.getId().intValue();

	}

	@Override
	public void delete(Integer id) throws SQLException {
		fileInfoMapper.delete(id);
	}

}
