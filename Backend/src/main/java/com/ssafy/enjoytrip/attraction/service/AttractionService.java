package com.ssafy.enjoytrip.attraction.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;

public interface AttractionService {
	
	List<AttractionDto> findAll() throws Exception;

	List<AttractionDto> findAllByContentTypeId(int contentTypeId) throws Exception;

	List<AttractionDto> findAllByTitleContains(String title) throws Exception;

	List<AttractionDto> findAllByAddrContains(String address) throws Exception;

	List<AttractionDto> findAllByZipcodeContains(String zipCode) throws Exception;

	List<AttractionDto> findAllBySidoCode(int sidoCode) throws Exception;

	List<AttractionDto> findAllBySidoCodeAndGugunCode(int sidoCode, int gugunCode) throws Exception;

	List<AttractionDto> findAllByOverviewContains(String overview) throws Exception;
	
	List<AttractionDto> findAllByAttraction(AttractionDto attractionDto) throws Exception;

	AttractionDto findByContentId(int contentId) throws Exception;
	
	List<AttractionDto> findAllHotAttraction() throws Exception;
	
}
