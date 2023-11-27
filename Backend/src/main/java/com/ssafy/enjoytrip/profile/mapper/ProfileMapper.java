package com.ssafy.enjoytrip.profile.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.profile.model.Profile;


@Mapper
public interface ProfileMapper {
	
	Integer count() throws SQLException;
	
	Integer countByBoardId(Long userId) throws SQLException;
	
	List<Profile> findAll() throws SQLException;
	
	List<Profile> findAllByUserId(Long userId) throws SQLException;
	
	Profile findById(Long id) throws SQLException;
	
	void insert(Profile entity) throws SQLException;
	
	void update(Profile entity) throws SQLException;
	
	void delete(Long id) throws SQLException;
	
}
