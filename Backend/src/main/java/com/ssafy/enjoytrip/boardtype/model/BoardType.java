package com.ssafy.enjoytrip.boardtype.model;

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
@ApiModel(value = "BoardEntity(게시판 타입 정보)", description = "게시판 타입 정보를 포함하고 있는 DomainEntity Class")
public class BoardType {
	@ApiModelProperty(value = "Index")
	private Long id;
	@ApiModelProperty(value = "게시판 타입명")
	private String name;
	@ApiModelProperty(value = "게시판 타입 설명")
	private String desc;
	@ApiModelProperty(value = "글생성일자")
	private String createdDate;
	@ApiModelProperty(value = "글수정일자")
	private String updatedDate;
	
}
