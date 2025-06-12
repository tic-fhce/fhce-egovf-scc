package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class avisoDtoRequest {
	private String titulo;
	private String detalle;
	private String icon;
	private int estado;

}
