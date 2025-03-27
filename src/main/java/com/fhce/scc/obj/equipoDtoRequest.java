package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipoDtoRequest {
	private String detalle;
	private String lugar;
	private String ip;
	private String puerto;
	private String mac;
	private String codigo;
	private int estado;

}
