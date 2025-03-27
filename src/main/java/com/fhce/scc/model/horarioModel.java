package com.fhce.scc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="horario")
public class horarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name = "_01cif")
	private Long cif;
	
	@Column (name = "_02lem")
	private String lem;
	
	@Column (name = "_03lsm")
	private String lsm;
	
	@Column (name = "_04let")
	private String let;
	
	@Column (name = "_05lst")
	private String lst;
	
	@Column (name = "_06mem")
	private String mem;
	
	@Column (name = "_07msm")
	private String msm;
	
	@Column (name = "_08met")
	private String met;
	
	@Column (name = "_09mst")
	private String mst;
	
	@Column (name = "_10miem")
	private String miem;
	
	@Column (name = "_11mism")
	private String mism;
	
	@Column (name = "_12miet")
	private String miet;
	
	@Column (name = "_13mist")
	private String mist;
	
	@Column (name = "_14jem")
	private String jem;
	
	@Column (name = "_15jsm")
	private String jsm;
	
	@Column (name = "_16jet")
	private String jet;
	
	@Column (name = "_17jst")
	private String jst;
	
	@Column (name = "_18vem")
	private String vem;
	
	@Column (name = "_19vsm")
	private String vsm;
	
	@Column (name = "_20vet")
	private String vet;
	
	@Column (name = "_21vst")
	private String vst;
	
	@Column (name = "_22sem")
	private String sem;
	
	@Column (name = "_23ssm")
	private String ssm;
	
	@Column (name = "_24set")
	private String set;
	
	@Column (name = "_25sst")
	private String sst;
	
	@Column (name = "_26dem")
	private String dem;
	
	@Column (name = "_27dsm")
	private String dsm;
	
	@Column (name = "_28det")
	private String det;
	
	@Column (name = "_29dst")
	private String dst;

}
