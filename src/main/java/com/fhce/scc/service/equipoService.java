package com.fhce.scc.service;

import java.util.List;

import com.fhce.scc.obj.equipoDtoRequest;
import com.fhce.scc.obj.equipoDtoResponse;

public interface equipoService {
	List<equipoDtoResponse> listar();
	equipoDtoResponse addEquipo(equipoDtoRequest equipoDtoRequest);
	equipoDtoResponse updateEquipo(equipoDtoResponse equipoDtoResponse);

}
