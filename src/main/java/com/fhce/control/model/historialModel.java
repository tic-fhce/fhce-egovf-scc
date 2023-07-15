package com.fhce.control.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="historial")
public class historialModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01cif;
	
	@Column
	private int _02horario;
	
	@Column
	private int _03gestion;
	
	@Column
	private int _04mes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long get_01cif() {
		return _01cif;
	}

	public void set_01cif(Long _01cif) {
		this._01cif = _01cif;
	}

	public int get_02horario() {
		return _02horario;
	}

	public void set_02horario(int _02horario) {
		this._02horario = _02horario;
	}

	public int get_03gestion() {
		return _03gestion;
	}

	public void set_03gestion(int _03gestion) {
		this._03gestion = _03gestion;
	}

	public int get_04mes() {
		return _04mes;
	}

	public void set_04mes(int _04mes) {
		this._04mes = _04mes;
	}

}
