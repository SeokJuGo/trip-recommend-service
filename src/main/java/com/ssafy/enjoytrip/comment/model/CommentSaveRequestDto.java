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
@ApiModel(value = "CommentSaveRequestDto(댓글 삽입 객체)", description = "댓글 삽입을 위한 RequestDto")
public class CommentSaveRequestDto {
	@ApiModelProperty(value = "댓글 내용")
	private String content;
	@ApiModelProperty(value = "USER PK")
	private Long userId;
	@ApiModelProperty(value = "BOARD PK")
	private Long boardId;

	public Comment toEntity() {
		return Comment.builder()
				.content(content)
				.userId(userId)
				.boardId(boardId)
				.build();
	}
	
	@Override
	public String toString() {
		return "CommentSaveRequestDto [content=" + content + ", userId=" + userId + ", boardId=" + boardId + "]";
	}
	
}
