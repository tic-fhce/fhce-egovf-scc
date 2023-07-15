package com.fhce.control.model;

public class ultimoModel {
	private Long cif;
	private String fecha;
	private String hora;
	public ultimoModel(Long cif, String fecha, String hora) {
		this.cif = cif;
		this.fecha = fecha;
		this.hora = hora;
	}
	public Long getCif() {
		return cif;
	}
	public void setCif(Long cif) {
		this.cif = cif;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}

