package com.ssafy.enjoytrip.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BoardRequestDto(게시글 요청 정보)", description = "게시글 요청 정보를 포함하고 있는 RequestDto Class")
public class BoardRequestDto {
	@ApiModelProperty(value = "글제목")
	private String title;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "작성자 아이디")
	private String username;
	@ApiModelProperty(value = "게시판 타입")
	private String boardType;
	
}
