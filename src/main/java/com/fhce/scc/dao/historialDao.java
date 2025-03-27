package com.fhce.scc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.scc.model.historialModel;

public interface historialDao extends JpaRepository<historialModel,Long>{
	
	@Query(value = "select * from historial where _01cif=? and _03gestion=? order by id desc",nativeQuery=true)
	List<historialModel>getHistorial(Long cif,int gestion);

}