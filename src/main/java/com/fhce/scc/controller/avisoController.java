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

import com.fhce.scc.obj.avisoDtoRequest;
import com.fhce.scc.obj.avisoDtoResponse;
import com.fhce.scc.service.avisoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("fhce-egovf-scc/aviso") //develop
//@RequestMapping("/aviso") //develop
@RequiredArgsConstructor
public class avisoController {
	
	private  final avisoService avisoService;
	
	@GetMapping("/getAviso")
	public ResponseEntity<List<avisoDtoResponse>>getAviso(){
		try {
			return new ResponseEntity<>(this.avisoService.getAviso(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAvisoAll")
	public ResponseEntity<List<avisoDtoResponse>>getAvisoAll(){
		try {
			return new ResponseEntity<>(this.avisoService.getAvisoAll(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addAviso")
	public ResponseEntity <avisoDtoResponse>addAviso(@RequestBody avisoDtoRequest avisoDtoRequest){
		try {
			return new ResponseEntity<>(this.avisoService.addAviso(avisoDtoRequest),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateAviso")
	public ResponseEntity<avisoDtoResponse> updateAviso(@RequestBody avisoDtoResponse avisoDtoResponse){
		try {
			return new ResponseEntity<>(this.avisoService.updateAviso(avisoDtoResponse),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
