package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class biometricoDtoResponse {
	
	private Long id;
	private Long user_id;
	private String nombre;
	private Long cif;
	private int estado;
	private int horario_id;
	private String lugar;
	private Long id_tipo;
	private String detalle;
	private int sexo;

}
