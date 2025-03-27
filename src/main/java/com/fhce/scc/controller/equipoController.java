package com.fhce.scc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.scc.obj.equipoDtoRequest;
import com.fhce.scc.obj.equipoDtoResponse;
import com.fhce.scc.service.equipoService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping("fhce-egovf-scc/equipo") //develop
@RequestMapping("/equipo") //develop
@RequiredArgsConstructor
public class equipoController {
	
	private final equipoService equipoService;

	@GetMapping("/listar")
	public ResponseEntity <List<equipoDtoResponse>> listar(){
		try {
			return new ResponseEntity<>(this.equipoService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/addEquipo")
	public ResponseEntity <equipoDtoResponse> addEquipo(@RequestBody equipoDtoRequest equipoDtoRequest) {
		try {
			return new ResponseEntity<>(this.equipoService.addEquipo(equipoDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateEquipo")
	public ResponseEntity <equipoDtoResponse>updateEquipo(@RequestBody equipoDtoResponse equipoDtoResponse) {
		try {
			return new ResponseEntity<>(this.equipoService.updateEquipo(equipoDtoResponse),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
