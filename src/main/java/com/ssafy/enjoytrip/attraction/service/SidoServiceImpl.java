package com.ssafy.enjoytrip.attraction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.mapper.SidoMapper;
import com.ssafy.enjoytrip.attraction.model.SidoDto;

@Service
public class SidoServiceImpl implements SidoService {
	private SidoMapper sidoMapper;

	public SidoServiceImpl(SidoMapper sidoMapper) {
		super();
		this.sidoMapper = sidoMapper;
	}

	@Override
	public SidoDto getSido(int sidoCode) throws Exception {
		return sidoMapper.getSido(sidoCode);
	}

	@Override
	public List<SidoDto> listSido() throws Exception {
		return sidoMapper.listSido();
	}

}
