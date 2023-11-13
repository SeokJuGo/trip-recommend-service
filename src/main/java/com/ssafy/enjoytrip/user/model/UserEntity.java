package com.ssafy.enjoytrip.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserEntity(회원정보)", description = "회원정보를 포함하고 있는 DomainEntity Class")
public class UserEntity {
	@ApiModelProperty(value = "Index")
	private Long id;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "아이디")
	private String username;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "이름")
	private String nickname;
	@ApiModelProperty(value = "가입생성일")
	private String createdDate;
	@ApiModelProperty(value = "업데이트일")
	private String updatedDate;
	@ApiModelProperty(value = "권한 Index")
	private Long roleId;

}
