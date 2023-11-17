package com.ssafy.enjoytrip.comment.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.comment.model.Comment;

@Mapper
public interface CommentMapper {
	
	Integer countByBoardId(Integer boardId) throws SQLException;
	
	List<Comment> findAllByBoardId(Integer boardId) throws SQLException;
	
	List<Comment> findAll() throws SQLException;
	
	void insert(Comment entity) throws SQLException;
	
	void update(Comment entity) throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
}
