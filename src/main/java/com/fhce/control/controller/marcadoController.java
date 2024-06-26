package com.fhce.control.controller;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import com.fhce.control.dao.marcadoDao;
import com.fhce.control.dao.obsDao;
import com.fhce.control.model.biometricoModel;
import com.fhce.control.model.formatoReporteModel;
import com.fhce.control.model.historialModel;
import com.fhce.control.model.horarioModel;
import com.fhce.control.model.marcadoModel;
import com.fhce.control.model.marcadoTotalModel;
import com.fhce.control.model.obsModel;
import com.fhce.control.model.obserModel;
import com.fhce.control.model.ultimoModel;
import com.fhce.control.obj.marcadoBrutoObj;


@RestController
@RequestMapping("fhce-egovf-scc/marcado") //develop
//@RequestMapping("marcado") //production
//@CrossOrigin("http://svfhce.umsa.bo/")//debelop Fhce
@CrossOrigin("http://172.16.14.91:8080/") //debelop house
public class marcadoController {
	
	@Autowired
	private marcadoDao marcadoDao;
	
	@Autowired
	private horarioDao horarioDao;
	
	@Autowired
	private biometricoDao biometricoDao;
	
	@Autowired
	private obsDao obsDao;
	
	@Autowired
	private historialDao historialDao;
	
	@PostMapping("/afa636b2fb7cc7ef69d9a6b7ab1550e02472114f")
	public void agregarMarcado(@RequestBody marcadoModel marcadoModel) {
		this.marcadoDao.save(marcadoModel);		
	} 
	
	@GetMapping("/listar")
	public List<marcadoModel> listar(){	
		return marcadoDao.findAll();
	}
	
	@GetMapping("/ultimo")
	public ultimoModel ultimo() {
		Long cif=(long) 0;
		List<marcadoModel>lista=this.marcadoDao.findAll();
		
		List<biometricoModel>biometrico=this.biometricoDao.findAll();
		for(int i=0; i<biometrico.size();i++) {
			if((lista.get(lista.size()-1).get_02user_id().longValue() == biometrico.get(i).get_01user_id().longValue()) && (lista.get(lista.size()-1).get_12lugar().equals(biometrico.get(i).get_06lugar()))){
				cif=biometrico.get(i).get_03cif();
				break;
			}
		}
		ultimoModel aux=new ultimoModel(cif,lista.get(lista.size()-1).get_03fecha(),lista.get(lista.size()-1).get_04hora());
		return aux;
	}
	
	@GetMapping("/reporteMes")
	public List<formatoReporteModel> getReporteMes(@RequestParam (value="cif") Long cif, @RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes,@RequestParam (value="di") int di,@RequestParam (value="df") int df)
	{
		Long horario = (long) 0;
		
		List<historialModel>listaHistorial = this.historialDao.getHistorial(cif, gestion);
		if(listaHistorial.size()==1) {
			horario = listaHistorial.get(0).get_02horario_id();
		}
		else {
			int limite = 0;
			Long horarioAux = (long) 0;
			horario = listaHistorial.get(0).get_02horario_id();
			for(int i=1;i<listaHistorial.size();i++) {
				if(listaHistorial.get(i).get_04mes()<mes)
					horario = listaHistorial.get(i).get_02horario_id();
				if(listaHistorial.get(i).get_04mes() == mes) {
					limite = listaHistorial.get(i).get_05dia();
					horarioAux = listaHistorial.get(i).get_02horario_id();
				}
					
			}
			if(limite >0 && di == 0) {
				di = 1;
				df = limite -1;
			}
			else {
				if(di>=limite && di >0 && limite >0)
					horario = horarioAux;
				if(df>=limite && df > 0 && di<limite && limite>0)
					df = limite - 1 ;
			}
			
		}
		System.out.println("############################################"+horario);
		List<formatoReporteModel>listaReporte=getReporte(cif,horario,gestion,mes);
		List<formatoReporteModel>reporteFinal= new ArrayList<formatoReporteModel>();
		int j=1;
		if(di==0) {
			for(int i=0;i<listaReporte.size();i++) {
				if(listaReporte.get(i).isMarcado())
				{
					listaReporte.get(i).setId(j);
					reporteFinal.add(listaReporte.get(i));
					//listaReporte.get(i).mostrar();
					j++;
				}
			}
		}
		else {
			for(int i=0;i<listaReporte.size();i++) {
				if(listaReporte.get(i).isMarcado() && listaReporte.get(i).getDay()>=di && listaReporte.get(i).getDay()<=df)
				{
					listaReporte.get(i).setId(j);
					reporteFinal.add(listaReporte.get(i));
					//listaReporte.get(i).mostrar();
					j++;
				}
			}
		}
		
		for(int i=0;i<reporteFinal.size();i++) {
			reporteFinal.get(i).mostrar();
		}
		
		
		return reporteFinal;
	}
	
