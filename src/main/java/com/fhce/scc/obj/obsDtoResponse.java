package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class obsDtoResponse {
	
	private Long id;
	private String uidobs;
	private String fechainicio;
	private String fechafin;
	private int gestion;
	private int mes;
	private int di;
	private int df;
	private String detalle;
	private String imagen;
	private String tipo;
	private String url;
	private int tipoId;
}
