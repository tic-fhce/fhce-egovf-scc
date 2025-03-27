package com.fhce.scc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.scc.model.obsModel;

public interface obsDao extends JpaRepository<obsModel,Long>{
	
	@Query(value = "select * from obs where _04gestion=? and _05mes=?",nativeQuery=true)
	List<obsModel>getObs(int gestion, int mes);
	
	@Query(value = "select * from obs where _01cif =? and _05gestion=? and _06mes=? and _16estado < 2",nativeQuery=true)
	List<obsModel>getObsUsuario(Long cif,int gestion, int mes);
	
	@Query(value = "select * from obs where _01cif =? and _05gestion=?",nativeQuery=true)
	List<obsModel>getObsPerfil(Long cif,int gestion);
	
	@Query(value = "select * from obs where _04gestion=?",nativeQuery=true)
	List<obsModel>getListaObs(int gestion);

}
