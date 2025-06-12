package com.fhce.scc.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.scc.dao.biometricoDao;
import com.fhce.scc.dao.historialDao;
import com.fhce.scc.dao.horarioDao;
import com.fhce.scc.dao.marcadoDao;
import com.fhce.scc.dao.obsBioDao;
import com.fhce.scc.dao.obsDao;
import com.fhce.scc.model.biometricoModel;
import com.fhce.scc.model.historialModel;
import com.fhce.scc.model.horarioModel;
import com.fhce.scc.model.marcadoModel;
import com.fhce.scc.model.obsBioModel;
import com.fhce.scc.model.obsModel;
import com.fhce.scc.obj.formatoReporteDtoResponse;
import com.fhce.scc.obj.marcadoDtoRequest;
import com.fhce.scc.obj.marcadoDtoResponse;
import com.fhce.scc.obj.marcadoDtoTotalResponse;
import com.fhce.scc.obj.obsDtoReporte;
import com.fhce.scc.obj.ultimoDtoResponse;
import com.fhce.scc.service.marcadoService;

import jakarta.transaction.Transactional;

@Service
public class marcadoServiceImpl implements marcadoService{
	private final marcadoDao marcadoDao;
	private final ModelMapper modelMapper;
	private final historialDao historialDao;
	private final biometricoDao biometricoDao;
	private final horarioDao horarioDao;
	private final obsDao obsDao;
	private final obsBioDao obsBioDao;
	public marcadoServiceImpl(marcadoDao marcadoDao,historialDao historialDao,biometricoDao biometricoDao,horarioDao horarioDao,obsDao obsDao, ModelMapper modelMapper,obsBioDao obdBioDao) {
		this.marcadoDao = marcadoDao;
		this.modelMapper = modelMapper;
		this.historialDao = historialDao;
		this.biometricoDao = biometricoDao;
		this.horarioDao = horarioDao;
		this.obsDao = obsDao;
		this.obsBioDao = obdBioDao;
	}
	
	@Transactional
	public marcadoDtoResponse addMarcado(marcadoDtoRequest req) {
		marcadoModel entity = new marcadoModel();
		entity.setUid(req.getUid());
		entity.setUser_id(req.getUser_id());
		entity.setFecha(req.getFecha());
		entity.setHora(req.getHora());
		entity.setGestion(req.getGestion());
		entity.setMes(req.getMes());
		entity.setDia(req.getDia());
		entity.setH(req.getH());
		entity.setM(req.getM());
		entity.setPunch(req.getPunch());
		entity.setRstatus(req.getRstatus());
		entity.setLugar(req.getLugar());

		marcadoModel saved = marcadoDao.save(entity);

		marcadoDtoResponse resp = new marcadoDtoResponse();
		resp.setId(saved.getId());
		resp.setUid(saved.getUid());
		resp.setUser_id(saved.getUser_id());
		resp.setFecha(saved.getFecha());
		resp.setHora(saved.getHora());
		resp.setGestion(saved.getGestion());
		resp.setMes(saved.getMes());
		resp.setDia(saved.getDia());
		resp.setH(saved.getH());
		resp.setM(saved.getM());
		resp.setPunch(saved.getPunch());
		resp.setRstatus(saved.getRstatus());
		resp.setLugar(saved.getLugar());

		return resp;
	}

	@Transactional
	public List<marcadoDtoResponse> listar() {
		List<marcadoDtoResponse>marcados = this.marcadoDao.findAll().stream()
				.map(marcado -> this.modelMapper.map(marcado, marcadoDtoResponse.class))
				.collect(Collectors.toList());
		return (marcados);
	}
	
