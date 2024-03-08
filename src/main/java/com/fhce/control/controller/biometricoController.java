package com.fhce.control.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
//@CrossOrigin("http://svfhce.umsa.bo/") //debelop Fhce
@CrossOrigin("http://172.16.14.91:8080/") //debelop house
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
	
	@GetMapping("/listarBiometrico")
	public List<biometricoModel>listarBiometrico(){
		List<biometricoModel> aux= this.biometricoDao.getCifUno();
		List<biometricoModel> biometrico = new ArrayList<biometricoModel>();
		List<Long>cif=new ArrayList<Long>();
		for(int i=0;i<aux.size();i++) {
			cif.add(aux.get(i).get_03cif());
		}
		
		cif=cif.stream().distinct().collect(Collectors.toList());
		
		for(int i=0;i<cif.size();i++) {
			for(int j=0;j<aux.size();j++) {
				if(cif.get(i).longValue()==aux.get(j).get_03cif().longValue()) {
					biometrico.add(aux.get(j));
					break;
				}
			}
		}
		
		return(biometrico);
	}
	
	@GetMapping("/listarBiometricoTipo")
	public List<biometricoModel>listarBiometricoTipo(@RequestParam (value="tipo") Long tipo){
		
		List<biometricoModel> aux = this.biometricoDao.getAll(tipo);//Seleccionamos todos los usuarios del biometrico que pertenescan al tipo 
		List<biometricoModel> biometrico = new ArrayList<biometricoModel>();
		
		// creamos una lista solo para cif y eliminamos los repetidos
		List<Long>cif=new ArrayList<Long>(); 
		for(int i=0;i<aux.size();i++) {
			cif.add(aux.get(i).get_03cif());
		}
		cif=cif.stream().distinct().collect(Collectors.toList());
		
		//llenamos la Lista biometrico solo con los datos cif's unicos
		for(int i=0;i<cif.size();i++) {
			for(int j=0;j<aux.size();j++) {
				if(cif.get(i).longValue()==aux.get(j).get_03cif().longValue()) {
					biometrico.add(aux.get(j));
					break;
				}
			}
		}
		
		return(biometrico); // devolvemos la lista
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
