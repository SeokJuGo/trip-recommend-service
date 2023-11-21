package com.ssafy.enjoytrip.comment.model;

import com.ssafy.enjoytrip.user.model.User;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "CommentResponseDto(댓글 조회 객체)", description = "댓글 조회를 위한 ResponseDto")
public class CommentResponseDto {
	@ApiModelProperty(value = "PK")
	private Long id;
	@ApiModelProperty(value = "댓글 내용")
	private String content;
	@ApiModelProperty(value = "USER NICKNAME")
	private UserResponseDto author;
	
	@ApiModelProperty(value = "댓글생성일자")
	private String createdDate;
	@ApiModelProperty(value = "댓글수정일자")
	private String updatedDate;
	
	public CommentResponseDto(Comment commentEntity, User userEntity) {
		this.id = commentEntity.getId();
		this.content = commentEntity.getContent();
		this.author = new UserResponseDto(userEntity);
		this.createdDate = commentEntity.getCreatedDate();
		this.updatedDate = commentEntity.getUpdatedDate();
	}
	
}
