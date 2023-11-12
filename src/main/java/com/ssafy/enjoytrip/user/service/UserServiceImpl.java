package com.ssafy.enjoytrip.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.mapper.UserMapper;
import com.ssafy.enjoytrip.user.model.UserRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public UserResponseDto findByUsername(String username) throws Exception {
		return userMapper.findByUsername(username);
	}

	@Override
	public List<UserResponseDto> findAll() throws Exception {
		return userMapper.findAll();
	}

	@Override
	public void regist(UserRequestDto userRequestDto) throws Exception {
		userMapper.regist(userRequestDto);
	}

	@Override
	public void update(UserRequestDto userRequestDto) throws Exception {
		userMapper.update(userRequestDto);
	}

	@Override
	public void delete(UserRequestDto userRequestDto) throws Exception {
		userMapper.delete(userRequestDto);
	}

}
