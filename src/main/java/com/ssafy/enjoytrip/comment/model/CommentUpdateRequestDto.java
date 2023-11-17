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
@ApiModel(value = "CommentUpdateRequestDto(댓글 수정 객체)", description = "댓글 수정을 위한 RequestDto")
public class CommentUpdateRequestDto {
	@ApiModelProperty(value = "PK")
	private Long id;
	@ApiModelProperty(value = "댓글 내용")
	private String content;
	
	public Comment toEntity() {
		return Comment.builder()
				.id(id)
				.content(content)
				.build();
	}

	@Override
	public String toString() {
		return "CommentUpdateRequestDto [id=" + id + ", content=" + content + "]";
	}
	
}