	@Transactional
	public List<marcadoDtoResponse> listarUserId(Long userId) {
		List<marcadoDtoResponse>marcados = this.marcadoDao.listarUserId(userId).stream()
				.map(marcado -> this.modelMapper.map(marcado, marcadoDtoResponse.class))
				.collect(Collectors.toList());
		return (marcados);
	}
	@Transactional
	public ultimoDtoResponse ultimo() {
		
		Long cif=(long) 0;
		List<marcadoModel>marcados=this.marcadoDao.findAll();
		
		List<biometricoModel>biometrico=this.biometricoDao.findAll();
		
		for(int i=0; i<biometrico.size();i++) {
			if((marcados.get(marcados.size()-1).getUser_id().longValue() == biometrico.get(i).getUser_id().longValue()) && (marcados.get(marcados.size()-1).getLugar().equals(biometrico.get(i).getLugar()))){
				cif=biometrico.get(i).getCif();
				break;
			}
		}
		ultimoDtoResponse aux=new ultimoDtoResponse(cif,marcados.get(marcados.size()-1).getId(),marcados.get(marcados.size()-1).getFecha(),marcados.get(marcados.size()-1).getHora());
		return aux;
	}
	
	@Transactional
	public List<formatoReporteDtoResponse> getReporteMes(Long cif,int gestion,int mes,int di,int df)
	{
		
		Long horario = (long) 0;
		
		List<historialModel>listaHistorial = this.historialDao.getHistorial(cif, gestion);
		listaHistorial.sort(Comparator.comparing(historialModel::getMessalida));
		
		if(listaHistorial.size()==1) {
			horario = listaHistorial.get(0).getHorario_id();
		}
		else {
			historialModel historialAux = null; 
			Long[] meses = new Long[12];
			Arrays.fill(meses, 0L);
			
			for (historialModel historial : listaHistorial) {
			    int mesSalida = historial.getMessalida();
			    Long horarioId = historial.getHorario_id();

			    for (int j = 0; j < 12; j++) {
			        if (j < mesSalida && meses[j] == 0L) {
			            meses[j] = horarioId;
			        }
			    }
			}
			horario = meses[mes-1];
			di=0;
			List<TuplaFecha> tuplas = new ArrayList<>();
			for(int i=0;i<listaHistorial.size();i++) {
				tuplas.add(new TuplaFecha(LocalDate.of(listaHistorial.get(i).getGestion(), listaHistorial.get(i).getMes(), listaHistorial.get(i).getDia()), LocalDate.of(listaHistorial.get(i).getGestionsalida(), listaHistorial.get(i).getMessalida(), listaHistorial.get(i).getDiasalida())));
				if(listaHistorial.get(i).getHorario_id().longValue()==horario)
					historialAux=listaHistorial.get(i);
			}
			RangoEnMes dias = obtenerDiasDelMes(tuplas,mes,gestion);
			di=dias.diaInicio;
			df=dias.diaFin;
			
		}
		List<formatoReporteDtoResponse>listaReporte = getReporte(cif,horario,gestion,mes);
		List<formatoReporteDtoResponse>reporteFinal= new ArrayList<formatoReporteDtoResponse>();
		
		int j=1;
		if(di==0) {
			for(int i=0;i<listaReporte.size();i++) {
				if(listaReporte.get(i).isMarcado())
				{
					listaReporte.get(i).setId(j);
					reporteFinal.add(listaReporte.get(i));
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
					j++;
				}
			}
		}
		/*
		for(int i=0;i<reporteFinal.size();i++) {
			reporteFinal.get(i).mostrar();
		}*/
		return reporteFinal;
	}
	
	@Transactional
	public List<formatoReporteDtoResponse> getReporteMesDia(Long id_horario,Long cif,int gestion,int mes,int di,int df)
	{
		List<formatoReporteDtoResponse>listaReporte = getReporte(cif,id_horario,gestion,mes);
		List<formatoReporteDtoResponse>reporteFinal = new ArrayList<formatoReporteDtoResponse>();
		int j=1;
		for(int i=0;i<listaReporte.size();i++) {
			if(listaReporte.get(i).isMarcado() && listaReporte.get(i).getDay()>=di && listaReporte.get(i).getDay()<=df)
			{
				listaReporte.get(i).setId(j);
				reporteFinal.add(listaReporte.get(i));
				j++;
			}
		}
		return reporteFinal;
	}
	
