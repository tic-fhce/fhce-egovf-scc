package com.fhce.control.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.control.dao.biometricoDao;
import com.fhce.control.dao.historialDao;
import com.fhce.control.dao.horarioDao;
import com.fhce.control.model.biometricoModel;
import com.fhce.control.model.historialModel;
import com.fhce.control.model.horarioModel;
import com.fhce.control.obj.horarioObj;

@RestController
@RequestMapping("fhce-egovf-scc/horario") //develop
//@RequestMapping("horario") //production
//@CrossOrigin("http://svfhce.umsa.bo/")//debelop Fhce
@CrossOrigin("http://192.168.31.47:8080/") //debelop house
public class horarioController {
	
	@Autowired
	private horarioDao horarioDao;
	
	@Autowired
	private biometricoDao biometricoDao;
	
	@Autowired
	private historialDao historialDao;
	
	@GetMapping("/listar")
	public List<horarioModel> listar(){
		
		return horarioDao.findAll();
	}
	@PostMapping("/addHorario")
	public void addHorario(@RequestBody horarioModel horarioModel) {
		LocalDate date = LocalDate.now();
		
		horarioModel aux = this.horarioDao.save(horarioModel);
		
		historialModel historialModel = new historialModel();
		historialModel.set_01cif(horarioModel.get_01cif());
		historialModel.set_02horario_id(aux.getId());
		historialModel.set_03gestion(date.getYear());
		historialModel.set_04mes(date.getMonthValue());
		historialModel.set_05dia(date.getDayOfMonth());
		
		this.historialDao.save(historialModel);
		
		List<biometricoModel>lista=this.biometricoDao.getPerfil(horarioModel.get_01cif());
		for(int i=0;i<lista.size();i++) {
			lista.get(i).set_05horario_id( Math.toIntExact(aux.getId()));
			this.biometricoDao.save(lista.get(i));
		}
		
	}
	@GetMapping("/getHorario")
	public horarioModel getHorario(@RequestParam (value="id_horario") Long id_horario,@RequestParam (value="cif") Long cif){
		
		return horarioDao.getHorario(id_horario, cif);
	}
	
	@GetMapping("/getListaHorario")
	public List<horarioObj>getListaHorario(@RequestParam (value="cif") Long cif,@RequestParam (value="gestion") int gestion){
		List<horarioObj>listaHorario = new ArrayList<horarioObj>();
		List<horarioModel>listaHorarioModel = this.horarioDao.getListaId(cif);
		List<historialModel>listaHistorialModel = this.historialDao.getHistorial(cif, gestion);
		
		horarioObj horarioObj;
		for(int i=0;i<listaHistorialModel.size();i++) {
			for(int j=0;j<listaHorarioModel.size();j++) {
				if(listaHistorialModel.get(i).get_02horario_id().longValue() == listaHorarioModel.get(j).getId().longValue()) {
					horarioObj = new horarioObj();
					horarioObj.setHistorial_id(listaHistorialModel.get(i).getId());
					horarioObj.setCif(cif);
					horarioObj.setGestion(listaHistorialModel.get(i).get_03gestion());
					horarioObj.setMes(listaHistorialModel.get(i).get_04mes());
					horarioObj.setDia(listaHistorialModel.get(i).get_05dia());
					horarioObj.setFecha(horarioObj.getGestion()+"-"+horarioObj.getMes()+"-"+horarioObj.getDia());
					horarioObj.setValido("La Actualidad");
					
					horarioObj.setHorario_id(listaHistorialModel.get(i).get_02horario_id());
					horarioObj.setLem(listaHorarioModel.get(j).get_02lem());
					horarioObj.setLsm(listaHorarioModel.get(j).get_03lsm());
					horarioObj.setLet(listaHorarioModel.get(j).get_04let());
					horarioObj.setLst(listaHorarioModel.get(j).get_05lst());
					horarioObj.setMem(listaHorarioModel.get(j).get_06mem());
					horarioObj.setMsm(listaHorarioModel.get(j).get_07msm());
					horarioObj.setMet(listaHorarioModel.get(j).get_08met());
					horarioObj.setMst(listaHorarioModel.get(j).get_09mst());
					horarioObj.setMiem(listaHorarioModel.get(j).get_10miem());
					horarioObj.setMism(listaHorarioModel.get(j).get_11mism());
					horarioObj.setMiet(listaHorarioModel.get(j).get_12miet());
					horarioObj.setMist(listaHorarioModel.get(j).get_13mist());
					horarioObj.setJem(listaHorarioModel.get(j).get_14jem());
					horarioObj.setJsm(listaHorarioModel.get(j).get_15jsm());
					horarioObj.setJet(listaHorarioModel.get(j).get_16jet());
					horarioObj.setJst(listaHorarioModel.get(j).get_17jst());
					horarioObj.setVem(listaHorarioModel.get(j).get_18vem());
					horarioObj.setVsm(listaHorarioModel.get(j).get_19vsm());
					horarioObj.setVet(listaHorarioModel.get(j).get_20vet());
					horarioObj.setVst(listaHorarioModel.get(j).get_21vst());
					horarioObj.setSem(listaHorarioModel.get(j).get_22sem());
					horarioObj.setSsm(listaHorarioModel.get(j).get_23ssm());
					horarioObj.setSet(listaHorarioModel.get(j).get_24set());
					horarioObj.setSst(listaHorarioModel.get(j).get_25sst());
					horarioObj.setDem(listaHorarioModel.get(j).get_26dem());
					horarioObj.setDsm(listaHorarioModel.get(j).get_27dsm());
					horarioObj.setDet(listaHorarioModel.get(j).get_28det());
					horarioObj.setDst(listaHorarioModel.get(j).get_29dst());
					
					listaHorario.add(horarioObj);
				}
			}
		}
		for(int i=1;i<listaHorario.size();i++)
		{
			listaHorario.get(i-1).setValido(listaHorario.get(i).getFecha());
		}
		
		return(listaHorario);
		
	}
	
}
