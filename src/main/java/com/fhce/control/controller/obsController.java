package com.fhce.control.controller;

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
import com.fhce.control.dao.obsDao;
import com.fhce.control.model.biometricoModel;
import com.fhce.control.model.obsModel;

@RestController
@RequestMapping("fhce-egovf-scc/obs") //develop 
//@RequestMapping("obs") //production
//@CrossOrigin("http://svfhce.umsa.bo/")//debelop Fhce
@CrossOrigin("http://192.168.31.45:8080/") //debelop house
public class obsController {
	
	@Autowired 
	private obsDao obsDao;
	
	@Autowired
	private biometricoDao biometricoDao;
	
	@PostMapping("/agregarObs")
	public void agregarObs(@RequestBody obsModel obsModel) {
		this.obsDao.save(obsModel);		
	}
	
	@PostMapping("/agregarObsAll")
	public void agregarObsAll(@RequestBody obsModel obsModel) {
		
		List<biometricoModel>lista = biometricoDao.getAll(obsModel.get_01cif());
		obsModel auxObs;
		for(int i=0;i<lista.size();i++) {
			auxObs=new obsModel();
			auxObs.set_01cif(lista.get(i).get_03cif());
			auxObs.set_02uidobs(obsModel.get_02uidobs());
			auxObs.set_03fechainicio(obsModel.get_03fechainicio());
			auxObs.set_04fechafin(obsModel.get_04fechafin());
			auxObs.set_05gestion(obsModel.get_05gestion());
			auxObs.set_06mes(obsModel.get_06mes());
			auxObs.set_07di(obsModel.get_07di());
			auxObs.set_08df(obsModel.get_08df());
			auxObs.set_09detalle(obsModel.get_09detalle());
			auxObs.set_10imagen(obsModel.get_10imagen());
			auxObs.set_11tipo(obsModel.get_11tipo());
			auxObs.set_12hora(obsModel.get_12hora());
			auxObs.set_13h(obsModel.get_13h());
			auxObs.set_14m(obsModel.get_14m());
			this.obsDao.save(auxObs);
		}		
	}
	
	@GetMapping("/getObsPerfil")
	public List<obsModel> getObsPerfil(@RequestParam (value="cif") Long cif,@RequestParam (value="gestion") int gestion){
		return obsDao.getObsPerfil(cif,gestion);
	}

}
