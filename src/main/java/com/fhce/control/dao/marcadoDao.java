package com.fhce.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.control.model.biometricoModel;
import com.fhce.control.model.marcadoModel;

public interface marcadoDao extends JpaRepository<marcadoModel,Long>{
	
	@Query(value = "select * from marcado where _02user_id =? and _12lugar=? and _05gestion=? and _06mes=?",nativeQuery=true)
	List<marcadoModel>getMarcado(Long user_id,String lugar,int gestion, int mes);

}
