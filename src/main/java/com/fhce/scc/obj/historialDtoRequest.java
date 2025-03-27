package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class historialDtoRequest {
	private Long cif;
	private Long horario_id;
	private int gestion;
	private int mes;
	private int dia;
	private int gestionsalida;
	private int messalida;
	private int diasalida;

}
