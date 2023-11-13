package com.ssafy.enjoytrip.user.service;

import java.util.ArrayList;
import java.util.List;

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
		List<UserEntity> userEntities = userMapper.findAll();
		List<UserResponseDto> responseDtos = new ArrayList<>();
		for (UserEntity userEntity : userEntities) {
			UserResponseDto responseDto = new UserResponseDto();
			responseDto.setId(userEntity.getId());
			responseDto.setEmail(userEntity.getEmail());
			responseDto.setNickname(userEntity.getNickname());
			responseDto.setUsername(userEntity.getUsername());
			// 추후 UserResponseDto에 rolename 필드 추가 및 setRolename() 처리 필요
			responseDto.setCreatedDate(userEntity.getCreatedDate());
			responseDto.setUpdatedDate(userEntity.getUpdatedDate());
			responseDtos.add(responseDto);
		}
		return responseDtos;
	}

	@Override
	public UserResponseDto findByUsername(String username) throws Exception {
		UserEntity userEntity = userMapper.findByUsername(username);
		if (userEntity == null) return null;
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setId(userEntity.getId());
		userResponseDto.setEmail(userEntity.getEmail());
		userResponseDto.setNickname(userEntity.getNickname());
		userResponseDto.setUsername(userEntity.getUsername());
		// 추후 UserResponseDto에 rolename 필드 추가 및 setRolename() 처리 필요
		userResponseDto.setCreatedDate(userEntity.getCreatedDate());
		userResponseDto.setUpdatedDate(userEntity.getUpdatedDate());
		return userResponseDto;
	}

	@Override
	public void regist(UserRequestDto userRequestDto) throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(userRequestDto.getEmail());
		userEntity.setNickname(userRequestDto.getNickname());
		userEntity.setUsername(userRequestDto.getUsername());
		userEntity.setPassword(userRequestDto.getPassword());
		userMapper.regist(userEntity);
	}

	@Override
	public void update(UserRequestDto userRequestDto) throws Exception {
		String username = userRequestDto.getUsername();
		UserEntity userEntity = userMapper.findByUsername(username);
		userEntity.setEmail(userRequestDto.getEmail());
		userEntity.setNickname(userRequestDto.getNickname());
		userEntity.setPassword(userRequestDto.getPassword());
		userMapper.update(userEntity);
	}

	@Override
	public void delete(AuthRequestDto authRequestDto) throws Exception {
		UserResponseDto responseDto = login(authRequestDto);
		if (responseDto != null)
			userMapper.delete(responseDto.getId().intValue());
	}

	@Override
	public UserResponseDto login(AuthRequestDto authRequestDto) throws Exception {
		UserEntity userEntity = userMapper.findByUsername(authRequestDto.getUsername());
		// 추후 BCryptPasswordEncoder 및 JWT로 암호화 처리 필요
		if (userEntity != null && userEntity.getPassword().equals(authRequestDto.getPassword())) {
			UserResponseDto userResponseDto = new UserResponseDto();
			userResponseDto.setId(userEntity.getId());
			userResponseDto.setEmail(userEntity.getEmail());
			userResponseDto.setNickname(userEntity.getNickname());
			userResponseDto.setUsername(userEntity.getUsername());
			// 추후 UserResponseDto에 rolename 필드 추가 및 setRolename() 처리 필요
			userResponseDto.setCreatedDate(userEntity.getCreatedDate());
			userResponseDto.setUpdatedDate(userEntity.getUpdatedDate());
			return userResponseDto;
		}
		return null;
	}
	
}
