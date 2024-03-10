package com.fhce.control.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biometrico")
public class biometricoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01user_id;
	
	@Column
	private String _02nombre;
	
	@Column
	private Long _03cif;
	
	@Column
	private int _04estado;
	
	@Column
	private int _05horario_id;
	
	@Column
	private String _06lugar;
	
	@Column
	private Long _07id_tipo;
	
	@Column
	private String _08detalle;
	
	@Column
	private int _09sexo;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long get_01user_id() {
		return _01user_id;
	}

	public void set_01user_id(Long _01user_id) {
		this._01user_id = _01user_id;
	}

	public String get_02nombre() {
		return _02nombre;
	}

	public void set_02nombre(String _02nombre) {
		this._02nombre = _02nombre;
	}

	public Long get_03cif() {
		return _03cif;
	}

	public void set_03cif(Long _03cif) {
		this._03cif = _03cif;
	}

	public int get_04estado() {
		return _04estado;
	}

	public void set_04estado(int _04estado) {
		this._04estado = _04estado;
	}

	public int get_05horario_id() {
		return _05horario_id;
	}

	public void set_05horario_id(int _05horario_id) {
		this._05horario_id = _05horario_id;
	}

	public String get_06lugar() {
		return _06lugar;
	}

	public void set_06lugar(String _06lugar) {
		this._06lugar = _06lugar;
	}

	public Long get_07id_tipo() {
		return _07id_tipo;
	}

	public void set_07id_tipo(Long _07id_tipo) {
		this._07id_tipo = _07id_tipo;
	}

	public String get_08detalle() {
		return _08detalle;
	}

	public void set_08detalle(String _08detalle) {
		this._08detalle = _08detalle;
	}

	public int get_09sexo() {
		return _09sexo;
	}

	public void set_09sexo(int _09sexo) {
		this._09sexo = _09sexo;
	}
	
}
