package com.fhce.control.obj;

import java.util.List;

import com.fhce.control.model.formatoReporteModel;

public class marcadoBrutoObj {
	private Long cif;
	private Long userId;
	private int horarioId;
	private String lugar;
	
	public marcadoBrutoObj(Long cif,Long userId,int horarioId,String lugar) {
		this.cif = cif;
		this.userId = userId;
		this.horarioId = horarioId;
		this.lugar = lugar;
	}

	public Long getCif() {
		return cif;
	}

	public void setCif(Long cif) {
		this.cif = cif;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getHorarioId() {
		return horarioId;
	}

	public void setHorarioId(int horarioId) {
		this.horarioId = horarioId;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	

}
