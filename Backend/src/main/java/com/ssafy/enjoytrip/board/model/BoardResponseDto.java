package com.ssafy.enjoytrip.board.model;

import com.ssafy.enjoytrip.boardtype.model.BoardType;
import com.ssafy.enjoytrip.boardtype.model.BoardTypeResponseDto;
import com.ssafy.enjoytrip.user.model.User;
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
	@ApiModelProperty(value = "조회수")
	private Long hit;
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
	
	public BoardResponseDto(Board entity) {
		this.id = entity.getId();
		this.hit = entity.getHit();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
	}
	
	public BoardResponseDto(Board boardEntity, User userEntity, BoardType boardTypeEntity) {
		this.id = boardEntity.getId();
		this.hit = boardEntity.getHit();
		this.title = boardEntity.getTitle();
		this.content = boardEntity.getContent();
		this.author = new UserResponseDto(userEntity);
		this.boardType = new BoardTypeResponseDto(boardTypeEntity);
		this.createdDate = boardEntity.getCreatedDate();
		this.updatedDate = boardEntity.getUpdatedDate();
	}

	public void setAuthor(User entity) {
		this.author = new UserResponseDto(entity);
	}

	public void setBoardType(BoardType entity) {
		this.boardType = new BoardTypeResponseDto(entity);
	}
	
}
