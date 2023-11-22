package com.ssafy.enjoytrip.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.attraction.mapper.AttractionMapper;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.board.mapper.PlanMapper;
import com.ssafy.enjoytrip.board.model.Plan;
import com.ssafy.enjoytrip.board.model.PlanRequestDto;
import com.ssafy.enjoytrip.board.model.PlanResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
	
	private final PlanMapper planMapper;
	private final AttractionMapper attractionMapper;
	

	@Override
	@Transactional
	public Integer count(Long boardId) throws SQLException {
		return planMapper.count(boardId.intValue());
	}

	@Override
	@Transactional
	public List<PlanResponseDto> findAllByBoardId(Long boardId) throws SQLException {
		List<Plan> plans = planMapper.findAllByBoardId(boardId.intValue());
		List<PlanResponseDto> responseDtos = new ArrayList<>();
		for (Plan plan : plans) {
			PlanResponseDto responseDto = new PlanResponseDto(plan);
			AttractionDto attraction = attractionMapper.findByContentId(plan.getAttractionId().intValue());
			responseDto.setAttraction(attraction);
			responseDtos.add(responseDto);
		}
		return responseDtos;
	}

	@Override
	@Transactional
	public Long insert(PlanRequestDto requestDto) throws SQLException {
		Plan entity = requestDto.toEntity();
		planMapper.insert(entity);
		return entity.getId();
	}

	@Override
	@Transactional
	public Long update(Long id, PlanRequestDto requestDto) throws SQLException {
		Plan entity = requestDto.toEntity(id);
		planMapper.update(entity);
		return entity.getId();
	}

	@Override
	@Transactional
	public Integer delete(Long id) throws SQLException {
		return planMapper.delete(id.intValue());
	}

	@Override
	@Transactional
	public Integer deleteAllByBoardId(Long boardId) throws SQLException {
		return planMapper.deleteAllByBoardId(boardId.intValue());
	}

}
