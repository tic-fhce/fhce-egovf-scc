package com.fhce.scc.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.scc.dao.obsBioDao;
import com.fhce.scc.model.obsBioModel;
import com.fhce.scc.obj.obsBioDtoResponse;
import com.fhce.scc.service.obsBioService;

import jakarta.transaction.Transactional;

@Service
public class obsBioServiceImpl implements obsBioService {
	private final ModelMapper modelMapper;
	private final obsBioDao obsBioDao;
	
	public obsBioServiceImpl(obsBioDao obsBioDao,ModelMapper modelMapper) {
		this.obsBioDao = obsBioDao;
		this.modelMapper = modelMapper;
	}
	
	@Transactional 
	public obsBioDtoResponse updateObsBio(obsBioDtoResponse obsBioDtoResponse) {
		
		obsBioModel obsBioModel = this.modelMapper.map(obsBioDtoResponse, obsBioModel.class);
		this.obsBioDao.save(obsBioModel);
		return (this.modelMapper.map(obsBioModel, obsBioDtoResponse.class));
	}

}
