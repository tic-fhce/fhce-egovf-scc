package com.fhce.scc.service;

import java.util.List;

import com.fhce.scc.obj.formatoReporteDtoResponse;
import com.fhce.scc.obj.marcadoDtoRequest;
import com.fhce.scc.obj.marcadoDtoResponse;
import com.fhce.scc.obj.marcadoDtoTotalResponse;
import com.fhce.scc.obj.ultimoDtoResponse;

public interface marcadoService {
	marcadoDtoResponse addMarcado(marcadoDtoRequest marcadoDtorequest);
	List<marcadoDtoResponse> listar() ;
	List<marcadoDtoResponse> listarUserId(Long userId);
	ultimoDtoResponse ultimo();
	List<formatoReporteDtoResponse> getReporteMes(Long cif,int gestion,int mes,int di,int df);
	List<formatoReporteDtoResponse> getReporteMesDia(Long id_horario,Long cif,int gestion,int mes,int di,int df);
	List<formatoReporteDtoResponse> getReporte(Long cif,Long id_horario,int gestion,int mes);
	List<marcadoDtoTotalResponse> getReporteTotal(int gestion,int mes,int tipo);
}
