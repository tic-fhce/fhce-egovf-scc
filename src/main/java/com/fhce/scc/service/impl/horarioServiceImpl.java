package com.fhce.scc.service.impl;

import java.time.LocalDate;
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
	public horarioDtoResponse addHorario(horarioDtoRequest req) {
		LocalDate fecha  = LocalDate.parse(req.getFecha());
		LocalDate salida = LocalDate.parse(req.getSalida());

		horarioModel hm = new horarioModel();
		hm.setCif( req.getCif() );
		hm.setLem( req.getLem() );
		hm.setLsm( req.getLsm() );
		hm.setLet( req.getLet() );
		hm.setLst( req.getLst() );
		hm.setMem( req.getMem() );
		hm.setMsm( req.getMsm() );
		hm.setMet( req.getMet() );
		hm.setMst( req.getMst() );
		hm.setMiem(req.getMiem());
		hm.setMism(req.getMism());
		hm.setMiet(req.getMiet());
		hm.setMist(req.getMist());
		hm.setJem( req.getJem() );
		hm.setJsm( req.getJsm() );
		hm.setJet( req.getJet() );
		hm.setJst( req.getJst() );
		hm.setVem( req.getVem() );
		hm.setVsm( req.getVsm() );
		hm.setVet( req.getVet() );
		hm.setVst( req.getVst() );
		hm.setSem( req.getSem() );
		hm.setSsm( req.getSsm() );
		hm.setSet( req.getSet() );
		hm.setSst( req.getSst() );
		hm.setDem( req.getDem() );
		hm.setDsm( req.getDsm() );
		hm.setDet( req.getDet() );
		hm.setDst( req.getDst() );

		horarioModel saved = horarioDao.save(hm);

		historialModel hist = new historialModel();
		hist.setCif( saved.getCif() );
		hist.setHorario_id( saved.getId() );
		hist.setGestion(    fecha.getYear() );
		hist.setMes(        fecha.getMonthValue() );
		hist.setDia(        fecha.getDayOfMonth() );
		hist.setGestionsalida( salida.getYear() );
		hist.setMessalida(     salida.getMonthValue() );
		hist.setDiasalida(     salida.getDayOfMonth() );
		historialDao.save(hist);

		List<biometricoModel> lista = biometricoDao.getPerfil(req.getCif());
		for (biometricoModel bm : lista) {
			bm.setHorario_id( Math.toIntExact(saved.getId()) );
			bm.setEstado(1);
			bm.setId_tipo(req.getTipo());
			biometricoDao.save(bm);
		}

		horarioDtoResponse resp = new horarioDtoResponse();
		resp.setId(   saved.getId() );
		resp.setCif(  saved.getCif() );
		resp.setLem(  saved.getLem() );
		resp.setLsm(  saved.getLsm() );
		resp.setLet(  saved.getLet() );
		resp.setLst(  saved.getLst() );
		resp.setMem(  saved.getMem() );
		resp.setMsm(  saved.getMsm() );
		resp.setMet(  saved.getMet() );
		resp.setMst(  saved.getMst() );
		resp.setMiem( saved.getMiem() );
		resp.setMism( saved.getMism() );
		resp.setMiet( saved.getMiet() );
		resp.setMist( saved.getMist() );
		resp.setJem(  saved.getJem() );
		resp.setJsm(  saved.getJsm() );
		resp.setJet(  saved.getJet() );
		resp.setJst(  saved.getJst() );
		resp.setVem(  saved.getVem() );
		resp.setVsm(  saved.getVsm() );
		resp.setVet(  saved.getVet() );
		resp.setVst(  saved.getVst() );
		resp.setSem(  saved.getSem() );
		resp.setSsm(  saved.getSsm() );
		resp.setSet(  saved.getSet() );
		resp.setSst(  saved.getSst() );
		resp.setDem(  saved.getDem() );
		resp.setDsm(  saved.getDsm() );
		resp.setDet(  saved.getDet() );
		resp.setDst(  saved.getDst() );

		return resp;
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
