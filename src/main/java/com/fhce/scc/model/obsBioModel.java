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
@Table(name="obsbio")
public class obsBioModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name = "_01cif")
	private Long cif;
	
	@Column (name = "_02idobs")
	private Long idObs;
	
	@Column (name = "_03horaentrada")
	private String horaEntrada;
	
	@Column (name = "_04hentrada")
	private int hEntrada;
	
	@Column (name = "_05mentrada")
	private int mEntrada;
	
	@Column (name = "_06horasalida")
	private String horaSalida;
	
	@Column (name = "_07hsalida")
	private int hSalida;
	
	@Column (name = "_08msalida")
	private int mSalida;
	
	@Column (name = "_09estado")
	private int estado;

}
