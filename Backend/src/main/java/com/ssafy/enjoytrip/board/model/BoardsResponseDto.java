package com.ssafy.enjoytrip.board.model;

import java.util.List;

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
@ApiModel(value = "BoardsResponseDto(게시글 목록 응답 정보)", description = "게시글 목록 응답 정보를 포함하고 있는 ResponseDto Class")
public class BoardsResponseDto {
	@ApiModelProperty(value = "페이지 오프셋")
	private int offset;
	@ApiModelProperty(value = "현재 페이지 번호")
	private int pageNum;
	@ApiModelProperty(value = "페이지 크기")
	private int pageSize;
	@ApiModelProperty(value = "전체 페이지 수")
	private int totalPages;
	@ApiModelProperty(value = "전체 데이터 수")
	private int totalElements;
	@ApiModelProperty(value = "첫 페이지 여부")
	private boolean first;
	@ApiModelProperty(value = "끝 페이지 여부")
	private boolean last;
	@ApiModelProperty(value = "게시글 데이터")
	List<BoardResponseDto> boards;
	
}
