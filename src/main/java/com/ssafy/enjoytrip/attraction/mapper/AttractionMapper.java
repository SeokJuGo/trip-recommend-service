package com.ssafy.enjoytrip.attraction.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;

@Mapper
public interface AttractionMapper {
	
	List<AttractionDto> findAll() throws SQLException;

	List<AttractionDto> findAllByContentTypeId(int contentTypeId) throws SQLException;

	List<AttractionDto> findAllByTitleContains(String title) throws SQLException;

	List<AttractionDto> findAllByAddrContains(String address) throws SQLException;

	List<AttractionDto> findAllByZipcodeContains(String zipCode) throws SQLException;

	List<AttractionDto> findAllBySidoCode(int sidoCode) throws SQLException;

	List<AttractionDto> findAllBySidoCodeAndGugunCode(int sidoCode, int gugunCode) throws SQLException;

	List<AttractionDto> findAllByOverviewContains(String overview) throws SQLException;
	
	List<AttractionDto> findAllByAttraction(AttractionDto attractionDto) throws SQLException;

	AttractionDto findByContentId(int contentId) throws SQLException;
	
	List<AttractionDto> findAllHotAttraction() throws SQLException;
	
}
