package com.ssafy.enjoytrip.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "UserResponseDto(로그인 응답 정보)", description = "로그인 응답 정보를 포함하고 있는 ResponseDto Class")
public class UserResponseDto {
	@ApiModelProperty(value = "Index")
	private Long id;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "아이디")
	private String username;
	@ApiModelProperty(value = "이름")
	private String nickname;
	@ApiModelProperty(value = "가입생성일")
	private String createdDate;
	@ApiModelProperty(value = "업데이트일")
	private String updatedDate;
	@ApiModelProperty(value = "권한 이름")
	private String rolename;
	
	public UserResponseDto(User entity) {
		this.id = entity.getId();
		this.email = entity.getEmail();
		this.username = entity.getUsername();
		this.nickname = entity.getNickname();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
		this.rolename = entity.getRolename();
	}
	
}
