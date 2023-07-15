package com.fhce.control.model;

import javax.persistence.Column;

public class obserModel {
	private Long id;
	private String uidobs;
	private String fecha;
	private String detalle;
	private String tipo;
	private String hora;
	private int h;
	private int m;
	
	public obserModel(String tipo) {
		this.tipo=tipo;
		this.uidobs="";
	}
	
	public obserModel(Long id, String uidobs, String fecha, String detalle, String tipo,
			String hora, int h, int m) {
	
		this.id = id;
		this.uidobs = uidobs;
		this.fecha = fecha;
		this.detalle = detalle;
		this.tipo = tipo;
		this.hora = hora;
		this.h = h;
		this.m = m;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUidobs() {
		return uidobs;
	}
	public void setUidobs(String uidobs) {
		this.uidobs = uidobs;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
