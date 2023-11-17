package com.ssafy.enjoytrip.fileinfo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.fileinfo.model.FileInfo;

@Mapper
public interface FileInfoMapper {
	
	Integer countByBoardId(Integer boardId) throws SQLException;
	
	List<FileInfo> findAllByBoardId(Integer boardId) throws SQLException;
	
	List<FileInfo> findAll() throws SQLException;
	
	void insert(FileInfo entity) throws SQLException;
	
	void update(FileInfo entity) throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
}
