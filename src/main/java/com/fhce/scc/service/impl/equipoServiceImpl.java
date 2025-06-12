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
	public equipoDtoResponse addEquipo(equipoDtoRequest request) {
		equipoModel entity = new equipoModel();
		entity.setDetalle(request.getDetalle());
		entity.setLugar(  request.getLugar());
		entity.setIp(     request.getIp());
		entity.setPuerto( request.getPuerto());
		entity.setMac(    request.getMac());
		entity.setCodigo(request.getCodigo());
		entity.setEstado(1);

		equipoModel saved = equipoDao.save(entity);

		equipoDtoResponse resp = new equipoDtoResponse();
		resp.setId(     saved.getId());
		resp.setDetalle(saved.getDetalle());
		resp.setLugar(  saved.getLugar());
		resp.setIp(     saved.getIp());
		resp.setPuerto(saved.getPuerto());
		resp.setMac(    saved.getMac());
		resp.setCodigo(saved.getCodigo());
		resp.setEstado(saved.getEstado());

		return resp;
	}

	
	@Transactional
	public equipoDtoResponse updateEquipo(equipoDtoResponse equipoDtoResponse){
		equipoModel equipoModel = this.modelMapper.map(equipoDtoResponse, equipoModel.class);
		this.equipoDao.save(equipoModel);
		return (this.modelMapper.map(equipoModel, equipoDtoResponse.class));
	}

}
