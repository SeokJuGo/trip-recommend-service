package com.ssafy.enjoytrip.profile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "ProfileResponseDto(첨부파일 조회 객체)", description = "첨부파일 조회를 위한 ResponseDto")
public class ProfileResponseDto {
	@ApiModelProperty(value = "PK")
	private Long id;
	@ApiModelProperty(value = "첨부파일 크기")
	private Long filesize;
	@ApiModelProperty(value = "첨부파일 이름")
	private String filename;
	@ApiModelProperty(value = "첨부파일 경로")
	private String filepath;
	@ApiModelProperty(value = "파일생성일자")
	private String createdDate;
	@ApiModelProperty(value = "파일수정일자")
	private String updatedDate;
	
	public ProfileResponseDto(Profile entity) {
		this.id = entity.getId();
		this.filesize = entity.getFilesize();
		this.filename = entity.getFilename();
		this.filepath = entity.getFilepath();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
	}
	
}
