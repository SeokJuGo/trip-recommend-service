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
public class BoardRequestDto {
	@ApiModelProperty(value = "글 제목")
	private Long hit;
	@ApiModelProperty(value = "글 제목")
	private String title;
	@ApiModelProperty(value = "글 내용")
	private String content;
	@ApiModelProperty(value = "작성자 PK")
	private Long userId;
	@ApiModelProperty(value = "게시판 타입 PK")
	private Long boardTypeId;
	
	public Board toEntity() {
		return Board.builder()
				.hit(hit)
				.title(title)
				.content(content)
				.boardTypeId(boardTypeId)
				.userId(userId)
				.build();
	}

	public Board toEntity(Long id) {
		return Board.builder()
				.id(id)
				.hit(hit)
				.title(title)
				.content(content)
				.boardTypeId(boardTypeId)
				.userId(userId)
				.build();
	}
	
}