	public List<formatoReporteDtoResponse> getReporte(Long cif,Long id_horario,int gestion,int mes){
		
		
		final Locale español = new Locale("es","MX");
		String valuedia = "";
		String valuemes = "";
		String fecha = "";
		String dia = "";
		
		List<biometricoModel> datos = this.biometricoDao.getPerfil(cif);
		
		horarioModel horarioModel = this.horarioDao.getById(id_horario);
		
		List<marcadoModel>listaMarcado = new ArrayList<marcadoModel>();
		
		List<formatoReporteDtoResponse>listaReporte = new ArrayList<formatoReporteDtoResponse>();
		
		
		List<obsModel>obsModel = this.obsDao.getObs(gestion, mes);
		
		List<obsBioModel>listaObsBioModel = this.obsBioDao.getCifObs(cif, 1);
		obsBioModel obsBioModel= null;
		List<obsDtoReporte>obsReporte = new ArrayList<obsDtoReporte>();
		obsDtoReporte obserAux;
		formatoReporteDtoResponse reporte;
		
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		//se crea la lista de marcado
		
		for (int i=0;i<datos.size();i++) {
			listaMarcado.addAll(this.marcadoDao.getMarcado(datos.get(i).getUser_id(),datos.get(i).getLugar(), gestion, mes));
		}
			
		
		
		
		//se crean las observaciones
		for(int i=0;i<obsModel.size();i++) {
			for(int j=obsModel.get(i).getDi();j<=obsModel.get(i).getDf();j++) {
				if(obsModel.get(i).getMes()>9)
					valuemes = Integer.toString(mes);
				else
					valuemes = "0"+Integer.toString(mes);
				if(j>9)
					valuedia = ""+j;
				else
					valuedia = "0"+j;
				fecha = obsModel.get(i).getGestion()+"-"+valuemes+"-"+valuedia;
				
				for(int k=0;k<listaObsBioModel.size();k++) {
					if(listaObsBioModel.get(k).getIdObs().longValue()==obsModel.get(i).getId().longValue()) {
						obsBioModel = listaObsBioModel.get(k);
						obsReporte.add(new obsDtoReporte(obsModel.get(i).getId(),obsModel.get(i).getUidObs(),fecha,obsModel.get(i).getDetalle(),obsModel.get(i).getTipo(),obsBioModel));
						break;
					}
				}
				
				
			}	
		}
				
		// Ordenamos la Lista Despues de Crearlo
		int menor = 0;
		int mayor = 0;
		marcadoModel aux;
		for(int i=0;i<listaMarcado.size()-1;i++) {
			marcadoModel marcadoMenor = listaMarcado.get(i);
			menor = marcadoMenor.getDia();
			for(int j=i+1;j<listaMarcado.size();j++) {
				marcadoModel marcadoMayor = listaMarcado.get(j);
				mayor = marcadoMayor.getDia();
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
		obsDtoReporte auxObserModel;
		for(int i=0;i<obsReporte.size()-1;i++) {
			obsDtoReporte obsDtoReporteMenor = obsReporte.get(i);
			menor = Integer.parseInt(obsDtoReporteMenor.getFecha().substring(8, 10));
			for(int j=i+1;j<obsReporte.size();j++) {		
				obsDtoReporte obserModelMayor = obsReporte.get(j);
				mayor = Integer.parseInt(obserModelMayor.getFecha().substring(8, 10));
				if(mayor < menor) {
					auxObserModel = obsReporte.get(i);
					obsReporte.set(i, obsReporte.get(j));
					obsReporte.set(j, auxObserModel);
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
	            List<obsDtoReporte>listaux=new ArrayList<obsDtoReporte>();
	            for(int i=0;i<obsReporte.size();i++) {
	            	if(obsReporte.get(i).getFecha().equals(fecha))
	            	{
	            		obserAux = new obsDtoReporte("");
	            		obserAux.setId(obsReporte.get(i).getId());
	            		obserAux.setFecha(obsReporte.get(i).getFecha());
	            		obserAux.setTipo(obsReporte.get(i).getTipo());
	            		obserAux.setUidobs(obsReporte.get(i).getUidobs());
	            		obserAux.setDetalle(obsReporte.get(i).getDetalle());
	            		obserAux.setObsBioModel(obsReporte.get(i).getObsBioModel());
	            		listaux.add(obserAux);
	            	}
	            }
	            
	            reporte = new formatoReporteDtoResponse(0,day.getDayOfWeek().getDisplayName(TextStyle.FULL, español),day.getDayOfMonth(),day.getMonthValue(),fecha,true,listaux);
	            listaReporte.add(reporte);
            }
            else
            	break;
        }
				

        //#####################################################################
		
		//colocamos el marcado en el reporte
        for(int i=0;i<listaReporte.size();i++) {
        	if(listaReporte.get(i).getObsDtoReporte().size()>0 && listaReporte.get(i).getObsDtoReporte().get(0).getTipo().equals("asueto")) {
    			String h[] = new String[4];
    			h[0]="asueto";
    			h[1]="asueto";
    			h[2]="asueto";
    			h[3]="asueto";
    			listaReporte.get(i).setHora(h);
    		}
        	for(int j=0;j<listaMarcado.size();j++) {
        		if(listaReporte.get(i).getFecha().equals(listaMarcado.get(j).getFecha())) {
        			listaReporte.get(i).addMarcado(this.modelMapper.map(listaMarcado.get(j),marcadoDtoResponse.class));
        		}
        	}
        	switch(listaReporte.get(i).getDia()) {
        		case "lunes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.getLem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.getLsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.getLet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.getLst());
        			break;
        		case "martes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.getMem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.getMsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.getMet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.getMst());
        			break;
        		case "miércoles":
        			listaReporte.get(i).setTurnoB(0, horarioModel.getMiem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.getMism());
        			listaReporte.get(i).setTurnoB(2, horarioModel.getMiet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.getMist());
        			break;
        		case "jueves":
        			listaReporte.get(i).setTurnoB(0, horarioModel.getJem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.getJsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.getJet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.getJst());
        			break;
        		case "viernes":
        			listaReporte.get(i).setTurnoB(0, horarioModel.getVem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.getVsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.getVet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.getVst());
        			break;
        		case "sábado":
        			listaReporte.get(i).setTurnoB(0, horarioModel.getSem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.getSsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.getSet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.getSst());
        			break;
        		case "domingo":
        			listaReporte.get(i).setTurnoB(0, horarioModel.getDem());
        			listaReporte.get(i).setTurnoB(1, horarioModel.getDsm());
        			listaReporte.get(i).setTurnoB(2, horarioModel.getDet());
        			listaReporte.get(i).setTurnoB(3, horarioModel.getDst());
        			break;
        	}
        	listaReporte.get(i).reporte(listaReporte.get(i).getFecha());
        }
        
        return listaReporte; 
	}
	@Transactional
	public List<marcadoDtoTotalResponse> getReporteTotal(int gestion,int mes,int tipo)
	{
		List<marcadoDtoTotalResponse>listaTotal = new ArrayList<marcadoDtoTotalResponse>();
		List<biometricoModel>listaBiometrico = this.biometricoDao.listarBiometricoTipo((long) tipo);
		int retraso=0;
		int antisipado=0;
		int penalisacion=0;
		int r[]=new int[4];
		String hora[] = new String[4];
		int mayor=0;
		int menor=0;
		boolean ingreso=true;
		marcadoDtoTotalResponse aux;
		
		for(int i=0;i<listaBiometrico.size();i++) {
			ingreso=true;
			// se verifica que el usuario no ingrese al ranking 2 veces 
			for(int k=0;k<listaTotal.size();k++) {
				if(listaTotal.get(k).getCif().longValue() == listaBiometrico.get(i).getCif().longValue()) {
					ingreso=false;
					break;
				}
			}
			if(ingreso) {
						
				List<formatoReporteDtoResponse>listaReporte;
				List<historialModel> historialModel = this.historialDao.getHistorial(listaBiometrico.get(i).getCif(), gestion);
				if(mes==historialModel.get(0).getMes()) {
					listaReporte = getReporteMes(listaBiometrico.get(i).getCif(),gestion,mes,historialModel.get(0).getDia(),31);
				}
				else {
					listaReporte = getReporteMes(listaBiometrico.get(i).getCif(),gestion,mes,0,0);
				}
				
				List<formatoReporteDtoResponse>reporteFinal = new ArrayList<formatoReporteDtoResponse>();
				int j=1;
				for(int k=0;k<listaReporte.size();k++) {
					if(listaReporte.get(k).isMarcado())
					{
						listaReporte.get(k).setId(j);
						reporteFinal.add(listaReporte.get(k));
						j++;
					}
				}
				
				retraso=0;
				antisipado=0;
				penalisacion=0;
				for (int f=0;f<reporteFinal.size();f++) {
					r=reporteFinal.get(f).getRetraso();
					hora= reporteFinal.get(f).getHora();
					if(r[0]>0 || r[1]>0 || r[2]>0 || r[3]>0||hora[0].equals("Sin Marcar")|| hora[1].equals("Sin Marcar")||hora[2].equals("Sin Marcar")||hora[3].equals("Sin Marcar")) {
						penalisacion+=1;
					}
					retraso=retraso+r[0]+r[2];
					antisipado=antisipado+r[1]+r[3];	
				}
				listaTotal.add(new marcadoDtoTotalResponse(listaBiometrico.get(i).getCif(),reporteFinal,retraso,antisipado,penalisacion));
			}
			
		}
		
		for(int i=0;i<listaTotal.size()-1;i++)
		{
			menor=listaTotal.get(i).getRetraso();
			for(int j=i+1;j<listaTotal.size();j++) {
				mayor=listaTotal.get(j).getRetraso();
				if(menor>mayor) {
					aux=listaTotal.get(i);
					listaTotal.set(i, listaTotal.get(j));
					listaTotal.set(j, aux);
					menor=listaTotal.get(i).getRetraso();
				}
			}
		}
		return listaTotal;
	}
	
	public static RangoEnMes obtenerDiasDelMes(List<TuplaFecha> tuplas, int mes, int anio) {
        for (TuplaFecha tupla : tuplas) {
            LocalDate inicio = tupla.inicio;
            LocalDate fin = tupla.fin;

            // Chequeamos si el mes y año está dentro de la tupla
            LocalDate inicioMes = LocalDate.of(anio, mes, 1);
            YearMonth ym = YearMonth.of(anio, mes);
            LocalDate finMes = ym.atEndOfMonth();

            // Si el rango del mes y el de la tupla se intersectan
            if (!(fin.isBefore(inicioMes) || inicio.isAfter(finMes))) {
                int diaInicio = Math.max(1, inicio.getMonthValue() == mes && inicio.getYear() == anio ? inicio.getDayOfMonth() : 1);
                int diaFin = Math.min(ym.lengthOfMonth(), fin.getMonthValue() == mes && fin.getYear() == anio ? fin.getDayOfMonth() : ym.lengthOfMonth());
                return new RangoEnMes(diaInicio, diaFin);
            }
        }

        return null; // No se encontró rango para ese mes
    }

}
/*Clases para obtener el rango de dias en los empleados que tienen mas de dos horarios en la gestion*/
class RangoEnMes {
    int diaInicio;
    int diaFin;

    RangoEnMes(int diaInicio, int diaFin) {
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
    }

    @Override
    public String toString() {
        return "Día inicio = " + diaInicio + ", Día fin = " + diaFin;
    }
}
class TuplaFecha {
    LocalDate inicio;
    LocalDate fin;

    TuplaFecha(LocalDate inicio, LocalDate fin) {
        this.inicio = inicio;
        this.fin = fin;
    }
}
