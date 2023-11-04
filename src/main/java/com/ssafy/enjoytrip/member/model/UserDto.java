package com.ssafy.enjoytrip.member.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
//@Builder
@ApiModel(value = "UserDto (회원정보)", description = "아이디, 비번, 이름을 가진   Domain Class")
public class UserDto {
	
	public UserDto() {
		super();
	}
	@ApiModelProperty(value = "회원아이디")
	private String id;
	@ApiModelProperty(value = "회원이름")
	private String name;
	@ApiModelProperty(value = "회원비밀번호")
	private String password;
	@ApiModelProperty(value = "이메일아이디")
	private String email;
	@ApiModelProperty(value = "가입일")
	private String createdDate;
	@ApiModelProperty(value = "업데이트일")
	private String updatedDate;

	
	

}
