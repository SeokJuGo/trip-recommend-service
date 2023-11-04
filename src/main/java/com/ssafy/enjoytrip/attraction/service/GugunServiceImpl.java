package com.ssafy.enjoytrip.attraction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.mapper.GugunMapper;
import com.ssafy.enjoytrip.attraction.model.GugunDto;

@Service
public class GugunServiceImpl implements GugunService {
	private GugunMapper mapper;

	public GugunServiceImpl(GugunMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public List<GugunDto> findAll() throws Exception {
		return mapper.findAll();
	}

	@Override
	public List<GugunDto> findAllBySidoCode(int sidoCode) throws Exception {
		return mapper.findAllBySidoCode(sidoCode);
	}

	@Override
	public GugunDto findBySidoCodeAndGugunCode(int sidoCode, int gugunCode) throws Exception {
		return mapper.findBySidoCodeAndGugunCode(sidoCode, gugunCode);
	}

}
