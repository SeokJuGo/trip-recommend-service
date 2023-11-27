package com.ssafy.enjoytrip.profile.model;

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
@ApiModel(value = "FileInfo(첨부파일 정보)", description = "첨부파일 정보를 포함하고 있는 Domain Entity Class")
public class Profile {
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
	@ApiModelProperty(value = "USER PK")
	private Long userId;
	
}
