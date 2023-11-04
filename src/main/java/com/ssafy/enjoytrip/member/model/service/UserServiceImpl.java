package com.ssafy.enjoytrip.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.member.model.UserDto;
import com.ssafy.enjoytrip.member.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
//	@Autowired
//	private SqlSession sqlSession;
	
	private UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String id) throws Exception {
//		return sqlSession.getMapper(MemberMapper.class).idCheck(userId);
		return userMapper.idCheck(id);
	}

	@Override
	public void joinUser(UserDto userDto) throws Exception {
//		sqlSession.getMapper(MemberMapper.class).joinMember(memberDto);
		userMapper.joinUser(userDto);
	}

	@Override
	public UserDto loginUser(UserDto userDto) throws Exception {
//		return sqlSession.getMapper(MemberMapper.class).loginMember(map);
		return userMapper.loginUser(userDto);
	}
	
	/* ADMIN */
	@Override
	public List<UserDto> listUser() throws Exception {
		return userMapper.listUser();
	}

	@Override
	public UserDto getUser(String id) throws Exception {
		return userMapper.getUser(id);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		userMapper.updateUser(userDto);
	}

	@Override
	public void deleteUser(String id) throws Exception {
		userMapper.deleteUser(id);		
	}

}
