package com.ssafy.enjoytrip.board.model;

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
@ApiModel(value = "BoardRequestDto(게시글 요청 정보)", description = "게시글 요청 정보를 포함하고 있는 RequestDto Class")
public class BoardSaveRequestDto {
	@ApiModelProperty(value = "글제목")
	private String title;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "작성자 아이디")
	private String username;
	@ApiModelProperty(value = "게시판 타입")
	private String boardType;
	
	public BoardEntity toEntity() {
		return BoardEntity.builder()
				.title(title)
				.content(content)
				.build();
	}
}
