package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class obsDtoRequest {
	private Long cif;
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
	private String hora;
	private int h;
	private int m;
	private String horaSalida;
	private int hSalida;
	private int mSalida;
	private String url;
	private int estado;
	private int tipoId;

}
