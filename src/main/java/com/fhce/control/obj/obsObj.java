package com.fhce.control.obj;

public class obsObj {
	private Long id;
	private Long cif;
	private int sexo;
	private String uidobs;
	private String fechainicio;
	private String fechafin;
	private int gestion;
	private int mes;
	private int di;
	private int df;
	private String detalle;
	private String imagen;
	private String tipo;
	private String hora;
	private int h;
	private int m;
	private String url;
	private int estado;
	public obsObj(Long id, Long cif,int sexo, String uidobs, String fechainicio, String fechafin, int gestion, int mes, int di,
			int df, String detalle, String imagen, String tipo, String hora, int h, int m, String url, int estado) {
		this.id = id;
		this.cif = cif;
		this.sexo = sexo;
		this.uidobs = uidobs;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.gestion = gestion;
		this.mes = mes;
		this.di = di;
		this.df = df;
		this.detalle = detalle;
		this.imagen = imagen;
		this.tipo = tipo;
		this.hora = hora;
		this.h = h;
		this.m = m;
		this.url = url;
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCif() {
		return cif;
	}
	public void setCif(Long cif) {
		this.cif = cif;
	}
	public String getUidobs() {
		return uidobs;
	}
	public void setUidobs(String uidobs) {
		this.uidobs = uidobs;
	}
	public String getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}
	public String getFechafin() {
		return fechafin;
	}
	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}
	public int getGestion() {
		return gestion;
	}
	public void setGestion(int gestion) {
		this.gestion = gestion;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getDi() {
		return di;
	}
	public void setDi(int di) {
		this.di = di;
	}
	public int getDf() {
		return df;
	}
	public void setDf(int df) {
		this.df = df;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	
	
}
