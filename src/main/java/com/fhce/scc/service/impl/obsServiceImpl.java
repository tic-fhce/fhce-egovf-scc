package com.fhce.scc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.scc.dao.biometricoDao;
import com.fhce.scc.dao.obsBioDao;
import com.fhce.scc.dao.obsDao;
import com.fhce.scc.model.biometricoModel;
import com.fhce.scc.model.obsBioModel;
import com.fhce.scc.model.obsModel;
import com.fhce.scc.obj.obsDtoObj;
import com.fhce.scc.obj.obsDtoResponse;
import com.fhce.scc.service.obsService;

import jakarta.transaction.Transactional;


@Service

public class obsServiceImpl implements obsService {
	
	private final obsDao obsDao;
	private final ModelMapper modelMapper;
	private final biometricoDao biometricoDao;
	private final obsBioDao obsBioDao;
	public obsServiceImpl(obsDao obsDao,biometricoDao biometricoDao, ModelMapper modelMapper,obsBioDao obsBioDao) {
		this.obsDao = obsDao;
		this.modelMapper = modelMapper;
		this.biometricoDao = biometricoDao;
		this.obsBioDao = obsBioDao;
	}
	
	@Transactional 
	public obsDtoObj addObs(obsDtoObj obsDtoObj) {
		Set<String> tiposEntrada = Set.of("asueto","Entrada. M.", "Entrada. T.", "horas", "extraordinario", "comision", "permiso");
		Set<String> tiposSalida = Set.of("continuo","Salida. M.", "Salida. T.");
		
		String[] partes = obsDtoObj.getHoraEntrada().split(":");
		obsDtoObj.setHEntrada(Integer.parseInt(partes[0]));
		obsDtoObj.setMEntrada(Integer.parseInt(partes[1]));
		
		partes = obsDtoObj.getHoraSalida().split(":");
		obsDtoObj.setHSalida(Integer.parseInt(partes[0]));
		obsDtoObj.setMSalida(Integer.parseInt(partes[1]));
		
		//Creamos la Observacion
		obsModel obsModel = new obsModel();
		obsModel.setUidObs(obsDtoObj.getUidobs());
		obsModel.setFechaInicio(obsDtoObj.getFechainicio());
		obsModel.setFechaFin(obsDtoObj.getFechafin());
		obsModel.setGestion(obsDtoObj.getGestion());
		obsModel.setMes(obsDtoObj.getMes());
		obsModel.setDi(obsDtoObj.getDi());
		obsModel.setDf(obsDtoObj.getDf());
		obsModel.setDetalle(obsDtoObj.getDetalle());
		obsModel.setImagen(obsDtoObj.getImagen());
		obsModel.setTipo(obsDtoObj.getTipo());
		obsModel.setUrl(obsDtoObj.getUrl());
		obsModel.setTipoId(0);
		this.obsDao.save(obsModel);
		
		// Agregamos las Observaciones a todos los usuarios que cumplen las condiciones
		if(tiposEntrada.contains(obsDtoObj.getTipo())) {
			obsDtoObj.setHoraSalida("");
			obsDtoObj.setHSalida(0);
			obsDtoObj.setMSalida(0);
		}else if(tiposSalida.contains(obsDtoObj.getTipo())) {
			obsDtoObj.setHoraEntrada("");
			obsDtoObj.setHEntrada(0);
			obsDtoObj.setMEntrada(0);
		}
		
		obsBioModel obsBioModel = new obsBioModel();
		obsBioModel.setCif(obsDtoObj.getCif());
		obsBioModel.setIdObs(obsModel.getId());
		obsBioModel.setEstado(obsDtoObj.getEstado());
		obsBioModel.setHoraEntrada(obsDtoObj.getHoraEntrada());
		obsBioModel.setHEntrada(obsDtoObj.getHEntrada());
		obsBioModel.setMEntrada(obsDtoObj.getMEntrada());
		obsBioModel.setHoraSalida(obsDtoObj.getHoraSalida());
		obsBioModel.setHSalida(obsDtoObj.getHSalida());
		obsBioModel.setMSalida(obsDtoObj.getMSalida());
		this.obsBioDao.save(obsBioModel);
		
		return (obsDtoObj);
	}
	@Transactional 
	public obsDtoResponse updateObs(obsDtoObj obsDtoObj) {
		Set<String> tiposEntrada = Set.of("asueto","Entrada. M.", "Entrada. T.", "horas", "extraordinario", "comision", "permiso");
		Set<String> tiposSalida = Set.of("continuo","Salida. M.", "Salida. T.");
		
		String[] partes;
		if(!obsDtoObj.getHoraEntrada().equals("")) {
			partes = obsDtoObj.getHoraEntrada().split(":");
			obsDtoObj.setHEntrada(Integer.parseInt(partes[0]));
			obsDtoObj.setMEntrada(Integer.parseInt(partes[1]));
		}
		if(!obsDtoObj.getHoraSalida().equals("")) {
			partes = obsDtoObj.getHoraSalida().split(":");
			obsDtoObj.setHSalida(Integer.parseInt(partes[0]));
			obsDtoObj.setMSalida(Integer.parseInt(partes[1]));
		}
		
		//Modificamos la Observacion
		obsModel obsModel = new obsModel();
		obsModel.setId(obsDtoObj.getId());
		obsModel.setUidObs(obsDtoObj.getUidobs());
		obsModel.setFechaInicio(obsDtoObj.getFechainicio());
		obsModel.setFechaFin(obsDtoObj.getFechafin());
		obsModel.setGestion(obsDtoObj.getGestion());
		obsModel.setMes(obsDtoObj.getMes());
		obsModel.setDi(obsDtoObj.getDi());
		obsModel.setDf(obsDtoObj.getDf());
		obsModel.setDetalle(obsDtoObj.getDetalle());
		obsModel.setImagen(obsDtoObj.getImagen());
		obsModel.setTipo(obsDtoObj.getTipo());
		obsModel.setUrl(obsDtoObj.getUrl());
		obsModel.setTipoId(0);
		this.obsDao.save(obsModel);
		
		// Agregamos las Observaciones a todos los usuarios que cumplen las condiciones
		if(tiposEntrada.contains(obsDtoObj.getTipo())) {
			obsDtoObj.setHoraSalida("");
			obsDtoObj.setHSalida(0);
			obsDtoObj.setMSalida(0);
		}else if(tiposSalida.contains(obsDtoObj.getTipo())) {
			obsDtoObj.setHoraEntrada("");
			obsDtoObj.setHEntrada(0);
			obsDtoObj.setMEntrada(0);
		}
		
		List<biometricoModel>lista;
		
		if(obsDtoObj.getSexo() == 0) {
			//getCif contiene el tipo de Empleado que es 
			lista = this.biometricoDao.listarBiometricoTipo(obsDtoObj.getCif());
		}
		else {
			lista = this.biometricoDao.getAllGenero(obsDtoObj.getCif(), obsDtoObj.getSexo());
		}
		
		// creamos una lista solo para cif y eliminamos los repetidos
		List<Long>cif=new ArrayList<Long>(); 
		for(int i=0;i<lista.size();i++) {
			cif.add(lista.get(i).getCif());
		}
		cif=cif.stream().distinct().collect(Collectors.toList());
		
		// modificamos los cif que ya estan registrados
		List<obsBioModel>obsBioModel = this.obsBioDao.getIdObs(obsDtoObj.getId());
		for(int i = 0; i<obsBioModel.size();i++) {
			obsBioModel.get(i).setHoraEntrada(obsDtoObj.getHoraEntrada());
			obsBioModel.get(i).setHEntrada(obsDtoObj.getHEntrada());
			obsBioModel.get(i).setMEntrada(obsDtoObj.getMEntrada());
			obsBioModel.get(i).setHoraSalida(obsDtoObj.getHoraSalida());
			obsBioModel.get(i).setHSalida(obsDtoObj.getHSalida());
			obsBioModel.get(i).setMSalida(obsDtoObj.getMSalida());
			this.obsBioDao.save(obsBioModel.get(i));
		}
		System.out.println("$$$$$$$$$$$$$" + obsBioModel.size()+"----"+cif.size());
		// agregamos a los que faltan
		List<Long>obsBM = new ArrayList<Long>();
		for(int i=0; i<obsBioModel.size();i++) {
			obsBM.add(obsBioModel.get(i).getCif());
		}
		
		for(int i=0;i<cif.size();i++) {
			if(!obsBM.contains(cif.get(i))) {
				System.out.println(cif.get(i));
				obsBioModel obsBio = new obsBioModel();
				
				obsBio.setCif(cif.get(i));
				
				obsBio.setIdObs(obsDtoObj.getId());
				obsBio.setEstado(0);
				obsBio.setHoraEntrada(obsDtoObj.getHoraEntrada());
				obsBio.setHEntrada(obsDtoObj.getHEntrada());
				obsBio.setMEntrada(obsDtoObj.getMEntrada());
				obsBio.setHoraSalida(obsDtoObj.getHoraSalida());
				obsBio.setHSalida(obsDtoObj.getHSalida());
				obsBio.setMSalida(obsDtoObj.getMSalida());
				this.obsBioDao.save(obsBio);
			}
		}
		
		return (this.modelMapper.map(obsModel, obsDtoResponse.class));
	}
	
