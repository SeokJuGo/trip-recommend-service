package com.ssafy.enjoytrip.board.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.Plan;

@Mapper
public interface PlanMapper {
	
	Integer count(Integer boardId) throws SQLException;
	
	List<Plan> findAllByBoardId(Integer boardId) throws SQLException;
	
	void insert(Plan entity) throws SQLException;
	
	void update(Plan entity) throws SQLException;
	
	Integer delete(Integer id) throws SQLException;
	
	Integer deleteAllByBoardId(Integer boardId) throws SQLException;
	
}
