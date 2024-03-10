package com.fhce.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.control.model.biometricoModel;

public interface biometricoDao extends JpaRepository<biometricoModel,Long>{
	
	@Query(value = "select * from biometrico where _03cif=0",nativeQuery=true)
	List<biometricoModel>getCifCero();
	
	@Query(value = "select * from biometrico where _03cif>0",nativeQuery=true)
	List<biometricoModel>getCifUno();
	
	@Query(value = "select * from biometrico where _03cif=?",nativeQuery=true)
	List<biometricoModel>getPerfil(Long cif);
	
	@Query(value = "select * from biometrico where _07id_tipo=? and _03cif>0 and _04estado=0",nativeQuery=true)
	List<biometricoModel>getAll(Long idtipo);
	
	@Query(value = "select * from biometrico where _07id_tipo=? and _09sexo=? and _03cif>0 and _04estado=0",nativeQuery=true)
	List<biometricoModel>getAllGenero(Long idtipo,int sexo);
	
}
