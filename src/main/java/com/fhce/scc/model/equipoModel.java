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
@Table(name="equipo")
public class equipoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name =  "_01detalle")
	private String detalle;
	
	@Column (name =  "_02lugar")
	private String lugar;
	
	@Column (name =  "_03ip")
	private String ip;
	
	@Column (name =  "_04puerto")
	private String puerto;
	
	@Column (name =  "_05mac")
	private String mac;
	
	@Column (name =  "_06codigo")
	private String codigo;
	
	@Column (name =  "_07estado")
	private int estado;

}
