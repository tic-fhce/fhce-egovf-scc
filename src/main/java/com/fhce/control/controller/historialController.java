package com.fhce.control.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.control.dao.historialDao;
import com.fhce.control.model.historialModel;

@RestController
@RequestMapping("fhce-egovf-scc/historial") //develop
//@RequestMapping("historial") //production
//@CrossOrigin("https://svfhce.umsa.bo/")//debelop Fhce
@CrossOrigin("http://192.168.31.45:8080/") //debelop house
public class historialController {
	
	@Autowired
	private historialDao historialDao;
	
	@GetMapping("/listar")
	public List<historialModel> listar(){
		return historialDao.findAll();
	}

}
