package com.ssafy.enjoytrip.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BoardEntity(게시글 정보)", description = "게시글 정보를 포함하고 있는 DomainEntity Class")
public class BoardEntity {
	@ApiModelProperty(value = "Index")
	private Long id;
	@ApiModelProperty(value = "글제목")
	private String title;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "글생성일자")
	private String createdDate;
	@ApiModelProperty(value = "글수정일자")
	private String updatedDate;
	
	@ApiModelProperty(value = "유저 Index")
	private Long userId;
	@ApiModelProperty(value = "게시판 타입 Index")
	private Long boardTypeId;
	
}
