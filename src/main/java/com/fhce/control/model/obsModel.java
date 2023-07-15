package com.fhce.control.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="obs")
public class obsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01cif;
	
	@Column
	private String _02uidobs;
	
	@Column
	private String _03fechainicio;
	
	@Column
	private String _04fechafin;
	
	@Column
	private int _05gestion;
	
	@Column
	private int _06mes;
	
	@Column
	private int _07di;
	
	@Column
	private int _08df;
	
	@Column
	private String _09detalle;
	
	@Column
	private String _10imagen;
	
	@Column
	private String _11tipo;
	
	@Column
	private String _12hora;
	
	@Column
	private int _13h;
	
	@Column
	private int _14m;

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

	public String get_03fechainicio() {
		return _03fechainicio;
	}

	public void set_03fechainicio(String _03fechainicio) {
		this._03fechainicio = _03fechainicio;
	}

	public String get_04fechafin() {
		return _04fechafin;
	}

	public void set_04fechafin(String _04fechafin) {
		this._04fechafin = _04fechafin;
	}

	public int get_05gestion() {
		return _05gestion;
	}

	public void set_05gestion(int _05gestion) {
		this._05gestion = _05gestion;
	}

	public int get_06mes() {
		return _06mes;
	}

	public void set_06mes(int _06mes) {
		this._06mes = _06mes;
	}

	public int get_07di() {
		return _07di;
	}

	public void set_07di(int _07di) {
		this._07di = _07di;
	}

	public int get_08df() {
		return _08df;
	}

	public void set_08df(int _08df) {
		this._08df = _08df;
	}

	public String get_09detalle() {
		return _09detalle;
	}

	public void set_09detalle(String _09detalle) {
		this._09detalle = _09detalle;
	}

	public String get_10imagen() {
		return _10imagen;
	}

	public void set_10imagen(String _10imagen) {
		this._10imagen = _10imagen;
	}

	public String get_02uidobs() {
		return _02uidobs;
	}

	public void set_02uidobs(String _02uidobs) {
		this._02uidobs = _02uidobs;
	}

	public String get_11tipo() {
		return _11tipo;
	}

	public void set_11tipo(String _11tipo) {
		this._11tipo = _11tipo;
	}

	public String get_12hora() {
		return _12hora;
	}

	public void set_12hora(String _12hora) {
		this._12hora = _12hora;
	}

	public int get_13h() {
		return _13h;
	}

	public void set_13h(int _13h) {
		this._13h = _13h;
	}

	public int get_14m() {
		return _14m;
	}

	public void set_14m(int _14m) {
		this._14m = _14m;
	}
	
}
