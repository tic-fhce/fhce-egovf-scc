package com.fhce.scc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.scc.dao.equipoDao;
import com.fhce.scc.model.equipoModel;
import com.fhce.scc.obj.equipoDtoRequest;
import com.fhce.scc.obj.equipoDtoResponse;
import com.fhce.scc.service.equipoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class equipoServiceImpl implements equipoService {
	
	private final equipoDao equipoDao;
	private final ModelMapper modelMapper;
	
	@Transactional
	public List<equipoDtoResponse> listar(){
		
		List<equipoDtoResponse> equipos = this.equipoDao.findAll().stream()
				.map(equipo -> this.modelMapper.map(equipo, equipoDtoResponse.class))
				.collect(Collectors.toList());
		return equipos;	
	}
	
	
	@Transactional
	public equipoDtoResponse addEquipo(equipoDtoRequest equipoDtoRequest){
		
		equipoModel equipoModel = this.modelMapper.map(equipoDtoRequest, equipoModel.class);
		equipoModel.setEstado(1);
		this.equipoDao.save(equipoModel);
		return (this.modelMapper.map(equipoModel, equipoDtoResponse.class));
	}
	
	@Transactional
	public equipoDtoResponse updateEquipo(equipoDtoResponse equipoDtoResponse){
		equipoModel equipoModel = this.modelMapper.map(equipoDtoResponse, equipoModel.class);
		this.equipoDao.save(equipoModel);
		return (this.modelMapper.map(equipoModel, equipoDtoResponse.class));
	}

}
