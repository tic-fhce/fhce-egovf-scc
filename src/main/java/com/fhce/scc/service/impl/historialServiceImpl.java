package com.fhce.scc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.scc.dao.historialDao;
import com.fhce.scc.obj.historialDtoResponse;
import com.fhce.scc.service.historialService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class historialServiceImpl implements historialService{
	private final historialDao historialDao;
	private final ModelMapper modelMapper;
	
	
	@Transactional
	public List<historialDtoResponse>listar(){
		List<historialDtoResponse>historiales = this.historialDao.findAll().stream()
				.map(historial -> this.modelMapper.map(historial, historialDtoResponse.class))
				.collect(Collectors.toList());
		return (historiales);
	}
	
}
