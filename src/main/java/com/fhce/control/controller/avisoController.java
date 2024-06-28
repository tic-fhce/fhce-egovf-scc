package com.fhce.control.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.control.dao.avisoDao;
import com.fhce.control.model.avisoModel;

@RestController
@RequestMapping("fhce-egovf-scc/aviso") //develop
//@RequestMapping("biometrico") //production
//@CrossOrigin("http://svfhce.umsa.bo/") //debelop Fhce
@CrossOrigin("http://172.16.14.91:8080/") //debelop house
public class avisoController {
	
	@Autowired
	private avisoDao avisoDao;
	
	@GetMapping("/getAviso")
	public List<avisoModel> getAviso(){
		return this.avisoDao.getAviso();
	}
	
	@GetMapping("/getAvisoAll")
	public List<avisoModel> getAvisoAll(){
		return this.avisoDao.findAll();
	}
	
	@PostMapping("/addAviso")
	public void addAviso(@RequestBody avisoModel avisoModel){
		this.avisoDao.save(avisoModel);
	}
	
	@PutMapping("/updateAviso")
	public void updateAviso(@RequestBody avisoModel avisoModel){
		this.avisoDao.save(avisoModel);
	}
	
}
