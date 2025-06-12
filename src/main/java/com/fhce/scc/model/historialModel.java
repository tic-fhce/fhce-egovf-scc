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
@Table(name="historial")
public class historialModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name = "_01cif")
	private Long cif;
	
	@Column (name = "_02horario_id")
	private Long horario_id;
	
	@Column (name = "_03gestion")
	private int gestion;
	
	@Column (name = "_04mes")
	private int mes;
	
	@Column (name = "_05dia")
	private int dia;
	
	@Column (name = "_06gestionsalida")
	private int gestionsalida;
	
	@Column (name = "_07messalida")
	private int messalida;
	
	@Column (name = "_08diasalida")
	private int diasalida;
	
}
