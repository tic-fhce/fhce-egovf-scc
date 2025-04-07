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

import com.fhce.scc.obj.formatoReporteDtoResponse;
import com.fhce.scc.obj.marcadoDtoRequest;
import com.fhce.scc.obj.marcadoDtoResponse;
import com.fhce.scc.obj.marcadoDtoTotalResponse;
import com.fhce.scc.obj.ultimoDtoResponse;
import com.fhce.scc.service.marcadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("fhce-egovf-scc/marcado") //develop
//@RequestMapping("/marcado") //develop

@RequiredArgsConstructor
public class marcadoController {
	
	private final marcadoService marcadoService;
	
	@PostMapping("/afa636b2fb7cc7ef69d9a6b7ab1550e02472114f")
	public ResponseEntity<marcadoDtoResponse> addMarcado(@RequestBody marcadoDtoRequest marcadoDtoRequest) {
		try {
			return new ResponseEntity<>(this.marcadoService.addMarcado(marcadoDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/listar")
	public ResponseEntity <List<marcadoDtoResponse>> listar(){
		try {
			return new ResponseEntity<>(this.marcadoService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/ultimo")
	public ResponseEntity <ultimoDtoResponse> ultimo() {
		try {
			return new ResponseEntity<>(this.marcadoService.ultimo(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarUserId")
	public ResponseEntity <List<marcadoDtoResponse>> listarUserId(@RequestParam (value="userId") Long userId){
		try {
			return new ResponseEntity<>(this.marcadoService.listarUserId(userId),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/reporteMes")
	public ResponseEntity <List<formatoReporteDtoResponse>> getReporteMes(@RequestParam (value="cif") Long cif, @RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes,@RequestParam (value="di") int di,@RequestParam (value="df") int df)
	{
		try {
			return new ResponseEntity<>(this.marcadoService.getReporteMes(cif,gestion,mes,di,df),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/reporteMesDia")
	public ResponseEntity <List<formatoReporteDtoResponse>> getReporteMesDia(@RequestParam (value="id_horario") Long id_horario,@RequestParam (value="cif") Long cif, @RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes,@RequestParam (value="di") int di,@RequestParam (value="df") int df) {
		try {
			return new ResponseEntity<>(this.marcadoService.getReporteMesDia(id_horario,cif,gestion,mes,di,df),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/reporteTotal")
	public ResponseEntity <List<marcadoDtoTotalResponse>> getReporteTotal(@RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes, @RequestParam (value="tipo") int tipo) {
		try {
			return new ResponseEntity<>(this.marcadoService.getReporteTotal(gestion,mes,tipo),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
