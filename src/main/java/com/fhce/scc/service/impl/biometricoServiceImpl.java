package com.fhce.scc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.scc.dao.biometricoDao;
import com.fhce.scc.model.biometricoModel;
import com.fhce.scc.obj.biometricoDtoResponse;
import com.fhce.scc.service.biometricoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class biometricoServiceImpl implements biometricoService{
	
	private final biometricoDao biometricoDao;
	private final ModelMapper modelMapper;
	
	@Transactional
	public List<biometricoDtoResponse> listar(){
		List<biometricoDtoResponse> biometricos = this.biometricoDao.findAll().stream()
		        .map(biometrico -> this.modelMapper.map(biometrico, biometricoDtoResponse.class))
		        .collect(Collectors.toList());
		return (biometricos);
	}
	
	@Transactional
	public List<biometricoDtoResponse> listarCifCero(){
		List<biometricoDtoResponse> biometricos = this.biometricoDao.listarCifCero().stream()
		        .map(biometrico -> this.modelMapper.map(biometrico, biometricoDtoResponse.class))
		        .collect(Collectors.toList());
		return (biometricos);
	}
	
	@Transactional
	public List<biometricoDtoResponse> listarEgovf(){
		List<biometricoDtoResponse> biometricos = this.biometricoDao.listarEgovf().stream()
		        .map(biometrico -> this.modelMapper.map(biometrico, biometricoDtoResponse.class))
		        .collect(Collectors.toList());
		return (biometricos);
	}
	
	@Transactional
	public List<biometricoDtoResponse> listarBiometrico(){
		List<biometricoDtoResponse> aux= this.biometricoDao.listarEgovf().stream()
		        .map(bio -> this.modelMapper.map(bio, biometricoDtoResponse.class))
		        .collect(Collectors.toList());
		
		List<biometricoDtoResponse> biometrico = new ArrayList<biometricoDtoResponse>();
		List<Long>cif = new ArrayList<Long>();
		
		for(int i=0;i<aux.size();i++) {
			cif.add(aux.get(i).getCif());
		}
		
		cif = cif.stream().distinct().collect(Collectors.toList());
		
		for(int i=0;i<cif.size();i++) {
			for(int j=0;j<aux.size();j++) {
				if(cif.get(i).longValue() == aux.get(j).getCif().longValue()) {
					biometrico.add(aux.get(j));
					break;
				}
			}
		}
		
		return(biometrico);
	}
	@Transactional
	public List<biometricoDtoResponse> listarBiometricoTipo(Long tipo){
		
		List<biometricoDtoResponse> aux = this.biometricoDao.listarBiometricoTipo(tipo).stream()
				.map(bio -> this.modelMapper.map(bio, biometricoDtoResponse.class))
				.collect(Collectors.toList());//Seleccionamos todos los usuarios del biometrico que pertenescan al tipo 
		
		List<biometricoDtoResponse> biometrico = new ArrayList<biometricoDtoResponse>();
		
		// creamos una lista solo para cif y eliminamos los repetidos
		List<Long>cif=new ArrayList<Long>(); 
		
		for(int i=0;i<aux.size();i++) {
			cif.add(aux.get(i).getCif());
		}
		cif = cif.stream().distinct().collect(Collectors.toList());
		
		//llenamos la Lista biometrico solo con los datos cif's unicos
		for(int i=0;i<cif.size();i++) {
			for(int j=0;j<aux.size();j++) {
				if(cif.get(i).longValue() == aux.get(j).getCif().longValue()) {
					biometrico.add(aux.get(j));
					break;
				}
			}
		}
		
		return(biometrico); // devolvemos la lista
	}
	@Transactional
	public List<biometricoDtoResponse> getPerfil(Long cif){
		
		List<biometricoDtoResponse> biometricos = this.biometricoDao.getPerfil(cif).stream()
				.map(biometrico -> this.modelMapper.map(biometrico, biometricoDtoResponse.class))
				.collect(Collectors.toList());
		return biometricos;
	}
	
	@Transactional
	public biometricoDtoResponse updateBiometrico(biometricoDtoResponse biometricoDtoResponse){
		biometricoModel biometricoModel = this.modelMapper.map(biometricoDtoResponse,biometricoModel.class);
		this.biometricoDao.save(biometricoModel);
		return (this.modelMapper.map(biometricoModel, biometricoDtoResponse.class));
	}
	
	@Transactional
	public biometricoDtoResponse estadoBiometrico(biometricoDtoResponse biometricoDtoResponse){
		biometricoModel biometricoModel = this.modelMapper.map(biometricoDtoResponse,biometricoModel.class);
		this.biometricoDao.save(biometricoModel);
		return (this.modelMapper.map(biometricoModel, biometricoDtoResponse.class));
	}
	
	@Transactional
	public List<biometricoDtoResponse> updateTipo(Long cif, Long tipo){
		
		List<biometricoDtoResponse>biometricos = this.biometricoDao.getPerfil(cif).stream()
				.map(biometrico -> this.modelMapper.map(biometrico, biometricoDtoResponse.class))
				.collect(Collectors.toList());
		
		biometricoDtoResponse aux;
		List<biometricoDtoResponse> response = new ArrayList<biometricoDtoResponse>();
		for(int i=0;i<biometricos.size();i++) {
			aux = biometricos.get(i);
			aux.setId_tipo(tipo);
			response.add(aux);
			this.biometricoDao.save(this.modelMapper.map(aux, biometricoModel.class));
		}
		return (response);
	}
	
	@Transactional
	public List<biometricoDtoResponse> updateBiometricoTipo(biometricoDtoResponse biometricoDtoResponse){
		
		List<biometricoDtoResponse>biometricos = this.biometricoDao.getPerfil(biometricoDtoResponse.getCif()).stream()
				.map(biometrico -> this.modelMapper.map(biometrico, biometricoDtoResponse.class))
				.collect(Collectors.toList());
		
		biometricoDtoResponse aux;
		
		List<biometricoDtoResponse> response = new ArrayList<biometricoDtoResponse>();
		for(int i=0;i<biometricos.size();i++) {
			aux = biometricos.get(i);
			aux.setId_tipo(biometricoDtoResponse.getId_tipo());
			response.add(aux);
			this.biometricoDao.save(this.modelMapper.map(aux, biometricoModel.class));
		}
		return (response);
	}
}
