package com.ssafy.enjoytrip.attraction.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.mapper.AttractionMapper;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;

@Service
public class AttractionServiceImpl implements AttractionService {
	private AttractionMapper mapper;

	public AttractionServiceImpl(AttractionMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public List<AttractionDto> findAll() throws Exception {
		return mapper.findAll();
	}

	@Override
	public List<AttractionDto> findAllByContentTypeId(int contentTypeId) throws Exception {
		return mapper.findAllByContentTypeId(contentTypeId);
	}

	@Override
	public List<AttractionDto> findAllByTitleContains(String title) throws Exception {
		return mapper.findAllByTitleContains(title);
	}

	@Override
	public List<AttractionDto> findAllByAddrContains(String address) throws Exception {
		return mapper.findAllByAddrContains(address);
	}

	@Override
	public List<AttractionDto> findAllByZipcodeContains(String zipCode) throws Exception {
		return mapper.findAllByZipcodeContains(zipCode);
	}

	@Override
	public List<AttractionDto> findAllBySidoCode(int sidoCode) throws Exception {
		return mapper.findAllBySidoCode(sidoCode);
	}

	@Override
	public List<AttractionDto> findAllBySidoCodeAndGugunCode(int sidoCode, int gugunCode) throws Exception {
		return mapper.findAllBySidoCodeAndGugunCode(sidoCode, gugunCode);
	}

	@Override
	public List<AttractionDto> findAllByOverviewContains(String overview) throws Exception {
		return mapper.findAllByOverviewContains(overview);
	}

	@Override
	public AttractionDto findByContentId(int contentId) throws Exception {
		return mapper.findByContentId(contentId);
	}

	@Override
	public List<AttractionDto> findAllByAttraction(AttractionDto attractionDto) throws Exception {
		return mapper.findAllByAttraction(attractionDto);
	}
	
	@Override
	public List<AttractionDto> findAllHotAttraction() throws SQLException {
		return mapper.findAllHotAttraction();
	}

}