	@Transactional 
	public obsDtoObj addObsAll(obsDtoObj obsDtoObj) {
		
		Set<String> tiposEntrada = Set.of("asueto","Entrada. M.", "Entrada. T.", "horas", "extraordinario", "comision", "permiso");
		Set<String> tiposSalida = Set.of("continuo","Salida. M.", "Salida. T.");
		
		String[] partes = obsDtoObj.getHoraEntrada().split(":");
		obsDtoObj.setHEntrada(Integer.parseInt(partes[0]));
		obsDtoObj.setMEntrada(Integer.parseInt(partes[1]));
		
		partes = obsDtoObj.getHoraSalida().split(":");
		obsDtoObj.setHSalida(Integer.parseInt(partes[0]));
		obsDtoObj.setMSalida(Integer.parseInt(partes[1]));
		
		List<biometricoModel>lista;
		
		if(obsDtoObj.getSexo() == 0) {
			//getCif contiene el tipo de Empleado que es 
			lista = this.biometricoDao.listarBiometricoTipo(obsDtoObj.getCif());
		}
		else {
			lista = this.biometricoDao.getAllGenero(obsDtoObj.getCif(), obsDtoObj.getSexo());
		}
		
		// creamos una lista solo para cif y eliminamos los repetidos
		List<Long>cif=new ArrayList<Long>(); 
		for(int i=0;i<lista.size();i++) {
			cif.add(lista.get(i).getCif());
		}
		cif=cif.stream().distinct().collect(Collectors.toList());
		
		//Creamos la Observacion
		obsModel obsModel = new obsModel();
		obsModel.setUidObs(obsDtoObj.getUidobs());
		obsModel.setFechaInicio(obsDtoObj.getFechainicio());
		obsModel.setFechaFin(obsDtoObj.getFechafin());
		obsModel.setGestion(obsDtoObj.getGestion());
		obsModel.setMes(obsDtoObj.getMes());
		obsModel.setDi(obsDtoObj.getDi());
		obsModel.setDf(obsDtoObj.getDf());
		obsModel.setDetalle(obsDtoObj.getDetalle());
		obsModel.setImagen(obsDtoObj.getImagen());
		obsModel.setTipo(obsDtoObj.getTipo());
		obsModel.setUrl(obsDtoObj.getUrl());
		obsModel.setTipoId(0);
		this.obsDao.save(obsModel);
		// Agregamos las Observaciones a todos los usuarios que cumplen las condiciones
		
		if(tiposEntrada.contains(obsDtoObj.getTipo())) {
			obsDtoObj.setHoraSalida("");
			obsDtoObj.setHSalida(0);
			obsDtoObj.setMSalida(0);
		}else if(tiposSalida.contains(obsDtoObj.getTipo())) {
			obsDtoObj.setHoraEntrada("");
			obsDtoObj.setHEntrada(0);
			obsDtoObj.setMEntrada(0);
		}
		
		obsBioModel obsBioModel;
		for(int i=0;i<cif.size();i++) {
			obsBioModel = new obsBioModel();
			obsBioModel.setCif(cif.get(i));
			obsBioModel.setIdObs(obsModel.getId());
			obsBioModel.setEstado(1);
			obsBioModel.setHoraEntrada(obsDtoObj.getHoraEntrada());
			obsBioModel.setHEntrada(obsDtoObj.getHEntrada());
			obsBioModel.setMEntrada(obsDtoObj.getMEntrada());
			obsBioModel.setHoraSalida(obsDtoObj.getHoraSalida());
			obsBioModel.setHSalida(obsDtoObj.getHSalida());
			obsBioModel.setMSalida(obsDtoObj.getMSalida());
			this.obsBioDao.save(obsBioModel);
		}
		return (obsDtoObj);
	}
		
