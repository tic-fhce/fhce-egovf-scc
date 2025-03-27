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
@Table(name="obs")
public class obsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name = "_01uidobs")
	private String uidObs;
	
	@Column (name = "_02fechainicio")
	private String fechaInicio;
	
	@Column (name = "_03fechafin")
	private String fechaFin;
	
	@Column (name = "_04gestion")
	private int gestion;
	
	@Column (name = "_05mes")
	private int mes;
	
	@Column (name = "_06di")
	private int di;
	
	@Column (name = "_07df")
	private int df;
	
	@Column (name = "_08detalle")
	private String detalle;
	
	@Column (name = "_09imagen")
	private String imagen;
	
	@Column (name = "_10tipo")
	private String tipo;
	
	@Column (name = "_11url")
	private String url;
	
	@Column (name = "_12tipoid")
	private int tipoId;
}
