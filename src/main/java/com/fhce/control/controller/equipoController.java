package com.fhce.control.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.control.dao.equipoDao;
import com.fhce.control.model.equipoModel;
import com.fhce.control.obj.equipoObj;

@RestController
@RequestMapping("fhce-egovf-scc/equipo") //develop
//@RequestMapping("equipo") //production
//@CrossOrigin("http://svfhce.umsa.bo/") //debelop Fhce
@CrossOrigin("http://172.16.14.91:8080/") //debelop house
public class equipoController {
	
	@Autowired
	private equipoDao equipoDao;
	
	@GetMapping("/listar")
	public List<equipoObj> listar(){
		List<equipoObj> lista = new ArrayList<equipoObj>();
		List<equipoModel> listaM = this.equipoDao.findAll();
		equipoModel aux;
		for(int i=0;i<listaM.size();i++) {
			aux = listaM.get(i);
			lista.add(new equipoObj(aux.getId(),aux.get_01detalle(),aux.get_02lugar(),aux.get_03ip(),aux.get_04puerto(),aux.get_05mac(),aux.get_06codigo(),aux.get_07estado()));
		}
		return lista;
	}
	
	@PostMapping("/addEquipo")
	public void addEquipo(@RequestBody equipoObj equipoObj) {
		equipoModel equipoModel = new equipoModel();
		
		equipoModel.set_01detalle(equipoObj.getDetalle());
		equipoModel.set_02lugar(equipoObj.getLugar());
		equipoModel.set_03ip(equipoObj.getIp());
		equipoModel.set_04puerto(equipoObj.getPuerto());
		equipoModel.set_05mac(equipoObj.getMac());
		equipoModel.set_06codigo(equipoObj.getCodigo());
		equipoModel.set_07estado(1);
		
		this.equipoDao.save(equipoModel);
	}
	
	@PutMapping("/updateEquipo")
	public void updateEquipo(@RequestBody equipoObj equipoObj) {
		equipoModel equipoModel = new equipoModel();
		
		equipoModel.setId(equipoObj.getId());
		equipoModel.set_01detalle(equipoObj.getDetalle());
		equipoModel.set_02lugar(equipoObj.getLugar());
		equipoModel.set_03ip(equipoObj.getIp());
		equipoModel.set_04puerto(equipoObj.getPuerto());
		equipoModel.set_05mac(equipoObj.getMac());
		equipoModel.set_06codigo(equipoObj.getCodigo());
		equipoModel.set_07estado(equipoObj.getEstado());
		this.equipoDao.save(equipoModel);
	}

}
