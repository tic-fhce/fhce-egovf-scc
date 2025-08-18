package com.fhce.scc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.scc.model.biometricoModel;

public interface biometricoDao extends JpaRepository<biometricoModel,Long>{
	
	@Query(value = "select * from biometrico where _03cif=0",nativeQuery=true)
	List<biometricoModel>listarCifCero();
	
	@Query(value = "select * from biometrico where _03cif>0",nativeQuery=true)
	List<biometricoModel>listarEgovf();
	
	@Query(value = "select * from biometrico where _03cif=? ",nativeQuery=true)
	List<biometricoModel>getPerfil(Long cif);
	
	@Query(value = "select * from biometrico where id=?",nativeQuery=true)
	biometricoModel getId(Long id);
	
	@Query(value = "select * from biometrico where _07id_tipo=? and _03cif>0 and _04estado=1",nativeQuery=true)
	List<biometricoModel>listarBiometricoTipo(Long idtipo);
	
	@Query(value = "select * from biometrico where _07id_tipo=? and _09sexo=? and _03cif>0 and _04estado=1",nativeQuery=true)
	List<biometricoModel>getAllGenero(Long idtipo,int sexo);
	
	
}