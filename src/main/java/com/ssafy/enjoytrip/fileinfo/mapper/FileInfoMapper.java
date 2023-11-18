package com.ssafy.enjoytrip.fileinfo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.fileinfo.model.FileInfo;

@Mapper
public interface FileInfoMapper {
	
	Integer count() throws SQLException;
	
	Integer countByBoardId(Long boardId) throws SQLException;
	
	List<FileInfo> findAll() throws SQLException;
	
	List<FileInfo> findAllByBoardId(Long boardId) throws SQLException;
	
	FileInfo findById(Long id) throws SQLException;
	
	void insert(FileInfo entity) throws SQLException;
	
	void update(FileInfo entity) throws SQLException;
	
	void delete(Long id) throws SQLException;
	
}
