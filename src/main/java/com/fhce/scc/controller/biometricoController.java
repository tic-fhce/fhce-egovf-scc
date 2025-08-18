package com.fhce.scc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.scc.obj.biometricoDtoResponse;
import com.fhce.scc.service.biometricoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("fhce-egovf-scc/biometrico") //develop
//@RequestMapping("/biometrico") //develop
@RequiredArgsConstructor
public class biometricoController {
	
	private final biometricoService biometricoService;

	@GetMapping("/listar")
	public ResponseEntity <List<biometricoDtoResponse>> listar(){
		try {
			return new ResponseEntity<>(this.biometricoService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListarCifCero")
	public ResponseEntity <List<biometricoDtoResponse>> listarCifCero(){
		try {
			return new ResponseEntity<>(this.biometricoService.getListarCifCero(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarEgovf")
	public ResponseEntity <List<biometricoDtoResponse>> listarEgovf(){
		try {
			return new ResponseEntity<>(this.biometricoService.listarEgovf(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarBiometrico")
	public ResponseEntity <List<biometricoDtoResponse>>listarBiometrico(){
		try {
			return new ResponseEntity<>(this.biometricoService.listarBiometrico(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarBiometricoTipo")
	public ResponseEntity <List<biometricoDtoResponse>>listarBiometricoTipo(@RequestParam (value="tipo") Long tipo){
		try {
			return new ResponseEntity<>(this.biometricoService.listarBiometricoTipo(tipo),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getPerfil")
	public ResponseEntity <List<biometricoDtoResponse>> getPerfil(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.biometricoService.getPerfil(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateBiometrico")
	public ResponseEntity <biometricoDtoResponse> updateBiometrico(@RequestBody biometricoDtoResponse biometricoDtoResponse) {
		try {
			return new ResponseEntity<>(this.biometricoService.updateBiometrico(biometricoDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updateBiometricoCif")
	public ResponseEntity <biometricoDtoResponse> updateBiometricoCif(@RequestBody biometricoDtoResponse biometricoDtoResponse) {
		try {
			return new ResponseEntity<>(this.biometricoService.updateBiometricoCif(biometricoDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/estadoBiometrico")
	public ResponseEntity<biometricoDtoResponse> estadoBiometrico(@RequestBody biometricoDtoResponse biometricoDtoResponse) {
		try {
			return new ResponseEntity<>(this.biometricoService.estadoBiometrico(biometricoDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateTipo")
	public ResponseEntity <List<biometricoDtoResponse>> updateTipo(@RequestParam (value="cif") Long cif, @RequestParam (value="tipo") Long tipo) {
		try {
			return new ResponseEntity<>(this.biometricoService.updateTipo(cif,tipo),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateBiometricoTipo")
	public ResponseEntity <List<biometricoDtoResponse>> updateBiometricoTipo(@RequestBody biometricoDtoResponse biometricoDtoResponse) {
		
		try {
			return new ResponseEntity<>(this.biometricoService.updateBiometricoTipo(biometricoDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
