package com.fhce.control.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="equipo")
public class equipoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private String _01detalle;
	
	@Column
	private String _02lugar;
	
	@Column
	private String _03ip;
	
	@Column
	private String _04puerto;
	
	@Column
	private String _05mac;
	
	@Column
	private String _06codigo;
	
	@Column
	private int _07estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String get_01detalle() {
		return _01detalle;
	}

	public void set_01detalle(String _01detalle) {
		this._01detalle = _01detalle;
	}

	public String get_02lugar() {
		return _02lugar;
	}

	public void set_02lugar(String _02lugar) {
		this._02lugar = _02lugar;
	}

	public String get_03ip() {
		return _03ip;
	}

	public void set_03ip(String _03ip) {
		this._03ip = _03ip;
	}

	public String get_04puerto() {
		return _04puerto;
	}

	public void set_04puerto(String _04puerto) {
		this._04puerto = _04puerto;
	}

	public String get_05mac() {
		return _05mac;
	}

	public void set_05mac(String _05mac) {
		this._05mac = _05mac;
	}

	public String get_06codigo() {
		return _06codigo;
	}

	public void set_06codigo(String _06codigo) {
		this._06codigo = _06codigo;
	}

	public int get_07estado() {
		return _07estado;
	}

	public void set_07estado(int _07estado) {
		this._07estado = _07estado;
	}
	
	
}
