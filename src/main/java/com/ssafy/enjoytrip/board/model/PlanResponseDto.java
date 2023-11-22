package com.ssafy.enjoytrip.board.model;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "PlanResponseDto(여행계획 응답 정보)", description = "여행계획 응답 정보를 포함하고 있는 ResponseDto Class")
public class PlanResponseDto {
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
	
	@ApiModelProperty(value = "Attraction 정보")
	private AttractionDto attraction;
	
	public PlanResponseDto(Plan plan) {
		this.id = plan.getId();
		this.detail = plan.getDetail();
		this.distance = plan.getDistance();
		this.createdDate = plan.getCreatedDate();
		this.updatedDate = plan.getUpdatedDate();
	}

	public void setAttraction(AttractionDto attraction) {
		this.attraction = attraction;
	}
	
}
