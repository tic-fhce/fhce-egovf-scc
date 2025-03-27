package com.fhce.scc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.scc.obj.obsBioDtoResponse;

import com.fhce.scc.service.obsBioService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping("fhce-egovf-scc/obsbio") //develop 
@RequestMapping("/obsbio") //develop 
@RequiredArgsConstructor
public class obsBioController {
	private final obsBioService obsBioService;
	
	@PutMapping("/updateObsBio")
	public ResponseEntity <obsBioDtoResponse>updateObsBio(@RequestBody obsBioDtoResponse obsBioDtoResponse) {
		try {
			return new ResponseEntity<>(this.obsBioService.updateObsBio(obsBioDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

}
