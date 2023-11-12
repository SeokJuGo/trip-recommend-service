package com.ssafy.enjoytrip.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.mapper.UserMapper;
import com.ssafy.enjoytrip.user.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserMapper userMapper;

	public UserDetailsServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.findByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException(username);

		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoles().stream().map(Enum::name).toArray(String[]::new))
				.build();
	}

}
