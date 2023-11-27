package com.ssafy.enjoytrip.comment.model;

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
@ApiModel(value = "Comment(댓글 정보)", description = "댓글 정보를 포함하고 있는 Domain Entity Class")
public class Comment {
	@ApiModelProperty(value = "PK")
	private Long id;
	@ApiModelProperty(value = "댓글 내용")
	private String content;
	@ApiModelProperty(value = "댓글생성일자")
	private String createdDate;
	@ApiModelProperty(value = "댓글수정일자")
	private String updatedDate;
	
	@ApiModelProperty(value = "USER PK")
	private Long userId;
	@ApiModelProperty(value = "BOARD PK")
	private Long boardId;
	
}
