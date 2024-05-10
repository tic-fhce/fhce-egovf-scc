package com.fhce.control.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aviso")
public class avisoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private String _01titulo;
	
	@Column
	private String _02detalle;
	
	@Column
	private String _03icon;
	
	@Column
	private int _04estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String get_01titulo() {
		return _01titulo;
	}

	public void set_01titulo(String _01titulo) {
		this._01titulo = _01titulo;
	}

	public String get_02detalle() {
		return _02detalle;
	}

	public void set_02detalle(String _02detalle) {
		this._02detalle = _02detalle;
	}

	public String get_03icon() {
		return _03icon;
	}

	public void set_03icon(String _03icon) {
		this._03icon = _03icon;
	}

	public int get_04estado() {
		return _04estado;
	}

	public void set_04estado(int _04estado) {
		this._04estado = _04estado;
	}

}
