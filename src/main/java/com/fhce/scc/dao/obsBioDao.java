package com.fhce.scc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.scc.model.obsBioModel;

public interface obsBioDao extends JpaRepository<obsBioModel,Long>{
	@Query(value = "select * from obsbio where _02idobs=? and _09estado<2 order by id desc",nativeQuery=true)
	List<obsBioModel>getIdObs(Long idObs);
	
	@Query(value = "select * from obsbio where _02idobs=? and _09estado=? order by id desc",nativeQuery=true)
	List<obsBioModel>getIdObs(Long idObs,int estado);
	
	@Query(value = "select * from obsbio where _01cif=? and _09estado=? order by id desc",nativeQuery=true)
	List<obsBioModel>getCifObs(Long cif,int estado);

}
