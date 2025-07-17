package com.fhce.scc.obj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fhce.scc.model.obsBioModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class formatoReporteDtoResponse {
	
	private int id;
	private String dia;
	private int day;
	private int month;
	private boolean marcado;
	private String fecha;
	private String turno[]=new String[4];
	private String turnoB[]=new String[4];
	private String lugar[]=new String[4];
	private String hora[]=new String[4];
	private int retraso[]=new int[4];
	
	private List<marcadoDtoResponse>marcadoDtoResponse;
	private List<obsDtoReporte> obsDtoReporte;
	
	public formatoReporteDtoResponse(int id,String dia, int day,int month,String fecha,boolean marcado,List<obsDtoReporte> obsDtoReporte) {
		this.id=id;
		this.dia=dia;
		this.day=day;
		this.month=month;
		this.marcado=marcado;
		this.fecha=fecha;
		this.turno[0]="Entrada M.";
		this.turno[1]="Salida M.";
		this.turno[2]="Entrada T.";
		this.turno[3]="Salida T.";
		this.hora[0]="0";
		this.hora[1]="0";
		this.hora[2]="0";
		this.hora[3]="0";
		this.lugar[0]="0";
		this.lugar[1]="0";
		this.lugar[2]="0";
		this.lugar[3]="0";
		this.retraso[0]=0;
		this.retraso[1]=0;
		this.retraso[2]=0;
		this.retraso[3]=0;
		this.marcadoDtoResponse = new ArrayList<marcadoDtoResponse>();
		this.obsDtoReporte = obsDtoReporte;
	}
	
	public void setTurnoB(int i,String hora) {
		this.turnoB[i]=hora;
	}
	
	public void addMarcado(marcadoDtoResponse marcadoDtoResponse) {
		this.marcadoDtoResponse.add(marcadoDtoResponse);
	}
	// AAAAAA
	public void reporte(String fecha) {	
		/*List<marcadoDtoResponse>entradaManana lista que recopila las horas de entrada */
		List<marcadoDtoResponse>entradaManana=new ArrayList<marcadoDtoResponse>();
		List<marcadoDtoResponse>salidaManana=new ArrayList<marcadoDtoResponse>();
		List<marcadoDtoResponse>entradaTarde=new ArrayList<marcadoDtoResponse>();
		List<marcadoDtoResponse>salidaTarde=new ArrayList<marcadoDtoResponse>();
		List<marcadoDtoResponse>horas=new ArrayList<marcadoDtoResponse>();
		int hEntrada =0;
		int mEntrada =0;
		int hSalida =0;
		int mSalida=0;
		int tolerancia = 0;
		int auxT = 40;
		int marcado[]=new int[4];
		int ingreso[]=new int[4];
		boolean continuo=false;
		boolean esContinuoIngresoDia = false;
		
		// ordemanos las horas 
		marcadoDtoResponse aux;
		int men=0;
		int may=0;
		for(int i=0;i<this.marcadoDtoResponse.size()-1;i++) {
			men=(this.marcadoDtoResponse.get(i).getH()*100)+this.marcadoDtoResponse.get(i).getM();
			for(int j=i+1;j<this.marcadoDtoResponse.size();j++) {
				may=(this.marcadoDtoResponse.get(j).getH()*100)+this.marcadoDtoResponse.get(j).getM();
				if(may<men) {
					aux=this.marcadoDtoResponse.get(i);
					this.marcadoDtoResponse.set(i, this.marcadoDtoResponse.get(j));
					this.marcadoDtoResponse.set(j, aux);
					men=may;
				}
			}
		}
		
		/* Verificamos los dias de marcado con el horario registrado
		y los llenos en el vectror marcado[] la Sumatoria de exsistencia de marcados deve de ser mayor a 0 */
		int h=0;
		int suma=0;
		for(int i=0;i<4;i++) {
			if(!this.turnoB[i].equals("continuo")) {
				marcado[i]=Integer.parseInt(turnoB[i].replace(":", ""));
				if(turnoB[i].length()>2)
					ingreso[i]=Integer.parseInt(turnoB[i].substring(0, 2));
			}
				
			else 
				marcado[i]=-1;
			suma=suma+marcado[i];
		}
		if(suma==0)
			this.marcado=false;
		// --- BLOQUE PARA GENERAR FICTICIOS SI NO HAY MARCADOS REALES PERO SÍ OBSERVACIONES ---
if(this.marcadoDtoResponse.size() == 0 && this.obsDtoReporte.size() > 0) {
    for (int j = 0; j < this.obsDtoReporte.size(); j++) {
        switch(this.obsDtoReporte.get(j).getTipo()) {
            case ("Permiso Medico"):
                obsBioModel obs = this.obsDtoReporte.get(j).getObsBioModel();

                // Permiso Médico en Entrada Mañana
                marcadoDtoResponse permisoEntradaM = new marcadoDtoResponse();
                permisoEntradaM.setId(0L);
                permisoEntradaM.setUid(0L);
                permisoEntradaM.setUser_id(0L);
                permisoEntradaM.setFecha(this.obsDtoReporte.get(j).getFecha());
                permisoEntradaM.setHora(obs.getHoraEntrada());
                permisoEntradaM.setGestion(0);
                permisoEntradaM.setMes(0);
                permisoEntradaM.setDia(0);
                permisoEntradaM.setH(obs.getHEntrada());
                permisoEntradaM.setM(obs.getMEntrada());
                permisoEntradaM.setPunch(0);
                permisoEntradaM.setRstatus(0);
                permisoEntradaM.setLugar("P. Medico");
                entradaManana.add(permisoEntradaM);

                // Permiso Médico en Salida Mañana
                marcadoDtoResponse permisoSalidaM = new marcadoDtoResponse();
                permisoSalidaM.setId(0L);
                permisoSalidaM.setUid(0L);
                permisoSalidaM.setUser_id(0L);
                permisoSalidaM.setFecha(this.obsDtoReporte.get(j).getFecha());
                permisoSalidaM.setHora(obs.getHoraSalida());
                permisoSalidaM.setGestion(0);
                permisoSalidaM.setMes(0);
                permisoSalidaM.setDia(0);
                permisoSalidaM.setH(obs.getHSalida());
                permisoSalidaM.setM(obs.getMSalida());
                permisoSalidaM.setPunch(0);
                permisoSalidaM.setRstatus(0);
                permisoSalidaM.setLugar("P. Medico");
                salidaManana.add(permisoSalidaM);

                // Permiso Médico en Entrada Tarde
                marcadoDtoResponse permisoEntradaT = new marcadoDtoResponse();
                permisoEntradaT.setId(0L);
                permisoEntradaT.setUid(0L);
                permisoEntradaT.setUser_id(0L);
                permisoEntradaT.setFecha(this.obsDtoReporte.get(j).getFecha());
                permisoEntradaT.setHora(obs.getHoraEntrada());
                permisoEntradaT.setGestion(0);
                permisoEntradaT.setMes(0);
                permisoEntradaT.setDia(0);
                permisoEntradaT.setH(obs.getHEntrada());
                permisoEntradaT.setM(obs.getMEntrada());
                permisoEntradaT.setPunch(0);
                permisoEntradaT.setRstatus(0);
                permisoEntradaT.setLugar("P. Medico");
                entradaTarde.add(permisoEntradaT);

                // Permiso Médico en Salida Tarde
                marcadoDtoResponse permisoSalidaT = new marcadoDtoResponse();
                permisoSalidaT.setId(0L);
                permisoSalidaT.setUid(0L);
                permisoSalidaT.setUser_id(0L);
                permisoSalidaT.setFecha(this.obsDtoReporte.get(j).getFecha());
                permisoSalidaT.setHora(obs.getHoraSalida());
                permisoSalidaT.setGestion(0);
                permisoSalidaT.setMes(0);
                permisoSalidaT.setDia(0);
                permisoSalidaT.setH(obs.getHSalida());
                permisoSalidaT.setM(obs.getMSalida());
                permisoSalidaT.setPunch(0);
                permisoSalidaT.setRstatus(0);
                permisoSalidaT.setLugar("P. Medico");
                salidaTarde.add(permisoSalidaT);

                break;
            // Aquí podrías agregar otros casos si en el futuro lo necesitas
        }
    }
}
// --- FIN BLOQUE FICTICIOS ---

		/* designamos los marcados en la lista para corelacionar con los horarios de marcados de los empleados*/
		tolerancia = 5;
		for(int i=0;i<this.marcadoDtoResponse.size();i++) {
			h=(this.marcadoDtoResponse.get(i).getH()*100)+this.marcadoDtoResponse.get(i).getM();
			/*h contiene la hora del marcado*/
			if(this.obsDtoReporte.size()==0) {/*preguntamos si en el dia existen observaciones si es cero creamos las listas con los marcados correspondientes*/
				
				if(h < marcado[0]+100) {
					/*marcado[0] contine el marcado de ingreso por las Mananas en el horario 
					se suma 100 para que el ingreso sea hasta una hora despues de su horario*/
					entradaManana.add(this.marcadoDtoResponse.get(i));
				}
				if(marcado[1]-100<h && h<=marcado[1]+100) {
					/*marcado[1] contine el marcado de salida por las Mananas
					se suma y resta 100 para tener un intervalo de salida por ejemplo 11:30 a 13:30 es lo que puede marcar*/
					salidaManana.add(this.marcadoDtoResponse.get(i));
				}
				if(marcado[2]-90<h && h<marcado[2]+100) {
					/*entrada[2] contine el marcado de ingreso  por las Tardes
					se resta 90 y se suma 100 para tener un intervalo de ingreso por ejemplo 13:40 a 15:30 es lo que puede marcar*/
					entradaTarde.add(this.marcadoDtoResponse.get(i));
				}
				if(h>marcado[3]-200) {
					/*marcado[3] contine el marcado de salida  por las Tardes
					se resta 20 para tener un marcado de 16:30 es lo que ya puede marcar*/
					salidaTarde.add(this.marcadoDtoResponse.get(i));
				}
			}
			else {/*sis exsisten observaciones verificamos cual de ellos es */
				

				for (int j=0;j<this.obsDtoReporte.size();j++) {
					marcadoDtoResponse auxMarcado;
					hEntrada = this.obsDtoReporte.get(j).getObsBioModel().getHEntrada();
					mEntrada = this.obsDtoReporte.get(j).getObsBioModel().getMEntrada();
					hSalida = this.obsDtoReporte.get(j).getObsBioModel().getHSalida();
					mSalida = this.obsDtoReporte.get(j).getObsBioModel().getMSalida();
					
					switch(this.obsDtoReporte.get(j).getTipo()) {
						
						case("continuo"):
							/*sis la observacion es continuo solo tomamos en cuentas las salidas*/
							if(h < marcado[0]+100) {
								/*si la hora en h (marcado) es menor al marcado[0] + 100 es de ingreso */
								entradaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(h>(hSalida*100+mSalida)-100) {
								/*preguntamos si h(marcado) es mayor a  hSalida de la observacion - 100 para registrarlo en el reporte*/
								salidaTarde.add(this.marcadoDtoResponse.get(i));
							}
							this.turnoB[1]="continuo";
							this.turnoB[2]="continuo";
							continuo=true;
							break;
							
						case ("continuoingreso"):
							tolerancia = 30;

							marcadoDtoResponse marcadoEntrada = null;
							marcadoDtoResponse marcadoSalida = null;

							for (marcadoDtoResponse marcadoAux : this.marcadoDtoResponse) {
								int minutos = marcadoAux.getH() * 60 + marcadoAux.getM();
								if (marcadoEntrada == null || minutos < (marcadoEntrada.getH() * 60 + marcadoEntrada.getM())) {
									marcadoEntrada = marcadoAux;
								}
								if (marcadoSalida == null || minutos > (marcadoSalida.getH() * 60 + marcadoSalida.getM())) {
									marcadoSalida = marcadoAux;
								}
							}

							int retrasoEntrada = 0;
							int minutosToleranciaUsados = 0;
							int entradaProgMin = hEntrada * 60 + mEntrada;

							if (marcadoEntrada != null) {
								int marcadoEntradaMin = marcadoEntrada.getH() * 60 + marcadoEntrada.getM();
								minutosToleranciaUsados = marcadoEntradaMin - entradaProgMin;
								if (minutosToleranciaUsados < 0) minutosToleranciaUsados = 0;

								if (minutosToleranciaUsados > tolerancia) {
									retrasoEntrada = minutosToleranciaUsados - tolerancia;
									minutosToleranciaUsados = tolerancia;
								} else {
									retrasoEntrada = 0;
								}
								entradaManana.add(marcadoEntrada);
							}

							int salidaProgMin = hSalida * 60 + mSalida;
							int salidaRealPermitidaMin = salidaProgMin + minutosToleranciaUsados;
							int anticipadoSalida = 0;

							int salidaHora = salidaRealPermitidaMin / 60;
							int salidaMin = salidaRealPermitidaMin % 60;
							this.turnoB[3] = String.format("%02d:%02d", salidaHora, salidaMin);

							if (marcadoSalida != null) {
								int marcadoSalidaMin = marcadoSalida.getH() * 60 + marcadoSalida.getM();
								if (marcadoSalidaMin < salidaRealPermitidaMin) {
									anticipadoSalida = salidaRealPermitidaMin - marcadoSalidaMin;
								} else {
									anticipadoSalida = 0;
								}
								salidaTarde.add(marcadoSalida);
							}

							this.retraso[0] = retrasoEntrada;
							this.retraso[3] = anticipadoSalida;

							this.turnoB[1] = "continuo";
							this.turnoB[2] = "continuo";
							continuo = true;
							esContinuoIngresoDia = true;
							break;
				
						
						case ("Permiso Medico"):
							obsBioModel obs = this.obsDtoReporte.get(j).getObsBioModel();

							// Permiso Médico en Entrada Mañana
							marcadoDtoResponse permisoEntradaM = new marcadoDtoResponse();
							permisoEntradaM.setId(0L);
							permisoEntradaM.setUid(0L);
							permisoEntradaM.setUser_id(0L);
							permisoEntradaM.setFecha(this.obsDtoReporte.get(j).getFecha());
							permisoEntradaM.setHora(obs.getHoraEntrada());
							permisoEntradaM.setGestion(0);
							permisoEntradaM.setMes(0);
							permisoEntradaM.setDia(0);
							permisoEntradaM.setH(obs.getHEntrada());
							permisoEntradaM.setM(obs.getMEntrada());
							permisoEntradaM.setPunch(0);
							permisoEntradaM.setRstatus(0);
							permisoEntradaM.setLugar("P. Medico");
							entradaManana.add(permisoEntradaM);

							// Permiso Médico en Salida Mañana
							marcadoDtoResponse permisoSalidaM = new marcadoDtoResponse();
							permisoSalidaM.setId(0L);
							permisoSalidaM.setUid(0L);
							permisoSalidaM.setUser_id(0L);
							permisoSalidaM.setFecha(this.obsDtoReporte.get(j).getFecha());
							permisoSalidaM.setHora(obs.getHoraSalida());
							permisoSalidaM.setGestion(0);
							permisoSalidaM.setMes(0);
							permisoSalidaM.setDia(0);
							permisoSalidaM.setH(obs.getHSalida());
							permisoSalidaM.setM(obs.getMSalida());
							permisoSalidaM.setPunch(0);
							permisoSalidaM.setRstatus(0);
							permisoSalidaM.setLugar("P. Medico");
							salidaManana.add(permisoSalidaM);

							// Permiso Médico en Entrada Tarde
							marcadoDtoResponse permisoEntradaT = new marcadoDtoResponse();
							permisoEntradaT.setId(0L);
							permisoEntradaT.setUid(0L);
							permisoEntradaT.setUser_id(0L);
							permisoEntradaT.setFecha(this.obsDtoReporte.get(j).getFecha());
							permisoEntradaT.setHora(obs.getHoraEntrada());
							permisoEntradaT.setGestion(0);
							permisoEntradaT.setMes(0);
							permisoEntradaT.setDia(0);
							permisoEntradaT.setH(obs.getHEntrada());
							permisoEntradaT.setM(obs.getMEntrada());
							permisoEntradaT.setPunch(0);
							permisoEntradaT.setRstatus(0);
							permisoEntradaT.setLugar("P. Medico");
							entradaTarde.add(permisoEntradaT);

							// Permiso Médico en Salida Tarde
							marcadoDtoResponse permisoSalidaT = new marcadoDtoResponse();
							permisoSalidaT.setId(0L);
							permisoSalidaT.setUid(0L);
							permisoSalidaT.setUser_id(0L);
							permisoSalidaT.setFecha(this.obsDtoReporte.get(j).getFecha());
							permisoSalidaT.setHora(obs.getHoraSalida());
							permisoSalidaT.setGestion(0);
							permisoSalidaT.setMes(0);
							permisoSalidaT.setDia(0);
							permisoSalidaT.setH(obs.getHSalida());
							permisoSalidaT.setM(obs.getMSalida());
							permisoSalidaT.setPunch(0);
							permisoSalidaT.setRstatus(0);
							permisoSalidaT.setLugar("P. Medico");
							salidaTarde.add(permisoSalidaT);

							break;


						case("Entrada M."):
							/*si el empleado no marco el ingreso se crea el ingreso deacuerdo a su observacion*/
							auxMarcado=new marcadoDtoResponse();
							auxMarcado.setHora(this.obsDtoReporte.get(j).getObsBioModel().getHoraEntrada());
							auxMarcado.setFecha(this.obsDtoReporte.get(j).getFecha());
							auxMarcado.setLugar(this.obsDtoReporte.get(j).getUidobs());
							auxMarcado.setH(hEntrada);
							auxMarcado.setM(mEntrada);
							auxMarcado.setId((long)0);
							
							entradaManana.add(auxMarcado);
							if(marcado[1]-100<h && h<=marcado[1]+100) {
								salidaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(marcado[2]-90<h && h<marcado[2]+100) {
								entradaTarde.add(this.marcadoDtoResponse.get(i));
							}
							if(h>marcado[3]-100) {
								salidaTarde.add(this.marcadoDtoResponse.get(i));
							}
							break;
						
						case("Salida M."):
							/*si el empleado no marco la salida en la manana creamos uno deacuerdo as su observacion*/
							auxMarcado=new marcadoDtoResponse();
							auxMarcado.setHora(this.obsDtoReporte.get(j).getObsBioModel().getHoraSalida());
							auxMarcado.setFecha(this.obsDtoReporte.get(j).getFecha());
							auxMarcado.setLugar(this.obsDtoReporte.get(j).getUidobs());
							auxMarcado.setH(hSalida);
							auxMarcado.setM(mSalida);
							auxMarcado.setId((long)0);
							salidaManana.add(auxMarcado);
							marcado[1]=hSalida*100+mSalida;
							if(h < marcado[0]+100) {
								entradaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(marcado[2]-90<h && h<marcado[2]+100) {
								entradaTarde.add(this.marcadoDtoResponse.get(i));
							}
							if(h>marcado[3]-100) {
								salidaTarde.add(this.marcadoDtoResponse.get(i));
							}
							break;
						
						case("Entrada T."):
							/*si el empleado no marco el ingreso en la Tader creamos su entrada deacuerdo a su obserbacion*/
							auxMarcado=new marcadoDtoResponse();
							auxMarcado.setHora(this.obsDtoReporte.get(j).getObsBioModel().getHoraEntrada());
							auxMarcado.setFecha(this.obsDtoReporte.get(j).getFecha());
							auxMarcado.setLugar(this.obsDtoReporte.get(j).getUidobs());
							auxMarcado.setH(hEntrada);
							auxMarcado.setM(mEntrada);
							auxMarcado.setId((long)0);
							entradaTarde.add(auxMarcado);
							
							if(h < marcado[0]+100) {
								entradaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(marcado[1]-100<h && h<=marcado[1]+100) {
								salidaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(h>marcado[3]-100) {
								salidaTarde.add(this.marcadoDtoResponse.get(i));
							}
							
							break;
						
						case("Salida T."):
							/*si el empleado no marco en la Salida tarde creamos uno deacuerdo a su observacion*/
							auxMarcado=new marcadoDtoResponse();
							auxMarcado.setHora(this.obsDtoReporte.get(j).getObsBioModel().getHoraSalida());
							auxMarcado.setFecha(this.obsDtoReporte.get(j).getFecha());
							auxMarcado.setLugar("UID");
							auxMarcado.setH(hSalida);
							auxMarcado.setM(mSalida);
							auxMarcado.setId((long)0);
							salidaTarde.add(auxMarcado);
							marcado[3]= hSalida*100+mSalida;
							if(h < marcado[0]+100) {
								entradaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(marcado[1]-100<h && h<=marcado[1]+100) {
								salidaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(marcado[2]-90<h && h<marcado[2]+100) {
								entradaTarde.add(this.marcadoDtoResponse.get(i));
							}
							break;
						
						case("tolerancia"):
							int toleranciaPermitida = 15;
							
							int horaEntradaProg = hEntrada * 60 + mEntrada;
							int horaMarcada = this.marcadoDtoResponse.get(i).getH() * 60 + this.marcadoDtoResponse.get(i).getM();
							
							int minutosRetraso = horaMarcada - horaEntradaProg;

							if (minutosRetraso <= toleranciaPermitida && minutosRetraso >= 0) {
								this.retraso[0] = 0;
							} else if (minutosRetraso > toleranciaPermitida) {
								this.retraso[0] = minutosRetraso - toleranciaPermitida;
							} else {
								this.retraso[0] = 0;
							}
							
							entradaManana.add(this.marcadoDtoResponse.get(i));

							if(marcado[1]-100 < h && h <= marcado[1]+100) {
								salidaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(marcado[2]-90 < h && h < marcado[2]+100) {
								entradaTarde.add(this.marcadoDtoResponse.get(i));
							}
							if(h > marcado[3]-100) {
								salidaTarde.add(this.marcadoDtoResponse.get(i));
							}
							break;

						
						case("extraordinario"):
							marcado[0]=Integer.parseInt(this.obsDtoReporte.get(j).getObsBioModel().getHoraEntrada().replace(":", ""));
							if(h < marcado[0]+100) {
								entradaManana.add(this.marcadoDtoResponse.get(i));
							}
							if(h>marcado[3]-200) {
								salidaTarde.add(this.marcadoDtoResponse.get(i));
							}
							this.turnoB[1]="continuo";
							this.turnoB[2]="continuo";
							continuo=true;
							break;						
						case("horas"):
							horas.add(this.marcadoDtoResponse.get(i));
							break;							
						case("comision"):
							break;						
						case("permiso"):
							break;

						//permiso medico funciona como asueto 
						//asueto 
					}
				
				}
			}
		}
		
		//verificamos si uno de los empelados marco y trabajo por horas
		if(horas.size()>0) {
			int mayor=horas.get(horas.size()-1).getH();
			int menor=horas.get(0).getH();
			for(int i=0;i<horas.size();i++) {
				if(menor==horas.get(i).getH()) {
					entradaManana.add(horas.get(i));
					ingreso[0]=horas.get(i).getH();
					marcado[0]=horas.get(i).getH()*100+horas.get(i).getM(); //Discriminacion Attemdamce 
				}
				if(mayor==horas.get(i).getH())
				{
					salidaTarde.add(horas.get(i));
					break;
				}
			}
			this.turnoB[1]="continuo";
			this.turnoB[2]="continuo";
			
			continuo=true;
		}
		
		
		/*si exsisten varias marcados seleccionamos el ultimo marcado que realizo*/ 
		// entrada en la mañana 
		if(entradaManana.size()>0) {
			aux=entradaManana.get(entradaManana.size()-1);
			h=(aux.getH()*100)+aux.getM();
			
			this.hora[0] = aux.getHora();
			this.lugar[0] = aux.getLugar()+" uid:"+aux.getId();
			
			if (!esContinuoIngresoDia)
        		this.retraso[0] = retraso(h, marcado[0] + tolerancia, aux, ingreso[0]);
			
		}
		else {
			continuo(0,this.turnoB[0]);
		}
		
		//salida en la Mañana
		if(salidaManana.size()>0) {
			aux=salidaManana.get(0);
			h=(aux.getH()*100)+aux.getM();
			this.hora[1]=aux.getHora();
			this.lugar[1]=aux.getLugar()+" uid:"+aux.getId();
			this.retraso[1]=anticipado(h,marcado[1],aux,ingreso[1]);
		}
		else {
			continuo(1,this.turnoB[1]);
		}
		
		//entrada en la tarde 
		if(entradaTarde.size()>0) {
			aux=entradaTarde.get(entradaTarde.size()-1);
			h=(aux.getH()*100)+aux.getM();
			this.hora[2]=aux.getHora();
			this.lugar[2]=aux.getLugar()+" uid:"+aux.getId();
			this.retraso[2]=retraso(h,marcado[2]+5,aux,ingreso[2]);
		}
		else {
			continuo(2,this.turnoB[2]);
		}
		
		//salida en la tarde 
		if(salidaTarde.size()>0) {
			aux=salidaTarde.get(0);
			h=(aux.getH()*100)+aux.getM();
			this.hora[3]=aux.getHora();
			this.lugar[3]=aux.getLugar()+" uid:"+aux.getId();
			if(continuo) {
				// SOLO pon en cero si NO es continuoingreso
				if (!esContinuoIngresoDia) {
					this.retraso[3]=0;
				}
				// Si es continuoingreso, respeta el valor calculado en el switch
			} else {
				this.retraso[3]=anticipado(h,marcado[3],aux,ingreso[3]);
			}
		} else {
			continuo(3,this.turnoB[3]);
		}


		if (esDiaPermisoMedico()) {
			Arrays.fill(this.retraso, 0);
		}

	}

	private boolean esDiaPermisoMedico() {
		for (int j = 0; j < this.obsDtoReporte.size(); j++) {
			if ("Permiso Medico".equals(this.obsDtoReporte.get(j).getTipo())) {
				return true;
			}
		}
		return false;
	}

	
	//Segmento modular de Continuo
	public void continuo(int index,String c) {
		if(c.equals("continuo")) {
			this.hora[index]="continuo";
			this.lugar[index]="Sin Lugar";
		}else if(this.hora[index].equals("asueto")) {
			this.lugar[index]="Asueto";
		}
		else {
			this.hora[index]="Sin Marcar";
			this.lugar[index]="Sin Lugar";
		}	
	}
	//Segmento modular de Retrasos
	public int retraso(int horaMarcado,int horaIngreso,marcadoDtoResponse marcadoDtoResponse,int i) {		
		int razon=0;
		int result=0;
		int suma = 0;
		int aux=marcadoDtoResponse.getH();
		while(i<aux) {
			i++;
			razon=razon+40;
		}	
		suma=horaMarcado-(horaIngreso+razon);
		if(suma >0)
			result=suma;
		return(result);
	}
	
	//Segmento moduloar de salidas anticipadas control Ocs Docquer 
	public int anticipado(int horaMarcado,int horaSalida,marcadoDtoResponse marcadoDtoResponse,int i) {
			
		int razon=0;
		int resta=0;
		int result=0;
		int suma = 0;
		int aux=marcadoDtoResponse.getH();
		while(aux<i) {
			aux++;
			razon=razon+40;
		}
			
		resta=horaSalida-razon;
		suma=horaMarcado-resta;
		if(suma <0)
			result=suma;
		return(result*-1);
	}
}
