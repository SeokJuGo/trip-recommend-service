package com.ssafy.enjoytrip.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.mapper.AuthMapper;
import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserEntity;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AuthMapper authMapper;


	@Override
	public UserEntity login(AuthRequestDto authRequestDto) throws SQLException {
//		@Override
//		public UserResponseDto login(AuthRequestDto authRequestDto) throws Exception {
//			// 추후 BCryptPasswordEncoder 및 JWT로 암호화 처리 필요
//			if (userEntity != null && userEntity.getPassword().equals(authRequestDto.getPassword())) {
//				// 추후 UserResponseDto에 rolename 필드 추가 및 setRolename() 처리 필요
//				return new UserResponseDto(userEntity);
//			}
//			return null;
//		}
		return authMapper.login(authRequestDto);
	}

	@Override
	public void delete(AuthRequestDto authRequestDto) throws Exception {
		authMapper.delete(authRequestDto);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("token", refreshToken);
		authMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id) throws Exception {
		return authMapper.getRefreshToken(id);
	}

	@Override
	public void deleRefreshToken(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		authMapper.deleteRefreshToken(map);
	}	

}