	@Transactional 
	public List<obsDtoResponse> getObsPerfil(Long cif, int gestion) {
		List<obsDtoResponse>observaciones = this.obsDao.getObsPerfil(cif, gestion).stream()
				.map(obs -> this.modelMapper.map(obs, obsDtoResponse.class))
				.collect(Collectors.toList());
		return (observaciones);
	}
	@Transactional 
	public List<obsDtoResponse> getObs(Long cif, int gestion,int mes) {
		List<obsDtoResponse>observaciones = this.obsDao.getObs(gestion,mes).stream()
				.map(obs -> this.modelMapper.map(obs, obsDtoResponse.class))
				.collect(Collectors.toList());
		/*List<obsDtoResponse>observaciones = this.obsDao.getObs(cif, gestion,mes).stream()
				.map(obs -> this.modelMapper.map(obs, obsDtoResponse.class))
				.collect(Collectors.toList());*/
		return (observaciones);
	}
	@Transactional 
	public List<obsDtoObj> getObsUsuario(Long cif, int gestion,int mes) {
		List<obsDtoObj> obsDtoObj= new ArrayList<obsDtoObj>();
		
		List<obsDtoResponse>observaciones = this.obsDao.getObs(gestion,mes).stream()
				.map(obs -> this.modelMapper.map(obs, obsDtoResponse.class))
				.collect(Collectors.toList());
		
		for (int i=0;i<observaciones.size();i++) {
			List<obsBioModel>obsBio = this.obsBioDao.getIdObs(observaciones.get(i).getId());
			for(int j=0;j<obsBio.size();j++) {
				if(obsBio.get(j).getCif().longValue()==cif.longValue()) {
					obsDtoObj.add(new obsDtoObj(obsBio.get(j).getId(),obsBio.get(j).getIdObs(),obsBio.get(j).getCif(),0,
					observaciones.get(i).getUidobs(),
					observaciones.get(i).getFechainicio(),
					observaciones.get(i).getFechafin(),
					observaciones.get(i).getGestion(),
					observaciones.get(i).getMes(),
					observaciones.get(i).getDi(),
					observaciones.get(i).getDf(),
					observaciones.get(i).getDetalle(),
					observaciones.get(i).getImagen(),
					observaciones.get(i).getTipo(),
					obsBio.get(j).getHoraEntrada(),
					obsBio.get(j).getHEntrada(),
					obsBio.get(j).getMEntrada(),
					obsBio.get(j).getHoraSalida(),
					obsBio.get(j).getHSalida(),
					obsBio.get(j).getMSalida(),
					observaciones.get(i).getUrl(),
					obsBio.get(j).getEstado()));
				}
			}
		}	
		return (obsDtoObj);
	}
	@Transactional 
	public List<obsDtoObj> getListaObs(int gestion) {
		
		List<obsDtoObj>listaObs = new ArrayList<obsDtoObj>();
		List<obsModel>listaObsModel = this.obsDao.getListaObs(gestion);
		obsDtoObj obsDtoObj;
		for(int i=0;i<listaObsModel.size();i++) {
			List<obsBioModel>obsBioModel = this.obsBioDao.getIdObs(listaObsModel.get(i).getId());
			if(obsBioModel.size()>0) {
				obsDtoObj = new obsDtoObj();
				obsDtoObj.setId(listaObsModel.get(i).getId());
				obsDtoObj.setIdObs(listaObsModel.get(i).getId());
				obsDtoObj.setCif((long) obsBioModel.size());
				obsDtoObj.setSexo(0);
				obsDtoObj.setUidobs(listaObsModel.get(i).getUidObs());
				obsDtoObj.setFechainicio(listaObsModel.get(i).getFechaInicio());
				obsDtoObj.setFechafin(listaObsModel.get(i).getFechaFin());
				obsDtoObj.setGestion(listaObsModel.get(i).getGestion());
				obsDtoObj.setMes(listaObsModel.get(i).getMes());
				obsDtoObj.setDi(listaObsModel.get(i).getDi());
				obsDtoObj.setDf(listaObsModel.get(i).getDf());
				obsDtoObj.setDetalle(listaObsModel.get(i).getDetalle());
				obsDtoObj.setImagen(listaObsModel.get(i).getImagen());
				obsDtoObj.setTipo(listaObsModel.get(i).getTipo());
				obsDtoObj.setHoraEntrada(obsBioModel.get(0).getHoraEntrada()); 
				obsDtoObj.setHEntrada(obsBioModel.get(0).getHEntrada());
				obsDtoObj.setMEntrada(obsBioModel.get(0).getMEntrada()); 
				obsDtoObj.setHoraSalida(obsBioModel.get(0).getHoraSalida()); 
				obsDtoObj.setHSalida(obsBioModel.get(0).getHSalida());
				obsDtoObj.setMSalida(obsBioModel.get(0).getMSalida());
				obsDtoObj.setUrl(listaObsModel.get(i).getUrl());
				obsDtoObj.setEstado(obsBioModel.get(0).getEstado());
				listaObs.add(obsDtoObj);
			}
		}
		
		return (listaObs);
	}
	
