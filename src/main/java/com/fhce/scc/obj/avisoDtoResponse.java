package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class avisoDtoResponse {
	
	private Long id;
	private String titulo;
	private String detalle;
	private String icon;
	private int estado;

}
