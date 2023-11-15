package com.ssafy.enjoytrip.boardtype.model;

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
@ApiModel(value = "BoardTypeRequestDto(게시판 타입 요청 정보)", description = "게시판 타입 요청 정보를 포함하고 있는 RequestDto Class")
public class BoardTypeRequestDto {
	@ApiModelProperty(value = "게시판 타입명")
	private String name;
	@ApiModelProperty(value = "게시판 타입 설명")
	private String desc;
	
	public BoardTypeEntity toEntity() {
		return BoardTypeEntity.builder()
				.name(name)
				.desc(desc)
				.build();
	}
}
