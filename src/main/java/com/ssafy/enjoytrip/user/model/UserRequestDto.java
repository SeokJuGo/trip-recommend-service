package com.ssafy.enjoytrip.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "UserRequestDto(회원가입 요청 정보)", description = "회원가입 요청 정보를 포함하고 있는 RequestDto Class")
public class UserRequestDto {
	@ApiModelProperty(value = "아이디")
	private String username;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "이름")
	private String nickname;
	@ApiModelProperty(value = "이메일")
	private String email;
	
	public User toEntity() {
		return User.builder()
				.email(email)
				.nickname(nickname)
				.username(username)
				.password(password)
				.build();
	}
	
}
