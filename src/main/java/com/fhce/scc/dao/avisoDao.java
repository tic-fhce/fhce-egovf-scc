package com.fhce.scc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.scc.model.avisoModel;

public interface avisoDao extends JpaRepository<avisoModel,Long>{
	
	@Query(value = "select * from aviso where _04estado=1",nativeQuery=true)
	List<avisoModel>getAviso();
}