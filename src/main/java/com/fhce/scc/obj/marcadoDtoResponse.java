package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class marcadoDtoResponse {
	private Long id;
	private Long uid;
	private Long user_id;
	private String fecha;
	private String hora;
	private int gestion;
	private int mes;
	private int dia;
	private int h;
	private int m;
	private int punch;
	private int rstatus;
	private String lugar;

}
