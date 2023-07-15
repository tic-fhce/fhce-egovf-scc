package com.fhce.control.model;

import java.util.List;

public class marcadoTotalModel {
	
	private Long cif;
	private List<formatoReporteModel> reporteFinal;
	int retraso;
	int antisipado;
	int pena;
	public marcadoTotalModel(Long cif, List<formatoReporteModel> reporteFinal, int retraso, int antisipado, int pena) {
	
		this.cif = cif;
		this.reporteFinal = reporteFinal;
		this.retraso = retraso;
		this.antisipado = antisipado;
		this.pena = pena;
	}
	public Long getCif() {
		return cif;
	}
	public void setCif(Long cif) {
		this.cif = cif;
	}
	public List<formatoReporteModel> getReporteFinal() {
		return reporteFinal;
	}
	public void setReporteFinal(List<formatoReporteModel> reporteFinal) {
		this.reporteFinal = reporteFinal;
	}
	public int getRetraso() {
		return retraso;
	}
	public void setRetraso(int retraso) {
		this.retraso = retraso;
	}
	public int getAntisipado() {
		return antisipado;
	}
	public void setAntisipado(int antisipado) {
		this.antisipado = antisipado;
	}
	public int getPena() {
		return pena;
	}
	public void setPena(int pena) {
		this.pena = pena;
	}
	
}