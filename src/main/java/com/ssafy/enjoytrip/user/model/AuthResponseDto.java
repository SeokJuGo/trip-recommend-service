package com.ssafy.enjoytrip.user.model;

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
@ApiModel(value = "AuthResponseDto(로그인 응답 정보)", description = "로그인 응답 정보를 포함하고 있는 Response Dto Class")
public class AuthResponseDto {
	@ApiModelProperty(value = "Token Type")
	private String tokenType;
	@ApiModelProperty(value = "Access Token")
	private String accessToken;
	@ApiModelProperty(value = "Refresh Token")
	private String refreshToken;
	
}