	@Transactional 
	public List<obsDtoObj> getListaObsEmpleado(int gestion) {
		List<obsDtoObj> obsDtoObj= new ArrayList<obsDtoObj>();
		
		List<obsDtoResponse>observaciones = this.obsDao.getListaObs(gestion).stream()
				.map(obs -> this.modelMapper.map(obs, obsDtoResponse.class))
				.collect(Collectors.toList());

		for (int i=0;i<observaciones.size();i++) {
			List<obsBioModel>obsBio =this.obsBioDao.getIdObs(observaciones.get(i).getId(),0);
			for(int j=0;j<obsBio.size();j++) {
				obsDtoObj.add(new obsDtoObj(obsBio.get(j).getId(),obsBio.get(j).getIdObs(),obsBio.get(j).getCif(),0,
				observaciones.get(i).getUidobs(),
				observaciones.get(i).getFechainicio(),
				observaciones.get(i).getFechafin(),
				observaciones.get(i).getGestion(),
				observaciones.get(i).getMes(),
				observaciones.get(i).getDi(),
				observaciones.get(i).getDf(),
				observaciones.get(i).getDetalle(),
				observaciones.get(i).getImagen(),
				observaciones.get(i).getTipo(),
				obsBio.get(j).getHoraEntrada(),
				obsBio.get(j).getHEntrada(),
				obsBio.get(j).getMEntrada(),
				obsBio.get(j).getHoraSalida(),
				obsBio.get(j).getHSalida(),
				obsBio.get(j).getMSalida(),
				observaciones.get(i).getUrl(),
				obsBio.get(j).getEstado()));
			}
		}
		return (obsDtoObj);
	}
	
