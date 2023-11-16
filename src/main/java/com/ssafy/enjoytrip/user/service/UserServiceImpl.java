package com.ssafy.enjoytrip.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.mapper.UserMapper;
import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserEntity;
import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;

	@Override
	public List<UserResponseDto> findAll() throws Exception {
		return userMapper.findAll().stream().map(UserResponseDto::new).collect(Collectors.toList());
	}

	@Override
	public UserResponseDto findById(Integer id) throws Exception {
		return new UserResponseDto(userMapper.findById(id));
	}

	@Override
	public UserResponseDto findByEmail(String email) throws Exception {
		return new UserResponseDto(userMapper.findByEmail(email));
	}
	
	@Override
	public UserResponseDto findByUsername(String username) throws Exception {
		// 추후 UserResponseDto에 rolename 필드 추가 및 setRolename() 처리 필요
		return new UserResponseDto(userMapper.findByUsername(username));
	}
	
	@Override
	public void regist(UserRequestDto userRequestDto) throws SQLException {
		userMapper.regist(userRequestDto);
	}
	
	@Override
	public void update(UserRequestDto userRequestDto) throws Exception {
		userMapper.update(userRequestDto.toEntity());
	}
	
	@Override
	public void delete(UserRequestDto userRequestDto) throws Exception {
		userMapper.delete(userRequestDto);
	}
}