	@GetMapping("/reporteMesDia")
	public List<formatoReporteModel> getReporteMesDia(@RequestParam (value="id_horario") Long id_horario,@RequestParam (value="cif") Long cif, @RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes,@RequestParam (value="di") int di,@RequestParam (value="df") int df) {
		
		List<formatoReporteModel>listaReporte=getReporte(cif,id_horario,gestion,mes);
		List<formatoReporteModel>reporteFinal= new ArrayList<formatoReporteModel>();
		int j=1;
		for(int i=0;i<listaReporte.size();i++) {
			if(listaReporte.get(i).isMarcado() && listaReporte.get(i).getDay()>=di && listaReporte.get(i).getDay()<=df)
			{
				listaReporte.get(i).setId(j);
				reporteFinal.add(listaReporte.get(i));
				listaReporte.get(i).mostrar();
				j++;
			}
		}
		return reporteFinal;
	}
	
	public List<formatoReporteModel> getReporte(Long cif,Long id_horario,int gestion,int mes){
		
		final Locale español = new Locale("es","MX");
		String valuedia = "";
		String valuemes = "";
		String fecha = "";
		String dia = "";
		
		List<biometricoModel> datos = this.biometricoDao.getPerfil(cif);
		
		horarioModel horarioModel = this.horarioDao.getById(id_horario);
		
		List<marcadoModel>listaMarcado = new ArrayList<marcadoModel>();
		
		List<formatoReporteModel>listaReporte = new ArrayList<formatoReporteModel>();
		
		List<obsModel>obsModel = this.obsDao.getObs(cif, gestion, mes);
		List<obserModel>listaobserModel = new ArrayList<obserModel>();
		obserModel obserAux;
		formatoReporteModel reporte;
		
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		//se crea la lista de marcado
		
		for (int i=0;i<datos.size();i++)
			listaMarcado.addAll(this.marcadoDao.getMarcado(datos.get(i).get_01user_id(),datos.get(i).get_06lugar(), gestion, mes));
		
		//se crean las observaciones
		for(int i=0;i<obsModel.size();i++) {
			for(int j=obsModel.get(i).get_07di();j<=obsModel.get(i).get_08df();j++) {
				if(obsModel.get(i).get_06mes()>9)
					valuemes = Integer.toString(mes);
				else
					valuemes = "0"+Integer.toString(mes);
				if(j>9)
					valuedia = ""+j;
				else
					valuedia = "0"+j;
				fecha = obsModel.get(i).get_05gestion()+"-"+valuemes+"-"+valuedia;
				listaobserModel.add(new obserModel(obsModel.get(i).getId(),obsModel.get(i).get_02uidobs(),fecha,obsModel.get(i).get_09detalle(),obsModel.get(i).get_11tipo(),obsModel.get(i).get_12hora(),obsModel.get(i).get_13h(),obsModel.get(i).get_14m()));
			}
		}
				
		// Ordenamos la Lista Despues de Crearlo
		int menor = 0;
		int mayor = 0;
		marcadoModel aux;
		for(int i=0;i<listaMarcado.size()-1;i++) {
			marcadoModel marcadoMenor = listaMarcado.get(i);
			menor = marcadoMenor.get_07dia();
			for(int j=i+1;j<listaMarcado.size();j++) {
				marcadoModel marcadoMayor = listaMarcado.get(j);
				mayor = marcadoMayor.get_07dia();
				if(mayor < menor) {
					aux = listaMarcado.get(i);
					listaMarcado.set(i, listaMarcado.get(j));
					listaMarcado.set(j, aux);
				}
			}
		}
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		
		// Ordenamos la Lista Despues de Crearlo la Lista de Observaciones
		menor = 0;
		mayor = 0;
		obserModel auxObserModel;
		for(int i=0;i<listaobserModel.size()-1;i++) {
			obserModel obserModelMenor=listaobserModel.get(i);
			menor = Integer.parseInt(obserModelMenor.getFecha().substring(8, 10));
			for(int j=i+1;j<listaobserModel.size();j++) {		
				obserModel obserModelMayor = listaobserModel.get(j);
				mayor = Integer.parseInt(obserModelMayor.getFecha().substring(8, 10));
				if(mayor < menor) {
					auxObserModel = listaobserModel.get(i);
					listaobserModel.set(i, listaobserModel.get(j));
					listaobserModel.set(j, auxObserModel);
				}
			}
		}	
		
		
		//#####################################################################
		//se crea el calendario con los dias corespondientes para el formato del reporte
		if(mes>9)
			valuemes = Integer.toString(mes);
		else
			valuemes = "0"+Integer.toString(mes);
		
		for ( LocalDate day = LocalDate.parse(gestion+"-"+valuemes+"-01"); day.getMonthValue() < mes+1 ; day = day.plusDays(1)) {
            if(day.getYear() == gestion) {
				if(day.getDayOfMonth()>9)
	            	dia = Integer.toString(day.getDayOfMonth());
	            else
	            	dia = "0"+Integer.toString(day.getDayOfMonth());
	            
	            fecha = day.getYear()+"-"+valuemes+"-"+dia;
	            List<obserModel>listaux=new ArrayList<obserModel>();
	            for(int i=0;i<listaobserModel.size();i++) {
	            	if(listaobserModel.get(i).getFecha().equals(fecha))
	            	{
	            		obserAux = new obserModel("");
	            		obserAux.setId(listaobserModel.get(i).getId());
	            		obserAux.setFecha(listaobserModel.get(i).getFecha());
	            		obserAux.setTipo(listaobserModel.get(i).getTipo());
	            		obserAux.setUidobs(listaobserModel.get(i).getUidobs());
	            		obserAux.setDetalle(listaobserModel.get(i).getDetalle());
	            		obserAux.setHora(listaobserModel.get(i).getHora());
	            		obserAux.setH(listaobserModel.get(i).getH());
	            		obserAux.setM(listaobserModel.get(i).getM());
	            		listaux.add(obserAux);
	            	}
	            }
	            
	            reporte = new formatoReporteModel(0,day.getDayOfWeek().getDisplayName(TextStyle.FULL, español),day.getDayOfMonth(),day.getMonthValue(),fecha,true,listaux);
	            listaReporte.add(reporte);
            }
            else
            	break;
        }
				

        //#####################################################################
		
		//colocamos el marcado en el reporte
        for(int i=0;i<listaReporte.size();i++) {
        	for(int j=0;j<listaMarcado.size();j++) {
        		if(listaReporte.get(i).getFecha().equals(listaMarcado.get(j).get_03fecha())) {
        			listaReporte.get(i).addMarcado(listaMarcado.get(j));
        		}
        	}
        	switch(listaReporte.get(i).getDia()) {
        		case "lunes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_02lem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_03lsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_04let());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_05lst());
        			break;
        		case "martes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_06mem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_07msm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_08met());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_09mst());
        			break;
        		case "miércoles":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_10miem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_11mism());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_12miet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_13mist());
        			break;
        		case "jueves":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_14jem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_15jsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_16jet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_17jst());
        			break;
        		case "viernes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_18vem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_19vsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_20vet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_21vst());
        			break;
        		case "sábado":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_22sem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_23ssm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_24set());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_25sst());
        			break;
        		case "domingo":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_26dem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_27dsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_28det());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_29dst());
        			break;
        	}
        	listaReporte.get(i).reporte();
        }
        
        return listaReporte; 
	}
	@GetMapping("/reporteTotal")
	public List<marcadoTotalModel> getReporteTotal(@RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes, @RequestParam (value="tipo") int tipo) {
		
		List<marcadoTotalModel>listaTotal = new ArrayList<marcadoTotalModel>();
		List<biometricoModel>listaBiometrico = this.biometricoDao.getAll((long) tipo);
		int retraso=0;
		int antisipado=0;
		int penalisacion=0;
		int r[]=new int[4];
		String lugar[] = new String[4];
		String hora[] = new String[4];
		int mayor=0;
		int menor=0;
		boolean ingreso=true;
		boolean pena= true;
		marcadoTotalModel aux;
		
		for(int i=0;i<listaBiometrico.size();i++) {
			ingreso=true;
			// se verifica que el usuario no ingrese al ranking 2 veces 
			for(int k=0;k<listaTotal.size();k++) {
				if(listaTotal.get(k).getCif().longValue() == listaBiometrico.get(i).get_03cif().longValue()) {
					ingreso=false;
					break;
				}
			}
			if(ingreso) {
						
				List<horarioModel>idhorarioLista = this.horarioDao.getListaId(listaBiometrico.get(i).get_03cif()); // Traemos la lista de Horarios del Empleado registrado en el biometrico
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+listaBiometrico.get(i).get_02nombre());
				List<formatoReporteModel>listaReporte = getReporte(listaBiometrico.get(i).get_03cif(),idhorarioLista.get(idhorarioLista.size()-1).getId(),gestion,mes);
				
				
				List<formatoReporteModel>reporteFinal = new ArrayList<formatoReporteModel>();
				int j=1;
				for(int k=0;k<listaReporte.size();k++) {
					if(listaReporte.get(k).isMarcado())
					{
						listaReporte.get(k).setId(j);
						reporteFinal.add(listaReporte.get(k));
						listaReporte.get(k).mostrar();
						j++;
					}
				}
				
				retraso=0;
				antisipado=0;
				penalisacion=0;
				for (int f=0;f<reporteFinal.size();f++) {
					r=reporteFinal.get(f).getRetraso();
					retraso=retraso+r[0]+r[2];
					antisipado=antisipado+r[1]+r[3];
						
					pena=false;
					lugar = reporteFinal.get(f).getLugar();
					hora= reporteFinal.get(f).getHora();
					if(reporteFinal.get(f).getObserModel().size()==0) {
						for(int k=0;k<4;k++) {
							if(lugar[k].equals("Sin Lugar") && hora[k].equals("Sin Marcar")) {
								pena=true;
							}
						}
					}
					if(pena) {
						penalisacion++;
					}
						
				}
				penalisacion=penalisacion+retraso+antisipado;
				listaTotal.add(new marcadoTotalModel(listaBiometrico.get(i).get_03cif(),reporteFinal,retraso,antisipado,penalisacion));
			}
			
		}
		
		for(int i=0;i<listaTotal.size()-1;i++)
		{
			menor=listaTotal.get(i).getPena();
			for(int j=i+1;j<listaTotal.size();j++) {
				mayor=listaTotal.get(j).getPena();
				if(menor>mayor) {
					aux=listaTotal.get(i);
					listaTotal.set(i, listaTotal.get(j));
					listaTotal.set(j, aux);
					menor=listaTotal.get(i).getPena();
				}
			}
		}
		return listaTotal;
	}
	@GetMapping("/reporteBruto")
	public void getReporteBruto(@RequestParam (value="gestion") int gestion,@RequestParam (value="mes") int mes, @RequestParam (value="tipo") int tipo) {
		List<biometricoModel>listabiometrico = this.biometricoDao.getAll((long) tipo);
		List<marcadoModel>listaMarcado;
		List<marcadoBrutoObj>marcado;
		List<String>cif = new ArrayList<String>();
		List<obsModel>obsModel;
		
		
		
		boolean existeCif = true;
		for(int i=0;i<listabiometrico.size();i++){
			System.out.println("###############################&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + listabiometrico.get(i).get_03cif());
			String lcif = listabiometrico.get(i).get_03cif().toString();
			existeCif = true;
			listaMarcado = new ArrayList<marcadoModel>();
			for(int c=0;c<cif.size();c++) {
				if(cif.get(c).equals(lcif)) {
					existeCif = false;
				}
			}
			if(existeCif) {
				marcado = new ArrayList<marcadoBrutoObj>();
				marcado.add(new marcadoBrutoObj(listabiometrico.get(i).get_03cif(),listabiometrico.get(i).get_01user_id(),listabiometrico.get(i).get_05horario_id(),listabiometrico.get(i).get_06lugar()));
				for(int j=i;j<listabiometrico.size();j++){
					if(listabiometrico.get(i).get_03cif().longValue() == listabiometrico.get(j).get_03cif().longValue()) {
						marcado.add(new marcadoBrutoObj(listabiometrico.get(i).get_03cif(),listabiometrico.get(i).get_01user_id(),listabiometrico.get(i).get_05horario_id(),listabiometrico.get(i).get_06lugar()));
					}
				}
				//Creamos lista de Marcados
				for(int j = 0 ; j < marcado.size(); j++)
					listaMarcado.addAll(this.marcadoDao.getMarcado(marcado.get(j).getUserId(),marcado.get(j).getLugar(), gestion, mes));
				
				// Ordenamos la Lista Despues de Crearlo
				listaDiaOrdenada(listaMarcado);
				
				//Extraemos las Obserbacione 
				obsModel = this.obsDao.getObs(marcado.get(0).getCif(), gestion, mes);
				List<obserModel>listaobserModel = new ArrayList<obserModel>();
				
				//se crean las observaciones
				listaObs(obsModel,listaobserModel,mes);
						
				// Ordenamos la Lista de observaciones Despues de Crearlo
				listaObsOrdenada(listaobserModel);
				
				//Creamos el formato de calendario
				List<formatoReporteModel>listaReporte = formatCalendar(new ArrayList<obserModel>(),gestion,mes);
				
				// Creamos el Formato del reporte
				horarioModel horarioModel = this.horarioDao.getById((long) marcado.get(0).getHorarioId());
				marcadoReporte(listaReporte,listaMarcado,horarioModel);
				for (int k = 0;k < listaReporte.size();k++ ) {
					listaReporte.get(k).mostrar();
				}
				
			}
		}
		
	}
	public void listaDiaOrdenada(List<marcadoModel>listaMarcado) {
		// Ordenamos la Lista Despues de Crearlo
		int menor = 0;
		int mayor = 0;
		marcadoModel aux;
		for(int i=0;i<listaMarcado.size()-1;i++) {
			marcadoModel marcadoMenor = listaMarcado.get(i);
			menor = marcadoMenor.get_07dia();
			for(int j=i+1;j<listaMarcado.size();j++) {
				marcadoModel marcadoMayor = listaMarcado.get(j);
				mayor = marcadoMayor.get_07dia();
				if(mayor < menor) {
					aux = listaMarcado.get(i);
					listaMarcado.set(i, listaMarcado.get(j));
					listaMarcado.set(j, aux);
				}
			}
		}
	}
	public void listaObsOrdenada(List<obserModel>listaobserModel) {
		int menor = 0;
		int mayor = 0;
		obserModel auxObserModel;
		for(int i=0;i<listaobserModel.size()-1;i++) {
			obserModel obserModelMenor=listaobserModel.get(i);
			menor = Integer.parseInt(obserModelMenor.getFecha().substring(8, 10));
			for(int j=i+1;j<listaobserModel.size();j++) {		
				obserModel obserModelMayor = listaobserModel.get(j);
				mayor = Integer.parseInt(obserModelMayor.getFecha().substring(8, 10));
				if(mayor < menor) {
					auxObserModel = listaobserModel.get(i);
					listaobserModel.set(i, listaobserModel.get(j));
					listaobserModel.set(j, auxObserModel);
				}
			}
		}
	}
	public void listaObs(List<obsModel>obsModel,List<obserModel>listaobserModel, int mes) {
		String valuemes = "";
		String valuedia = "";
		String fecha = "";
		for(int i=0;i<obsModel.size();i++) {
			for(int j=obsModel.get(i).get_07di();j<=obsModel.get(i).get_08df();j++) {
				if(obsModel.get(i).get_06mes()>9)
					valuemes = Integer.toString(mes);
				else
					valuemes = "0"+Integer.toString(mes);
				if(j>9)
					valuedia = ""+j;
				else
					valuedia = "0"+j;
				fecha = obsModel.get(i).get_05gestion()+"-"+valuemes+"-"+valuedia;
				listaobserModel.add(new obserModel(obsModel.get(i).getId(),obsModel.get(i).get_02uidobs(),fecha,obsModel.get(i).get_09detalle(),obsModel.get(i).get_11tipo(),obsModel.get(i).get_12hora(),obsModel.get(i).get_13h(),obsModel.get(i).get_14m()));
			}
		}
	}
	public List<formatoReporteModel> formatCalendar(List<obserModel>listaobserModel,int gestion,int mes) {
		List<formatoReporteModel>listaReporte = new ArrayList<formatoReporteModel>();
		
		final Locale español = new Locale("es","MX");
		
		String valuemes = "";
		String valuedia = "";
		String dia = "";
		String fecha = "";
		obserModel obserAux;
		formatoReporteModel reporte;
		
		if(mes>9)
			valuemes = Integer.toString(mes);
		else
			valuemes = "0"+Integer.toString(mes);
		
		for ( LocalDate day = LocalDate.parse(gestion+"-"+valuemes+"-01"); day.getMonthValue() < mes+1 ; day = day.plusDays(1)) {
            if(day.getYear() == gestion) {
				if(day.getDayOfMonth()>9)
	            	dia = Integer.toString(day.getDayOfMonth());
	            else
	            	dia = "0"+Integer.toString(day.getDayOfMonth());
	            
	            fecha = day.getYear()+"-"+valuemes+"-"+dia;
	            List<obserModel>listaux = new ArrayList<obserModel>();
	            for(int i = 0;i<listaobserModel.size();i++) {
	            	if(listaobserModel.get(i).getFecha().equals(fecha))
	            	{
	            		obserAux = new obserModel("");
	            		obserAux.setId(listaobserModel.get(i).getId());
	            		obserAux.setFecha(listaobserModel.get(i).getFecha());
	            		obserAux.setTipo(listaobserModel.get(i).getTipo());
	            		obserAux.setUidobs(listaobserModel.get(i).getUidobs());
	            		obserAux.setDetalle(listaobserModel.get(i).getDetalle());
	            		obserAux.setHora(listaobserModel.get(i).getHora());
	            		obserAux.setH(listaobserModel.get(i).getH());
	            		obserAux.setM(listaobserModel.get(i).getM());
	            		listaux.add(obserAux);
	            	}
	            }
	            
	            reporte = new formatoReporteModel(0,day.getDayOfWeek().getDisplayName(TextStyle.FULL, español),day.getDayOfMonth(),day.getMonthValue(),fecha,true,listaux);
	            listaReporte.add(reporte);
            }
            else
            	break;
        }
		return listaReporte;
	}
	public void marcadoReporte(List<formatoReporteModel> listaReporte,List<marcadoModel>listaMarcado,horarioModel horarioModel) {
		for(int i=0;i<listaReporte.size();i++) {
        	for(int j=0;j<listaMarcado.size();j++) {
        		if(listaReporte.get(i).getFecha().equals(listaMarcado.get(j).get_03fecha())) {
        			listaReporte.get(i).addMarcado(listaMarcado.get(j));
        		}
        	}
        	switch(listaReporte.get(i).getDia()) {
        		case "lunes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_02lem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_03lsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_04let());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_05lst());
        			break;
        		case "martes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_06mem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_07msm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_08met());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_09mst());
        			break;
        		case "miércoles":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_10miem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_11mism());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_12miet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_13mist());
        			break;
        		case "jueves":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_14jem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_15jsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_16jet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_17jst());
        			break;
        		case "viernes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_18vem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_19vsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_20vet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_21vst());
        			break;
        		case "sábado":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_22sem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_23ssm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_24set());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_25sst());
        			break;
        		case "domingo":
        			listaReporte.get(i).setTurnoB(0, horarioModel.get_26dem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.get_27dsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.get_28det());
        			listaReporte.get(i).setTurnoB(3, horarioModel.get_29dst());
        			break;
        	}
        	listaReporte.get(i).reporte();
        }
	}
	public Long getHorarioId(Long cif, int gestion, int mes,int di, int df) {
		Long horario = (long) 0;
		
		List<historialModel>listaHistorial = this.historialDao.getHistorial(cif, gestion);
		if(listaHistorial.size()==1) {
			horario = listaHistorial.get(0).get_02horario_id();
		}
		else {
			int limite = 0;
			Long horarioAux = (long) 0;
			horario = listaHistorial.get(0).get_02horario_id();
			for(int i=1;i<listaHistorial.size();i++) {
				if(listaHistorial.get(i).get_04mes()<mes)
					horario = listaHistorial.get(i).get_02horario_id();
				if(listaHistorial.get(i).get_04mes() == mes) {
					limite = listaHistorial.get(i).get_05dia();
					horarioAux = listaHistorial.get(i).get_02horario_id();
				}
					
			}
			if(limite >0 && di == 0) {
				di = 1;
				df = limite -1;
			}
			else {
				if(di>=limite && di >0 && limite >0)
					horario = horarioAux;
				if(df>=limite && df > 0 && di<limite && limite>0)
					df = limite - 1 ;
			}
			
		}
		return horario;
	}
}
