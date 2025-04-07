package com.fhce.scc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.scc.obj.historialDtoResponse;
import com.fhce.scc.service.historialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("fhce-egovf-scc/historial") //develop
//@RequestMapping("/historial") //develop
@RequiredArgsConstructor
public class historialController {
	
	private final historialService historialService;
	
	@GetMapping("/listar")
	public ResponseEntity <List<historialDtoResponse>> listar(){
		try {
			return new ResponseEntity<>(this.historialService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
