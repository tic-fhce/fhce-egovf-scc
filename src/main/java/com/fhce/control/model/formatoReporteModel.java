package com.fhce.control.model;

import java.util.ArrayList;
import java.util.List;

public class formatoReporteModel {
	
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
	private List<marcadoModel>marcadoModel;
	private List<obserModel> obserModel;
	
	public formatoReporteModel(int id,String dia, int day,int month,String fecha,boolean marcado,List<obserModel> obserModel) {
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
		this.marcadoModel=new ArrayList<marcadoModel>();
		this.obserModel=obserModel;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String[] getTurno() {
		return turno;
	}

	public void setTurno(String[] turno) {
		this.turno = turno;
	}

	public String[] getHora() {
		return hora;
	}

	public void setHora(String[] hora) {
		this.hora = hora;
	}

	public int[] getRetraso() {
		return retraso;
	}

	public void setRetraso(int[] retraso) {
		this.retraso = retraso;
	}

	public String[] getLugar() {
		return lugar;
	}

	public void setLugar(String[] lugar) {
		this.lugar = lugar;
	}
	
	public void setHora(int i,String hora) {
		this.hora[i]=hora;
	}
	public void setLugar(int i,String lugar) {
		this.lugar[i]=lugar;
	}
	public String getHora(int i) {
		return(this.hora[i]);
	}
	public String getTurno(int i) {
		return(this.turno[i]);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<obserModel> getObserModel() {
		return obserModel;
	}

	public void setObserModel(List<obserModel> obserModel) {
		this.obserModel = obserModel;
	}
	
	public void setObserModel(int i, obserModel obserModel) {
		this.obserModel.set(i, obserModel);
	}

	public void addMarcado(marcadoModel marcadoModel) {
		this.marcadoModel.add(marcadoModel);
	}
	public void mostrar() {
		System.out.println(this.fecha + " - "+ this.marcado);
		System.out.println(this.dia+" "+this.day);
		//System.out.println(this.obserModel.getTipo() +" -- " +this.obserModel.getHora());
		System.out.println("Turno "+this.turno[0] +"\t"+this.turno[1]+"\t"+this.turno[2]+"\t"+this.turno[3]);
		System.out.println("Turno "+this.turnoB[0] +"\t"+this.turnoB[1]+"\t"+this.turnoB[2]+"\t"+this.turnoB[3]);
		System.out.println("Hora "+this.hora[0] +"\t"+this.hora[1]+"\t"+this.hora[2]+"\t"+this.hora[3]);
		System.out.println("Lugar "+this.lugar[0] +"\t"+this.lugar[1]+"\t"+this.lugar[2]+"\t"+this.lugar[3]);
		System.out.println("Retraso "+this.retraso[0] +"\t"+this.retraso[1]+"\t"+this.retraso[2]+"\t"+this.retraso[3]);
		for(int i=0;i<this.marcadoModel.size();i++) {
			System.out.println(this.marcadoModel.get(i).get_03fecha() + " " +this.marcadoModel.get(i).get_04hora()+" "+this.marcadoModel.get(i).get_12lugar());
		}
	}
	
	public void setTurnoB(int i,String hora) {
		this.turnoB[i]=hora;
	}
	
	public void reporte() {
		
		List<marcadoModel>entradaManana=new ArrayList<marcadoModel>();
		List<marcadoModel>salidaManana=new ArrayList<marcadoModel>();
		List<marcadoModel>entradaTarde=new ArrayList<marcadoModel>();
		List<marcadoModel>salidaTarde=new ArrayList<marcadoModel>();
		List<marcadoModel>horas=new ArrayList<marcadoModel>();
		int entrada[]=new int[4];
		int ingreso[]=new int[4];
		boolean continuo=false;
		
		// ordemanos las horas 
		marcadoModel aux;
		int men=0;
		int may=0;
		for(int i=0;i<this.marcadoModel.size()-1;i++) {
			men=(this.marcadoModel.get(i).get_08h()*100)+this.marcadoModel.get(i).get_09m();
			for(int j=i+1;j<this.marcadoModel.size();j++) {
				may=(this.marcadoModel.get(j).get_08h()*100)+this.marcadoModel.get(j).get_09m();
				if(may<men) {
					aux=this.marcadoModel.get(i);
					this.marcadoModel.set(i, this.marcadoModel.get(j));
					this.marcadoModel.set(j, aux);
					men=may;
				}
			}
		}
		
		// reorganizamos las horas 
		int h=0;
		int suma=0;
		for(int i=0;i<4;i++) {
			if(!this.turnoB[i].equals("continuo")) {
				entrada[i]=Integer.parseInt(turnoB[i].replace(":", ""));
				if(turnoB[i].length()>2)
					ingreso[i]=Integer.parseInt(turnoB[i].substring(0, 2));
			}
				
			else 
				entrada[i]=-1;
			suma=suma+entrada[i];
		}
		if(suma==0)
			this.marcado=false;
		
		for(int i=0;i<this.marcadoModel.size();i++) {
			h=(this.marcadoModel.get(i).get_08h()*100)+this.marcadoModel.get(i).get_09m();
			if(this.obserModel.size()==0) {
				
				if(h < entrada[0]+100) {
					entradaManana.add(this.marcadoModel.get(i));
				}
				if(entrada[1]-100<h && h<=entrada[1]+100) {
					salidaManana.add(this.marcadoModel.get(i));
				}
				if(entrada[2]-90<h && h<entrada[2]+100) {
					entradaTarde.add(this.marcadoModel.get(i));
				}
				if(h>entrada[3]-200) {
					salidaTarde.add(this.marcadoModel.get(i));
				}
			}
			else {
				for (int j=0;j<this.obserModel.size();j++) {
					marcadoModel auxMarcado;
					switch(this.obserModel.get(j).getTipo()) {
						
					case("continuo"):
							if(h < entrada[0]+100) {
								entradaManana.add(this.marcadoModel.get(i));
							}
							if(h>(this.obserModel.get(j).getH()*100+this.obserModel.get(j).getM())-100) {
								salidaTarde.add(this.marcadoModel.get(i));
							}
							this.turnoB[1]="continuo";
							this.turnoB[2]="continuo";
							continuo=true;
							break;
						
						case("Entrada M."):
							auxMarcado=new marcadoModel();
							auxMarcado.set_04hora(this.obserModel.get(j).getHora());
							auxMarcado.set_03fecha(this.obserModel.get(j).getFecha());
							auxMarcado.set_12lugar(this.obserModel.get(j).getUidobs());
							auxMarcado.set_08h(this.obserModel.get(j).getH());
							auxMarcado.set_09m(this.obserModel.get(j).getM());
							auxMarcado.setId((long)0);
							entradaManana.add(auxMarcado);
							if(entrada[1]-100<h && h<=entrada[1]+100) {
								salidaManana.add(this.marcadoModel.get(i));
							}
							if(entrada[2]-90<h && h<entrada[2]+100) {
								entradaTarde.add(this.marcadoModel.get(i));
							}
							if(h>entrada[3]-100) {
								salidaTarde.add(this.marcadoModel.get(i));
							}
							break;
						
						case("Salida M."):
							auxMarcado=new marcadoModel();
							auxMarcado.set_04hora(this.obserModel.get(j).getHora());
							auxMarcado.set_03fecha(this.obserModel.get(j).getFecha());
							auxMarcado.set_12lugar(this.obserModel.get(j).getUidobs());
							auxMarcado.set_08h(this.obserModel.get(j).getH());
							auxMarcado.set_09m(this.obserModel.get(j).getM());
							auxMarcado.setId((long)0);
							salidaManana.add(auxMarcado);
							entrada[2]=this.obserModel.get(j).getH();
							if(h < entrada[0]+100) {
								entradaManana.add(this.marcadoModel.get(i));
							}
							if(entrada[2]-90<h && h<entrada[2]+100) {
								entradaTarde.add(this.marcadoModel.get(i));
							}
							if(h>entrada[3]-100) {
								salidaTarde.add(this.marcadoModel.get(i));
							}
							break;
						
						case("Entrada T."):
							auxMarcado=new marcadoModel();
							auxMarcado.set_04hora(this.obserModel.get(j).getHora());
							auxMarcado.set_03fecha(this.obserModel.get(j).getFecha());
							auxMarcado.set_12lugar(this.obserModel.get(j).getUidobs());
							auxMarcado.set_08h(this.obserModel.get(j).getH());
							auxMarcado.set_09m(this.obserModel.get(j).getM());
							auxMarcado.setId((long)0);
							entradaTarde.add(auxMarcado);
							if(h < entrada[0]+100) {
								entradaManana.add(this.marcadoModel.get(i));
							}
							if(entrada[1]-100<h && h<=entrada[1]+100) {
								salidaManana.add(this.marcadoModel.get(i));
							}
							if(h>entrada[3]-100) {
								salidaTarde.add(this.marcadoModel.get(i));
							}
							
							break;
						
						case("Salida T."):
							auxMarcado=new marcadoModel();
							auxMarcado.set_04hora(this.obserModel.get(j).getHora());
							auxMarcado.set_03fecha(this.obserModel.get(j).getFecha());
							auxMarcado.set_12lugar("UID");
							auxMarcado.set_08h(this.obserModel.get(j).getH());
							auxMarcado.set_09m(this.obserModel.get(j).getM());
							auxMarcado.setId((long)0);
							salidaTarde.add(auxMarcado);
							entrada[3]=this.obserModel.get(j).getH();
							if(h < entrada[0]+100) {
								entradaManana.add(this.marcadoModel.get(i));
							}
							if(entrada[1]-100<h && h<=entrada[1]+100) {
								salidaManana.add(this.marcadoModel.get(i));
							}
							if(entrada[2]-90<h && h<entrada[2]+100) {
								entradaTarde.add(this.marcadoModel.get(i));
							}
							break;
						
						case("tolerancia"):
							if(h < entrada[0]+100) {
								entradaManana.add(this.marcadoModel.get(i));
								entrada[0]=(this.obserModel.get(i).getH()*100)+this.obserModel.get(i).getM();
							}
							if(entrada[1]-100<h && h<=entrada[1]+100) {
								salidaManana.add(this.marcadoModel.get(i));
							}
							if(entrada[2]-90<h && h<entrada[2]+100) {
								entradaTarde.add(this.marcadoModel.get(i));
							}
							if(h>entrada[3]-100) {
								salidaTarde.add(this.marcadoModel.get(i));
							}
							break;
						
						case("extraordinario"):
							entrada[0]=Integer.parseInt(this.obserModel.get(j).getHora().replace(":", ""));
							if(h < entrada[0]+100) {
								entradaManana.add(this.marcadoModel.get(i));
							}
							if(h>entrada[3]-200) {
								salidaTarde.add(this.marcadoModel.get(i));
							}
							this.turnoB[1]="continuo";
							this.turnoB[2]="continuo";
							continuo=true;
							break;
						
						case("horas"):
							horas.add(this.marcadoModel.get(i));
							break;
							
						case("comision"):
							break;
						
						case("permiso"):
							break;
					}
				}
			}
		}
		
		//Discriminamos horas
		if(horas.size()>0) {
			int mayor=horas.get(horas.size()-1).get_08h();
			int menor=horas.get(0).get_08h();
			for(int i=0;i<horas.size();i++) {
				if(menor==horas.get(i).get_08h()) {
					entradaManana.add(horas.get(i));
					ingreso[0]=horas.get(i).get_08h();
					entrada[0]=horas.get(i).get_08h()*100+horas.get(i).get_09m(); //Discriminacion Attemdamce 
				}
				if(mayor==horas.get(i).get_08h())
				{
					salidaTarde.add(horas.get(i));
					break;
				}
			}
			this.turnoB[1]="continuo";
			this.turnoB[2]="continuo";
			
			continuo=true;
		}
		
		
		//verificamos las entradas 
		
		// entrada en la mañana 
		if(entradaManana.size()>0) {
			aux=entradaManana.get(entradaManana.size()-1);
			h=(aux.get_08h()*100)+aux.get_09m();
			
			this.hora[0]=aux.get_04hora();
			this.lugar[0]=aux.get_12lugar()+" uid:"+aux.getId();
			
			this.retraso[0]=retraso(h,entrada[0]+5,aux,ingreso[0]);
		}
		else {
			continuo(0,this.turnoB[0]);
		}
		
		//salida en la Mañana
		if(salidaManana.size()>0) {
			aux=salidaManana.get(0);
			h=(aux.get_08h()*100)+aux.get_09m();
			this.hora[1]=aux.get_04hora();
			this.lugar[1]=aux.get_12lugar()+" uid:"+aux.getId();
			this.retraso[1]=anticipado(h,entrada[1],aux,ingreso[1]);
		}
		else {
			continuo(1,this.turnoB[1]);
		}
		
		//entrada en la tarde 
		if(entradaTarde.size()>0) {
			aux=entradaTarde.get(entradaTarde.size()-1);
			h=(aux.get_08h()*100)+aux.get_09m();
			this.hora[2]=aux.get_04hora();
			this.lugar[2]=aux.get_12lugar()+" uid:"+aux.getId();
			this.retraso[2]=retraso(h,entrada[2]+5,aux,ingreso[2]);
		}
		else {
			continuo(2,this.turnoB[2]);
		}
		
		//salida en la tarde 
		if(salidaTarde.size()>0) {
			aux=salidaTarde.get(0);
			h=(aux.get_08h()*100)+aux.get_09m();
			this.hora[3]=aux.get_04hora();
			this.lugar[3]=aux.get_12lugar()+" uid:"+aux.getId();
			if(continuo)
				this.retraso[3]=0;
			else
				this.retraso[3]=anticipado(h,entrada[3],aux,ingreso[3]);
		}
		else {
			continuo(3,this.turnoB[3]);
		}
	}
	
	//Segmento modular de Continuo
	public void continuo(int index,String c) {
		if(c.equals("continuo")) {
			this.hora[index]="continuo";
			this.lugar[index]="Sin Lugar";
		}
		else {
			this.hora[index]="Sin Marcar";
			this.lugar[index]="Sin Lugar";
		}
			
	}
	
	//Segmento modular de Retrasos
	public int retraso(int horaMarcado,int horaIngreso,marcadoModel marcadoModel,int i) {		
		int razon=0;
		int result=0;
		int suma = 0;
		int aux=marcadoModel.get_08h();
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
	public int anticipado(int horaMarcado,int horaSalida,marcadoModel marcadoModel,int i) {
		
		int razon=0;
		int resta=0;
		int result=0;
		int suma = 0;
		int aux=marcadoModel.get_08h();
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

