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
@ApiModel(value = "PlanRequestDto(여행계획 요청 정보)", description = "여행계획 요청 정보를 포함하고 있는 RequestDto Class")
public class PlanRequestDto {
	@ApiModelProperty(value = "상세")
	private String detail;
	@ApiModelProperty(value = "거리")
	private Integer distance;
	@ApiModelProperty(value = "Board PK")
	private Long boardId;
	@ApiModelProperty(value = "Attraction PK")
	private Long attractionId;
	
	public Plan toEntity() {
		return Plan.builder()
				.detail(detail)
				.distance(distance)
				.boardId(boardId)
				.attractionId(attractionId)
				.build();
	}

	public Plan toEntity(Long id) {
		return Plan.builder()
				.id(id)
				.detail(detail)
				.distance(distance)
				.boardId(boardId)
				.attractionId(attractionId)
				.build();
	}
}
