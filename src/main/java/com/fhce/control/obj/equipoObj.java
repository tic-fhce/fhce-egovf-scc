package com.fhce.control.obj;

public class equipoObj {
	private Long id;
    private String detalle;
    private String lugar;
    private String ip;
    private String puerto;
    private String mac;
    private String codigo;
    private int estado;
    
	public equipoObj(Long id, String detalle, String lugar, String ip, String puerto, String mac, String codigo,
			int estado) {
		this.id = id;
		this.detalle = detalle;
		this.lugar = lugar;
		this.ip = ip;
		this.puerto = puerto;
		this.mac = mac;
		this.codigo = codigo;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