	@Transactional 
	public List<obsDtoObj> getListaObsEliminado() {
		List<obsDtoObj> obsDtoObj= new ArrayList<obsDtoObj>();
		
		List<obsDtoResponse>observaciones = this.obsDao.findAll().stream()
				.map(obs -> this.modelMapper.map(obs, obsDtoResponse.class))
				.collect(Collectors.toList());

		for (int i=0;i<observaciones.size();i++) {
			List<obsBioModel>obsBio =this.obsBioDao.getIdObs(observaciones.get(i).getId(),2);
			for(int j=0;j<obsBio.size();j++) {
				obsDtoObj.add(new obsDtoObj(obsBio.get(j).getId(),obsBio.get(j).getIdObs(),obsBio.get(j).getCif(),0,
				observaciones.get(i).getUidobs(),
				observaciones.get(i).getFechainicio(),
				observaciones.get(i).getFechafin(),
				observaciones.get(i).getGestion(),
				observaciones.get(i).getMes(),
				observaciones.get(i).getDi(),
				observaciones.get(i).getDf(),
				observaciones.get(i).getDetalle(),
				observaciones.get(i).getImagen(),
				observaciones.get(i).getTipo(),
				obsBio.get(j).getHoraEntrada(),
				obsBio.get(j).getHEntrada(),
				obsBio.get(j).getMEntrada(),
				obsBio.get(j).getHoraSalida(),
				obsBio.get(j).getHSalida(),
				obsBio.get(j).getMSalida(),
				observaciones.get(i).getUrl(),
				obsBio.get(j).getEstado()));
			}
		}
		return (obsDtoObj);
	}
	@Transactional 
	public obsDtoResponse updateObsEmpleado(obsDtoObj obsDtoObj) {
		
		//Modificamos la Observacion
		obsModel obsModel = new obsModel();
		obsModel.setId(obsDtoObj.getIdObs());
		obsModel.setUidObs(obsDtoObj.getUidobs());
		obsModel.setFechaInicio(obsDtoObj.getFechainicio());
		obsModel.setFechaFin(obsDtoObj.getFechafin());
		obsModel.setGestion(obsDtoObj.getGestion());
		obsModel.setMes(obsDtoObj.getMes());
		obsModel.setDi(obsDtoObj.getDi());
		obsModel.setDf(obsDtoObj.getDf());
		obsModel.setDetalle(obsDtoObj.getDetalle());
		obsModel.setImagen(obsDtoObj.getImagen());
		obsModel.setTipo(obsDtoObj.getTipo());
		obsModel.setUrl(obsDtoObj.getUrl());
		obsModel.setTipoId(0);
		this.obsDao.save(obsModel);
		
		obsBioModel obsBioModel = new obsBioModel();
		
		obsBioModel.setId(obsDtoObj.getId());
		obsBioModel.setIdObs(obsModel.getId());
		obsBioModel.setCif(obsDtoObj.getCif());
		obsBioModel.setHoraEntrada(obsDtoObj.getHoraEntrada());
		obsBioModel.setHEntrada(obsDtoObj.getHEntrada());
		obsBioModel.setMEntrada(obsDtoObj.getMEntrada());
		obsBioModel.setHoraSalida(obsDtoObj.getHoraSalida());
		obsBioModel.setHSalida(obsDtoObj.getHSalida());
		obsBioModel.setMSalida(obsDtoObj.getMSalida());
		obsBioModel.setEstado(obsDtoObj.getEstado());
		this.obsBioDao.save(obsBioModel);
		
		return (this.modelMapper.map(obsModel, obsDtoResponse.class));
	}
}
