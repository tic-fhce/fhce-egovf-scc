package com.fhce.scc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.scc.obj.obsDtoObj;
import com.fhce.scc.obj.obsDtoResponse;
import com.fhce.scc.service.obsService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping("fhce-egovf-scc/obs") //develop 
@RequestMapping("/obs") //develop 
@RequiredArgsConstructor
public class obsController {
	private final obsService obsService;

	@GetMapping("/getListaObs")
	public ResponseEntity <List<obsDtoResponse>> getListaObs(@RequestParam (value="gestion") int gestion){
		try {
			return new ResponseEntity<>(this.obsService.getListaObs(gestion),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getListaObsEmpleado")
	public ResponseEntity <List<obsDtoObj>> getListaObsEmpleado(@RequestParam (value="gestion") int gestion){
		try {
			return new ResponseEntity<>(this.obsService.getListaObsEmpleado(gestion),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getListaObsEliminado")
	public ResponseEntity <List<obsDtoObj>> getListaObsEliminado(){
		try {
			return new ResponseEntity<>(this.obsService.getListaObsEliminado(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addObs")
	public ResponseEntity<obsDtoObj>  addObs(@RequestBody obsDtoObj obsDtoObj) {
		try {
			return new ResponseEntity<>(this.obsService.addObs(obsDtoObj),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping("/addObsAll")
	public ResponseEntity<obsDtoObj>  addObsAll(@RequestBody obsDtoObj obsDtoObj) {
		try {
			return new ResponseEntity<>(this.obsService.addObsAll(obsDtoObj),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getObsPerfil")
	public ResponseEntity <List<obsDtoResponse>> getObsPerfil(@RequestParam (value="cif") Long cif,@RequestParam (value="gestion") int gestion){
		try {
			return new ResponseEntity<>(this.obsService.getObsPerfil(cif,gestion),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getObs")
	public ResponseEntity<List<obsDtoResponse>> getObs(@RequestParam (value="cif") Long cif,@RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes){
		try {
			return new ResponseEntity<>(this.obsService.getObs(cif,gestion,mes),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/getObsUsuario")
	public ResponseEntity <List<obsDtoObj>> getObsUsuario(@RequestParam (value="cif") Long cif,@RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes){
		try {
			return new ResponseEntity<>(this.obsService.getObsUsuario(cif,gestion,mes),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateObs")
	public ResponseEntity <obsDtoResponse>updateObs(@RequestBody obsDtoObj obsDtoObj) {
		try {
			return new ResponseEntity<>(this.obsService.updateObs(obsDtoObj),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
