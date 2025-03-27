package com.fhce.scc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.scc.model.marcadoModel;

public interface marcadoDao extends JpaRepository<marcadoModel,Long>{
	
	@Query(value = "select * from marcado where _02user_id =? and _12lugar=? and _05gestion=? and _06mes=?",nativeQuery=true)
	List<marcadoModel>getMarcado(Long user_id,String lugar,int gestion, int mes);
	
	@Query(value = "select * from marcado where _02user_id =?",nativeQuery=true)
	List<marcadoModel>listarUserId(Long user_id);

}