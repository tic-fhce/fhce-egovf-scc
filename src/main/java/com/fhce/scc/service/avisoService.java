package com.fhce.scc.service;

import java.util.List;

import com.fhce.scc.obj.avisoDtoRequest;
import com.fhce.scc.obj.avisoDtoResponse;

public interface avisoService {
	List<avisoDtoResponse> getAviso();
	List<avisoDtoResponse> getAvisoAll();
	avisoDtoResponse addAviso(avisoDtoRequest avisoDtoRequest);
	avisoDtoResponse updateAviso(avisoDtoResponse avisoDtoResponse);

}
