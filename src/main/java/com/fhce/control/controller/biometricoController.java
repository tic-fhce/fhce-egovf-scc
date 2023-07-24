package com.fhce.control.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.control.dao.biometricoDao;
import com.fhce.control.model.biometricoModel;

@RestController
@RequestMapping("fhce-egovf-scc/biometrico") //develop
//@RequestMapping("biometrico") //production
//@CrossOrigin("urlcliente/") //debelop Fhce
@CrossOrigin("http://192.168.31.45:8081/") //debelop house
public class biometricoController {
	
	@Autowired
	private biometricoDao biometricoDao;
	
	@GetMapping("/listar")
	public List<biometricoModel> listar(){
		
		return this.biometricoDao.findAll();
	}
	
	@GetMapping("/listarCifCero")
	public List<biometricoModel> listarCifCero(){
		
		return this.biometricoDao.getCifCero();
	}
	
	@GetMapping("/listarEgovf")
	public List<biometricoModel> listarEgovf(){
		
		return this.biometricoDao.getCifUno();
	}
	
	@GetMapping("/getPerfil")
	public List<biometricoModel> getPerfil(@RequestParam (value="cif") Long cif){
		return this.biometricoDao.getPerfil(cif);
	}
	
	@PutMapping("/agregarBiometrico")
	public void agregarBiometrico(@RequestBody biometricoModel biometricoModel) {
		this.biometricoDao.save(biometricoModel);
		
	}
	@PutMapping("/estadoBiometrico")
	public void estadoBiometrico(@RequestBody biometricoModel biometricoModel) {
		this.biometricoDao.save(biometricoModel);
	}
}
