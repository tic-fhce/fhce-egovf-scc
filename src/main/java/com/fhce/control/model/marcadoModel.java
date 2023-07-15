package com.fhce.control.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marcado")
public class marcadoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01uid;
	
	@Column
	private Long _02user_id;
	
	@Column
	private String _03fecha;
	
	@Column
	private String _04hora;
	
	@Column
	private int _05gestion;
	
	@Column
	private int _06mes;
	
	@Column
	private int _07dia;
	
	@Column
	private int _08h;
	
	@Column
	private int _09m;
	
	@Column
	private int _10punch;
	
	@Column
	private int _11rstatus;
	
	@Column
	private String _12lugar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long get_01uid() {
		return _01uid;
	}

	public void set_01uid(Long _01uid) {
		this._01uid = _01uid;
	}

	public Long get_02user_id() {
		return _02user_id;
	}

	public void set_02user_id(Long _02user_id) {
		this._02user_id = _02user_id;
	}

	public String get_03fecha() {
		return _03fecha;
	}

	public void set_03fecha(String _03fecha) {
		this._03fecha = _03fecha;
	}

	public String get_04hora() {
		return _04hora;
	}

	public void set_04hora(String _04hora) {
		this._04hora = _04hora;
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

	public int get_07dia() {
		return _07dia;
	}

	public void set_07dia(int _07dia) {
		this._07dia = _07dia;
	}

	public int get_08h() {
		return _08h;
	}

	public void set_08h(int _08h) {
		this._08h = _08h;
	}

	public int get_09m() {
		return _09m;
	}

	public void set_09m(int _09m) {
		this._09m = _09m;
	}

	public int get_10punch() {
		return _10punch;
	}

	public void set_10punch(int _10punch) {
		this._10punch = _10punch;
	}

	public int get_11rstatus() {
		return _11rstatus;
	}

	public void set_11rstatus(int _11rstatus) {
		this._11rstatus = _11rstatus;
	}

	public String get_12lugar() {
		return _12lugar;
	}

	public void set_12lugar(String _12lugar) {
		this._12lugar = _12lugar;
	}

}
