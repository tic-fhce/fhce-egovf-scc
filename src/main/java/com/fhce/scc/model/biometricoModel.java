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
@Table(name="biometrico")
public class biometricoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name = "_01user_id")
	private Long user_id;
	
	@Column (name = "_02nombre")
	private String nombre;
	
	@Column (name = "_03cif")
	private Long cif;
	
	@Column (name = "_04estado")
	private int estado;
	
	@Column (name = "_05horario_id")
	private int horario_id;
	
	@Column (name = "_06lugar")
	private String lugar;
	
	@Column (name = "_07id_tipo")
	private Long id_tipo;
	
	@Column (name = "_08detalle")
	private String detalle;
	
	@Column (name = "_09sexo")
	private int sexo;

}
