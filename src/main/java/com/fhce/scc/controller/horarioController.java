package com.fhce.scc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.scc.obj.horarioDtoObj;
import com.fhce.scc.obj.horarioDtoRequest;
import com.fhce.scc.obj.horarioDtoResponse;
import com.fhce.scc.service.horarioService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping("fhce-egovf-scc/horario") //develop
@RequestMapping("/horario") //develop
@RequiredArgsConstructor
public class horarioController {
	
	private final horarioService horarioService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<horarioDtoResponse>> listar(){
		try {
			return new ResponseEntity<>(this.horarioService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addHorario")
	public ResponseEntity<horarioDtoResponse> addHorario(@RequestBody horarioDtoRequest horarioDtoRequest) {
		try {
			return new ResponseEntity<>(this.horarioService.addHorario(horarioDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getHorario")
	public ResponseEntity <horarioDtoResponse> getHorario(@RequestParam (value="gestion") int gestion,@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.horarioService.getHorario(gestion,cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListaHorario")
	public ResponseEntity <List<horarioDtoObj>>getListaHorario(@RequestParam (value="cif") Long cif,@RequestParam (value="gestion") int gestion){
		try {
			return new ResponseEntity<>(this.horarioService.getListaHorario(cif,gestion),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}
