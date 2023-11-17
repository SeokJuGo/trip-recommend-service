package com.ssafy.enjoytrip.fileinfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "CommentSaveRequestDto(첨부파일 요청 객체)", description = "첨부파일 요청을 위한 RequestDto")
public class FileInfoRequestDto {
	@ApiModelProperty(value = "첨부파일 크기")
	private Long filesize;
	@ApiModelProperty(value = "첨부파일 이름")
	private String filename;
	@ApiModelProperty(value = "첨부파일 경로")
	private String filepath;
	@ApiModelProperty(value = "BOARD PK")
	private Long boardId;
	
	public FileInfo toEntity() {
		return FileInfo.builder()
				.filesize(filesize)
				.filename(filename)
				.filepath(filepath)
				.boardId(boardId)
				.build();
	}

	@Override
	public String toString() {
		return "FileInfoRequestDto [filesize=" + filesize + ", filename=" + filename + ", filepath=" + filepath
				+ ", boardId=" + boardId + "]";
	}
	
}
