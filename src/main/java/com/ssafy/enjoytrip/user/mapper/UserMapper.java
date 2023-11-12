package com.ssafy.enjoytrip.user.mapper;

import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.user.model.User;

@Repository
public interface UserMapper {
	
	User findByUsername(String username);
	
}
