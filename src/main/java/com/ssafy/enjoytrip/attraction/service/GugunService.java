package com.ssafy.enjoytrip.attraction.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.GugunDto;

public interface GugunService {
	List<GugunDto> findAll() throws Exception;

	List<GugunDto> findAllBySidoCode(int sidoCode) throws Exception;

	GugunDto findBySidoCodeAndGugunCode(int sidoCode, int gugunCode) throws Exception;
}
