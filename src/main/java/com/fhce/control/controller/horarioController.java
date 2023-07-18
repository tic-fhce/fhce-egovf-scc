package com.fhce.control.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.control.dao.biometricoDao;
import com.fhce.control.dao.horarioDao;
import com.fhce.control.model.biometricoModel;
import com.fhce.control.model.horarioModel;

@RestController
@RequestMapping("fhce-egovf-scc/horario") //develop
//@RequestMapping("horario") //production
//@CrossOrigin("urlcliente/")//debelop Fhce
@CrossOrigin("http://192.168.31.45:8081/") //debelop house
public class horarioController {
	
	@Autowired
	private horarioDao horarioDao;
	
	@Autowired
	private biometricoDao biometricoDao;
	
	@GetMapping("/listar")
	public List<horarioModel> listar(){
		
		return horarioDao.findAll();
	}
	@PostMapping("/addHorario")
	public void addHorario(@RequestBody horarioModel horarioModel) {
		horarioModel aux = this.horarioDao.save(horarioModel);
		List<biometricoModel>lista=this.biometricoDao.getPerfil(horarioModel.get_01cif());
		for(int i=0;i<lista.size();i++) {
			lista.get(i).set_05horario_id( Math.toIntExact(aux.getId()));
			this.biometricoDao.save(lista.get(i));
		}
		
	}
	@GetMapping("/getHorario")
	public horarioModel getHorario(@RequestParam (value="id_horario") Long id_horario,@RequestParam (value="cif") Long cif){
		
		return horarioDao.getHorario(id_horario, cif);
	}
	
}
