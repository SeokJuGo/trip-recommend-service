package com.ssafy.enjoytrip.boardtype.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "BoardTypeResponseDto(게시판 타입 응답 정보)", description = "게시판 타입 응답 정보를 포함하고 있는 ResponseDto Class")
public class BoardTypeResponseDto {
	@ApiModelProperty(value = "PK")
	private Long id;
	@ApiModelProperty(value = "게시판 타입명")
	private String name;
	@ApiModelProperty(value = "게시판 타입 설명")
	private String desc;
	@ApiModelProperty(value = "글생성일자")
	private String createdDate;
	@ApiModelProperty(value = "글수정일자")
	private String updatedDate;
	
	public BoardTypeResponseDto(BoardType boardTypeEntity) {
		this.id = boardTypeEntity.getId();
		this.name = boardTypeEntity.getName();
		this.desc = boardTypeEntity.getDesc();
		this.createdDate = boardTypeEntity.getCreatedDate();
		this.updatedDate = boardTypeEntity.getUpdatedDate();
	}
}
