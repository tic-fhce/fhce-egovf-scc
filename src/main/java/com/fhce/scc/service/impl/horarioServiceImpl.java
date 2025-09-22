package com.fhce.scc.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.scc.dao.biometricoDao;
import com.fhce.scc.dao.historialDao;
import com.fhce.scc.dao.horarioDao;
import com.fhce.scc.model.biometricoModel;
import com.fhce.scc.model.historialModel;
import com.fhce.scc.model.horarioModel;
import com.fhce.scc.obj.horarioDtoObj;
import com.fhce.scc.obj.horarioDtoRequest;
import com.fhce.scc.obj.horarioDtoResponse;
import com.fhce.scc.service.horarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class horarioServiceImpl implements horarioService{
	private final horarioDao horarioDao;
	private final historialDao historialDao;
	private final biometricoDao biometricoDao; 
	private final ModelMapper modelMapper;
	
	@Transactional
	public List<horarioDtoResponse>listar(){
		List<horarioDtoResponse> horarios = this.horarioDao.findAll().stream()
				.map(horario -> this.modelMapper.map(horario, horarioDtoResponse.class))
				.collect(Collectors.toList());
		return (horarios);
	}
	@Transactional
	public horarioDtoResponse addHorario(horarioDtoRequest horarioDtoRequest){
		
		LocalDate fecha = LocalDate.parse(horarioDtoRequest.getFecha());
		LocalDate salida = LocalDate.parse(horarioDtoRequest.getSalida());
		
		horarioModel horarioModel = this.modelMapper.map(horarioDtoRequest, horarioModel.class);
		horarioModel aux = this.horarioDao.save(horarioModel);
		
		//Actualizamos las fechas en el historial
		
		List<historialModel>antiguo = this.historialDao.getHistorial(horarioDtoRequest.getCif(), fecha.getYear());
		
		if(antiguo.size()>0) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate f = LocalDate.parse(horarioDtoRequest.getFecha(), formatter);
	        LocalDate diaAnterior = f.minusDays(1);
	        String resultado = diaAnterior.format(formatter);
	        LocalDate newSalida = LocalDate.parse(resultado);
			antiguo.get(antiguo.size()-1).setGestionsalida(newSalida.getYear());
			antiguo.get(antiguo.size()-1).setMessalida(newSalida.getMonthValue());
			antiguo.get(antiguo.size()-1).setDiasalida(newSalida.getDayOfMonth());
			
			this.historialDao.save(antiguo.get(antiguo.size()-1));
			
		}
		
		//creamos la nueva fecha 
		historialModel historialModel = new historialModel();
		historialModel.setCif(horarioDtoRequest.getCif());
		historialModel.setHorario_id(aux.getId());
		historialModel.setGestion(fecha.getYear());
		historialModel.setMes(fecha.getMonthValue());
		historialModel.setDia(fecha.getDayOfMonth());
		historialModel.setGestionsalida(salida.getYear());
		historialModel.setMessalida(salida.getMonthValue());
		historialModel.setDiasalida(salida.getDayOfMonth());
		
		
		
		this.historialDao.save(historialModel);
		
		// cambiamos los id de horarios a los biometricos
		List<biometricoModel>lista = this.biometricoDao.getPerfil(horarioDtoRequest.getCif());
		
		for(int i=0;i<lista.size();i++) {
			lista.get(i).setHorario_id( Math.toIntExact(aux.getId()));
			lista.get(i).setEstado(1);
			lista.get(i).setId_tipo(horarioDtoRequest.getTipo());
			this.biometricoDao.save(lista.get(i));
		}
		
		return (this.modelMapper.map(horarioModel, horarioDtoResponse.class));
	}
	@Transactional
	public horarioDtoResponse getHorario(int gestion,Long cif){
		horarioModel horarioModel;
		List<historialModel> historiales = this.historialDao.getHistorial(cif, gestion);
		if(historiales.size()>0) {
			horarioModel = this.horarioDao.getHorario(historiales.get(0).getHorario_id(), cif);
		}
		else {
			horarioModel = new horarioModel();
			horarioModel.setCif(cif);
			horarioModel.setId((long)0);
		}
		return (this.modelMapper.map(horarioModel, horarioDtoResponse.class));
	}
	@Transactional
	public List<horarioDtoObj> getListaHorario(Long cif, int gestion){
		
		List<horarioDtoObj>listaHorario = new ArrayList<horarioDtoObj>();
		
		List<horarioModel>listaHorarioModel = this.horarioDao.getListaId(cif);
		List<historialModel>listaHistorialModel = this.historialDao.getHistorial(cif, gestion);
		
		horarioDtoObj horarioDtoObj;
		for(int i=0;i<listaHistorialModel.size();i++) {
			for(int j=0;j<listaHorarioModel.size();j++) {
				if(listaHistorialModel.get(i).getHorario_id().longValue() == listaHorarioModel.get(j).getId().longValue()) {
					horarioDtoObj = new horarioDtoObj();
					horarioDtoObj.setHistorial_id(listaHistorialModel.get(i).getId());
					horarioDtoObj.setCif(cif);
					horarioDtoObj.setGestion(listaHistorialModel.get(i).getGestion());
					horarioDtoObj.setMes(listaHistorialModel.get(i).getMes());
					horarioDtoObj.setDia(listaHistorialModel.get(i).getDia());
					horarioDtoObj.setFecha(horarioDtoObj.getGestion()+"-"+horarioDtoObj.getMes()+"-"+horarioDtoObj.getDia());
					horarioDtoObj.setValido(listaHistorialModel.get(i).getGestionsalida()+"-"+listaHistorialModel.get(i).getMessalida()+"-"+listaHistorialModel.get(i).getDiasalida());
					
					horarioDtoObj.setHorario_id(listaHistorialModel.get(i).getHorario_id());
					horarioDtoObj.setLem(listaHorarioModel.get(j).getLem());
					horarioDtoObj.setLsm(listaHorarioModel.get(j).getLsm());
					horarioDtoObj.setLet(listaHorarioModel.get(j).getLet());
					horarioDtoObj.setLst(listaHorarioModel.get(j).getLst());
					horarioDtoObj.setMem(listaHorarioModel.get(j).getMem());
					horarioDtoObj.setMsm(listaHorarioModel.get(j).getMsm());
					horarioDtoObj.setMet(listaHorarioModel.get(j).getMet());
					horarioDtoObj.setMst(listaHorarioModel.get(j).getMst());
					horarioDtoObj.setMiem(listaHorarioModel.get(j).getMiem());
					horarioDtoObj.setMism(listaHorarioModel.get(j).getMism());
					horarioDtoObj.setMiet(listaHorarioModel.get(j).getMiet());
					horarioDtoObj.setMist(listaHorarioModel.get(j).getMist());
					horarioDtoObj.setJem(listaHorarioModel.get(j).getJem());
					horarioDtoObj.setJsm(listaHorarioModel.get(j).getJsm());
					horarioDtoObj.setJet(listaHorarioModel.get(j).getJet());
					horarioDtoObj.setJst(listaHorarioModel.get(j).getJst());
					horarioDtoObj.setVem(listaHorarioModel.get(j).getVem());
					horarioDtoObj.setVsm(listaHorarioModel.get(j).getVsm());
					horarioDtoObj.setVet(listaHorarioModel.get(j).getVet());
					horarioDtoObj.setVst(listaHorarioModel.get(j).getVst());
					horarioDtoObj.setSem(listaHorarioModel.get(j).getSem());
					horarioDtoObj.setSsm(listaHorarioModel.get(j).getSsm());
					horarioDtoObj.setSet(listaHorarioModel.get(j).getSet());
					horarioDtoObj.setSst(listaHorarioModel.get(j).getSst());
					horarioDtoObj.setDem(listaHorarioModel.get(j).getDem());
					horarioDtoObj.setDsm(listaHorarioModel.get(j).getDsm());
					horarioDtoObj.setDet(listaHorarioModel.get(j).getDet());
					horarioDtoObj.setDst(listaHorarioModel.get(j).getDst());
					
					listaHorario.add(horarioDtoObj);
				}
			}
		}
		/*for(int i=1;i<listaHorario.size();i++)
		{
			listaHorario.get(i-1).setValido(listaHorario.get(i).getFecha());
		}*/
		
		return(listaHorario);
	}
	
}
