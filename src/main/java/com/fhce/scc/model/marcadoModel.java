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
@Table(name="marcado")
public class marcadoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name = "_01uid")
	private Long uid;
	
	@Column (name = "_02user_id")
	private Long user_id;
	
	@Column (name = "_03fecha")
	private String fecha;
	
	@Column (name = "_04hora")
	private String hora;
	
	@Column (name = "_05gestion")
	private int gestion;
	
	@Column (name = "_06mes")
	private int mes;
	
	@Column (name = "_07dia")
	private int dia;
	
	@Column (name = "_08h")
	private int h;
	
	@Column (name = "_09m")
	private int m;
	
	@Column (name = "_10punch")
	private int punch;
	
	@Column (name = "_11rstatus")
	private int rstatus;
	
	@Column (name = "_12lugar")
	private String lugar;
	

}
