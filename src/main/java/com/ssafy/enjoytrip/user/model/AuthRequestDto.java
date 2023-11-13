package com.ssafy.enjoytrip.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AuthRequestDto(로그인 정보)", description = "로그인 정보를 포함하고 있는 Request Dto Class")
public class AuthRequestDto {
	@ApiModelProperty(value = "아이디")
	private String username;
	@ApiModelProperty(value = "비밀번호")
	private String password;
}
