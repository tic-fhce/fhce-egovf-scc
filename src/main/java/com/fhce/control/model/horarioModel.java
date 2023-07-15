package com.fhce.control.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario")
public class horarioModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01cif;
	
	@Column
	private String _02lem;
	
	@Column
	private String _03lsm;
	
	@Column
	private String _04let;
	
	@Column
	private String _05lst;
	
	@Column
	private String _06mem;
	
	@Column
	private String _07msm;
	
	@Column
	private String _08met;
	
	@Column
	private String _09mst;
	
	@Column
	private String _10miem;
	
	@Column
	private String _11mism;
	
	@Column
	private String _12miet;
	
	@Column
	private String _13mist;
	
	@Column
	private String _14jem;
	
	@Column
	private String _15jsm;
	
	@Column
	private String _16jet;
	
	@Column
	private String _17jst;
	
	@Column
	private String _18vem;
	
	@Column
	private String _19vsm;
	
	@Column
	private String _20vet;
	
	@Column
	private String _21vst;
	
	@Column
	private String _22sem;
	
	@Column
	private String _23ssm;
	
	@Column
	private String _24set;
	
	@Column
	private String _25sst;
	
	@Column
	private String _26dem;
	
	@Column
	private String _27dsm;
	
	@Column
	private String _28det;
	
	@Column
	private String _29dst;

	public Long getId() {
		return id;
	}

	public Long get_01cif() {
		return _01cif;
	}

	public void set_01cif(Long _01cif) {
		this._01cif = _01cif;
	}

	public String get_02lem() {
		return _02lem;
	}

	public void set_02lem(String _02lem) {
		this._02lem = _02lem;
	}

	public String get_03lsm() {
		return _03lsm;
	}

	public void set_03lsm(String _03lsm) {
		this._03lsm = _03lsm;
	}

	public String get_04let() {
		return _04let;
	}

	public void set_04let(String _04let) {
		this._04let = _04let;
	}

	public String get_05lst() {
		return _05lst;
	}

	public void set_05lst(String _05lst) {
		this._05lst = _05lst;
	}

	public String get_06mem() {
		return _06mem;
	}

	public void set_06mem(String _06mem) {
		this._06mem = _06mem;
	}

	public String get_07msm() {
		return _07msm;
	}

	public void set_07msm(String _07msm) {
		this._07msm = _07msm;
	}

	public String get_08met() {
		return _08met;
	}

	public void set_08met(String _08met) {
		this._08met = _08met;
	}

	public String get_09mst() {
		return _09mst;
	}

	public void set_09mst(String _09mst) {
		this._09mst = _09mst;
	}

	public String get_10miem() {
		return _10miem;
	}

	public void set_10miem(String _10miem) {
		this._10miem = _10miem;
	}

	public String get_11mism() {
		return _11mism;
	}

	public void set_11mism(String _11mism) {
		this._11mism = _11mism;
	}

	public String get_12miet() {
		return _12miet;
	}

	public void set_12miet(String _12miet) {
		this._12miet = _12miet;
	}

	public String get_13mist() {
		return _13mist;
	}

	public void set_13mist(String _13mist) {
		this._13mist = _13mist;
	}

	public String get_14jem() {
		return _14jem;
	}

	public void set_14jem(String _14jem) {
		this._14jem = _14jem;
	}

	public String get_15jsm() {
		return _15jsm;
	}

	public void set_15jsm(String _15jsm) {
		this._15jsm = _15jsm;
	}

	public String get_16jet() {
		return _16jet;
	}

	public void set_16jet(String _16jet) {
		this._16jet = _16jet;
	}

	public String get_17jst() {
		return _17jst;
	}

	public void set_17jst(String _17jst) {
		this._17jst = _17jst;
	}

	public String get_18vem() {
		return _18vem;
	}

	public void set_18vem(String _18vem) {
		this._18vem = _18vem;
	}

	public String get_19vsm() {
		return _19vsm;
	}

	public void set_19vsm(String _19vsm) {
		this._19vsm = _19vsm;
	}

	public String get_20vet() {
		return _20vet;
	}

	public void set_20vet(String _20vet) {
		this._20vet = _20vet;
	}

	public String get_21vst() {
		return _21vst;
	}

	public void set_21vst(String _21vst) {
		this._21vst = _21vst;
	}

	public String get_22sem() {
		return _22sem;
	}

	public void set_22sem(String _22sem) {
		this._22sem = _22sem;
	}

	public String get_23ssm() {
		return _23ssm;
	}

	public void set_23ssm(String _23ssm) {
		this._23ssm = _23ssm;
	}

	public String get_24set() {
		return _24set;
	}

	public void set_24set(String _24set) {
		this._24set = _24set;
	}

	public String get_25sst() {
		return _25sst;
	}

	public void set_25sst(String _25sst) {
		this._25sst = _25sst;
	}

	public String get_26dem() {
		return _26dem;
	}

	public void set_26dem(String _26dem) {
		this._26dem = _26dem;
	}

	public String get_27dsm() {
		return _27dsm;
	}

	public void set_27dsm(String _27dsm) {
		this._27dsm = _27dsm;
	}

	public String get_28det() {
		return _28det;
	}

	public void set_28det(String _28det) {
		this._28det = _28det;
	}

	public String get_29dst() {
		return _29dst;
	}

	public void set_29dst(String _29dst) {
		this._29dst = _29dst;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
