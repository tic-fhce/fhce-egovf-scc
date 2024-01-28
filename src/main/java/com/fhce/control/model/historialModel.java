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
	private Long _02horario_id;
	
	@Column
	private int _03gestion;
	
	@Column
	private int _04mes;
	
	@Column
	private int _05dia;

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

	public Long get_02horario_id() {
		return _02horario_id;
	}

	public void set_02horario_id(Long _02horario_id) {
		this._02horario_id = _02horario_id;
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

	public int get_05dia() {
		return _05dia;
	}

	public void set_05dia(int _05dia) {
		this._05dia = _05dia;
	}

}
