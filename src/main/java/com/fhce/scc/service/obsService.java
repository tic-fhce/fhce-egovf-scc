package com.fhce.scc.service;

import java.util.List;

import com.fhce.scc.obj.obsDtoObj;
import com.fhce.scc.obj.obsDtoRequest;
import com.fhce.scc.obj.obsDtoResponse;

public interface obsService {
	obsDtoResponse addObsAll(obsDtoRequest request);
    obsDtoResponse addObs(obsDtoRequest request);
	List<obsDtoResponse> getObsPerfil(Long cif, int gestion);
	List<obsDtoResponse> getObs(Long cif, int gestion,int mes);
	List<obsDtoObj> getObsUsuario(Long cif, int gestion,int mes);
	obsDtoResponse updateObs(obsDtoObj obsDtoObj);
	obsDtoResponse updateObsEmpleado(obsDtoObj obsDtoObj);
	List<obsDtoObj> getListaObs(int gestion);
	List<obsDtoObj> getListaObsEmpleado(int gestion);
	List<obsDtoObj> getListaObsEliminado();
}
