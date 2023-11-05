package com.ssafy.enjoytrip.attraction.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.SidoDto;

public interface SidoService {
	SidoDto getSido(int sidoCode) throws Exception;

	List<SidoDto> listSido() throws Exception;
}
