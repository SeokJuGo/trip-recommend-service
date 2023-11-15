package com.ssafy.enjoytrip.board.model;

import com.ssafy.enjoytrip.boardtype.model.BoardTypeEntity;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeResponseDto;
import com.ssafy.enjoytrip.user.model.UserEntity;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "BoardResponseDto(게시글 응답 정보)", description = "게시글 응답 정보를 포함하고 있는 ResponseDto Class")
public class BoardResponseDto {
	@ApiModelProperty(value = "Index")
	private Long id;
	@ApiModelProperty(value = "글제목")
	private String title;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "작성자 정보")
	private UserResponseDto author;
	@ApiModelProperty(value = "게시판 타입")
	private BoardTypeResponseDto boardType;
	
	@ApiModelProperty(value = "글생성일자")
	private String createdDate;
	@ApiModelProperty(value = "글수정일자")
	private String updatedDate;
	
	public BoardResponseDto(BoardEntity boardEntity, UserEntity userEntity, BoardTypeEntity boardTypeEntity) {
		this.id = boardEntity.getId();
		this.title = boardEntity.getTitle();
		this.content = boardEntity.getContent();
		this.author = new UserResponseDto(userEntity);
		this.boardType = new BoardTypeResponseDto(boardTypeEntity);
		this.createdDate = boardEntity.getCreatedDate();
		this.updatedDate = boardEntity.getUpdatedDate();
	}
	
}
