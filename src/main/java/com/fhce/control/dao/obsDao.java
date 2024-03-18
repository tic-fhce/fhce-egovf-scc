package com.fhce.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.control.model.marcadoModel;
import com.fhce.control.model.obsModel;

public interface obsDao extends JpaRepository<obsModel,Long>{
	
	@Query(value = "select * from obs where _01cif =? and _05gestion=? and _06mes=? and _16estado = 1",nativeQuery=true)
	List<obsModel>getObs(Long cif,int gestion, int mes);
	
	@Query(value = "select * from obs where _01cif =? and _05gestion=? and _06mes=? and _16estado < 2",nativeQuery=true)
	List<obsModel>getObsUsuario(Long cif,int gestion, int mes);
	
	@Query(value = "select * from obs where _01cif =? and _05gestion=?",nativeQuery=true)
	List<obsModel>getObsPerfil(Long cif,int gestion);
	
	@Query(value = "select * from obs where _16estado = 0",nativeQuery=true)
	List<obsModel>getListaObs();

}
