package com.fhce.scc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.scc.model.horarioModel;

public interface horarioDao extends JpaRepository<horarioModel,Long>{
	@Query(value = "select * from horario where id=? and _01cif=?",nativeQuery=true)
	horarioModel getHorario(Long id, Long cif);
	
	@Query(value = "select * from horario where _01cif=?",nativeQuery=true)
	List<horarioModel> getListaId(Long cif);

}