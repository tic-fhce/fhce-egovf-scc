package com.fhce.scc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.scc.dao.avisoDao;
import com.fhce.scc.model.avisoModel;
import com.fhce.scc.obj.avisoDtoRequest;
import com.fhce.scc.obj.avisoDtoResponse;
import com.fhce.scc.service.avisoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class avisoServiceImpl implements avisoService{
	private final avisoDao avisoDao;
	private final ModelMapper modelMapper;

	
	@Transactional
	public List<avisoDtoResponse> getAviso(){
		List<avisoDtoResponse> avisos = this.avisoDao.getAviso().stream()
		        .map(aviso -> modelMapper.map(aviso, avisoDtoResponse.class))
		        .collect(Collectors.toList());
		return (avisos);
	}
	@Transactional
	public List<avisoDtoResponse> getAvisoAll(){
		List<avisoDtoResponse> avisos = avisoDao.findAll().stream()
		        .map(aviso -> modelMapper.map(aviso, avisoDtoResponse.class))
		        .collect(Collectors.toList());
		return (avisos);
	}
	
	@Transactional
	public avisoDtoResponse addAviso(avisoDtoRequest avisoDtoRequest) {
		avisoModel avisoModel = modelMapper.map(avisoDtoRequest, avisoModel.class);
		this.avisoDao.save(avisoModel);
		return modelMapper.map(avisoModel, avisoDtoResponse.class);
	}
	
	@Transactional
	public avisoDtoResponse updateAviso(avisoDtoResponse avisoDtoResponse) {
		avisoModel avisoModel = modelMapper.map(avisoDtoResponse, avisoModel.class);
		this.avisoDao.save(avisoModel);
		return modelMapper.map(avisoModel, avisoDtoResponse.class);
	}

}
