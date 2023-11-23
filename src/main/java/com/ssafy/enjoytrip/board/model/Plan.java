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
@ApiModel(value = "Plan(여행계획 정보)", description = "여행계획 정보를 포함하고 있는 Domain Entity Class")
public class Plan {
	@ApiModelProperty(value = "PK")
	private Long id;
	@ApiModelProperty(value = "상세")
	private String detail;
	@ApiModelProperty(value = "거리")
	private Integer distance;
	@ApiModelProperty(value = "글생성일자")
	private String createdDate;
	@ApiModelProperty(value = "글수정일자")
	private String updatedDate;
	
	@ApiModelProperty(value = "Board PK")
	private Long boardId;
	@ApiModelProperty(value = "Attraction PK")
	private Long attractionId;
	
}
