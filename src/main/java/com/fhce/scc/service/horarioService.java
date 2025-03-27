package com.fhce.scc.service;

import java.util.List;

import com.fhce.scc.obj.horarioDtoObj;
import com.fhce.scc.obj.horarioDtoRequest;
import com.fhce.scc.obj.horarioDtoResponse;

public interface horarioService {
	List<horarioDtoResponse>listar();
	horarioDtoResponse addHorario(horarioDtoRequest horarioDtoRequest);
	horarioDtoResponse getHorario(int gestion,Long cif);
	List<horarioDtoObj> getListaHorario(Long cif, int gestion);

}
