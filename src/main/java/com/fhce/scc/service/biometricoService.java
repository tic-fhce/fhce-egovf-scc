package com.fhce.scc.service;

import java.util.List;


import com.fhce.scc.obj.biometricoDtoResponse;

public interface biometricoService {
	List<biometricoDtoResponse> listar();
	List<biometricoDtoResponse> getListarCifCero();
	List<biometricoDtoResponse> listarEgovf();
	List<biometricoDtoResponse> listarBiometrico();
	List<biometricoDtoResponse> listarBiometricoTipo(Long tipo);
	List<biometricoDtoResponse> getPerfil(Long cif);
	biometricoDtoResponse updateBiometrico(biometricoDtoResponse biometricoDtoResponse);
	biometricoDtoResponse estadoBiometrico(biometricoDtoResponse biometricoDtoResponse);
	List<biometricoDtoResponse> updateTipo(Long cif, Long tipo);
	List<biometricoDtoResponse> updateBiometricoTipo(biometricoDtoResponse biometricoDtoResponse);
	biometricoDtoResponse updateBiometricoCif(biometricoDtoResponse biometricoDtoResponse);
}
